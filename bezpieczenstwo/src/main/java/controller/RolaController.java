package controller;

import model.entity.Rola;
import model.entity.Uzytkownik;
import model.security.CustomUserDetails;
import model.service.RolaService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ada on 2017-04-06.
 */
@Controller
public class RolaController {


    private UzytkownikService uzytkownikService;
    private RolaService rolaService;
    private SessionRegistry sessionRegistry;

    @Autowired
    public RolaController(UzytkownikService uzytkownikService, RolaService rolaService, SessionRegistry sessionRegistry) {
        this.uzytkownikService = uzytkownikService;
        this.rolaService = rolaService;
        this.sessionRegistry = sessionRegistry;
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/dodawanieRoliUzytkownikowi", method = RequestMethod.POST)
    public String dodawanieRoliUzytkownikowi(ModelMap model, @RequestParam("imieINazwisko") int idUzytkownika, @RequestParam("rola") int idRoli) {
        Uzytkownik uzytkownik = uzytkownikService.display(idUzytkownika);
        Rola rola = rolaService.displayWithoutPermission(idRoli);
        for (Rola r : uzytkownik.getRole())
            if (r.getNazwa().equals(rola.getNazwa())) {
                //model.addAttribute("wiadomosc", "Użytkownik już posiada daną rolę!");
                return "redirect:/index";

            }

        uzytkownik.getRole().add(rola);
        rola.getUzytkownicy().add(uzytkownik);
        uzytkownikService.insert(uzytkownik);
        rolaService.insert(rola);
        // model.addAttribute("wiadomosc", "Dodano nową rolę użytkownikowi");
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'DELETE_ROLE')")
    @RequestMapping(value = "/index/usuwanieRoliUzytkownikowi", method = RequestMethod.POST)
    public String usuwanieRoliUzytkownikowi(ModelMap model, @RequestParam("imieINazwisko") int idUzytkownika, @RequestParam("rola") int idRoli) {
        rolaService.deleteRoleFromUser(idRoli, idUzytkownika);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/usunRoleUzytkownikowi.htm")
    public String usunRoleUzytkownikowi(ModelMap model) {
        List role = rolaService.displayAllNamesAndId();
        List uzytkownicy = uzytkownikService.displayAllNamesAndId();
        model.addAttribute("listaUzytkownikow", uzytkownicy);
        model.addAttribute("listaRol", role);
        return "role/usunRoleUzytkownikowi";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/dodajRoleUzytkownikowi.htm")
    public String dodajRoleUzytkownikowi(ModelMap model) {
        List role = rolaService.displayAllNamesAndId();
        List uzytkownicy = uzytkownikService.displayAllNamesAndId();
        model.addAttribute("listaUzytkownikow", uzytkownicy);
        model.addAttribute("listaRol", role);
        return "role/dodajRoleUzytkownikowi";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/dodajRole.htm")
    public String dodajRole() {
        return "role/dodajRole";
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_ROLE')")
    @RequestMapping(value = "/index/dodawanieRoli", method = RequestMethod.POST)
    public String dodawanieRoli(ModelMap model, @ModelAttribute Rola rola) {
        List role = rolaService.displayAll();
        for (Object r : role) {
            if (r.toString().equals(rola.getNazwa())) {
                model.addAttribute("status", "blad_dodawania_roli");
                return "redirect:/index";
            }
        }
        rolaService.insert(rola);
        return "redirect:/index";
    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_ROLE')")
    @RequestMapping(value = "/index/edytujRole.htm")
    public String edytujRole(ModelMap model) {
        List role = rolaService.displayAllNamesAndId();
        model.addAttribute("listaRol", role);
        return "role/edytujRole";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_ROLE')")
    @RequestMapping(value = "/index/edycjaRoli", method = RequestMethod.POST)
    public String edytowanieRoli(@RequestParam("nazwa") String nazwaRoli, @RequestParam("rola") int idRoli, Authentication authentication) {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        Rola rola = rolaService.display(idRoli);
        CustomUserDetails cs;
        for (Object principal : principals) {
            cs = (CustomUserDetails) principal;
            if (cs.getWybranaRola().equals(rola.getNazwa()))
                cs.setWybranaRola(nazwaRoli);
        }
        rola.setNazwa(nazwaRoli);
        rolaService.insert(rola);


        return "redirect:/index";
    }

    @PreAuthorize("hasPermission(authentication, 'DELETE_ROLE')")
    @RequestMapping(value = "/index/usunRole.htm")
    public String usunRole(ModelMap model) {
        List role = rolaService.displayAllNamesAndId();
        model.addAttribute("listaRol", role);
        return "role/usunRole";
    }


    @PreAuthorize("hasPermission(authentication, 'DELETE_ROLE')")
    @RequestMapping(value = "/index/usuwanieRoli", method = RequestMethod.POST)
    public String usuwanieRoli(ModelMap model, @RequestParam("rola") int idRoli) {
        if (rolaService.display(idRoli).getNazwa().equals("ADMINISTRATOR")) {
            model.addAttribute("status", "usuwanie_admina");
            return "redirect:/index";
        }
        rolaService.delete(idRoli);
        model.addAttribute("status", "powodzenie");
        return "redirect:/index";
    }


}
