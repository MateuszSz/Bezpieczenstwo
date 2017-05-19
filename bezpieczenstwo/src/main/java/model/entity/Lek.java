package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by mateu on 19.03.2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
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

}
