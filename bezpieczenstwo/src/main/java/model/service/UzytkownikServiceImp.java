package model.service;

import model.entity.Uzytkownik;
import model.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mateu on 19.03.2017.
 */
@Service
public class UzytkownikServiceImp implements UzytkownikService {

    @Autowired
    private UzytkownikRepository uzytkownikRepository;


    public void insert(Uzytkownik uzytkownik) {
        uzytkownikRepository.insert(uzytkownik);
    }

    public Uzytkownik display(int id) {
        return uzytkownikRepository.display(id);
    }

    public boolean isLoginAndPasswodCorrect(String email, String haslo) {
        return uzytkownikRepository.isLoginAndPasswodCorrect(email, haslo);
    }

    public int findIdUsingEmail(String email) {
        return uzytkownikRepository.findIdUsingEmail(email);
    }

    public List displayAllNamesAndId() {
        return uzytkownikRepository.displayAllNamesAndId();
    }

    public List displayAllNamesAndIdByRole(String rola) {
        return uzytkownikRepository.displayAllNamesAndIdByRole(rola);
    }

    public void merge(Uzytkownik uzytkownik) {
        uzytkownikRepository.merge(uzytkownik);
    }

    public Uzytkownik displayWithMarks(int id) {
        return uzytkownikRepository.displayWithMarks(id);
    }

    public int findIdUsingName(String name) {
        return uzytkownikRepository.findIdUsingName(name);
    }
}
