package model.repository;

import model.entity.DzienPracy;
import model.entity.Rola;
import model.entity.Uzytkownik;
import model.security.CustomPermissionEvaluator;
import model.service.*;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;


public class UzytkownikRepositoryImp implements UzytkownikRepository {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private KsiazkaService ksiazkaService;
    @Autowired
    private OcenaService ocenaService;
    @Autowired
    private LekService lekService;
    @Autowired
    private RolaService rolaService;
    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UprawnienieService uprawnienieService;
    @Autowired
    private DzienPracyService dzienPracyService;

    @Transactional
    public void insert(Uzytkownik uzytkownik) {
        sessionFactory.getCurrentSession().saveOrUpdate(uzytkownik);
    }


    @Transactional
    public Uzytkownik display(int id) {
        Uzytkownik uzytkownik = (Uzytkownik) sessionFactory.getCurrentSession().get(Uzytkownik.class, id);
        Hibernate.initialize(uzytkownik.getRole());
        for (Rola rola : uzytkownik.getRole()) {
            Hibernate.initialize(rola.getUprawnienia());
        }
      //  Hibernate.initialize(uzytkownik.getOceny());
        return uzytkownik;
    }

    @Transactional
    public Uzytkownik displayWithMarks(int id) {
        Uzytkownik uzytkownik = (Uzytkownik) sessionFactory.getCurrentSession().get(Uzytkownik.class, id);
        Hibernate.initialize(uzytkownik.getOcenyNauczyciel());
        Hibernate.initialize(uzytkownik.getOcenyUczen());
        return uzytkownik;
    }


    @Transactional
    public List displayAllNamesAndId() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select uzytkownik.id,uzytkownik.imieINazwisko from uzytkownik");
        List results = sqlQuery.list();
        return results;
    }

    @Transactional
    public List displayAllNamesAndIdByRole(String rola) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id, uzytkownik.imieINazwisko from uzytkownik, rola, uzytkownik_rola " +
                "where uzytkownik_rola.uzytkownicy_id = uzytkownik.id " +
                "and uzytkownik_rola.role_id = rola.id and rola.nazwa = \"" + rola + "\"");
        List results = sqlQuery.list();
        return results;

    }

    public void merge(Uzytkownik uzytkownik) {
        uzytkownik = (Uzytkownik) sessionFactory.getCurrentSession().merge(uzytkownik);
        sessionFactory.getCurrentSession().update(uzytkownik);

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
    @Transactional
    public int findIdUsingName(String name) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id from uzytkownik where uzytkownik.imieINazwisko = \"" + name + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }

    @Transactional
    public List displayAllWithoutPassword(){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id, uzytkownik.email, uzytkownik.imieINazwisko from uzytkownik");
        return sqlQuery.list();

    }
    @Transactional
    public void delete(int id) {
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM uzytkownik WHERE uzytkownik.id=" + id);
        sqlQuery2.executeUpdate();

    }

}
