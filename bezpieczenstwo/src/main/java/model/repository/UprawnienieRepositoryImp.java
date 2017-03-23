package model.repository;

import model.entity.Uprawnienie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UprawnienieRepositoryImp implements UprawnienieRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insert(Uprawnienie uprawnienie) {
        sessionFactory.getCurrentSession().saveOrUpdate(uprawnienie);
    }

    @Transactional
    public Uprawnienie display(int id) {
        return (Uprawnienie) sessionFactory.getCurrentSession().get(Uprawnienie.class, id);
    }

}
