package controller;

import model.entity.DzienPracy;
import model.entity.Ksiazka;
import model.entity.Uzytkownik;
import model.security.CustomUserDetails;
import model.service.DzienPracyService;
import model.service.KsiazkaService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ada on 2017-04-08.
 */
@Controller
public class DzienPracyController {

    @Autowired
    private DzienPracyService dzienPracyService;
    @Autowired
    private UzytkownikService uzytkownikService;


    @PreAuthorize("hasPermission(authentication, 'ADD_DNIPRACY')")
    @RequestMapping(value = "/index/dodajDzienPracy.htm")
    public String dodajDzienPracy() {
        return "dodajDzienPracy";
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_DNIPRACY')")
    @RequestMapping(value = "/index/dodawanieDniaPracy", method = RequestMethod.POST)
    public String dodawanieDniaPracy(@ModelAttribute DzienPracy dzienPracy, Authentication authentication) {
        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik pracownik = uzytkownikService.display(cs.getId());
        dzienPracy.setUzytkownik(pracownik);
        dzienPracyService.insert(dzienPracy);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_DNIPRACY')")
    @RequestMapping(value = "/index/edytujDzienPracy.htm")
    public String edytujDzienPracy(ModelMap model, @RequestParam("id") int id) {

        DzienPracy wybrany = dzienPracyService.display(id);
        model.addAttribute("idDnia", id);
        model.addAttribute("dzienTygodnia", wybrany.getDzienTygodnia());
        model.addAttribute("godzinaRozpoczecia", wybrany.getGodzinaRozpoczecia());
        model.addAttribute("godzinaZakonczenia", wybrany.getGodzinaZakonczenia());

        return "edytujDzienPracy";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_DNIPRACY')")
    @RequestMapping(value = "/index/edytowanieDniaPracy", method = RequestMethod.POST)
    public String edytowanieDniaPracy(ModelMap model, @ModelAttribute DzienPracy dzienPracy, @RequestParam("id") int id, Authentication authentication) {

        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik pracownik = uzytkownikService.display(cs.getId());
        DzienPracy zmieniony= dzienPracyService.display(id);
        zmieniony.setUzytkownik(pracownik);
        zmieniony.setGodzinaRozpoczecia(dzienPracy.getGodzinaRozpoczecia());
        zmieniony.setGodzinaZakonczenia(dzienPracy.getGodzinaZakonczenia());
        zmieniony.setDzienTygodnia(dzienPracy.getDzienTygodnia());

        dzienPracyService.insert(zmieniony);

        return "redirect:/index";

    }


    @PreAuthorize("hasPermission(authentication, 'DELETE_DNIPRACY')")
    @RequestMapping(value = "/index/usunDzienPracy")
    public String usunDzienPracy(ModelMap model,  @RequestParam("id") int id) {

        dzienPracyService.delete(id);
        return "redirect:/index";

    }










}
