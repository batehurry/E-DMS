/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Comments;
import com.erenerdogan.service.CommentDaoImp;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class CommentBean implements Serializable {

    @ManagedProperty(value = "#{param.userID}")
    private int userID;
    @ManagedProperty(value = "#{param.fileID}")
    private String fileID;
    @ManagedProperty(value = "#{param.commentID}")
    private int commentID;
    private String title;
    private String description;
    private List<Comments> comments;

    public CommentBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        fileID = params.get("fileID");
        System.out.println("File ID : " + fileID);
        if (fileID != null) {
            comments = new CommentDaoImp().getAllComment(Integer.parseInt(fileID));
        }
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
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

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String sendComment() {
        System.out.println("Comment send");
        System.out.println(userID + " " + fileID + " " + title + " " + description);
        new CommentDaoImp().addComment(userID, Integer.parseInt(fileID), title, description);
        return "fileView?fileID=" + fileID + "&faces-redirect=true";
    }
    
    public String sendWorkComment() {
        System.out.println("Comment send");
        System.out.println(userID + " " + fileID + " " + title + " " + description);
        new CommentDaoImp().addComment(userID, Integer.parseInt(fileID), title, description);
        return "workView?fileID=" + fileID + "&faces-redirect=true";
    }

    public String removeComment() {
        System.out.println("Remove Comment " +commentID);

        boolean result = new CommentDaoImp().removeComment(userID, commentID);
        if (!result) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Remove Comment Error", "You dont have comment"));
        }
        return "fileView?fileID=" + fileID + "&faces-redirect=true";
    }
    
    public String removeViewComment() {
        System.out.println("Remove Comment " +commentID);

        boolean result = new CommentDaoImp().removeComment(userID, commentID);
        if (!result) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Remove Comment Error", "You dont have comment"));
        }
        return "workView?fileID=" + fileID + "&faces-redirect=true";
    }
}
