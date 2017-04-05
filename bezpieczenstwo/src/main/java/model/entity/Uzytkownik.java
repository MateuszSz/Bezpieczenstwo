package model.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mateu on 19.03.2017.
 */
@Entity
@Transactional
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String haslo;
    private String imieINazwisko;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Lek> leki = new HashSet<Lek>();
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Ksiazka> ksiazki = new HashSet<Ksiazka>();
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Ocena> oceny = new HashSet<Ocena>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Rola> role = new HashSet<Rola>();

    public Uzytkownik(String email, String haslo, String imieINazwisko) {
        this.email = email;
        this.haslo = haslo;
        this.imieINazwisko = imieINazwisko;
    }

    public Uzytkownik() {
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Collection<Rola> getRole() {
        return role;
    }

    public void setRole(Collection<Rola> role) {
        this.role = role;
    }

    public Collection<Ksiazka> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(Collection<Ksiazka> ksiazki) {
        this.ksiazki = ksiazki;
    }

    public Collection<Ocena> getOceny() {
        return oceny;
    }

    public void setOceny(Collection<Ocena> oceny) {
        this.oceny = oceny;
    }

    public Collection<Lek> getLeki() {
        return leki;
    }

    public void setLeki(Collection<Lek> leki) {
        this.leki = leki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }


    public String getImieINazwisko() {
        return imieINazwisko;
    }

    public void setImieINazwisko(String imieINazwisko) {
        this.imieINazwisko = imieINazwisko;
    }


}
