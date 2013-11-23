/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Groups;
import com.erenerdogan.service.FilesDaoImpl;
import com.erenerdogan.service.GroupSharedDaoImpl;
import com.erenerdogan.service.GroupsDaoImpl;
import com.erenerdogan.service.TagsDaoImpl;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class FileBean implements Serializable {

    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{groupBean}")
    private GroupBean group;
    
    private List<Files> files;
    private List<String> tags;
    private UploadedFile file;
    private String fileName;
    private String description;
    private Date date;

    public FileBean() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();
        if (!results.contains(query)) {
            results.add(query);
        }
        return results;
    }

    public List<Files> getFiles() {
        System.out.println(user.getId());

        files = new FilesDaoImpl().myAllFiles(user.getId());
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }

    public void upload(ActionEvent ae) {
        System.out.println("Upload");
        System.out.println(file.getFileName());
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("Upload");
        file = event.getFile();
        System.out.println(event.getFile().getFileName());

    }

    public String aktar() {
        System.out.println("Aktar");

        Files f = new Files();
        f.setFname(fileName);
        f.setFdescription(description);
        Timestamp t = new Timestamp(new Date().getTime());
        f.setFrdate(t);
        f.setFpath(file.getFileName());
        
        new FilesDaoImpl().uploadFile(f);

        if (tags!=null && tags.size() > 0) {
            for (String tag : tags) {
                new TagsDaoImpl().addTags(f, tag);
            }
        }

        List<String> target = group.getPermissions().getTarget();
        List<Groups> gr = new GroupsDaoImpl().getAllGroups();
        if (target.size() > 0) {
            // Türkçe karakter Problemi var 
            System.out.println("Target e geldi");
            for (String string : target) {
                System.out.println("Target e geldi" + string);
                for (Groups groups : gr) {
                    System.out.println("Target e geldi" + groups.getGname());
                    if (groups.getGname().equals(string)) {
                        System.out.println("İş Tamam...");
                        new GroupSharedDaoImpl().addGroupsShared(f, groups);
                    }
                }
            }
        } else {
            System.out.println("Target e gelmedi");
            for (Groups groups : user.getMyGroups()) {
                new GroupSharedDaoImpl().addGroupsShared(f, groups);
            }
        }
        return "admin";
    }
}
