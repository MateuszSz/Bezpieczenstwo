package controller;

import model.entity.Ksiazka;
import model.service.KsiazkaService;
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
public class KsiazkaController {

    private KsiazkaService ksiazkaService;
    private UzytkownikService uzytkownikService;

    @Autowired
    public KsiazkaController(KsiazkaService ksiazkaService, UzytkownikService uzytkownikService) {
        this.ksiazkaService = ksiazkaService;
        this.uzytkownikService = uzytkownikService;
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_KSIAZKI')")
    @RequestMapping(value = "/index/dodajKsiazke.htm")
    public String dodajKsiazke() {
        return "ksiazki/dodajKsiazke";
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_KSIAZKI')")
    @RequestMapping(value = "/index/dodawanieKsiazki", method = RequestMethod.POST)
    public String dodawanieKsiazki(Principal principal, @ModelAttribute Ksiazka ksiazka) {
        ksiazka.setUzytkownik(uzytkownikService.display(uzytkownikService.findIdUsingEmail(principal.getName())));
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
        return "ksiazki/edytujKsiazke";
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


    @PreAuthorize("hasPermission(authentication, 'DELETE_KSIAZKI')")
    @RequestMapping(value = "/index/usunKsiazke")
    public String usunKsiazke(ModelMap model, @RequestParam("id") int id) {

        ksiazkaService.delete(id);
        return "redirect:/index";

    }


}
