/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import com.erenerdogan.service.FilesDaoImpl;
import com.erenerdogan.service.UsersDaoImpl;
import java.io.Serializable;
import java.util.List;
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
    
    private String name;
    private String surname;
    private String email;
    private List<Groups> myGroups;
    

    public Settings() {
        
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.myGroups = user.getMyGroups();
    }

    public List<Groups> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<Groups> myGroups) {
        this.myGroups = myGroups;
    }
    
    
    
    public void updateMyProfile(){
        Users u = new UsersDaoImpl().getUser(user.getId());
        u.setUemail(email);
        u.setUname(name);
        u.setUsurname(surname);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        new UsersDaoImpl().updateUser(user.getId(), u);
    }
}
