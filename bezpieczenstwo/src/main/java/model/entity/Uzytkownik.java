package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mateu on 19.03.2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
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


}
