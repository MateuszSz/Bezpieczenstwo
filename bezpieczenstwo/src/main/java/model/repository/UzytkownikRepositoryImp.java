package model.repository;

import model.entity.Uzytkownik;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mateu on 19.03.2017.
 */
public class UzytkownikRepositoryImp implements UzytkownikRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insert(Uzytkownik uzytkownik) {
        sessionFactory.getCurrentSession().saveOrUpdate(uzytkownik);
    }

    @Transactional
    public Uzytkownik display(int id) {
        return (Uzytkownik) sessionFactory.getCurrentSession().get(Uzytkownik.class, id);

    }

    @Transactional
    public boolean isLoginAndPasswodCorrect(String email, String haslo) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uzytkownik.class);
        Uzytkownik uzytkownik = (Uzytkownik) criteria.add(Restrictions.eq("haslo", haslo)).uniqueResult();
        if (uzytkownik.getEmail().equals(email)) return true;
        else return false;
    }

    //Wydobywanie ID użytkownika po jego Emailu.
    //Dane zapisywane są do listy, a potem odczytywana zawartość pod 0 - czyli znaleziony id.
    @Transactional
    public int findIdUsingEmail(String email) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id from uzytkownik where uzytkownik.email = \"" + email + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }

}
