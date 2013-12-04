/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.service.FilesDaoImpl;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class FileViewBean implements Serializable{

    private String fileID;
    private Files fileView;

    public FileViewBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        fileID = params.get("fileID");
        System.out.println("File ID : "+ fileID);
        fileView = new FilesDaoImpl().getFileView(Integer.parseInt(fileID));
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }
    
    

    public Files getFileView() {
        return fileView;
    }

    public void setFileView(Files fileView) {
        this.fileView = fileView;
    }
}
