/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.service.FilesDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class DeadlineBean implements Serializable{
    private List<Files> deadlineFiles;

    public DeadlineBean() {
        
    }

    public List<Files> getDeadlineFiles() {
        deadlineFiles = new FilesDaoImpl().getAllDeadline();
        return deadlineFiles;
    }

    public void setDeadlineFiles(List<Files> deadlineFiles) {
        this.deadlineFiles = deadlineFiles;
    }
    
    
            
}
