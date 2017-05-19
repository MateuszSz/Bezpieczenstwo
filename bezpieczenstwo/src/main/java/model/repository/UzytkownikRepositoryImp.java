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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Repository
@Transactional
public class UzytkownikRepositoryImp implements UzytkownikRepository {



    private SessionFactory sessionFactory;

    @Autowired
    public UzytkownikRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void insert(Uzytkownik uzytkownik) {
        sessionFactory.getCurrentSession().saveOrUpdate(uzytkownik);
    }



    public Uzytkownik display(int id) {
        Uzytkownik uzytkownik = (Uzytkownik) sessionFactory.getCurrentSession().get(Uzytkownik.class, id);
        Hibernate.initialize(uzytkownik.getRole());
        for (Rola rola : uzytkownik.getRole()) {
            Hibernate.initialize(rola.getUprawnienia());
        }

        return uzytkownik;
    }


    public Uzytkownik displayWithMarks(int id) {
        Uzytkownik uzytkownik = (Uzytkownik) sessionFactory.getCurrentSession().get(Uzytkownik.class, id);
        Hibernate.initialize(uzytkownik.getOcenyNauczyciel());
        Hibernate.initialize(uzytkownik.getOcenyUczen());
        return uzytkownik;
    }



    public List displayAllNamesAndId() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select uzytkownik.id,uzytkownik.imieINazwisko from uzytkownik");
        return sqlQuery.list();
    }


    public List displayAllNamesAndIdByRole(String rola) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id, uzytkownik.imieINazwisko from uzytkownik, rola, uzytkownik_rola " +
                "where uzytkownik_rola.uzytkownicy_id = uzytkownik.id " +
                "and uzytkownik_rola.role_id = rola.id and rola.nazwa = \"" + rola + "\"");
        return sqlQuery.list();

    }

    public void merge(Uzytkownik uzytkownik) {
        uzytkownik = (Uzytkownik) sessionFactory.getCurrentSession().merge(uzytkownik);
        sessionFactory.getCurrentSession().update(uzytkownik);

    }


    public boolean isLoginAndPasswodCorrect(String email, String haslo) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uzytkownik.class);
        Uzytkownik uzytkownik = (Uzytkownik) criteria.add(Restrictions.eq("haslo", haslo)).uniqueResult();
        return uzytkownik.getEmail().equals(email);
    }


    public int findIdUsingEmail(String email) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id from uzytkownik where uzytkownik.email = \"" + email + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }

    public int findIdUsingName(String name) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id from uzytkownik where uzytkownik.imieINazwisko = \"" + name + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }


    public List displayAllWithoutPassword(){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select uzytkownik.id, uzytkownik.email, uzytkownik.imieINazwisko from uzytkownik");
        return sqlQuery.list();

    }

    public void delete(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE from dzienpracy WHERE dzienpracy.uzytkownik_id = " + id);
        sqlQuery.executeUpdate();
        sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE from uzytkownik_rola WHERE uzytkownik_rola.uzytkownicy_id =" + id);
        sqlQuery.executeUpdate();
        sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("UPDATE lek SET lek.uzytkownik_id = NULL WHERE lek.uzytkownik_id =" + id);
        sqlQuery.executeUpdate();
        sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE from ocena where ocena.nauczyciel_id =" + id);
        sqlQuery.executeUpdate();
        sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE from ocena where ocena.uczen_id =" + id);
        sqlQuery.executeUpdate();
        sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("UPDATE ksiazka SET ksiazka.uzytkownik_id = NULL WHERE ksiazka.uzytkownik_id =" + id);
        sqlQuery.executeUpdate();
        sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM uzytkownik WHERE uzytkownik.id=" + id);
        sqlQuery.executeUpdate();

    }

    public List displayAllLecturers(){
       SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select DISTINCT uzytkownik.id, uzytkownik.imieINazwisko, uzytkownik.email FROM uzytkownik, uzytkownik_rola, rola, rola_uprawnienie, uprawnienie WHERE uzytkownik.id = uzytkownik_rola.uzytkownicy_id AND uzytkownik_rola.role_id = rola.id and rola_uprawnienie.Rola_id = rola.id and rola_uprawnienie.uprawnienia_id = uprawnienie.id and uprawnienie.nazwa = \"READ_DNIPRACY\"");
        return sqlQuery.list();
    }

}
