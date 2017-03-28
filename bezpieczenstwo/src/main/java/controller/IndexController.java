package controller;

import model.service.LekService;
import model.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by mateu on 12.03.2017.
 */
@Controller
public class IndexController {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private LekService lekService;

//TODO WSZYSTKIE RZECZY NIEZWIĄZANE Z SECURITY
}
