package model.service;

import model.repository.UzytkownikRepository;
import model.entity.Uzytkownik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int findIdUsingEmail(String email){
        return uzytkownikRepository.findIdUsingEmail(email);
    }
}
