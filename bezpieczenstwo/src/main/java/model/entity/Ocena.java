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
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ocena;
    private String przedmiot;
    @ManyToOne
    private Uzytkownik nauczyciel;
    @ManyToOne
    private Uzytkownik uczen;


    public Ocena(String ocena, String przedmiot, Uzytkownik nauczyciel, Uzytkownik uczen) {
        this.ocena = ocena;
        this.przedmiot = przedmiot;
        this.nauczyciel = nauczyciel;
        this.uczen = uczen;
    }
}
