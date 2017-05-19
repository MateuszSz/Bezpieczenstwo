package model.repository;

import model.entity.Ksiazka;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ada on 2017-03-31.
 */
@Repository
@Transactional
public class KsiazkaRepositoryImp implements KsiazkaRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public KsiazkaRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Ksiazka ksiazka) {
        sessionFactory.getCurrentSession().saveOrUpdate(ksiazka);

    }

    public void delete(int id) {
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM ksiazka WHERE ksiazka.id=" + id);
        sqlQuery2.executeUpdate();

    }

    public Ksiazka display(int id) {
        return (Ksiazka) sessionFactory.getCurrentSession().get(Ksiazka.class, id);
    }

    public List displayAllByEmail(String email) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select ksiazka.id, ksiazka.ISBN, ksiazka.autor, ksiazka.tytul, ksiazka.seria, ksiazka.dostepnosc " +
                "from ksiazka, uzytkownik_ksiazka, uzytkownik WHERE ksiazka.id=uzytkownik_ksiazka.ksiazki_id and uzytkownik_ksiazka.Uzytkownik_id=uzytkownik.id and uzytkownik.email = \"" + email + "\"");
        return sqlQuery.list();
    }

    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("Select ksiazka.id, ksiazka.ISBN, ksiazka.autor, ksiazka.tytul, ksiazka.seria, ksiazka.dostepnosc from ksiazka");
        return sqlQuery.list();
    }

}
