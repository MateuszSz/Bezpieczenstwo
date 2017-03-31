package model.service;

import model.entity.Rola;
import model.repository.RolaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class RolaServiceImp implements RolaService {

    @Autowired
    private RolaRepository rolaRepository;

    public void insert(Rola rola) {
        rolaRepository.insert(rola);
    }

    public Rola display(int id) {
        return rolaRepository.display(id);
    }

    public int findIdUsingName(String nazwa) {
        return rolaRepository.findIdUsingName(nazwa);
    }

    public List displayAll() {
        return rolaRepository.displayAll();
    }

}
