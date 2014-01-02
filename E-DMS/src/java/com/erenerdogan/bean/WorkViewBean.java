/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Users;
import com.erenerdogan.entities.Works;
import com.erenerdogan.service.FilesDaoImpl;
import com.erenerdogan.service.UsersDaoImpl;
import com.erenerdogan.service.WorksDaoImpl;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class WorkViewBean {
    
    
    private int workID;
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;
    @ManagedProperty(value="#{param.fileID}")
    private String fileID;
    
    private List<Works> work;
    private List<Files> subFiles;
    private Files fileView;
    
    private String workTitle;
    private String workDescription;
    private UploadedFile upload;
    private DefaultStreamedContent download;

    public WorkViewBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        fileID = params.get("fileID");
        System.out.println("File ID : "+ fileID);
        if(fileID!= null)
            fileView = new FilesDaoImpl().getFileView(Integer.parseInt(fileID));
    }
    
    
    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        System.out.println("GET = " + download.getName());
        return download;
    }

    public void prepDownload(String fileName) throws Exception {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        File file = new File(path + "files/" + fileName);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
        System.out.println("PREP = " + download.getName());
    }

    public List<Files> getSubFiles() {
        subFiles = new FilesDaoImpl().getSubFiles(Integer.parseInt(fileID));
        return subFiles;
    }

    public void setSubFiles(List<Files> subFiles) {
        this.subFiles = subFiles;
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

    public List<Works> getWork() {
        if(fileID!=null)
            work = new WorksDaoImpl().getFileWork(Integer.parseInt(fileID));
        
        return work;
    }

    public void setWork(List<Works> work) {
        this.work = work;
    }
    
    public UploadedFile getUpload() {
        return upload;
    }

    public void setUpload(UploadedFile upload) {
        this.upload = upload;
    }

    private String fileUpload(UploadedFile f) {
        try {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
            String name = fmt.format(new Date())
                    + f.getFileName().substring(
                    f.getFileName().lastIndexOf('.'));
            File file = new File(path + "files/" + name);

            InputStream is = f.getInputstream();
            OutputStream out = new FileOutputStream(file);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                System.out.println(buf.length);
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
            System.out.println(file.getPath());
            return name;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    
    
    public void doWork(){
        System.out.println("Work ID : "+ workID);
        System.out.println("Work User ID : "+ userBean.getId());
        System.out.println("Work File ID : "+ fileID);
        String path = fileUpload(upload);
        Files f = new Files();
        f.setFname(workTitle);
        f.setFdescription(workDescription);
        f.setFpath(path);
        f.setFsubid(Integer.parseInt(fileID));
        Timestamp t = new Timestamp(new Date().getTime());
        f.setFrdate(t);
        Users u = new UsersDaoImpl().getUser(userBean.getId());
        f.setFuid(u);
        new FilesDaoImpl().uploadFile(f);
        new WorksDaoImpl().changeWorkStatus(userBean.getId(), Integer.parseInt(fileID));
        
    }
}
