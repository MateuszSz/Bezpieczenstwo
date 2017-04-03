package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
/**
 * Created by Ada on 2017-04-03.
 */
@Entity
public class DzienPracy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dzienTygodnia;
    private SimpleDateFormat godzinaRozpoczecia = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat godzinaZakonczenia = new SimpleDateFormat("HH:mm");

    public DzienPracy(){}

    public DzienPracy(String dzienTygodnia, SimpleDateFormat godzinaRozpoczecia, SimpleDateFormat godzinaZakonczenia ){

        this.dzienTygodnia=dzienTygodnia;
        this.godzinaRozpoczecia=godzinaRozpoczecia;
        this.godzinaZakonczenia=godzinaZakonczenia;

    }

    public String getDzienTygodnia(){return this.dzienTygodnia; }

    public void setDzienTygodnia(String dzienTygodnia){this.dzienTygodnia = dzienTygodnia; }

    public SimpleDateFormat getGodzinaRozpoczecia(){return  this.godzinaRozpoczecia;}

    public void setGodzinaRozpoczecia(SimpleDateFormat godzinaRozpoczecia){this.godzinaRozpoczecia = godzinaRozpoczecia; }

    public SimpleDateFormat getGodzinaZakonczenia(){return  this.godzinaZakonczenia;}

    public void setGodzinaZakonczenia(SimpleDateFormat godzinaZakonczenia){this.godzinaZakonczenia = godzinaZakonczenia; }
}
