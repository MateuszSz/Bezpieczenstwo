package model.repository;

import model.entity.Lek;
import org.hibernate.Criteria;
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

    //tworzy się automatycznie bez konstruktora
    @Autowired
    private SessionFactory sessionFactory;


    public void insert(Lek lek) {
        sessionFactory.getCurrentSession().saveOrUpdate(lek);
    }

    public Lek display(int id) {
        return (Lek) sessionFactory.getCurrentSession().get(Lek.class, id);
    }

    public List displayAllByEmail(String email) {

        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select lek.id, lek.nazwaLeku, lek.dawkowanie, lek.ilosc from lek, uzytkownik_lek, uzytkownik\n" +
                "where lek.id=uzytkownik_lek.leki_id and uzytkownik_lek.Uzytkownik_id = uzytkownik.id and uzytkownik.email = \"" + email + "\"");
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List results = sqlQuery.list();
        return results;
    }

    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select lek.id, lek.nazwaLeku, lek.dawkowanie, lek.ilosc from lek");
        List results = sqlQuery.list();
        return results;
    }

}
