package controller;

import model.entity.Rola;
import model.entity.Uprawnienie;
import model.service.RolaService;
import model.service.UprawnienieService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateu on 15.04.2017.
 */
@Controller
public class UprawnieniaController {
    @Autowired
    private RolaService rolaService;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UprawnienieService uprawnienieService;

    @PreAuthorize("hasPermission(authentication, 'ADD_UPRAWNIENIA')")
    @RequestMapping(value = "/index/dodawanieUprawnien", method = RequestMethod.POST)
    public String dodawanieUprawnien(ModelMap model, @RequestParam("rola") String nazwaRoli, @RequestParam("tablica") String nazwaTablicy
            , @RequestParam("uprawnienie") String nazwaUprawnienia) {
        Uprawnienie uprawnienie = uprawnienieService.findByName(nazwaUprawnienia + "_" + nazwaTablicy);
        Rola rola = rolaService.display(rolaService.findIdUsingName(nazwaRoli));
        for (Uprawnienie uprawnienie1 : rola.getUprawnienia()) {
            if (uprawnienie1.getNazwa().equals(uprawnienie.getNazwa()))
                return "redirect:/index";
        }
        rola.getUprawnienia().add(uprawnienie);
        rolaService.insert(rola);
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'ADD_UPRAWNIENIA')")
    @RequestMapping(value = "/index/dodajUprawnienie.htm")
    public String dodajUprawnienie(ModelMap model) {

        List listaRol = rolaService.displayAll();
        model.addAttribute("listaRol", listaRol);

        return "uprawnienia/dodajUprawnienie";
    }

    @PreAuthorize("hasPermission(authentication, 'DELETE_UPRAWNIENIA')")
    @RequestMapping(value = "/index/usuwanieUprawnien", method = RequestMethod.POST)
    public String usuwanieUprawnien(ModelMap model, @RequestParam("rola") String nazwaRoli, @RequestParam("tablica") String nazwaTablicy
            , @RequestParam("uprawnienie") String nazwaUprawnienia) {
        Uprawnienie uprawnienie = uprawnienieService.findByName(nazwaUprawnienia + "_" + nazwaTablicy);
        Rola rola = rolaService.display(rolaService.findIdUsingName(nazwaRoli));
        boolean czyIstieje = false;
        for (Uprawnienie uprawnienie1 : rola.getUprawnienia()) {
            if (uprawnienie1.getNazwa().equals(uprawnienie.getNazwa())) {
                czyIstieje = true;
                uprawnienie = uprawnienie1;
            }
        }
        if (czyIstieje) {
            rola.getUprawnienia().remove(uprawnienie);
            rolaService.insert(rola);
        }
        return "redirect:/index";

    }

    @PreAuthorize("hasPermission(authentication, 'DELETE_UPRAWNIENIA')")
    @RequestMapping(value = "/index/usunUprawnienie.htm")
    public String usunUprawnienie(ModelMap model) {

        List listaRol = rolaService.displayAll();
        model.addAttribute("listaRol", listaRol);

        return "uprawnienia/usunUprawnienie";
    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_UPRAWNIENIA')")
    @RequestMapping(value = "/index/edytujUprawnienie.htm")
    public String edytujUprawnienia(ModelMap model) {

        List listaRol = rolaService.displayAll();
        model.addAttribute("listaRol", listaRol);

        return "uprawnienia/edytujUprawnienie";
    }

    @PreAuthorize("hasPermission(authentication, 'EDIT_UPRAWNIENIA')")
    @RequestMapping(value = "/index/edycjaUprawnien", method = RequestMethod.POST)
    public String edycjaUprawnien(ModelMap model, @RequestParam("rola") String nazwaRoli, @RequestParam("tablica") String nazwaTablicy) {
        Rola rola = rolaService.display(rolaService.findIdUsingName(nazwaRoli));
        List<String> listaUprawnien = new ArrayList<String>();

        for (Uprawnienie u : rola.getUprawnienia()) {
            if (u.getNazwa().contains(nazwaTablicy))
                listaUprawnien.add(u.getNazwa());
        }
        model.addAttribute("listaUprawnien", listaUprawnien);
        model.addAttribute("rola", nazwaRoli);
        model.addAttribute("tablica", nazwaTablicy);
        return "uprawnienia/edycjaUprawnien";

    }


}
