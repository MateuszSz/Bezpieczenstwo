package model.service;

import model.entity.Ksiazka;
import model.repository.KsiazkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ada on 2017-03-31.
 */
@Service
public class KsiazkaServiceImp implements KsiazkaService {


    private KsiazkaRepository ksiazkaRepository;

    @Autowired
    public KsiazkaServiceImp(KsiazkaRepository ksiazkaRepository) {
        this.ksiazkaRepository = ksiazkaRepository;
    }

    public void insert(Ksiazka ksiazka) {
        ksiazkaRepository.insert(ksiazka);

    }

    public void delete(int id) {
        ksiazkaRepository.delete(id);

    }

    public Ksiazka display(int id) {
        return ksiazkaRepository.display(id);
    }

    public List displayAllByEmail(String email) {
        return ksiazkaRepository.displayAllByEmail(email);
    }

    public List displayAll() {
        return ksiazkaRepository.displayAll();
    }

}
