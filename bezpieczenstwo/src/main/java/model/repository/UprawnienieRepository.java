package model.repository;

import model.entity.Uprawnienie;

import java.util.List;

/**
 * Created by mateu on 23.03.2017.
 */
public interface UprawnienieRepository {
    void insert(Uprawnienie uprawnienie);

    List displayAllByRoleName(String nazwa);

    Uprawnienie display(int id);
}
