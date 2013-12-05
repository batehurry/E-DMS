/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class GroupsDaoImpl implements GroupsDaoInterface {

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

    @Override
    public Set<Groups> getHierarchyGroup(List<Groups> group) {
        System.out.println("getHierarchyGroup Geldi.");
        Set<Groups> groupSet = new LinkedHashSet<Groups>();
        for (Groups g : group) {
            Groups tmp;
            groupSet.add(g);
            do {
                g = em.find(Groups.class, g.getGsubid());
                groupSet.add(g);
                System.out.println("id " + g.getGid() + " subid " + g.getGsubid());
            } while (!g.getGid().equals(g.getGsubid()));
        }
        System.out.println("getHierarchyGroup Bitti.");
        return groupSet;
    }

    @Override
    public void editGroups(Users user, Set<Groups> group) {
        EntityTransaction et = em.getTransaction();
        
        
        //remove
        et.begin();
        List<Groups> l = (List<Groups>) user.getGroupsCollection();
        
        while(!l.isEmpty()) {
            
            Groups managedTag = em.merge(l.get(0));
            managedTag.getUsersCollection().remove(user);
            user.getGroupsCollection().remove(l.get(0));
            Users managedUser = em.merge(user);
        }
        
        et.commit();
        
        //update
        et.begin();
        
        for (Groups groups : group) {
            if (user.getGroupsCollection().contains(groups) && groups.getUsersCollection().contains(user)) {
                System.out.println("Var");
                continue;
            }
            Groups managedTag = em.merge(groups);
            managedTag.getUsersCollection().add(user);

            user.getGroupsCollection().add(groups);
            Users managedUser = em.merge(user);
            System.out.println("EDİT GROUPS");
        }
        et.commit();
        em.close();
    }
}
