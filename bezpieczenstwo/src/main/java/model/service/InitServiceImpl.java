package model.service;

import model.repository.LekRepository;
import model.repository.RolaRepository;
import model.repository.UprawnienieRepository;
import model.repository.UzytkownikRepository;
import model.entity.Lek;
import model.entity.Rola;
import model.entity.Uprawnienie;
import model.entity.Uzytkownik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by mateu on 22.03.2017.
 */
@Transactional
@Service
public class InitServiceImpl {

    @Autowired
    private UzytkownikRepository uzytkownikRepository;

    @Autowired
    private RolaRepository rolaRepository;

    @Autowired
    private LekRepository lekRepository;

    @Autowired
    private UprawnienieRepository uprawnienieRepository;

    @PostConstruct
    private void init() {


        Rola rola1 = new Rola("ADMINISTRATOR");
        Uprawnienie uprawnienie1 = new Uprawnienie("READ_KSIAZKI");
        rola1.getUprawnienia().add(uprawnienie1);
        uprawnienieRepository.insert(uprawnienie1);
        rolaRepository.insert(rola1);

        Rola rola2 = new Rola("DYREKTOR");
        rolaRepository.insert(rola2);
        Rola rola3 = new Rola("BIBLIOTEKARZ");
        rolaRepository.insert(rola3);
        Rola rola4 = new Rola("ADMINISTRATOR");
        rolaRepository.insert(rola4);

        Uzytkownik uzytkownik = new Uzytkownik("ada@wp.pl", "mati", "Ada Rynkowska");
        Uzytkownik uzytkownik2 = new Uzytkownik("mati@wp.pl", "ada", "Mateusz Szymczak");
        uzytkownik.getRole().add(rola1);
        uzytkownik2.getRole().add(rola1);
        uzytkownik2.getRole().add(rola3);
        uzytkownikRepository.insert(uzytkownik);
        uzytkownikRepository.insert(uzytkownik2);

        Lek lek = new Lek("Dwa razy dziennie", "Rutinoskorbin", "3");
        lekRepository.insert(lek);
        uzytkownik2.getLeki().add(lek);
        uzytkownikRepository.insert(uzytkownik2);


        Uprawnienie uprawnienie = new Uprawnienie("READ_LEKI");
        rola3.getUprawnienia().add(uprawnienie);
        rolaRepository.insert(rola3);
        uprawnienieRepository.insert(uprawnienie);

    }


}
