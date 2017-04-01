package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mateu on 20.03.2017.
 */
@Entity
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String autor;
    private String ISBN;
    private String seria;
    private String tytul;
    private String dostepnosc;

    public Ksiazka() {
    }

    public Ksiazka(String autor, String ISBN, String seria, String tytul, String dostepnosc) {
        this.autor = autor;
        this.ISBN = ISBN;
        this.seria = seria;
        this.tytul = tytul;
        this.dostepnosc = dostepnosc;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(String dostepnosc) {
        this.dostepnosc = dostepnosc;
    }
}
