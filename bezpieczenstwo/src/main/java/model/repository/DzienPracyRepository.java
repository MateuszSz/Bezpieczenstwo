package model.repository;

import model.entity.DzienPracy;
import model.entity.Ksiazka;

import java.util.List;

/**
 * Created by Ada on 2017-04-08.
 */
public interface DzienPracyRepository {

    void insert(DzienPracy dzienPracy);

    void delete(int id);

    DzienPracy display(int id);

    List displayAllById(int id);

    List displayAll();




}
