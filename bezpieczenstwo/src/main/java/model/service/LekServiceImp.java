package model.service;

import model.repository.LekRepository;
import model.entity.Lek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mateu on 20.03.2017.
 */
@Service
public class LekServiceImp implements LekService {

    @Autowired
    private LekRepository lekRepository;


    public void insert(Lek lek) {
        lekRepository.insert(lek);
    }

    public Lek display(int id) {
        return lekRepository.display(id);
    }

    public List displayAllByEmail(String email) {
        return lekRepository.displayAllByEmail(email);
    }
}
