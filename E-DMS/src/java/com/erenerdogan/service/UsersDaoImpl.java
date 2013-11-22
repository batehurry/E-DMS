/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Users;
import java.sql.Timestamp;
import java.util.Date;
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
        if (user.getUpassword().equals(password)) {
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
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Users getUser(int id) {
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
}
