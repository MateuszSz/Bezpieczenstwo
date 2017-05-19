package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mateu on 20.03.2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Uprawnienie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nazwa;

    public Uprawnienie(String nazwa) {
        this.nazwa = nazwa;
    }


}
