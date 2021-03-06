package model.service;

import model.entity.Ocena;
import model.repository.OcenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ada on 2017-04-04.
 */
@Service
public class OcenaServiceImp implements OcenaService {

    private OcenaRepository ocenaRepository;

    @Autowired
    public OcenaServiceImp(OcenaRepository ocenaRepository) {
        this.ocenaRepository = ocenaRepository;
    }

    public void insert(Ocena ocena) {
        ocenaRepository.insert(ocena);
    }

    public void delete(int id) {
        ocenaRepository.delete(id);
    }

    public Ocena display(int id) {
        return ocenaRepository.display(id);

    }

    public List displayAllByIdNauczyciela(int id) {
        return ocenaRepository.displayAllByIdNauczyciela(id);

    }

    public List displayAllByIdUcznia(int id) {
        return ocenaRepository.displayAllByIdUcznia(id);
    }

    public List displayAll() {
        return ocenaRepository.displayAll();

    }

}
