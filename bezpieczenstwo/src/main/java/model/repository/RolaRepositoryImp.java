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
        Hibernate.initialize(rola.getUzytkownicy());
        Hibernate.initialize(rola.getUprawnienia());
        return rola;
    }

    @Transactional
    public Rola displayWithoutPermission(int id) {
        Rola rola = (Rola) sessionFactory.getCurrentSession().get(Rola.class, id);
        Hibernate.initialize(rola.getUzytkownicy());
        return rola;
    }

    @Transactional
    public int findIdUsingName(String nazwa) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select rola.id from rola where rola.nazwa = \"" + nazwa + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }

    @Transactional
    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select rola.nazwa from rola");
        return sqlQuery.list();
    }

    @Transactional
    public List displayAllNamesAndId() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select rola.id,rola.nazwa from rola");
        return sqlQuery.list();
    }

    @Transactional
    public List displayWithUserName() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.imieINazwisko, rola.nazwa from uzytkownik, rola, uzytkownik_rola " +
                "where uzytkownik_rola.uzytkownicy_id = uzytkownik.id " +
                "and uzytkownik_rola.role_id = rola.id");
        List results = sqlQuery.list();
        return results;
    }

    @Transactional
    public void delete(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM uzytkownik_rola WHERE uzytkownik_rola.role_id=" + id);
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM rola_uprawnienie WHERE rola_id=" + id);
        SQLQuery sqlQuery3 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM rola WHERE rola.id=" + id);
        sqlQuery.executeUpdate();
        sqlQuery2.executeUpdate();
        sqlQuery3.executeUpdate();
    }

}
