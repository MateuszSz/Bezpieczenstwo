package controller;

import model.entity.Lek;
import model.service.LekService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by Ada on 2017-04-06.
 */

@Controller
public class LekController {

    private UzytkownikService uzytkownikService;
    private LekService lekService;

    @Autowired
    public LekController(UzytkownikService uzytkownikService, LekService lekService) {
        this.uzytkownikService = uzytkownikService;
        this.lekService = lekService;
    }




    @RequestMapping(value = "/wyloguj")
    public String wyswietlWiadomoscWylogowania() {
        return "logout";
    }

    //Sprawdzenie czy, osoba która chce się dostać do tej metody ma uprawnienia dodawania lekow
    @PreAuthorize("hasPermission(authentication, 'ADD_LEKI')")
    @RequestMapping(value = "/index/dodajLek.htm")
    public String dodajLek() {

        return "leki/dodajLek";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_LEKI')")
    @RequestMapping(value = "/index/dodawanieLeku", method = RequestMethod.POST)
    public String dodaj(Principal principal, @ModelAttribute Lek lek) {
        lek.setUzytkownik(uzytkownikService.display(uzytkownikService.findIdUsingEmail(principal.getName())));
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
        return "leki/edytujLek";
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

    @PreAuthorize("hasPermission(authentication, 'DELETE_LEKI')")
    @RequestMapping(value = "/index/usunLek")
    public String usunKsiazke(ModelMap model, @RequestParam("id") int id) {

        lekService.delete(id);
        return "redirect:/index";

    }


}
