package model.service;

import model.entity.Uprawnienie;
import model.repository.UprawnienieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mateu on 23.03.2017.
 */
public class UprawnienieServiceImp implements UprawnienieService {


    private UprawnienieRepository uprawnienieRepository;

    @Autowired
    public UprawnienieServiceImp(UprawnienieRepository uprawnienieRepository) {
        this.uprawnienieRepository = uprawnienieRepository;
    }

    public void insert(Uprawnienie uprawnienie) {
        uprawnienieRepository.insert(uprawnienie);

    }

    public List displayAllByRoleName(String nazwa) {
        return uprawnienieRepository.displayAllByRoleName(nazwa);
    }

    public Uprawnienie display(int id) {
        return uprawnienieRepository.display(id);
    }

    public Uprawnienie findByName(String name) {
        return uprawnienieRepository.findByName(name);
    }
}
