package model.service;

import model.entity.DzienPracy;
import model.repository.DzienPracyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ada on 2017-04-08.
 */
@Service
public class DzienPracyServiceImp implements DzienPracyService {


    private DzienPracyRepository dzienPracyRepository;

    @Autowired
    public DzienPracyServiceImp(DzienPracyRepository dzienPracyRepository) {
        this.dzienPracyRepository = dzienPracyRepository;
    }

    public void delete(int id) {
        dzienPracyRepository.delete(id);
    }

    public void insert(DzienPracy dzienPracy) {
        dzienPracyRepository.insert(dzienPracy);
    }

    public DzienPracy display(int id) {
        return dzienPracyRepository.display(id);
    }

    public List displayAllById(int id) {
        return dzienPracyRepository.displayAllById(id);
    }

    public List displayAll() {
        return dzienPracyRepository.displayAll();
    }


}
