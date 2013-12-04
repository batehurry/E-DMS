/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Users;
import com.erenerdogan.service.FilesDaoImpl;
import com.erenerdogan.service.UsersDaoImpl;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class Settings implements Serializable{
    
    
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;

    public Settings() {
        
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
    
    
}
