package model.repository;

import model.entity.Rola;

import java.util.List;

/**
 * Created by mateu on 23.03.2017.
 */
public interface RolaRepository {
    void insert(Rola rola);

    Rola display(int id);

    int findIdUsingName(String nazwa);

    public List displayAll();
}
