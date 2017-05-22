package controller;

import model.entity.Ksiazka;
import model.entity.Uzytkownik;
import model.security.CustomUserDetails;
import model.service.KsiazkaService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * Created by mateu on 06.05.2017.
 */
@Controller
public class UzytkownikController {


    private UzytkownikService uzytkownikService;
    private SessionRegistry sessionRegistry;

    @Autowired
    public UzytkownikController(UzytkownikService uzytkownikService, SessionRegistry sessionRegistry) {
        this.uzytkownikService = uzytkownikService;
        this.sessionRegistry = sessionRegistry;
    }



    @PreAuthorize("hasPermission(authentication, 'ADD_UZYTKOWNICY')")
    @RequestMapping(value = "/index/dodajUzytkownika.htm")
    public String dodajKsiazke() {
        return "uzytkownicy/dodajUzytkownika";
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_UZYTKOWNICY')")
    @RequestMapping(value = "/index/dodawanieUzytkownika", method = RequestMethod.POST)
    public String dodawanieKsiazki( ModelMap model,  @ModelAttribute Uzytkownik uzytkownik) {
        String password = uzytkownik.getHaslo();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        uzytkownik.setHaslo(passwordEncoder.encode(password));
        uzytkownikService.insert(uzytkownik);
        model.addAttribute("wiadomosc", "dodano_uzytkownika");
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'DELETE_UZYTKOWNICY')")
    @RequestMapping(value = "/index/usunUzytkownika.htm")
    public String usunRole(ModelMap model) {
        List uzytkownicy = uzytkownikService.displayAllWithoutPassword();
        model.addAttribute("listaUzytkownikow", uzytkownicy);
        return "uzytkownicy/usunUzytkownika";
    }


    @PreAuthorize("hasPermission(authentication, 'DELETE_UZYTKOWNICY')")
    @RequestMapping(value = "/index/usuwanieUzytkownika", method = RequestMethod.POST)
    public String usuwanieRoli(Authentication authentication, ModelMap model, @RequestParam("uzytkownik") int idUzytkownika) {

        List<Object> principals = sessionRegistry.getAllPrincipals();

        CustomUserDetails cs;
        for (Object principal : principals) {
            cs = (CustomUserDetails) principal;
            if (cs.getId() == idUzytkownika) {
                cs.setEmail("loggedOut");
                cs.setAuthorities(null);
            }
        }

        uzytkownikService.delete(idUzytkownika);
        CustomUserDetails cs2 = (CustomUserDetails) authentication.getPrincipal();
        if(cs2.getId() == idUzytkownika )
            return "redirect:/logout";
        model.addAttribute("wiadomosc", "powodzenie");
        return "redirect:/index";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_UZYTKOWNICY')")
    @RequestMapping(value = "index/edytujUzytkownika.htm")
    public String edytujRole(ModelMap model) {
        List uzytkownicy = uzytkownikService.displayAllWithoutPassword();
        model.addAttribute("listaUzytkownikow", uzytkownicy);
        return "uzytkownicy/edytujUzytkownika";
    }


    ///index/edytujUzytkownika.htm
    @PreAuthorize("hasPermission(authentication, 'EDIT_UZYTKOWNICY')")
    @RequestMapping(value = "index/edycjaUzytkownika")
    public String edytowanieUzytkownika(ModelMap model, @RequestParam("uzytkownik") int idUzytkownika) {
        Uzytkownik uzytkownicy = uzytkownikService.display(idUzytkownika);
        model.addAttribute("id", uzytkownicy.getId());
        model.addAttribute("email", uzytkownicy.getEmail());
        model.addAttribute("imieINazwisko", uzytkownicy.getImieINazwisko());
        return "uzytkownicy/edycjaUzytkownika";
    }

    ///index/edytowanieUzytkownika
    @PreAuthorize("hasPermission(authentication, 'EDIT_UZYTKOWNICY')")
    @RequestMapping(value = "index/edytowanieUzytkownika", method = RequestMethod.POST)
    public String edytowanieKsiazki(ModelMap model, @ModelAttribute Uzytkownik uzytkownik, @RequestParam("id") int id) {
        String password = uzytkownik.getHaslo();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Uzytkownik zmieniony = uzytkownikService.display(id);
        zmieniony.setEmail(uzytkownik.getEmail());
        zmieniony.setImieINazwisko(uzytkownik.getImieINazwisko());
        zmieniony.setHaslo(passwordEncoder.encode(password));
        uzytkownikService.insert(zmieniony);
        model.addAttribute("wiadomosc", "dokonano_educjo");
        return "redirect:/index";

    }
}
