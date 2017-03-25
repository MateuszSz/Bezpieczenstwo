package controller;

import model.entity.Lek;
import model.entity.Uzytkownik;
import model.service.LekService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mateu on 12.03.2017.
 */
@Controller
public class IndexController {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private LekService lekService;




//    @RequestMapping(value = "/insert", method = RequestMethod.GET)
//    public String insert(@RequestParam("imie") String imie, @RequestParam("wiek") int wiek) {
//        //studentService.insert(new Student(imie, wiek));
//
//        Uzytkownik uzytkownik = new Uzytkownik("mati@wp.pl", "ada", "Mateusz Szymczak");
//        Lek lek = new Lek("ss", "ddd", "ddd");
//        Lek lek2 = new Lek("ss2", "ddd2", "ddd2");
//        uzytkownik.getLeki().add(lek);
//        uzytkownik.getLeki().add(lek2);
//        lekService.insert(lek);
//        lekService.insert(lek2);
//        uzytkownikService.insert(uzytkownik);
//
//
//        return "trol";
//    }
//    @RequestMapping(value = "/display",method = RequestMethod.GET)
//    public String display(@RequestParam("id") int id, Map<String, Object> map){
//        Student student = studentService.display(id);
//        map.put("imie", student.getName());
//        map.put("wiek", student.getAge());
//        return "wypisz";
//    }
}
