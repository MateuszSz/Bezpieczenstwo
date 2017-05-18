package controller;

import model.entity.Uzytkownik;
import model.security.CustomPermissionEvaluator;
import model.security.CustomUserDetails;
import model.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateu on 22.03.2017.
 */
@Controller
public class SecurityController {

    private final static int LICZBA_TABLIC = 9;
    private final static int LEKI = 1;
    private final static int ROLE = 2;
    private final static int UPRAWNIENIA = 3;
    private final static int KSIAZKI = 4;
    private final static int WYSTAWIONEOCENY = 5;
    private final static int MOJEOCENY = 6;
    private final static int DNIPRACY = 7;
    private final static int UZYTKOWNICY = 8;
    private final static int READ = 0;
    private final static int WRITE = 1;
    private final static int EDIT = 2;
    private final static int DELETE = 3;

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private KsiazkaService ksiazkaService;
    @Autowired
    private OcenaService ocenaService;
    @Autowired
    private LekService lekService;
    @Autowired
    private RolaService rolaService;
    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UprawnienieService uprawnienieService;
    @Autowired
    private DzienPracyService dzienPracyService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "login_error", required = false) boolean login_error) {
        Object o = request.getSession().getAttribute("wiadomosc");
        if (o != null)
            modelMap.addAttribute("wiadomosc", o.toString());

        List role = rolaService.displayAllNamesAndId();
        modelMap.addAttribute("listaRol", role);
        return "login";
    }


    @RequestMapping(value = {"/index", "/"})
    public String index(ModelMap model, Authentication authentication, @RequestParam(value = "status", required = false) String status) {
        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();
        List roleDoSprawdzenia = rolaService.displayAll();
        boolean czyRolaJestWBazie = false;
        for (Object o : roleDoSprawdzenia)
            if (o.toString().equals(cs.getWybranaRola()))
                czyRolaJestWBazie = true;

        if (!czyRolaJestWBazie)
            return "redirect:/logout";
        if(cs.getEmail().equals("loggedOut"))
            return "redirect:/logout";
        List uzytkownicy = null;
        List leki = null;
        List roleZUzytkownikami = null;
        List role = null;
        List ksiazki = null;
        List uprawnienia = null;
        List mojeOceny = null;
        List wystawioneOceny = null;
        List uczniowie = null;
        List dniMojejPracy = null;
        List wszystkieDniPracy = null;
        String wiadomosc = "";
        if (status != null) {
            if (status.equals("usuwanie_admina"))
                wiadomosc = "Nie możesz usunąć roli Administratora!";
            else if (status.equals("blad_dodawania_roli"))
                wiadomosc = "Rola już istnieje!";
        }

        if (cs.isRedirected()) {
            wiadomosc = "Nastąpiło przekierowanie logowania na " + cs.getWybranaRola();
            ((CustomUserDetails) authentication.getPrincipal()).setRedirected(false);
        }

        if(customPermissionEvaluator.hasPermission(authentication, null, "READ_UZYTKOWNICY")){
            uzytkownicy = uzytkownikService.displayAllWithoutPassword();
        }

        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_LEKI"))
            leki = lekService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_ROLE") &&
                customPermissionEvaluator.hasPermission(authentication, null, "READ_UZYTKOWNICY") )
            roleZUzytkownikami = rolaService.displayWithUserName();
        if(customPermissionEvaluator.hasPermission(authentication, null, "READ_ROLE"))
            role = rolaService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_KSIAZKI"))
            ksiazki = ksiazkaService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_UPRAWNIENIA"))
            uprawnienia = tworzenieTablicyPozwolen();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_WYSTAWIONEOCENY"))
            wystawioneOceny = ocenaService.displayAllByIdNauczyciela(cs.getId());
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_MOJEOCENY"))
            mojeOceny = ocenaService.displayAllByIdUcznia(cs.getId());
        if (customPermissionEvaluator.hasPermission(authentication, null, "ADD_WYSTAWIONEOCENY"))
            uczniowie = uzytkownikService.displayAllNamesAndIdByRole("UCZEN");
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_DNIPRACY"))
            dniMojejPracy = dzienPracyService.displayAllById(cs.getId());
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_WSZYSTKIEDNIPRACY"))
            wszystkieDniPracy = dzienPracyService.displayAll();
        //dodawanie atrybutu do modelu.
        //Model jest przekazywany do index jsp samoczynnie w returnie
        model.addAttribute("idUzytkownika", cs.getId());
        model.addAttribute("wiadomosc", wiadomosc);
        model.addAttribute("rola", cs.getWybranaRola());
        model.addAttribute("imieINazwisko", cs.getName());
        model.addAttribute("listaLekow", leki);
        model.addAttribute("listaRolZUzytkownikami", roleZUzytkownikami);
        model.addAttribute("listaKsiazek", ksiazki);
        model.addAttribute("listaUprawnien", uprawnienia);
        model.addAttribute("listaWystawionychOcen", wystawioneOceny);
        model.addAttribute("listaMoichOcen", mojeOceny);
        model.addAttribute("listaUczniow", uczniowie);
        model.addAttribute("listaMoichDniPracy", dniMojejPracy);
        model.addAttribute("listaWszystkichDniPracy", wszystkieDniPracy);
        model.addAttribute("listaUzytkownikow", uzytkownicy);
        model.addAttribute("listaRol", role);
        return "index";
    }


    private List tworzenieTablicyPozwolen() {
        ArrayList<String[]> tablicaPozwolen = new ArrayList<String[]>();
        List role = rolaService.displayAll();
        List uprawnienia;
        Object[] nazwyRol = role.toArray();
        String nazwaUprawnienia;
        int iterator = 0;

        //indeksy w tablicyPozwolen odpowiadają różnym tablicom
        //indeksy w tablicach odpowiadają różnym rolom
        for (int i = 0; i < nazwyRol.length; i++) {
            String tablica[] = new String[LICZBA_TABLIC];
            for (int j = 0; j < LICZBA_TABLIC; j++)
                tablica[j] = "----";
            tablicaPozwolen.add(tablica);
        }
        for (Object r : role) {
            uprawnienia = uprawnienieService.displayAllByRoleName(r.toString());
            tablicaPozwolen.get(iterator)[0] = (String) nazwyRol[iterator];
            for (Object o : uprawnienia) {
                nazwaUprawnienia = o.toString();
                if (nazwaUprawnienia.contains("READ"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'S', READ);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'S', READ);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'S', READ);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'S', READ);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'S', READ);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'S', READ);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'S', READ);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'S', READ);


                else if (nazwaUprawnienia.contains("ADD"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'I', WRITE);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'I', WRITE);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'I', WRITE);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'I', WRITE);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'I', WRITE);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'I', WRITE);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'I', WRITE);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'I', WRITE);


                else if (nazwaUprawnienia.contains("EDIT"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'U', EDIT);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'U', EDIT);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'U', EDIT);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'U', EDIT);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'U', EDIT);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'U', EDIT);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'U', EDIT);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'U', EDIT);


                else if (nazwaUprawnienia.contains("DELETE"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'D', DELETE);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'D', DELETE);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'D', DELETE);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'D', DELETE);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'D', DELETE);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'D', DELETE);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'D', DELETE);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'D', DELETE);

            }
            iterator++;
        }
        return tablicaPozwolen;
    }

    private String zmienNapis(String napis, char znak, int miejsce) {
        char[] napisChar = napis.toCharArray();
        napisChar[miejsce] = znak;
        return String.valueOf(napisChar);
    }
}
