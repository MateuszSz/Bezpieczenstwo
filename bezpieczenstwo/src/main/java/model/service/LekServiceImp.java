package model.service;

import model.entity.Lek;
import model.repository.LekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mateu on 20.03.2017.
 */
@Service
public class LekServiceImp implements LekService {


    private LekRepository lekRepository;

    @Autowired
    public LekServiceImp(LekRepository lekRepository) {
        this.lekRepository = lekRepository;
    }

    public void delete(int id) {
        lekRepository.delete(id);
    }

    public void insert(Lek lek) {
        lekRepository.insert(lek);
    }

    public Lek display(int id) {
        return lekRepository.display(id);
    }

    public List displayAllByEmail(String email) {
        return lekRepository.displayAllByEmail(email);
    }

    public List displayAll() {
        return lekRepository.displayAll();
    }
}
