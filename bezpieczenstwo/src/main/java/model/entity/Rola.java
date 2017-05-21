package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mateu on 20.03.2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nazwa;
    @ManyToMany(mappedBy = "role")
    private Collection<Uzytkownik> uzytkownicy = new HashSet<Uzytkownik>();
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Uprawnienie> uprawnienia = new HashSet<Uprawnienie>();

    @ManyToMany
    private Collection<Rola> seperacjaRol = new HashSet<Rola>();


    public Rola(String nazwa) {
        this.nazwa = nazwa;
    }

}
