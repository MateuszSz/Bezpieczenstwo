package model.repository;

import model.entity.Uprawnienie;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class UprawnienieRepositoryImp implements UprawnienieRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public UprawnienieRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void insert(Uprawnienie uprawnienie) {
        sessionFactory.getCurrentSession().saveOrUpdate(uprawnienie);
    }

    @Transactional
    public Uprawnienie display(int id) {
        return (Uprawnienie) sessionFactory.getCurrentSession().get(Uprawnienie.class, id);
    }

    @Transactional
    public Uprawnienie findByName(String name) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT uprawnienie.id FROM uprawnienie WHERE uprawnienie.nazwa = \"" + name + "\"");
        List results = sqlQuery.list();
        return display((Integer) results.get(0));
    }

    @Transactional
    public List displayAllByRoleName(String nazwa) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT uprawnienie.nazwa FROM uprawnienie, rola_uprawnienie, rola WHERE rola_uprawnienie.Rola_id = rola.id AND rola_uprawnienie.uprawnienia_id = uprawnienie.id AND rola.nazwa = '" + nazwa + "'");
        return sqlQuery.list();
    }
}
