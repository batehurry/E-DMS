/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Groups;
import com.erenerdogan.service.GroupsDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class GroupBean implements Serializable{
    
    private DualListModel<String> permissions;
    

    public GroupBean() {
        List<String> sourge = new ArrayList<String>();
        List<String> target = new ArrayList<String>();
        List<Groups> gr = new GroupsDaoImpl().getAllGroups();
        for (Groups groups : gr) {
            sourge.add(groups.getGname());
        }
        permissions = new DualListModel<String>(sourge, target);
    }

    public DualListModel<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(DualListModel<String> permissions) {
        this.permissions = permissions;
    }
    
}
