package model.repository;

import model.entity.Rola;

import java.util.List;

/**
 * Created by mateu on 23.03.2017.
 */
public interface RolaRepository {
    void insert(Rola rola);

    void delete(int id);

    void deleteRoleFromUser(int idRoli, int idUzytkownika);

    Rola display(int id);

    Rola displayWithoutPermission(int id);

    int findIdUsingName(String nazwa);

    List displayAll();

    List displayWithUserName();

    List displayAllNamesAndId();
}
