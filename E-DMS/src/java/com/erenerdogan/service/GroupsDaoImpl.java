/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Groups;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class GroupsDaoImpl implements GroupsDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public GroupsDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<Groups> getUserGroups(int id) {
        List<Groups> groups = (List<Groups>) new UsersDaoImpl().getUser(id).getGroupsCollection();
        
        return groups;
        
    }

    @Override
    public List<Groups> getAllGroups() {
        List<Groups> groups = em.createNamedQuery("Groups.findAll", Groups.class).getResultList();
        
        return groups;
    }
    
}
