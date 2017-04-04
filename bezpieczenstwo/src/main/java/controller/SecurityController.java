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
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_LEKI"))
            leki = lekService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_ROLE"))
            role = rolaService.displayWithUserName();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_KSIAZKI"))
            ksiazki = ksiazkaService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_UPRAWNIENIA"))
            uprawnienia = tworzenieTablicyPozwolen();


        //dodawanie atrybutu do modelu.
        //Model jest przekazywany do index jsp samoczynnie w returnie

        model.addAttribute("rola", cs.getWybranaRola());
        model.addAttribute("imieINazwisko", cs.getName());
        model.addAttribute("listaLekow", leki);
        model.addAttribute("listaRol", role);
        model.addAttribute("listaKsiazek", ksiazki);
        model.addAttribute("listaUprawnien", uprawnienia);

        return "index";
    }

    //Sprawdzenie czy, osoba która chce się dostać do tej metody ma uprawnienia dodawania lekow
    @PreAuthorize("hasPermission(authentication, 'ADD_LEKI')")
    @RequestMapping(value = "/index/dodajLek.htm")
    public String dodajLek() {

        return "dodajLek";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_LEKI')")
    @RequestMapping(value = "/index/dodawanieLeku", method = RequestMethod.POST)
    public String dodaj(@ModelAttribute Lek lek) {
        lekService.insert(lek);
        return "redirect:/index";

    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_LEKI')")
    @RequestMapping(value = "/index/edytujLek.htm")
    public String edytujLek(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("idLeku", id);
        Lek wybrany = lekService.display(id);
        model.addAttribute("idLeku", wybrany.getId());
        model.addAttribute("nazwaLeku", wybrany.getNazwaLeku());
        model.addAttribute("dawkowanieLeku", wybrany.getDawkowanie());
        model.addAttribute("iloscLeku", wybrany.getIlosc());
        return "edytujLek";
    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_LEKI')")
    @RequestMapping(value = "/index/edytowanieLeku", method = RequestMethod.POST)
    public String edytowanieLeku(ModelMap model, @ModelAttribute Lek lek, @RequestParam("id") int id) {
        Object j = model.get("idLeku");
        Lek zmieniony = lekService.display(id); // w przyszlosci bedzie lek.getId();
        zmieniony.setDawkowanie(lek.getDawkowanie());
        zmieniony.setIlosc(lek.getIlosc());
        zmieniony.setNazwaLeku(lek.getNazwaLeku());
        zmieniony.setId(id);
        lekService.insert(zmieniony);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'ADD_KSIAZKI')")
    @RequestMapping(value = "/index/dodajKsiazke.htm")
    public String dodajKsiazke() {
        return "dodajKsiazke";
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_KSIAZKI')")
    @RequestMapping(value = "/index/dodawanieKsiazki", method = RequestMethod.POST)
    public String dodawanieKsiazki(@ModelAttribute Ksiazka ksiazka) {
        ksiazkaService.insert(ksiazka);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_KSIAZKI')")
    @RequestMapping(value = "/index/edytujKsiazki.htm")
    public String edytujKsiazke(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("idKsiazki", id);
        Ksiazka wybrany = ksiazkaService.display(id);
        model.addAttribute("autor", wybrany.getAutor());
        model.addAttribute("tytul", wybrany.getTytul());
        model.addAttribute("ISBN", wybrany.getISBN());
        model.addAttribute("dostepnosc", wybrany.getDostepnosc());
        model.addAttribute("seria", wybrany.getSeria());
        return "edytujKsiazke";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_KSIAZKI')")
    @RequestMapping(value = "/index/edytowanieKsiazki", method = RequestMethod.POST)
    public String edytowanieKsiazki(ModelMap model, @ModelAttribute Ksiazka ksiazka, @RequestParam("id") int id) {

        Ksiazka zmieniony = ksiazkaService.display(id);
        zmieniony.setAutor(ksiazka.getAutor());
        zmieniony.setDostepnosc(ksiazka.getDostepnosc());
        zmieniony.setISBN(ksiazka.getISBN());
        zmieniony.setSeria(ksiazka.getSeria());
        zmieniony.setTytul(ksiazka.getTytul());
        ksiazkaService.insert(zmieniony);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/dodawanieRoliUzytkownikowi", method = RequestMethod.POST)
    public String dodawanieRoliUzytkownikowi(ModelMap model, @RequestParam("imieINazwisko") int idUzytkownika, @RequestParam("rola") int idRoli) {
        Uzytkownik uzytkownik = uzytkownikService.display(idUzytkownika);
        Rola rola = rolaService.displayWithoutPermission(idRoli);
        for (Rola r : uzytkownik.getRole())
            if (r.getNazwa().equals(rola.getNazwa())) {
                model.addAttribute("wiadomosc", "Użytkownik już posiada daną rolę!");
                return "redirect:/index";

            }

        uzytkownik.getRole().add(rola);
        rola.getUzytkownicy().add(uzytkownik);
        uzytkownikService.insert(uzytkownik);
        rolaService.insert(rola);
        model.addAttribute("wiadomosc", "Dodano nową rolę użytkownikowi");
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/dodajRoleUzytkownikowi.htm")
    public String dodajRoleUzytkownikowi(ModelMap model) {
        List role = rolaService.displayAllNamesAndId();
        List uzytkownicy = uzytkownikService.displayAllNamesAndId();
        model.addAttribute("listaUzytkownikow", uzytkownicy);
        model.addAttribute("listaRol", role);
        return "dodajRoleUzytkownikowi";
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
