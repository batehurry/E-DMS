/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Loggers;
import com.erenerdogan.entities.Users;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class UsersDaoImpl implements UsersDaoInterface {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UsersDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public Users authenticate(String email, String password) {
        Users user = em.createNamedQuery("Users.findByUemail", Users.class).setParameter("uemail", email).getSingleResult();
        if (user == null) {
            return null;
        }
        if (user.getUpassword().equals(password) && user.getUstatus() == 1) {
            return user;
        }
        return null;
    }

    @Override
    public boolean createUser(String email, String password, String name, String surname) {
        try {
            Users user = new Users();
            user.setUemail(email);
            user.setUname(name);
            user.setUpassword(password);
            user.setUsurname(surname);
            Date d = new Date();
            user.setUrdate(new Timestamp(d.getTime()));
            user.setUstatus(0);
            user.setUauthorized(0);
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();
            Timestamp t = new Timestamp(new Date().getTime());
            Loggers l = new Loggers();

            l.setLname("Yeni Kullanici Eklendi.");
            l.setLdescription(user.getUname() + " " + user.getUsurname() + " adlı kullanici kayit oldu.");
            l.setLtype(1);
            l.setLdate(t);

            et.begin();
            em.persist(l);
            et.commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Users getUser(int id) {
        System.out.println("GET USER");
        return em.find(Users.class, id);
    }

    @Override
    public void changePassword(int id, String newPassword) {
        Users u = em.find(Users.class, id);
        u.setUpassword(newPassword);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(u);
        et.commit();
        em.close();
    }

    @Override
    public List<Users> getAllUsers() {
        return em.createNamedQuery("Users.findAll", Users.class).getResultList();
    }

    @Override
    public void deleteUser(int id) {
        Users u = em.find(Users.class, id);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(u);
        et.commit();
        Timestamp t = new Timestamp(new Date().getTime());
        Loggers l = new Loggers();

        l.setLname("Kullanici Silme");
        l.setLdescription(u.getUname() + " " + u.getUsurname() + " adli kullanici silindi.");
        l.setLtype(3);
        l.setLdate(t);

        et.begin();
        em.persist(l);
        et.commit();
        em.close();
    }

    @Override
    public void updateUser(int id, Users user) {
        System.out.println("Edit grup size " + user.getGroupsCollection().size());
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(user);
        et.commit();

        Timestamp t = new Timestamp(new Date().getTime());
        Loggers l = new Loggers();

        l.setLname("Kullanici Guncelleme");
        l.setLdescription(user.getUname() + " " + user.getUsurname() + " adli kullanici güncellendi.");
        l.setLtype(2);
        l.setLdate(t);

        et.begin();
        em.persist(l);
        et.commit();
        em.close();
    }

    @Override
    public List<Users> getPendingUsers() {
        return em.createNamedQuery("Users.findByUstatus").setParameter("ustatus", 0).getResultList();
    }

    @Override
    public boolean getUserStatus(int id) {
        int status = em.find(Users.class, id).getUstatus();
        if (status == 1) {
            return true;
        }
        return false;
    }
}
