package model.repository;

import model.entity.Rola;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        Rola rola = (Rola) sessionFactory.getCurrentSession().get(Rola.class, id);
        Hibernate.initialize(rola.getUprawnienia());
        return rola;
    }


    @Transactional
    public int findIdUsingName(String nazwa) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select rola.id from rola where rola.nazwa = \"" + nazwa + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }
}
