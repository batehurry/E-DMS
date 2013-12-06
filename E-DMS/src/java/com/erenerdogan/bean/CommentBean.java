/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.service.CommentDaoImp;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class CommentBean implements Serializable{
    @ManagedProperty(value="#{param.userID}")
    private int userID;
    @ManagedProperty(value="#{param.fileID}")
    private int fileID;
    
    private String title;
    private String description;

    public CommentBean() {
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String sendComment(){
        System.out.println("Comment send");
        System.out.println(userID+ " " +fileID+ " " +title+ " " +description);
        new CommentDaoImp().addComment(userID, fileID, title, description);
        return "fileView?fileID="+fileID+"faces-redirect=true";
    }
}
