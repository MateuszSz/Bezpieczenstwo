package model.repository;

import model.entity.Ksiazka;

import java.util.List;

/**
 * Created by Ada on 2017-03-31.
 */
public interface KsiazkaRepository {

    void insert(Ksiazka ksiazka);

    void delete(int id);

    Ksiazka display(int id);

    List displayAllByEmail(String email);

    List displayAll();

}
