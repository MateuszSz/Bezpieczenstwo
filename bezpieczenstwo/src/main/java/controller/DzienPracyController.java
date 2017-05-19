package controller;

import model.entity.DzienPracy;
import model.entity.Uzytkownik;
import model.security.CustomPermissionEvaluator;
import model.security.CustomUserDetails;
import model.service.DzienPracyService;
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

import java.util.List;

/**
 * Created by Ada on 2017-04-08.
 */
@Controller
public class DzienPracyController {

    @Autowired
    private DzienPracyService dzienPracyService;
    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;


    @PreAuthorize("hasPermission(authentication, 'ADD_DNIPRACY')")
    @RequestMapping(value = "/index/dodajDzienPracy.htm")
    public String dodajDzienPracy() {
        return "dzien_pracy/dodajDzienPracy";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_DNIPRACY')")
    @RequestMapping(value = "/index/dodajDzienPracyInnym.htm")
    public String dodajDzienPracyinnym(ModelMap model) {
        List ludzie = uzytkownikService.displayAllLecturers();
        model.addAttribute("ludzie", ludzie);
        return "dzien_pracy/dodajDzienPracyInnym";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_DNIPRACY')")
    @RequestMapping(value = "/index/dodawanieDniaPracy", method = RequestMethod.POST)
    public String dodawanieDniaPracy(@ModelAttribute DzienPracy dzienPracy, Authentication authentication,@RequestParam(value = "idPracownika", required = false) Integer idPracownika) {
        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik pracownik = uzytkownikService.display(cs.getId());
        if(idPracownika != null)
            pracownik = uzytkownikService.display(idPracownika);

        dzienPracy.setUzytkownik(pracownik);
        dzienPracyService.insert(dzienPracy);
        //return "redirect:/index/pokazDniPracyInnychPracownikow";
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_DNIPRACY')")
    @RequestMapping(value = "/index/edytujDzienPracy.htm")
    public String edytujDzienPracy(ModelMap model, @RequestParam("id") int id,@RequestParam(value = "idPracownika", required = false) Integer idPracownika) {

        DzienPracy wybrany = dzienPracyService.display(id);
        model.addAttribute("idDnia", id);
        model.addAttribute("dzienTygodnia", wybrany.getDzienTygodnia());
        model.addAttribute("godzinaRozpoczecia", wybrany.getGodzinaRozpoczecia());
        model.addAttribute("godzinaZakonczenia", wybrany.getGodzinaZakonczenia());
        if(idPracownika != null)
            model.addAttribute("idPracownika", idPracownika);
        return "dzien_pracy/edytujDzienPracy";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_DNIPRACY')")
    @RequestMapping(value = "/index/edytowanieDniaPracy", method = RequestMethod.POST)
    public String edytowanieDniaPracy(ModelMap model, @ModelAttribute DzienPracy dzienPracy, @RequestParam("id") int id, @RequestParam(value = "idPracownika", required = false) Integer idPracownika, Authentication authentication) {
        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik pracownik = uzytkownikService.display(cs.getId());
        if(idPracownika != null)
            pracownik = uzytkownikService.display(idPracownika);

        DzienPracy zmieniony = dzienPracyService.display(id);
        zmieniony.setUzytkownik(pracownik);
        zmieniony.setGodzinaRozpoczecia(dzienPracy.getGodzinaRozpoczecia());
        zmieniony.setGodzinaZakonczenia(dzienPracy.getGodzinaZakonczenia());
        zmieniony.setDzienTygodnia(dzienPracy.getDzienTygodnia());

        dzienPracyService.insert(zmieniony);

        return "redirect:/index";

    }


    @PreAuthorize("hasPermission(authentication, 'DELETE_DNIPRACY')")
    @RequestMapping(value = "/index/usunDzienPracy")
    public String usunDzienPracy(ModelMap model, @RequestParam("id") int id) {

        dzienPracyService.delete(id);
        return "redirect:/index";

    }



    @PreAuthorize("hasPermission(authentication, 'EDIT_DNIPRACY') && hasPermission(authentication, 'READ_UZYTKOWNICY')")
    @RequestMapping(value = "/index/pokazDniPracyInnychPracownikow")
    public String pokazDniPracyInnychPracownikow(ModelMap model, Authentication authentication) {
        List wszystkieDniPracy = null;
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_DNIPRACY"))
            wszystkieDniPracy = dzienPracyService.displayAll();
        model.addAttribute("listaDniPracy", wszystkieDniPracy);
        return "dzien_pracy/pokazDniPracyInnych";

    }
}
