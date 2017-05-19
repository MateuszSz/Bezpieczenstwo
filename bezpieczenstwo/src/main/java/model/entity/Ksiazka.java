package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by mateu on 20.03.2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String autor;
    private String ISBN;
    private String seria;
    private String tytul;
    private String dostepnosc;
    @ManyToOne
    private Uzytkownik uzytkownik;

    public Ksiazka(String autor, String ISBN, String seria, String tytul, String dostepnosc) {
        this.autor = autor;
        this.ISBN = ISBN;
        this.seria = seria;
        this.tytul = tytul;
        this.dostepnosc = dostepnosc;
    }

    public Ksiazka(String autor, String ISBN, String seria, String tytul, String dostepnosc, Uzytkownik uzytkownik) {
        this.autor = autor;
        this.ISBN = ISBN;
        this.seria = seria;
        this.tytul = tytul;
        this.dostepnosc = dostepnosc;
        this.uzytkownik = uzytkownik;
    }

}
