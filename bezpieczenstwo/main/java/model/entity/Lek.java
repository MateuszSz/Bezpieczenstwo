package model.entity;

import javax.persistence.*;

/**
 * Created by mateu on 19.03.2017.
 */
@Entity
public class Lek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dawkowanie;
    private String nazwaLeku;
    private String ilosc;
    @ManyToOne
    private Uzytkownik uzytkownik;

    public Lek(String dawkowanie, String nazwaLeku, String ilosc, Uzytkownik uzytkownik) {
        this.dawkowanie = dawkowanie;
        this.nazwaLeku = nazwaLeku;
        this.ilosc = ilosc;
        this.uzytkownik = uzytkownik;

    }

    public Lek(String dawkowanie, String nazwaLeku, String ilosc) {
        this.dawkowanie = dawkowanie;
        this.nazwaLeku = nazwaLeku;
        this.ilosc = ilosc;
    }

    public Lek() {
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDawkowanie() {
        return dawkowanie;
    }

    public void setDawkowanie(String dawkowanie) {
        this.dawkowanie = dawkowanie;
    }


    public String getNazwaLeku() {
        return nazwaLeku;
    }

    public void setNazwaLeku(String nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }


    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

}
