package controller;

import model.entity.Ksiazka;
import model.entity.Lek;
import model.entity.Rola;
import model.entity.Uzytkownik;
import model.security.CustomPermissionEvaluator;
import model.security.CustomUserDetails;
import model.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateu on 22.03.2017.
 */
@Controller
public class SecurityController {

    private final static int LICZBA_TABLIC = 6;
    private final static int LICZBA_ROL = 9;
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

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = {"/index", "/"})
    public String index(ModelMap model, Authentication authentication) {
        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();

        List leki = null;
        List role = null;
        List ksiazki = null;
        List uprawnienia = null;
        List mojeOceny = null;
        List wystawioneOceny= null;
        List uczniowie=null;


        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_LEKI"))
            leki = lekService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_ROLE"))
            role = rolaService.displayWithUserName();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_KSIAZKI"))
            ksiazki = ksiazkaService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_UPRAWNIENIA"))
            uprawnienia = tworzenieTablicyPozwolen();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_WYSTAWIONEOCENY"))
            wystawioneOceny=ocenaService.displayAllByIdNauczyciela(cs.getId());
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_MOJEOCENY"))
            mojeOceny=ocenaService.displayAllByIdUcznia(cs.getId());
        if (customPermissionEvaluator.hasPermission(authentication, null, "ADD_WYSTAWIONEOCENY"))
            uczniowie=uzytkownikService.displayAllNamesAndIdByRole("UCZEN");

        //dodawanie atrybutu do modelu.
        //Model jest przekazywany do index jsp samoczynnie w returnie

        model.addAttribute("rola", cs.getWybranaRola());
        model.addAttribute("imieINazwisko", cs.getName());
        model.addAttribute("listaLekow", leki);
        model.addAttribute("listaRol", role);
        model.addAttribute("listaKsiazek", ksiazki);
        model.addAttribute("listaUprawnien", uprawnienia);
        model.addAttribute("listaWystawionychOcen", wystawioneOceny);
        model.addAttribute("listaMoichOcen", mojeOceny);
        model.addAttribute("uczniowie", uczniowie);
        return "index";
    }




    private List tworzenieTablicyPozwolen() {
        ArrayList<String[]> tablicaPozwolen = new ArrayList<String[]>();
        List role = rolaService.displayAll();
        List uprawnienia;
        String[] nazwyTablic = new String[]{"Administrator", "Dyrektor", "Higienistka", "Nauczyciel", "Uczeń", "Bibliotekarz"};
        String nazwaUprawnienia;
        int iterator = 0;

        //indeksy w tablicyPozwolen odpowiadają różnym tablicom
        //indeksy w tablicach odpowiadają różnym rolom
        for (int i = 0; i < LICZBA_TABLIC; i++) {
            String tablica[] = new String[LICZBA_ROL];
            for (int j = 0; j < LICZBA_ROL; j++)
                tablica[j] = "----";
            tablicaPozwolen.add(tablica);
        }
        for (Object r : role) {
            uprawnienia = uprawnienieService.displayAllByRoleName(r.toString());
            tablicaPozwolen.get(iterator)[0] = nazwyTablic[iterator];
            for (Object o : uprawnienia) {
                nazwaUprawnienia = o.toString();
                if (nazwaUprawnienia.contains("READ"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'R', READ);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'R', READ);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'R', READ);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'R', READ);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'R', READ);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'R', READ);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'R', READ);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'R', READ);



                else if (nazwaUprawnienia.contains("ADD"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'A', WRITE);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'A', WRITE);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'A', WRITE);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'A', WRITE);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'A', WRITE);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'A', WRITE);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'A', WRITE);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'A', WRITE);




                else if (nazwaUprawnienia.contains("EDIT"))
                    if (nazwaUprawnienia.contains("LEKI"))
                        tablicaPozwolen.get(iterator)[LEKI] = zmienNapis(tablicaPozwolen.get(iterator)[LEKI], 'E', EDIT);
                    else if (nazwaUprawnienia.contains("KSIAZKI"))
                        tablicaPozwolen.get(iterator)[KSIAZKI] = zmienNapis(tablicaPozwolen.get(iterator)[KSIAZKI], 'E', EDIT);
                    else if (nazwaUprawnienia.contains("ROLE"))
                        tablicaPozwolen.get(iterator)[ROLE] = zmienNapis(tablicaPozwolen.get(iterator)[ROLE], 'E', EDIT);
                    else if (nazwaUprawnienia.contains("UPRAWNIENIA"))
                        tablicaPozwolen.get(iterator)[UPRAWNIENIA] = zmienNapis(tablicaPozwolen.get(iterator)[UPRAWNIENIA], 'E', EDIT);
                    else if (nazwaUprawnienia.contains("DNIPRACY"))
                        tablicaPozwolen.get(iterator)[DNIPRACY] = zmienNapis(tablicaPozwolen.get(iterator)[DNIPRACY], 'E', EDIT);
                    else if (nazwaUprawnienia.contains("MOJEOCENY"))
                        tablicaPozwolen.get(iterator)[MOJEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[MOJEOCENY], 'E', EDIT);
                    else if (nazwaUprawnienia.contains("WYSTAWIONEOCENY"))
                        tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY] = zmienNapis(tablicaPozwolen.get(iterator)[WYSTAWIONEOCENY], 'E', EDIT);
                    else
                        tablicaPozwolen.get(iterator)[UZYTKOWNICY] = zmienNapis(tablicaPozwolen.get(iterator)[UZYTKOWNICY], 'E', EDIT);


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
