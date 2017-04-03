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
        Uprawnienie uprawnienieReadLeki = new Uprawnienie("READ_LEKI");
        Uprawnienie uprawnienieAddLeki = new Uprawnienie("ADD_LEKI");
        Uprawnienie uprawnienieEditLeki = new Uprawnienie("EDIT_LEKI");
        Uprawnienie uprawnienieDeleteLeki = new Uprawnienie("DELETE_LEKI");

        Uprawnienie uprawnienieReadKsiazki = new Uprawnienie("READ_KSIAZKI");
        Uprawnienie uprawnienieAddKsiazki = new Uprawnienie("ADD_KSIAZKI");
        Uprawnienie uprawnienieEditKsiazki = new Uprawnienie("EDIT_KSIAZKI");
        Uprawnienie uprawnienieDeleteKsiazki = new Uprawnienie("DELETE_KSIAZKI");

        Uprawnienie uprawnienieReadRole= new Uprawnienie("READ_ROLE");
        Uprawnienie uprawnienieAddRole = new Uprawnienie("ADD_ROLE");
        Uprawnienie uprawnienieEditRole = new Uprawnienie("EDIT_ROLE");
        Uprawnienie uprawnienieDeleteRole = new Uprawnienie("DELETE_ROLE");

        Uprawnienie uprawnienieReadOcena= new Uprawnienie("READ_OCENA");
        Uprawnienie uprawnienieAddOcena = new Uprawnienie("ADD_OCENA");
        Uprawnienie uprawnienieEditOcena = new Uprawnienie("EDIT_OCENA");
        Uprawnienie uprawnienieDeleteOcena = new Uprawnienie("DELETE_OCENA");

        Uprawnienie uprawnienieReadUzytkownik= new Uprawnienie("READ_UZYTKOWNIK");
        Uprawnienie uprawnienieAddUzytkownik = new Uprawnienie("ADD_UZYTKOWNIK");
        Uprawnienie uprawnienieEditUzytkownik = new Uprawnienie("EDIT_UZYTKOWNIK");
        Uprawnienie uprawnienieDeleteUzytkownik = new Uprawnienie("DELETE_UZYTKOWNIK");

        Uprawnienie uprawnienieReadDzienPracy= new Uprawnienie("READ_DZIEN_PRACY");
        Uprawnienie uprawnienieAddDzienPracy = new Uprawnienie("ADD_DZIEN_PRACY");
        Uprawnienie uprawnienieEditDzienPracy = new Uprawnienie("EDIT_DZIEN_PRACY");
        Uprawnienie uprawnienieDeleteDzienPracy = new Uprawnienie("DELETE_DZIEN_PRACY");

        Uprawnienie uprawnienieReadUprawnienie= new Uprawnienie("READ_UPRAWNIENIE");
        Uprawnienie uprawnienieAddUprawnienie = new Uprawnienie("ADD_UPRAWNIENIE");
        Uprawnienie uprawnienieEditUprawnienie = new Uprawnienie("EDIT_UPRAWNIENIE");
        Uprawnienie uprawnienieDeleteUprawnienie = new Uprawnienie("DELETE_UPRAWNIENIE");


        rolaHigienistka.getUprawnienia().add(uprawnienieReadLeki);
        rolaHigienistka.getUprawnienia().add(uprawnienieAddLeki);
        rolaHigienistka.getUprawnienia().add(uprawnienieEditLeki);
        rolaHigienistka.getUprawnienia().add(uprawnienieDeleteLeki);
        rolaHigienistka.getUprawnienia().add(uprawnienieReadDzienPracy);

        rolaAdmina.getUprawnienia().add(uprawnienieReadUzytkownik);
        rolaAdmina.getUprawnienia().add(uprawnienieAddUzytkownik);
        rolaAdmina.getUprawnienia().add(uprawnienieEditUzytkownik);
        rolaAdmina.getUprawnienia().add(uprawnienieDeleteUzytkownik);
        rolaAdmina.getUprawnienia().add(uprawnienieReadRole);
        rolaAdmina.getUprawnienia().add(uprawnienieAddRole);
        rolaAdmina.getUprawnienia().add(uprawnienieEditRole);
        rolaAdmina.getUprawnienia().add(uprawnienieDeleteRole);
        rolaAdmina.getUprawnienia().add(uprawnienieReadDzienPracy);
        rolaAdmina.getUprawnienia().add(uprawnienieAddDzienPracy);
        rolaAdmina.getUprawnienia().add(uprawnienieEditDzienPracy);
        rolaAdmina.getUprawnienia().add(uprawnienieDeleteDzienPracy);
        rolaAdmina.getUprawnienia().add(uprawnienieReadUprawnienie);
        rolaAdmina.getUprawnienia().add(uprawnienieAddUprawnienie);
        rolaAdmina.getUprawnienia().add(uprawnienieEditUprawnienie);
        rolaAdmina.getUprawnienia().add(uprawnienieDeleteUprawnienie);

        rolaDyrektora.getUprawnienia().add(uprawnienieReadDzienPracy);
        rolaDyrektora.getUprawnienia().add(uprawnienieAddDzienPracy);
        rolaDyrektora.getUprawnienia().add(uprawnienieEditDzienPracy);
        rolaDyrektora.getUprawnienia().add(uprawnienieDeleteDzienPracy);
        rolaDyrektora.getUprawnienia().add(uprawnienieReadKsiazki);
        rolaDyrektora.getUprawnienia().add(uprawnienieReadLeki);

        rolaNauczyciel.getUprawnienia().add(uprawnienieReadDzienPracy);
        rolaNauczyciel.getUprawnienia().add(uprawnienieReadOcena);
        rolaNauczyciel.getUprawnienia().add(uprawnienieAddOcena);
        rolaNauczyciel.getUprawnienia().add(uprawnienieEditOcena);
        rolaNauczyciel.getUprawnienia().add(uprawnienieDeleteOcena);

        rolaBibliotekarz.getUprawnienia().add(uprawnienieReadDzienPracy);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieReadKsiazki);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieAddKsiazki);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieEditKsiazki);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieDeleteKsiazki);

        rolaUczen.getUprawnienia().add(uprawnienieReadOcena);

        uprawnienieRepository.insert(uprawnienieReadLeki);
        uprawnienieRepository.insert(uprawnienieAddLeki);
        uprawnienieRepository.insert(uprawnienieEditLeki);
        uprawnienieRepository.insert(uprawnienieDeleteLeki);

        uprawnienieRepository.insert(uprawnienieReadKsiazki);
        uprawnienieRepository.insert(uprawnienieAddKsiazki);
        uprawnienieRepository.insert(uprawnienieEditKsiazki);
        uprawnienieRepository.insert(uprawnienieDeleteKsiazki);

        uprawnienieRepository.insert(uprawnienieReadRole);
        uprawnienieRepository.insert(uprawnienieAddRole);
        uprawnienieRepository.insert(uprawnienieEditRole);
        uprawnienieRepository.insert(uprawnienieDeleteRole);

        uprawnienieRepository.insert(uprawnienieReadOcena);
        uprawnienieRepository.insert(uprawnienieAddOcena);
        uprawnienieRepository.insert(uprawnienieEditOcena);
        uprawnienieRepository.insert(uprawnienieDeleteOcena);

        uprawnienieRepository.insert(uprawnienieReadUzytkownik);
        uprawnienieRepository.insert(uprawnienieAddUzytkownik);
        uprawnienieRepository.insert(uprawnienieEditUzytkownik);
        uprawnienieRepository.insert(uprawnienieDeleteUzytkownik);

        uprawnienieRepository.insert(uprawnienieReadDzienPracy);
        uprawnienieRepository.insert(uprawnienieAddDzienPracy);
        uprawnienieRepository.insert(uprawnienieEditDzienPracy);
        uprawnienieRepository.insert(uprawnienieDeleteDzienPracy);

        uprawnienieRepository.insert(uprawnienieReadUprawnienie);
        uprawnienieRepository.insert(uprawnienieAddUprawnienie);
        uprawnienieRepository.insert(uprawnienieEditUprawnienie);
        uprawnienieRepository.insert(uprawnienieDeleteUprawnienie);

        rolaRepository.insert(rolaAdmina);
        rolaRepository.insert(rolaHigienistka);
        rolaRepository.insert(rolaBibliotekarz);
        rolaRepository.insert(rolaUczen);
        rolaRepository.insert(rolaDyrektora);
        rolaRepository.insert(rolaNauczyciel);


    }


}
