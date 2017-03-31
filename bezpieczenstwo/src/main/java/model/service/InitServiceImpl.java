package model.service;

import model.entity.Lek;
import model.entity.Rola;
import model.entity.Uprawnienie;
import model.entity.Uzytkownik;
import model.repository.LekRepository;
import model.repository.RolaRepository;
import model.repository.UprawnienieRepository;
import model.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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


    //METODA @POSTCONSTRUCT WYWOLA SIE GDY TE WSZYSTKIE AUTOWIRED NA GORZE ZOSTANA ZINICJALIZOWANE
    @PostConstruct
    private void init() {

        // TODO ZMIENIC NAZWE INSERT NA insertOrUpdate

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        //  TWORZENIE ROL
        Rola rola1 = new Rola("ADMINISTRATOR");
        rolaRepository.insert(rola1);
        Rola rola2 = new Rola("DYREKTOR");
        rolaRepository.insert(rola2);
        Rola rola3 = new Rola("HIGIENISTKA");
        rolaRepository.insert(rola3);

//  TWORZENIE URZYTKOWNIKOW I POLACZENIE ICH Z ROLAMI
        Uzytkownik uzytkownik = new Uzytkownik("ada@wp.pl", passwordEncoder.encode("mati"), "Ada Rynkowska");
        Uzytkownik uzytkownik2 = new Uzytkownik("mati@wp.pl", passwordEncoder.encode("ada"), "Mateusz Szymczak");
        uzytkownik.getRole().add(rola1);
        uzytkownik2.getRole().add(rola1);
        uzytkownik2.getRole().add(rola3);
        uzytkownikRepository.insert(uzytkownik);
        uzytkownikRepository.insert(uzytkownik2);

//  TWORZENIE LEKOW
        Lek lek = new Lek("Dwa razy dziennie", "Rutinoskorbin", "3");
        List<Lek> lista = new ArrayList<Lek>();
        lista.add(new Lek("Dwa razy dziennie, dwie tabletki po posiłku", "Ibuprom", "20"));
        lista.add(new Lek("Raz dziennie", "Trybiotyk", "3"));
        lista.add(new Lek("Trzy razy dziennie", "Stoperan", "30"));

        for (Lek l : lista) {
            lekRepository.insert(l);
            uzytkownik2.getLeki().add(l);
        }

        lekRepository.insert(lek);
        uzytkownik2.getLeki().add(lek);
        uzytkownikRepository.insert(uzytkownik2);

//  TWORZENIE UPRAWNIEN I POWIAZANIE ICH Z ROLAMI
        Uprawnienie uprawnienie1 = new Uprawnienie("READ_KSIAZKI");
        Uprawnienie uprawnienie = new Uprawnienie("READ_LEKI");
        Uprawnienie uprawnienie3 = new Uprawnienie("READ_ROLE");
        Uprawnienie uprawnienie2 = new Uprawnienie("ADD_LEKI");
        rola3.getUprawnienia().add(uprawnienie);
        rola3.getUprawnienia().add(uprawnienie2);
        rola1.getUprawnienia().add(uprawnienie1);
        rola1.getUprawnienia().add(uprawnienie3);
        rolaRepository.insert(rola1);
        rolaRepository.insert(rola3);
        uprawnienieRepository.insert(uprawnienie3);
        uprawnienieRepository.insert(uprawnienie1);
        uprawnienieRepository.insert(uprawnienie);
        uprawnienieRepository.insert(uprawnienie2);


    }


}
