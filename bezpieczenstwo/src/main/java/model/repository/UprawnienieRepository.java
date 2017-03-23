package model.repository;

import model.entity.Uprawnienie;

/**
 * Created by mateu on 23.03.2017.
 */
public interface UprawnienieRepository {
    void insert(Uprawnienie uprawnienie);

    Uprawnienie display(int id);
}
