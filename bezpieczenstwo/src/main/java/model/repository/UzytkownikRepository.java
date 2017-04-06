package model.repository;

import model.entity.Uzytkownik;

import java.util.List;

/**
 * Created by mateu on 19.03.2017.
 */
public interface UzytkownikRepository {
    void insert(Uzytkownik uzytkownik);

    Uzytkownik display(int id);

    boolean isLoginAndPasswodCorrect(String email, String haslo);

    int findIdUsingEmail(String email);

    List displayAllNamesAndId();

    List displayAllNamesAndIdByRole(String rola);

    void merge(Uzytkownik uzytkownik);

}
