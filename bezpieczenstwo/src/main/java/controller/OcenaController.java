package controller;

import model.entity.Ocena;
import model.entity.Uzytkownik;
import model.service.OcenaService;
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
 * Created by Ada on 2017-04-06.
 */
@Controller
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;
    @Autowired
    private UzytkownikService uzytkownikService;

    @PreAuthorize("hasPermission(authentication, 'ADD_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/dodajOcene.htm")
    public String dodajOcene() {
        return "Oceny/dodajOcene";
    }


    /*
    @PreAuthorize("hasPermission(authentication, 'ADD_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/dodawanieOceny", method = RequestMethod.POST)
    public String dodawanieOceny(Authentication authentication,@ModelAttribute("ocena") String ocena,@ModelAttribute("przedmiot") String przedmiot) {
        CustomUserDetails cs = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik nauczyciel= uzytkownikService.display(cs.getId());
        Uzytkownik uczen= uzytkownikService.display(3);
        Ocena nowaOcena = new Ocena(ocena, przedmiot, nauczyciel, uczen);
        nauczyciel.getOceny().add(nowaOcena);
        ocenaService.insert(nowaOcena);
        return "redirect:/index";

    }*/

    @PreAuthorize("hasPermission(authentication, 'ADD_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/dodawanieOceny", method = RequestMethod.POST)
    public String dodawanieOceny(Authentication authentication, @ModelAttribute Ocena ocena) {


        Uzytkownik nauczyciel = uzytkownikService.display(1);
        ocena.setNauczyciel(nauczyciel);
        Uzytkownik uczen = uzytkownikService.display(3);


        ocena.setUczen(uczen);

        ocenaService.insert(ocena);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/edytujOcene.htm")
    public String edytujOcene(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("idOceny", id);
        Ocena wybrany = ocenaService.display(id);
        model.addAttribute("przedmiot", wybrany.getPrzedmiot());
        model.addAttribute("ocena", wybrany.getOcena());
        String nazwiskoUcznia = wybrany.getUczen().getImieINazwisko();
        model.addAttribute("nazwiskoUcznia", nazwiskoUcznia);


        return "Oceny/edytujOcene";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/edytowanieOceny", method = RequestMethod.POST)
    public String edytowanieOceny(ModelMap model, @ModelAttribute("ocena") String ocena, @ModelAttribute("przedmiot") String przedmiot, @RequestParam("id") int id) {
        Ocena zmieniona = ocenaService.display(id);
        zmieniona.setOcena(ocena);
        zmieniona.setPrzedmiot(przedmiot);
        ocenaService.insert(zmieniona);
        return "redirect:/index";

    }


}
