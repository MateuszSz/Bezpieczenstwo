package model.repository;

import model.entity.Ksiazka;

/**
 * Created by Ada on 2017-03-31.
 */
public interface KsiazkaRepository {
    void insert(Ksiazka ksiazka);
    Ksiazka display(int id);



}
