package controller;

import model.entity.Rola;
import model.entity.Uzytkownik;
import model.service.RolaService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ada on 2017-04-06.
 */
@Controller
public class RolaController {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private RolaService rolaService;

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





}
