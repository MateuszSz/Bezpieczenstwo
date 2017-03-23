package model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mateu on 20.03.2017.
 */
@Entity
public class Rola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nazwa;
    @ManyToMany(mappedBy = "role")
    private Collection<Uzytkownik> uzytkownicy = new HashSet<Uzytkownik>();
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Uprawnienie> uprawnienia = new HashSet<Uprawnienie>();


    public Rola() {
    }

    public Rola(String nazwa) {
        this.nazwa = nazwa;
    }

    public Collection<Uprawnienie> getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(Collection<Uprawnienie> uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public Collection<Uzytkownik> getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(Collection<Uzytkownik> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
