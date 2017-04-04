package model.service;

import model.entity.Ocena;
import model.repository.OcenaRepository;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ada on 2017-04-04.
 */
@Service
public class OcenaServiceImp implements OcenaService {
    @Autowired
    private OcenaRepository ocenaRepository;

    public void insert(Ocena ocena){
        ocenaRepository.insert(ocena);
    }

    public Ocena display(int id){
       return ocenaRepository.display(id);

    }

    public List displayAllByIdNauczyciela(int id){
        return ocenaRepository.displayAllByIdNauczyciela(id);

    }

    public List displayAllByIdUcznia(int id){
        return ocenaRepository.displayAllByIdUcznia(id);
    }

    public List displayAll(){
       return ocenaRepository.displayAll();

    }

}
