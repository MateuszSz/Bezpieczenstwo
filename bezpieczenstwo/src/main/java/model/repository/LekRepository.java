package model.repository;

import model.entity.Lek;

import java.util.List;

/**
 * Created by mateu on 19.03.2017.
 */
public interface LekRepository {

    void insert(Lek lek);

    void delete(int id);

    Lek display(int id);

    List displayAllByEmail(String email);

    List displayAll();
}
