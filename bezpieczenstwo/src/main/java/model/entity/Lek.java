package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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


    public Lek(String dawkowanie, String nazwaLeku, String ilosc) {
        this.dawkowanie = dawkowanie;
        this.nazwaLeku = nazwaLeku;
        this.ilosc = ilosc;

    }

    public Lek() {
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
