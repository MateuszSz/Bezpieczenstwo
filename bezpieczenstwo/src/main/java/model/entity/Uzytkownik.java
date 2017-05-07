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
    @OneToMany(mappedBy = "uzytkownik", orphanRemoval=true)
    private Collection<Lek> leki = new HashSet<Lek>();
    @OneToMany(mappedBy = "uzytkownik", orphanRemoval=true)
    private Collection<Ksiazka> ksiazki = new HashSet<Ksiazka>();
    @OneToMany(mappedBy = "nauczyciel", orphanRemoval=true)
    private Collection<Ocena> ocenyNauczyciel = new HashSet<Ocena>();

    @OneToMany(mappedBy = "uczen", orphanRemoval=true)
    private Collection<Ocena> ocenyUczen = new HashSet<Ocena>();

    @OneToMany(mappedBy = "uzytkownik", orphanRemoval=true, cascade = CascadeType.ALL)
    private Collection<DzienPracy> dniPracy = new HashSet<DzienPracy>();

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

    public Collection<Ocena> getOcenyNauczyciel() {
        return ocenyNauczyciel;
    }

    public void setOcenyNauczyciel(Collection<Ocena> ocenyNauczyciel) {
        this.ocenyNauczyciel = ocenyNauczyciel;
    }

    public Collection<Ocena> getOcenyUczen() {
        return ocenyUczen;
    }

    public void setOcenyUczen(Collection<Ocena> ocenyUczen) {
        this.ocenyUczen = ocenyUczen;
    }

    public Collection<DzienPracy> getDniPracy() {
        return dniPracy;
    }

    public void setDniPracy(Collection<DzienPracy> dniPracy) {
        this.dniPracy = dniPracy;
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
