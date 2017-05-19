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



    private SessionFactory sessionFactory;

    @Autowired
    public DzienPracyRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void delete(int id) {

        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM dzienpracy WHERE dzienpracy.id=" + id);
        sqlQuery.executeUpdate();
    }

    public void insert(DzienPracy dzienPracy) {
        sessionFactory.getCurrentSession().saveOrUpdate(dzienPracy);
    }

    public DzienPracy display(int id) {
        return (DzienPracy) sessionFactory.getCurrentSession().get(DzienPracy.class, id);
    }

    public List displayAllById(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT d.id, d.dzienTygodnia, d.godzinaRozpoczecia, d.godzinaZakonczenia FROM dzienpracy d WHERE d.uzytkownik_id=" + id);
        return sqlQuery.list();

    }

    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT d.id,d.dzienTygodnia, d.godzinaRozpoczecia, d.godzinaZakonczenia, u.imieINazwisko, u.id as 'u.id' FROM dzienpracy d, uzytkownik u WHERE d.uzytkownik_id=u.id");
        return sqlQuery.list();


    }

}
