package model.service;

import model.entity.*;
import model.repository.*;
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
    private KsiazkaRepository ksiazkaRepository;

    @Autowired
    private UprawnienieRepository uprawnienieRepository;


    //METODA @POSTCONSTRUCT WYWOLA SIE GDY TE WSZYSTKIE AUTOWIRED NA GORZE ZOSTANA ZINICJALIZOWANE
    @PostConstruct
    private void init() {

        // TODO ZMIENIC NAZWE INSERT NA insertOrUpdate

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        //  TWORZENIE ROL
        Rola rolaAdmina = new Rola("ADMINISTRATOR");
        rolaRepository.insert(rolaAdmina);
        Rola rolaDyrektora = new Rola("DYREKTOR");
        rolaRepository.insert(rolaDyrektora);
        Rola rolaHigienistka = new Rola("HIGIENISTKA");
        rolaRepository.insert(rolaHigienistka);
        Rola rolaNauczyciel = new Rola ( "NAUCZYCIEL");
        rolaRepository.insert(rolaNauczyciel);
        Rola rolaUczen = new Rola ("UCZEN");
        rolaRepository.insert(rolaUczen);
        Rola rolaBibliotekarz = new  Rola("BIBLIOTEKARZ");
        rolaRepository.insert(rolaBibliotekarz);

//  TWORZENIE URZYTKOWNIKOW I POLACZENIE ICH Z ROLAMI
        Uzytkownik uzytkownik = new Uzytkownik("ada@wp.pl", passwordEncoder.encode("mati"), "Ada Rynkowska");
        Uzytkownik uzytkownik2 = new Uzytkownik("mati@wp.pl", passwordEncoder.encode("ada"), "Mateusz Szymczak");
        uzytkownik.getRole().add(rolaAdmina);
        uzytkownik.getRole().add(rolaDyrektora);
        uzytkownik.getRole().add(rolaUczen);
        uzytkownik.getRole().add(rolaBibliotekarz);
        uzytkownik2.getRole().add(rolaNauczyciel);
        uzytkownik2.getRole().add(rolaAdmina);
        uzytkownik2.getRole().add(rolaHigienistka);
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

        //TWORZENIE KSIAZEK

        List<Ksiazka>ksiazki= new ArrayList<Ksiazka>();
        ksiazki.add(new Ksiazka("Brandon Sanderson", "987-83-7480-670-1", "Siewca Wojny ", "Rozjemca", "dostepna"));

        for(Ksiazka k: ksiazki){
            ksiazkaRepository.insert(k);
            uzytkownik.getKsiazki().add(k);
        }
        uzytkownikRepository.insert(uzytkownik);

//  TWORZENIE UPRAWNIEN I POWIAZANIE ICH Z ROLAMI
        Uprawnienie uprawnienieReadKsiazki = new Uprawnienie("READ_KSIAZKI");
        Uprawnienie uprawnienieReadLeki = new Uprawnienie("READ_LEKI");
        Uprawnienie uprawnienieReadRole = new Uprawnienie("READ_ROLE");
        Uprawnienie uprawnienieAddLeki = new Uprawnienie("ADD_LEKI");
        Uprawnienie uprawnienieEditLeki = new Uprawnienie("EDIT_LEKI");

        rolaHigienistka.getUprawnienia().add(uprawnienieReadLeki);
        rolaHigienistka.getUprawnienia().add(uprawnienieAddLeki);
        rolaHigienistka.getUprawnienia().add(uprawnienieEditLeki);
        rolaAdmina.getUprawnienia().add(uprawnienieReadKsiazki);
        rolaAdmina.getUprawnienia().add(uprawnienieReadRole);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieReadKsiazki);

        uprawnienieRepository.insert(uprawnienieReadRole);
        uprawnienieRepository.insert(uprawnienieReadKsiazki);
        uprawnienieRepository.insert(uprawnienieReadLeki);
        uprawnienieRepository.insert(uprawnienieAddLeki);
        uprawnienieRepository.insert(uprawnienieEditLeki);

        rolaRepository.insert(rolaAdmina);
        rolaRepository.insert(rolaHigienistka);
        rolaRepository.insert(rolaBibliotekarz);

    }


}
