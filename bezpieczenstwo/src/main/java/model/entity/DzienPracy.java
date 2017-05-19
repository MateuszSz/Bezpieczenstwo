package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.REMOVE;


/**
 * Created by Ada on 2017-04-03.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DzienPracy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dzienTygodnia;
    private String godzinaRozpoczecia;
    private String godzinaZakonczenia;
    @ManyToOne
    private Uzytkownik uzytkownik;

    public DzienPracy(String dzienTygodnia, String godzinaRozpoczecia, String godzinaZakonczenia, Uzytkownik uzytkownik) {

        this.dzienTygodnia = dzienTygodnia;
        this.godzinaRozpoczecia = godzinaRozpoczecia;
        this.godzinaZakonczenia = godzinaZakonczenia;
        this.uzytkownik = uzytkownik;

    }
}
