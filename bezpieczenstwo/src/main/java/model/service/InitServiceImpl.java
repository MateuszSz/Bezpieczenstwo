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
    private OcenaRepository ocenaRepository;

    @Autowired
    private UprawnienieRepository uprawnienieRepository;


    @Autowired
    private DzienPracyRepository dzienPracyRepository;


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
        Rola rolaNauczyciel = new Rola("NAUCZYCIEL");
        rolaRepository.insert(rolaNauczyciel);
        Rola rolaUczen = new Rola("UCZEN");
        rolaRepository.insert(rolaUczen);
        Rola rolaBibliotekarz = new Rola("BIBLIOTEKARZ");
        rolaRepository.insert(rolaBibliotekarz);

//  TWORZENIE URZYTKOWNIKOW I POLACZENIE ICH Z ROLAMI
        Uzytkownik uzytkownik = new Uzytkownik("ada@wp.pl", passwordEncoder.encode("mati"), "Ada Rynkowska");
        Uzytkownik uzytkownik2 = new Uzytkownik("mati@wp.pl", passwordEncoder.encode("ada"), "Mateusz Szymczak");
        Uzytkownik uzytkownik3 = new Uzytkownik("ania@wp.pl", passwordEncoder.encode("haslo"), "Ania Lisiecka");
        Uzytkownik uzytkownik4 = new Uzytkownik("wolak@wp.pl", passwordEncoder.encode("haslo"), "Michal Wolak");

        uzytkownik.getRole().add(rolaAdmina);
        uzytkownik.getRole().add(rolaDyrektora);
        uzytkownik.getRole().add(rolaUczen);
        uzytkownik.getRole().add(rolaBibliotekarz);
        uzytkownik.getRole().add(rolaNauczyciel);
        uzytkownik2.getRole().add(rolaBibliotekarz);
        uzytkownik2.getRole().add(rolaNauczyciel);
        uzytkownik2.getRole().add(rolaAdmina);
        uzytkownik2.getRole().add(rolaHigienistka);
        uzytkownik3.getRole().add(rolaUczen);
        uzytkownik4.getRole().add(rolaUczen);
        uzytkownikRepository.insert(uzytkownik);
        uzytkownikRepository.insert(uzytkownik2);
        uzytkownikRepository.insert(uzytkownik3);
        uzytkownikRepository.insert(uzytkownik4);

//  TWORZENIE LEKOW
        Lek lek = new Lek("Dwa razy dziennie", "Rutinoskorbin", "3",uzytkownik2);
        List<Lek> lista = new ArrayList<Lek>();
        lista.add(new Lek("Dwa razy dziennie, dwie tabletki po posiłku", "Ibuprom", "20", uzytkownik2));
        lista.add(new Lek("Raz dziennie", "Trybiotyk", "3", uzytkownik2));
        lista.add(new Lek("Trzy razy dziennie", "Stoperan", "30", uzytkownik2));

        for (Lek l : lista) {
            lekRepository.insert(l);
            uzytkownik2.getLeki().add(l);
        }

        lekRepository.insert(lek);
        uzytkownik2.getLeki().add(lek);
        uzytkownikRepository.insert(uzytkownik2);

        //TWORZENIE KSIAZEK

        List<Ksiazka> ksiazki = new ArrayList<Ksiazka>();
        ksiazki.add(new Ksiazka("Brandon Sanderson", "987-83-7480-670-1", "Siewca Wojny ", "Rozjemca", "dostepna", uzytkownik2));

        for (Ksiazka k : ksiazki) {
            ksiazkaRepository.insert(k);
            uzytkownik.getKsiazki().add(k);
        }
        uzytkownikRepository.insert(uzytkownik);

        //Tworzenie ocen

        List<Ocena> oceny = new ArrayList<Ocena>();
        oceny.add(new Ocena("4", "Przyroda", uzytkownik2, uzytkownik3));
        oceny.add(new Ocena("3", "Przyroda", uzytkownik2, uzytkownik4));
        oceny.add(new Ocena("3", "Wf", uzytkownik2, uzytkownik3));
        oceny.add(new Ocena("6", "Wf", uzytkownik2, uzytkownik4));
        oceny.add(new Ocena("6", "Matematyka", uzytkownik2, uzytkownik3));
        oceny.add(new Ocena("3", "Matematyka", uzytkownik2, uzytkownik4));
        oceny.add(new Ocena("6", "J.angielski", uzytkownik2, uzytkownik3));
        oceny.add(new Ocena("4", "J.angielski", uzytkownik2, uzytkownik4));

        for (Ocena o : oceny) {
            ocenaRepository.insert(o);
            uzytkownik2.getOcenyNauczyciel().add(o);
        }

        //Tworzenie dni pracy

        List<DzienPracy> dniPracy = new ArrayList<DzienPracy>();
        dniPracy.add(new DzienPracy("Poniedzialek", "08:00", "10:00", uzytkownik));
        dniPracy.add(new DzienPracy("Wtorek", "09:00", "15:00", uzytkownik));
        dniPracy.add(new DzienPracy("Sroda", "08:00", "13:00", uzytkownik));
        dniPracy.add(new DzienPracy("Czwartek", "11:00", "18:00", uzytkownik));
        dniPracy.add(new DzienPracy("Piatek", "08:00", "12:00", uzytkownik));

        for (DzienPracy d : dniPracy) {
            dzienPracyRepository.insert(d);
            uzytkownik.getDniPracy().add(d);
        }

        List<DzienPracy> dniPracy2 = new ArrayList<DzienPracy>();
        dniPracy2.add(new DzienPracy("Poniedzialek", "07:00", "15:00", uzytkownik2));
        dniPracy2.add(new DzienPracy("Wtorek", "11:00", "15:00", uzytkownik2));
        dniPracy2.add(new DzienPracy("Sroda", "09:00", "12:00", uzytkownik2));
        dniPracy2.add(new DzienPracy("Czwartek", "10:00", "16:00", uzytkownik2));
        dniPracy2.add(new DzienPracy("Piatek", "08:00", "15:00", uzytkownik2));

        for (DzienPracy d : dniPracy2) {
            dzienPracyRepository.insert(d);
            uzytkownik2.getDniPracy().add(d);
        }


