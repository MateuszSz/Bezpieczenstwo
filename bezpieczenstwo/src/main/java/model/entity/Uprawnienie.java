package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mateu on 20.03.2017.
 */
@Entity
public class Uprawnienie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String uprawnienie;

    public Uprawnienie() {
    }

    public Uprawnienie(String uprawnienie) {
        this.uprawnienie = uprawnienie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUprawnienie() {
        return uprawnienie;
    }

    public void setUprawnienie(String uprawnienie) {
        this.uprawnienie = uprawnienie;
    }
}
