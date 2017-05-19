package model.entity;

import javax.persistence.*;

/**
 * Created by mateu on 19.03.2017.
 */
@Entity
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

    public Ocena() {
    }

    public Ocena(String ocena, String przedmiot, Uzytkownik nauczyciel, Uzytkownik uczen) {
        this.ocena = ocena;
        this.przedmiot = przedmiot;
        this.nauczyciel = nauczyciel;
        this.uczen = uczen;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot = przedmiot;
    }

    public Uzytkownik getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Uzytkownik nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Uzytkownik getUczen() {
        return uczen;
    }

    public void setUczen(Uzytkownik uczen) {
        this.uczen = uczen;
    }
}
