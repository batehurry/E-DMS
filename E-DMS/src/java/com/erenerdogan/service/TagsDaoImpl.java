/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Tags;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class TagsDaoImpl implements TagsDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public TagsDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public void addTags(Files files, String tag) {
        Tags tags = new Tags();
        tags.setTfid(files);
        tags.setTname(tag);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tags);
        et.commit();
        em.close();
    }

    @Override
    public void editTags(Files file, String tag) {
        Tags tags = new Tags();
        tags.setTfid(file);
        tags.setTname(tag);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(tags);
        et.commit();
    }

    @Override
    public void removeFileTags(Files file) {
        List<Tags> tags = (List<Tags>) em.find(Files.class, file.getFid()).getTagsCollection();
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (Tags tags1 : tags) {
            em.remove(tags1);
        }
        et.commit();
    }
    
}
