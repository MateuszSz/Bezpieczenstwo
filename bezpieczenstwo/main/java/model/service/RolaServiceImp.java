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

    public void delete(int id) {
        rolaRepository.delete(id);
    }

    public Rola display(int id) {
        return rolaRepository.display(id);
    }

    public Rola displayWithoutPermission(int id) {
        return rolaRepository.displayWithoutPermission(id);
    }

    public int findIdUsingName(String nazwa) {
        return rolaRepository.findIdUsingName(nazwa);
    }

    public List displayAll() {
        return rolaRepository.displayAll();
    }

    public List displayWithUserName() {
        return rolaRepository.displayWithUserName();
    }

    public List displayAllNamesAndId() {
        return rolaRepository.displayAllNamesAndId();
    }

}
