package model.service;

import model.repository.RolaRepository;
import model.entity.Rola;
import org.springframework.beans.factory.annotation.Autowired;


public class RolaServiceImp implements RolaService {

    @Autowired
    private RolaRepository rolaRepository;

    public void insert(Rola rola) {
        rolaRepository.insert(rola);
    }

    public Rola display(int id) {
        return rolaRepository.display(id);
    }
}
