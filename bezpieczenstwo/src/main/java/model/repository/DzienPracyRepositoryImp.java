package model.repository;

import model.entity.DzienPracy;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ada on 2017-04-08.
 */
@Repository
@Transactional
public class DzienPracyRepositoryImp implements DzienPracyRepository {


    @Autowired
    private SessionFactory sessionFactory;

    public void delete(int id){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM uzytkownik_dzienpracy WHERE uzytkownik_dzienpracy.dniPracy_id="+id);
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM dzienpracy WHERE dzienpracy.id="+id);
        sqlQuery.executeUpdate();
        sqlQuery2.executeUpdate();

    }

    public void insert(DzienPracy dzienPracy) {
        sessionFactory.getCurrentSession().saveOrUpdate(dzienPracy);
    }

    public DzienPracy display(int id) {
        return (DzienPracy) sessionFactory.getCurrentSession().get(DzienPracy.class, id);
    }

    public List displayAllById(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT d.id, d.dzienTygodnia, d.godzinaRozpoczecia, d.godzinaZakonczenia FROM dzienpracy d WHERE d.uzytkownik_id="+id);
        List results = sqlQuery.list();
        return results;

    }

    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT d.dzienTygodnia, d.godzinaRozpoczecia, d.godzinaZakonczenia, u.imieINazwisko FROM dzienpracy d, uzytkownik u WHERE d.uzytkownik_id=u.id");
        List results = sqlQuery.list();
        return results;


    }

}
