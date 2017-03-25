package controller;

import model.entity.Uzytkownik;
import model.security.UserPermision;
import model.service.LekService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by mateu on 22.03.2017.
 */
@Controller
public class SecurityController {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private LekService lekService;

    @Autowired
    private UserPermision userPermision;

//114 Pabloo
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = {"/index", "/" })
    public String index(ModelMap model, Authentication authentication) {

        //pobranie E-mail zalogowanego użytkownika
        String email = authentication.getName();
        int userId = uzytkownikService.findIdUsingEmail(email);
        List leki = null;

        if(userPermision.hasPermision("READ_LEKI"));
            leki = lekService.displayAllByEmail(email);

        model.addAttribute("listaLekow", leki);
        return "index";
    }

    @PreAuthorize("@userPermision.hasPermision('READ_LEKI')")
    @RequestMapping(value = "/trol")
    public String wczytaj(Authentication authentication) {
        return "trol";
    }

}
