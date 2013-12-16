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
public class ArchiveBean implements Serializable{
    private List<Files> archive;

    public ArchiveBean() {
    }

    public List<Files> getArchive() {
        archive = new FilesDaoImpl().getAllArchived();
        return archive;
    }

    public void setArchive(List<Files> archive) {
        this.archive = archive;
    }
    
    
    
}
