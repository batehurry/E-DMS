/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Loggers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class LoggersDaoImpl implements LoggersDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public LoggersDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public void addLoggers(Loggers loggers) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(loggers);
        et.commit();
        em.close();
    }

    @Override
    public List<Loggers> getAllLoggers() {
        return em.createNamedQuery("Loggers.findAll",Loggers.class).getResultList();
    }
    
}
