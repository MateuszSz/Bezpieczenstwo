package controller;

import model.entity.Lek;
import model.security.CustomPermissionEvaluator;
import model.security.CustomUserDetails;
import model.service.KsiazkaService;
import model.service.LekService;
import model.service.RolaService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mateu on 22.03.2017.
 */
@Controller
public class SecurityController {

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

        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_LEKI"))
            leki = lekService.displayAll();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_ROLE"))
            role = rolaService.displayWithUserName();
        if (customPermissionEvaluator.hasPermission(authentication, null, "READ_KSIAZKI"))
            ksiazki = ksiazkaService.displayAll();

        //dodawanie atrybutu do modelu.
        //Model jest przekazywany do index jsp samoczynnie w returnie

        model.addAttribute("rola", cs.getWybranaRola());
        model.addAttribute("imieINazwisko", cs.getName());
        model.addAttribute("listaLekow", leki);
        model.addAttribute("listaRol", role);
        model.addAttribute("listaKsiazek", ksiazki);
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
    public String dodaj(@ModelAttribute Lek lek){
        lekService.insert(lek);
        return "redirect:/index";

    }


}
