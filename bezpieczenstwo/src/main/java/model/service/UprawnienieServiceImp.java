package model.service;

import model.entity.Uprawnienie;
import model.repository.UprawnienieRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mateu on 23.03.2017.
 */
public class UprawnienieServiceImp implements UprawnienieService {

    @Autowired
    UprawnienieRepository uprawnienieRepository;


    public void insert(Uprawnienie uprawnienie) {
        uprawnienieRepository.insert(uprawnienie);

    }

    public Uprawnienie display(int id) {
        return uprawnienieRepository.display(id);
    }
}
