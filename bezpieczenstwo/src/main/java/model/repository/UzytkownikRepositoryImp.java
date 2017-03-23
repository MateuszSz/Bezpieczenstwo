package model.repository;

import model.entity.Uzytkownik;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

}
