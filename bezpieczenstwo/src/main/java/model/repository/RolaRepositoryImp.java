package model.repository;

import model.entity.Rola;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class RolaRepositoryImp implements RolaRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RolaRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void insert(Rola rola) {
        sessionFactory.getCurrentSession().saveOrUpdate(rola);
    }


    public Rola display(int id) {
        Rola rola = (Rola) sessionFactory.getCurrentSession().get(Rola.class, id);
        Hibernate.initialize(rola.getUzytkownicy());
        Hibernate.initialize(rola.getUprawnienia());
        Hibernate.initialize(rola.getSeperacjaRol());
        return rola;
    }


    public Rola displayWithoutPermission(int id) {
        Rola rola = (Rola) sessionFactory.getCurrentSession().get(Rola.class, id);
        Hibernate.initialize(rola.getUzytkownicy());
        return rola;
    }


    public int findIdUsingName(String nazwa) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT rola.id FROM rola WHERE rola.nazwa = \"" + nazwa + "\"");
        List results = sqlQuery.list();
        return (Integer) results.get(0);
    }


    public List displayAll() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT rola.nazwa FROM rola");
        return sqlQuery.list();
    }


    public List displayAllNamesAndId() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT rola.id,rola.nazwa FROM rola");
        return sqlQuery.list();
    }

    public List displayAllSeperateRoles(String name){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT r2.nazwa FROM rola r, rola r2, rola_rola WHERE r.id = rola_rola.Rola_id AND r2.id = rola_rola.seperacjaRol_id and r.nazwa = \""+ name +"\"");
        return sqlQuery.list();
    }

    public List displayAllRolesAndSeperateRoles(){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT r.nazwa as 'Rola', r2.nazwa as 'Seperacja' FROM rola r, rola r2, rola_rola WHERE r.id = rola_rola.Rola_id AND r2.id = rola_rola.seperacjaRol_id");
        return sqlQuery.list();

    }

    public List displayWithUserName() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT uzytkownik.imieINazwisko, rola.nazwa FROM uzytkownik, rola, uzytkownik_rola " +
                "WHERE uzytkownik_rola.uzytkownicy_id = uzytkownik.id " +
                "AND uzytkownik_rola.role_id = rola.id");
        return sqlQuery.list();
    }


    public void delete(int id) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM uzytkownik_rola WHERE uzytkownik_rola.role_id=" + id);
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM rola_uprawnienie WHERE rola_id=" + id);
        SQLQuery sqlQuery3 = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM rola WHERE rola.id=" + id);
        sqlQuery.executeUpdate();
        sqlQuery2.executeUpdate();
        sqlQuery3.executeUpdate();
    }


    public void deleteRoleFromUser(int idRoli, int idUzytkownika) {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("delete from uzytkownik_rola WHERE uzytkownik_rola.role_id = " +idRoli + " and uzytkownik_rola.uzytkownicy_id = " + idUzytkownika + "");
        sqlQuery.executeUpdate();
    }

}
