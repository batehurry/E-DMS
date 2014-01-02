/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Users;
import com.erenerdogan.entities.Works;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class WorksDaoImpl implements WorksDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public WorksDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<Works> getMyWorks(int userID) {
        Users u = new UsersDaoImpl().getUser(userID);
        return em.createNamedQuery("Works.findMyAll", Works.class).setParameter("wuid", u).getResultList();
    }

    public void addWork(Works w) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(w);
        et.commit();
        em.close();
    }

    @Override
    public List<Works> getFileWork(int fileID) {
         Files f = new FilesDaoImpl().getFileView(fileID);
         return em.createNamedQuery("Works.findFileUserAll", Works.class).setParameter("wfid", f).getResultList();
    }

    @Override
    public void changeWorkStatus(int userID, int fileID) {
        
        Users u = em.find(Users.class, userID);
        Files f = em.find(Files.class, fileID);
        Works w = em.createNamedQuery("Works.findMyWorkAll", Works.class).setParameter("wuid", u).setParameter("wfid", f).getSingleResult();
        EntityTransaction et = em.getTransaction();
        et.begin();
        w.setWstatus(1);
        em.merge(w);
        et.commit();
        em.close();
    }

    
    
    
    
}
