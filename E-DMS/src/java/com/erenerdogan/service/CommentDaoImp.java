/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Comments;
import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Loggers;
import com.erenerdogan.entities.Users;
import java.sql.Timestamp;
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
public class CommentDaoImp implements CommentDaoInterface {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CommentDaoImp() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
    }

    @Override
    public void addComment(int userID, int fileID, String title, String description) {
        Comments c = new Comments();
        c.setCtitle(title);
        c.setCdescription(description);
        Files f = em.find(Files.class, fileID);
        c.setCfid(f);
        Users u = em.find(Users.class, userID);
        c.setCuid(u);
        Date d = new Date();
        c.setCdate(new Timestamp(d.getTime()));

        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(c);
        et.commit();

        Timestamp t = new Timestamp(new Date().getTime());
        Loggers l = new Loggers();

        l.setLname(c.getCtitle());
        l.setLdescription(u.getUname() + " " + u.getUsurname() + " Yorum Ekledi.");
        l.setLtype(1);
        l.setLdate(t);

        et.begin();
        em.persist(l);
        et.commit();
        em.close();
    }

    @Override
    public boolean removeComment(int userID, int commentID) {
        Comments c = em.find(Comments.class, commentID);
        Users u = em.find(Users.class, userID);
        if (c.getCuid().getUid() == userID || u.getUauthorized() == 1) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.remove(c);
            et.commit();

            Timestamp t = new Timestamp(new Date().getTime());
            Loggers l = new Loggers();

            l.setLname(c.getCtitle());
            l.setLdescription(u.getUname() + " " + u.getUsurname() + " Yorum Silindi.");
            l.setLtype(3);
            l.setLdate(t);

            et.begin();
            em.persist(l);
            et.commit();
            em.close();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Comments> getAllComment(int fileID) {
        List<Comments> commentList = (List<Comments>) em.find(Files.class, fileID).getCommentsCollection();
        return commentList;
    }
}
