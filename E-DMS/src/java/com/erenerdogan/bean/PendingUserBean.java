/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import com.erenerdogan.service.GroupsDaoImpl;
import com.erenerdogan.service.UsersDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class PendingUserBean implements Serializable {

    private List<Users> pendingUsers;
    @ManagedProperty(value = "#{param.userID}")
    private int userID;
    private Users user;
    private List<Groups> groups;
    private DualListModel<String> permissions;
    private boolean status;
    private String name;
    private String surname;
    private String email;
    private boolean auth;

    public PendingUserBean() {
        System.out.println("Pending Construct");
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

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Users> getPendingUsers() {
        pendingUsers = new UsersDaoImpl().getPendingUsers();
        return pendingUsers;
    }

    public void setPendingUsers(List<Users> pendingUsers) {
        this.pendingUsers = pendingUsers;
    }

    public int getUserID() {
        System.out.println("UserID "+userID + "Target size " + permissions.getTarget().size());
        
        user = new UsersDaoImpl().getUser(userID);
        name = user.getUname();
        surname = user.getUsurname();
        email = user.getUemail();
        status = new UsersDaoImpl().getUserStatus(userID);
        if(user.getUauthorized()==1)
            auth = true;
        else
            auth = false;
        System.out.println(status);
        List<Groups> group = (List<Groups>) user.getGroupsCollection();
        System.out.println("Groups Size :" + group.size());
        List<String> sourge = new ArrayList<String>();
        List<String> target = new ArrayList<String>();
        List<Groups> gr = new GroupsDaoImpl().getAllGroups();
        for (Groups groups : gr) {
            sourge.add(groups.getGname());
        }
        permissions = new DualListModel<String>(sourge, target);
        for (Groups g : group) {
            permissions.getTarget().add(g.getGname());
            permissions.getSource().remove(g.getGname());
        }
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public String editUser() {
        System.out.println("geldi");
        return "edit?userID=" + userID + "&faces-redirect=true";
    }

    public void updateUser() {
        System.out.println("Name UserID : "+ name + " " + userID);
        user = new UsersDaoImpl().getUser(userID);
        System.out.println("user name " + user.getUname());
        user.setUname(name);
        user.setUsurname(surname);
        user.setUemail(email);
        if(auth==true)
            user.setUauthorized(1);
        else
            user.setUauthorized(0);
        Set<Groups> myGroups = new LinkedHashSet<Groups>();
        List<Groups> allGroups = new GroupsDaoImpl().getAllGroups();
        for (String string : permissions.getTarget()) {
            System.out.println("Target Size : "+permissions.getTarget().size());
            for (Groups g : allGroups) {
                if (g.getGname().equalsIgnoreCase(string) && !myGroups.contains(g)) {
                    
                    myGroups.add(g);
                }
            }
        }
        
        if (status == true) {
            System.out.println("status true");
            user.setUstatus(1);
        } else {
            System.out.println("status false");
            user.setUstatus(0);
        }
        
        new UsersDaoImpl().updateUser(user.getUid(), user);
        new GroupsDaoImpl().editGroups(user, myGroups);
        System.out.println("Güncellendi");
        System.out.println("Target"+ permissions.getTarget().size());
    }
}
