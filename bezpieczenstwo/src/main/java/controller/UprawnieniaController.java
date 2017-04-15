package controller;

import model.entity.Rola;
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
    public String dodawanieUprawnien(ModelMap model, @ModelAttribute Rola rola) {
        List role = rolaService.displayAll();
        for(Object r : role){
            if(r.toString().equals(rola.getNazwa())){
                model.addAttribute("status", "blad_dodawania_roli");
                return "redirect:/index";
            }
        }
        rolaService.insert(rola);
        return "redirect:/index";
    }
    @PreAuthorize("hasPermission(authentication, 'ADD_UPRAWNIENIA')")
    @RequestMapping(value = "/index/dodajUprawnienie.htm")
    public String dodajRole() {
        return "dodajUprawnienie";
    }



}
