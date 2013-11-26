/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.FileStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class FileStatusDaoImp implements FileStatusDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public FileStatusDaoImp() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<FileStatus> getAllFileStatus() {
        return em.createNamedQuery("FileStatus.findAll", FileStatus.class).getResultList();
    }

    @Override
    public FileStatus getFileStatus(int id) {
        return em.find(FileStatus.class, id);
    }
    
}
