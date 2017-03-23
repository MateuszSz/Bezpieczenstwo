package model.repository;

import model.entity.Rola;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RolaRepositoryImp implements RolaRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insert(Rola rola) {
        sessionFactory.getCurrentSession().saveOrUpdate(rola);
    }

    @Transactional
    public Rola display(int id) {
        return (Rola) sessionFactory.getCurrentSession().get(Rola.class, id);
    }
}
