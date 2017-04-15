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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
                                      ,@RequestParam("uprawnienie") String nazwaUprawnienia  ) {
        Uprawnienie uprawnienie = uprawnienieService.findByName(nazwaUprawnienia + "_" + nazwaTablicy);
        Rola rola = rolaService.display(rolaService.findIdUsingName(nazwaRoli));
        if(!rola.getUprawnienia().contains(uprawnienie)) {
            rola.getUprawnienia().add(uprawnienie);
            rolaService.insert(rola);
        }
        return "redirect:/index";

    }
    @PreAuthorize("hasPermission(authentication, 'ADD_UPRAWNIENIA')")
    @RequestMapping(value = "/index/dodajUprawnienie.htm")
    public String dodajRole(ModelMap model) {

        List listaRol = rolaService.displayAll();
        model.addAttribute("listaRol", listaRol);

        return "uprawnienia/dodajUprawnienie";
    }



}
