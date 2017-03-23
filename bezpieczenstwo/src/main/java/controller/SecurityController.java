package controller;

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


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index(ModelMap model, Principal principal, Authentication authentication) {
        String email = principal.getName();
        List leki = null;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("READ_LEKI"));
        if(authorized)
            leki = lekService.displayAllByEmail(email);

        model.addAttribute("listaLekow", leki);
        return "index";
    }

    @PreAuthorize("hasRole('READ_LEKI')")
    @RequestMapping(value = "/trol")
    public String wczytaj(Authentication authentication) {

        return "trol";
    }

}
