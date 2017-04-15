package model.repository;

import model.entity.Ocena;

import java.util.List;

/**
 * Created by Ada on 2017-04-04.
 */
public interface OcenaRepository {

    void insert(Ocena ocena);

    //void delete(int id);

    Ocena display(int id);

    List displayAllByIdNauczyciela(int id);

    List displayAllByIdUcznia(int id);

    List displayAll();
}
