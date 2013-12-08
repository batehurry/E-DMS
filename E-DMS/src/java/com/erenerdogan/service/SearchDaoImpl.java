/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.service;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Tags;
import com.erenerdogan.entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eren
 */
public class SearchDaoImpl implements SearchDaoInterface{
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private List<Files> files;
    
    public SearchDaoImpl() {
        emf = Persistence.createEntityManagerFactory("E-DMSPU");
        em = emf.createEntityManager();
        files = new ArrayList<Files>();
    }

    @Override
    public List<Files> searchTag(int userID, String tag) {
         List<Tags> tags = em.createNamedQuery("Tags.findByTname",Tags.class).setParameter("tname", tag).getResultList();
         for (Tags t : tags) {
             List<GroupShared> gs = (List<GroupShared>) t.getTfid().getGroupSharedCollection();
             for (GroupShared groupShared : gs) {
                 List<Users> u = (List<Users>) groupShared.getGsgid().getUsersCollection();
                 for (Users users : u) {
                     if(users.getUid() == userID)
                     {
                         files.add(t.getTfid());
                     }
                 }
             }
            
        }
         em.close();
        return files;
    }
    
}
