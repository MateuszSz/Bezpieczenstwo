package model.repository;

import model.entity.Ocena;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ada on 2017-04-04.
 */
@Repository
@Transactional
public class OcenaRepositoryImp implements OcenaRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void insert(Ocena ocena) {
        sessionFactory.getCurrentSession().saveOrUpdate(ocena);
    }

    public Ocena display(int id) {
        return (Ocena) sessionFactory.getCurrentSession().get(Ocena.class, id);

    }

    public List displayAllByIdNauczyciela(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery
                ("Select o.id, o.przedmiot, o.ocena, u.imieINazwisko from ocena o, uzytkownik u WHERE o.nauczyciel_id=" + id + " and u.id=o.uczen_id");
        List results = sqlQuery.list();
        return results;

    }

    public List displayAllByIdUcznia(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery
                ("Select o.przedmiot, o.ocena, u.imieINazwisko from ocena o, uzytkownik u WHERE o.uczen_id=" + id + " and u.id=o.nauczyciel_id");
        List results = sqlQuery.list();
        return results;

    }

    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery
                ("Select ocena.przedmiot, ocena.ocena, ocena.nauczyciel_id.imieINazwisko, ocena.uczen_id.imieINazwisko from ocena");
        List results = sqlQuery.list();
        return results;


    }


}