//  TWORZENIE UPRAWNIEN I POWIAZANIE ICH Z ROLAMI
        Uprawnienie uprawnienieReadLeki = new Uprawnienie("READ_LEKI");
        Uprawnienie uprawnienieAddLeki = new Uprawnienie("ADD_LEKI");
        Uprawnienie uprawnienieEditLeki = new Uprawnienie("EDIT_LEKI");
        Uprawnienie uprawnienieDeleteLeki = new Uprawnienie("DELETE_LEKI");

        Uprawnienie uprawnienieReadKsiazki = new Uprawnienie("READ_KSIAZKI");
        Uprawnienie uprawnienieAddKsiazki = new Uprawnienie("ADD_KSIAZKI");
        Uprawnienie uprawnienieEditKsiazki = new Uprawnienie("EDIT_KSIAZKI");
        Uprawnienie uprawnienieDeleteKsiazki = new Uprawnienie("DELETE_KSIAZKI");

        Uprawnienie uprawnienieReadRole = new Uprawnienie("READ_ROLE");
        Uprawnienie uprawnienieAddRole = new Uprawnienie("ADD_ROLE");
        Uprawnienie uprawnienieEditRole = new Uprawnienie("EDIT_ROLE");
        Uprawnienie uprawnienieDeleteRole = new Uprawnienie("DELETE_ROLE");

        Uprawnienie uprawnienieReadWystawionaOcena = new Uprawnienie("READ_WYSTAWIONEOCENY");
        Uprawnienie uprawnienieAddWystawionaOcena = new Uprawnienie("ADD_WYSTAWIONEOCENY");
        Uprawnienie uprawnienieEditWystawionaOcena = new Uprawnienie("EDIT_WYSTAWIONEOCENY");
        Uprawnienie uprawnienieDeleteWystawionaOcena = new Uprawnienie("DELETE_WYSTAWIONEOCENY");

        Uprawnienie uprawnienieReadMojaOcena = new Uprawnienie("READ_MOJEOCENY");
        Uprawnienie uprawnienieAddMojaOcena = new Uprawnienie("ADD_MOJEOCENY");
        Uprawnienie uprawnienieEditMojaOcena = new Uprawnienie("EDIT_MOJEOCENY");
        Uprawnienie uprawnienieDeleteMojaOcena = new Uprawnienie("DELETE_MOJEOCENY");

        Uprawnienie uprawnienieReadUzytkownik = new Uprawnienie("READ_UZYTKOWNICY");
        Uprawnienie uprawnienieAddUzytkownik = new Uprawnienie("ADD_UZYTKOWNICY");
        Uprawnienie uprawnienieEditUzytkownik = new Uprawnienie("EDIT_UZYTKOWNICY");
        Uprawnienie uprawnienieDeleteUzytkownik = new Uprawnienie("DELETE_UZYTKOWNICY");

        Uprawnienie uprawnienieReadDzienPracy = new Uprawnienie("READ_DNIPRACY");
        Uprawnienie uprawnienieAddDzienPracy = new Uprawnienie("ADD_DNIPRACY");
        Uprawnienie uprawnienieEditDzienPracy = new Uprawnienie("EDIT_DNIPRACY");
        Uprawnienie uprawnienieDeleteDzienPracy = new Uprawnienie("DELETE_DNIPRACY");

        Uprawnienie uprawnienieReadUprawnienie = new Uprawnienie("READ_UPRAWNIENIA");
        Uprawnienie uprawnienieAddUprawnienie = new Uprawnienie("ADD_UPRAWNIENIA");
        Uprawnienie uprawnienieEditUprawnienie = new Uprawnienie("EDIT_UPRAWNIENIA");
        Uprawnienie uprawnienieDeleteUprawnienie = new Uprawnienie("DELETE_UPRAWNIENIA");


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
        rolaNauczyciel.getUprawnienia().add(uprawnienieReadWystawionaOcena);
        rolaNauczyciel.getUprawnienia().add(uprawnienieAddWystawionaOcena);
        rolaNauczyciel.getUprawnienia().add(uprawnienieEditWystawionaOcena);
        rolaNauczyciel.getUprawnienia().add(uprawnienieDeleteWystawionaOcena);

        rolaBibliotekarz.getUprawnienia().add(uprawnienieReadDzienPracy);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieReadKsiazki);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieAddKsiazki);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieEditKsiazki);
        rolaBibliotekarz.getUprawnienia().add(uprawnienieDeleteKsiazki);

        rolaUczen.getUprawnienia().add(uprawnienieReadMojaOcena);

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

        uprawnienieRepository.insert(uprawnienieReadWystawionaOcena);
        uprawnienieRepository.insert(uprawnienieAddWystawionaOcena);
        uprawnienieRepository.insert(uprawnienieEditWystawionaOcena);
        uprawnienieRepository.insert(uprawnienieDeleteWystawionaOcena);

        uprawnienieRepository.insert(uprawnienieReadMojaOcena);
        uprawnienieRepository.insert(uprawnienieAddMojaOcena);
        uprawnienieRepository.insert(uprawnienieEditMojaOcena);
        uprawnienieRepository.insert(uprawnienieDeleteMojaOcena);

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
