/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Groups;
import java.util.ArrayList;
import java.util.Iterator;
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
public class GroupSharedDaoImpl implements GroupSharedDaoInterface {

    private EntityManagerFactory emf;
    private EntityManager em;

    public GroupSharedDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<GroupShared> getMyGroupShared(int id) {
        List<Groups> groups = new GroupsDaoImpl().getUserGroups(id);

        List<GroupShared> groupShareds = new ArrayList<GroupShared>();

        for (Groups g : groups) {
            for (GroupShared gs : g.getGroupSharedCollection()) {
                groupShareds.add(gs);
            }
        }
        return groupShareds;
    }

    @Override
    public void addGroupsShared(Files file, List<Groups> groupID) {
        System.out.println("AddGroupsShared Geldi.");
        Set<Groups> groupSet = new GroupsDaoImpl().getHierarchyGroup(groupID);
        List<GroupShared> list = new ArrayList<GroupShared>();
        for (Groups group : groupSet) {
            GroupShared gs = new GroupShared();
            gs.setGsfid(file);
            gs.setGsgid(group);
            list.add(gs);
            System.out.println("add");
        }
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (GroupShared groupShared : list) {
            em.persist(groupShared);
        }
        et.commit();
        em.close();
        System.out.println("AddGroupsShared Bitti.");
    }

    @Override
    public void editGroupsShared(Files file, List<Groups> groupID) {
        removeGroupsShared(file);
        addGroupsShared(file, groupID);
    }

    @Override
    public void removeGroupsShared(Files file) {
        List<GroupShared> gs = (List<GroupShared>) file.getGroupSharedCollection();
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (GroupShared groupShared : gs) {
            em.remove(em.merge(groupShared));
        }
        et.commit();

    }
}
