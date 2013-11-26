/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import com.erenerdogan.service.GroupsDaoImpl;
import com.erenerdogan.service.UsersDaoImpl;
import com.erenerdogan.service.UsersDaoInterface;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author eren
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String newPassword;
    private String oldPassword;
    private List<Groups> myGroups;
    private List<Users> allUser;
    private boolean editable;
    

    public UserBean() {
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    

    public List<Users> getAllUser() {
        allUser = new UsersDaoImpl().getAllUsers();
        return allUser;
    }

    public void setAllUser(List<Users> allUser) {
        this.allUser = allUser;
    }

    
    public List<Groups> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<Groups> myGroups) {
        this.myGroups = myGroups;
    }

    
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String doLogin() {
        if (email != null && password != null) {
            UsersDaoInterface users = new UsersDaoImpl();
            Users user = users.authenticate(email, password);
            id = user.getUid();
            name = user.getUname();
            surname = user.getUsurname();
            myGroups = new GroupsDaoImpl().getUserGroups(id);
            FacesContext context = FacesContext.getCurrentInstance();


            if (user == null) {
                context.addMessage(null, new FacesMessage("Login Error", "Name or Surname Mistake"));
            } else {
                context.addMessage(null, new FacesMessage("Login Successful", "Hello " + name + " " + surname));
                return "admin";
            }
        }
        return "index";
    }

    public void doCreate(ActionEvent ae) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!email.equals("") && !surname.equals("") && !name.equals("") && !surname.equals("")) {
            UsersDaoInterface user = new UsersDaoImpl();
            boolean result = user.createUser(email, password, name, surname);
            if (result) {
                context.addMessage(null, new FacesMessage("Successfull", "Create account"));
            } else {
                context.addMessage(null, new FacesMessage("Error", "don't create account"));
            }
        } else {
            context.addMessage(null, new FacesMessage("Create An Account Error", "Requered Area"));
        }
    }

    public String logout() {
        System.out.println("logout");
        return "index";
    }

    public void changePassword(ActionEvent e) {
        if (oldPassword.equals(password)) {
            new UsersDaoImpl().changePassword(id, newPassword);
        }

    }
    
    public void remove(Users user){
        new UsersDaoImpl().deleteUser(user.getUid());
    } 
    
    public void edit(Users user){
        user.setEditable(true);
    } 
    
    
}
