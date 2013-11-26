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
    public void addGroupsShared(Files file, Groups groupID) {

        GroupShared gs = new GroupShared();
        gs.setGsfid(file);
        gs.setGsgid(groupID);

        EntityTransaction et = em.getTransaction();
        et.begin();
        if (groupID.getGid().equals(groupID.getGsubid())) {
            em.persist(gs);
        } else {
            List<GroupShared> l = new ArrayList<GroupShared>();
            l.add(gs);
            do {
                groupID = em.find(Groups.class, groupID.getGsubid());
                GroupShared gs1 = new GroupShared();
                gs1.setGsfid(file);
                gs1.setGsgid(groupID);
                l.add(gs1);
            } while (!groupID.getGid().equals(groupID.getGsubid()));
            
            for (GroupShared groupShared : l) {
                em.persist(groupShared);
            }
        }
        et.commit();
        em.close();
    }
}
