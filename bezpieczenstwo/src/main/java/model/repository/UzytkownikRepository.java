package model.repository;

import model.entity.Uzytkownik;

/**
 * Created by mateu on 19.03.2017.
 */
public interface UzytkownikRepository {
    void insert(Uzytkownik uzytkownik);

    Uzytkownik display(int id);

    boolean isLoginAndPasswodCorrect(String email, String haslo);


}
