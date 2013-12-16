/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Users;
import java.sql.Timestamp;
import java.util.ArrayList;
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
public class FilesDaoImpl implements FilesDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public FilesDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<Files> myAllFiles(int userID) {
        List<GroupShared> listGroupShareds = new GroupSharedDaoImpl().getMyGroupShared(userID);
        List<Files> files = new ArrayList<Files>();
        for (GroupShared gs : listGroupShareds) {
            if(gs.getGsfid().getFstatus()!=1)
                files.add(gs.getGsfid());   
        }
        return files;
    }

    @Override
    public List<Files> getAllFiles() {
        return em.createNamedQuery("Files.findAll", Files.class).getResultList();
    }

    @Override
    public void uploadFile(Files file) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(file);
        et.commit();
        em.close();
    }

    @Override
    public Files getFileView(int id) {
        return em.find(Files.class, id);
    }

    @Override
    public void deleteFile(int fileID) {
        Files f = em.find(Files.class, fileID);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(f);
        et.commit();
        em.close();
    }

    @Override
    public void editFile(Files file) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(file);
        et.commit();
    }

    @Override
    public List<Files> getAllDeadline() {
        Timestamp t = new Timestamp(new Date().getTime());
        return em.createNamedQuery("Files.findByFdeadline",Files.class).setParameter("fdeadline", t).getResultList();        
    }

    @Override
    public List<Files> getAllArchived() {
        return em.createNamedQuery("Files.findByFstatus",Files.class).setParameter("fstatus", 1).getResultList();
    }
    
    
    
}
