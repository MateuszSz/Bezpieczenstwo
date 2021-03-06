package controller;

import model.entity.Ocena;
import model.entity.Uzytkownik;
import model.security.CustomUserDetails;
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

import java.util.List;

/**
 * Created by Ada on 2017-04-06.
 */
@Controller
public class OcenaController {


    private OcenaService ocenaService;
    private UzytkownikService uzytkownikService;

    @Autowired
    public OcenaController(OcenaService ocenaService, UzytkownikService uzytkownikService) {
        this.ocenaService = ocenaService;
        this.uzytkownikService = uzytkownikService;
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/dodajOcene.htm")
    public String dodajOcene(ModelMap model) {

        List uczniowie = uzytkownikService.displayAllNamesAndIdByRole("UCZEN");
        model.addAttribute("listaUczniow" , uczniowie);
        return "oceny/dodajOcene";
    }





    @PreAuthorize("hasPermission(authentication, 'ADD_WYSTAWIONEOCENY')")
    @RequestMapping(value = "/index/dodawanieOceny", method = RequestMethod.POST)
    public String dodawanieOceny(Authentication authentication, @RequestParam("imieINazwisko")int imieINazwisko, @ModelAttribute Ocena ocena) {
        ocenaService.insert(ocena);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik nauczyciel = uzytkownikService.displayWithMarks(customUserDetails.getId());
        ocena.setNauczyciel(nauczyciel);
        Uzytkownik uczen = uzytkownikService.displayWithMarks(imieINazwisko);
       ocena.setUczen(uczen);
       uzytkownikService.insert(nauczyciel);
       uzytkownikService.insert(uczen);
        ocenaService.insert(ocena);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'ADD_MOJEOCENY')")
    @RequestMapping(value = "/index/dodajMojaOcene.htm")
    public String dodajMojaOcene(ModelMap model) {

        //List uczniowie = uzytkownikService.displayAllNamesAndIdByRole("UCZEN");
        //model.addAttribute("listaUczniow" , uczniowie);
        return "oceny/dodajMojaOcene";
    }


    @PreAuthorize("hasPermission(authentication, 'ADD_MOJEOCENY')")
    @RequestMapping(value = "/index/dodawanieMojejOceny", method = RequestMethod.POST)
    public String dodawanieMojejOceny(Authentication authentication, @ModelAttribute Ocena ocena) {
        //ocenaService.insert(ocena);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Uzytkownik nauczyciel = uzytkownikService.displayWithMarks(customUserDetails.getId());
        ocena.setNauczyciel(nauczyciel);
        Uzytkownik uczen = uzytkownikService.displayWithMarks(customUserDetails.getId());
        ocena.setUczen(uczen);
        uzytkownikService.insert(nauczyciel);
        uzytkownikService.insert(uczen);
        ocenaService.insert(ocena);
        return "redirect:/index";

    }




    @PreAuthorize("hasPermission(authentication, 'EDIT_WYSTAWIONEOCENY')|| hasPermission(authentication, 'EDIT_MOJEOCENY')")
    @RequestMapping(value = "/index/edytujOcene.htm")
    public String edytujOcene(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("idOceny", id);
        Ocena wybrany = ocenaService.display(id);
        model.addAttribute("przedmiot", wybrany.getPrzedmiot());
        model.addAttribute("ocena", wybrany.getOcena());
        String nazwiskoUcznia = wybrany.getUczen().getImieINazwisko();
        model.addAttribute("nazwiskoUcznia", nazwiskoUcznia);


        return "oceny/edytujOcene";
    }


    @PreAuthorize("hasPermission(authentication, 'EDIT_WYSTAWIONEOCENY')|| hasPermission(authentication, 'EDIT_MOJEOCENY')")
    @RequestMapping(value = "/index/edytowanieOceny", method = RequestMethod.POST)
    public String edytowanieOceny(ModelMap model, @ModelAttribute("ocena") String ocena, @ModelAttribute("przedmiot") String przedmiot, @RequestParam("id") int id) {
        Ocena zmieniona = ocenaService.display(id);
        zmieniona.setOcena(ocena);
        zmieniona.setPrzedmiot(przedmiot);
        ocenaService.insert(zmieniona);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'DELETE_WYSTAWIONEOCENY')|| hasPermission(authentication, 'DELETE_MOJEOCENY')")
    @RequestMapping(value="/index/usunOcene", method = RequestMethod.GET)
    public String usuwanieOceny(ModelMap model, @RequestParam("id") int id) {
        ocenaService.delete(id);
        return "redirect:/index";

    }
}
