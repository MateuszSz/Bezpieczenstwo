package controller;

import model.entity.Ocena;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ada on 2017-04-06.
 */
@Controller
public class OcenaController {

    @PreAuthorize("hasPermission(authentication, 'ADD_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/dodajOcene.htm")
    public String dodajOcene() {
        return "dodajOcene";
    }

    @PreAuthorize("hasPermission(authentication, 'ADD_KSIAZKI')")
    @RequestMapping(value = "/index/dodawanieOceny", method = RequestMethod.POST)
    public String dodawanieOceny(@ModelAttribute Ocena ocena) {

        return "redirect:/index";

    }

}
