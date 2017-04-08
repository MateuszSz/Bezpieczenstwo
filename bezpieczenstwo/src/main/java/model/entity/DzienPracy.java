package model.entity;

import javax.persistence.*;


/**
 * Created by Ada on 2017-04-03.
 */
@Entity
public class DzienPracy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dzienTygodnia;
    private String godzinaRozpoczecia;
    private String godzinaZakonczenia;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Uzytkownik uzytkownik;

    public DzienPracy() {
    }

    public DzienPracy(String dzienTygodnia, String godzinaRozpoczecia, String godzinaZakonczenia, Uzytkownik uzytkownik) {

        this.dzienTygodnia = dzienTygodnia;
        this.godzinaRozpoczecia = godzinaRozpoczecia;
        this.godzinaZakonczenia = godzinaZakonczenia;
        this.uzytkownik = uzytkownik;

    }

    public String getDzienTygodnia() {
        return this.dzienTygodnia;
    }

    public void setDzienTygodnia(String dzienTygodnia) {
        this.dzienTygodnia = dzienTygodnia;
    }

    public String getGodzinaRozpoczecia() {
        return this.godzinaRozpoczecia;
    }

    public void setGodzinaRozpoczecia(String godzinaRozpoczecia) {
        this.godzinaRozpoczecia = godzinaRozpoczecia;
    }

    public String getGodzinaZakonczenia() {
        return this.godzinaZakonczenia;
    }

    public void setGodzinaZakonczenia(String godzinaZakonczenia) {
        this.godzinaZakonczenia = godzinaZakonczenia;
    }

    public Uzytkownik getUzytkownik(){return this.uzytkownik;}

    public void setUzytkownik(Uzytkownik uzytkownik){this.uzytkownik= uzytkownik;}
}
