package model.repository;

import model.entity.Lek;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mateu on 19.03.2017.
 */
@Repository
@Transactional
public class LekRepositoryImp implements LekRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public LekRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Lek lek) {
        sessionFactory.getCurrentSession().saveOrUpdate(lek);
    }

    public void delete(int id) {
        
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM lek WHERE lek.id=" + id);
        sqlQuery2.executeUpdate();

    }


    public Lek display(int id) {
        return (Lek) sessionFactory.getCurrentSession().get(Lek.class, id);
    }

    public List displayAllByEmail(String email) {

        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select lek.id, lek.nazwaLeku, lek.dawkowanie, lek.ilosc from lek, uzytkownik_lek, uzytkownik\n" +
                "where lek.id=uzytkownik_lek.leki_id and uzytkownik_lek.Uzytkownik_id = uzytkownik.id and uzytkownik.email = \"" + email + "\"");
        List results = sqlQuery.list();
        return results;
    }

    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select lek.id, lek.nazwaLeku, lek.dawkowanie, lek.ilosc from lek");
        return sqlQuery.list();
    }

}
