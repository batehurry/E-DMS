/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import com.erenerdogan.service.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
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
    @ManagedProperty(value = "#{fileStatusBean}")
    private FileStatusBean fileStatus;
    @ManagedProperty(value="#{param.fileID}")
    private String fileID;
    
    private List<Files> files;
    private List<Files> allFiles;
    private List<String> tags;
    private UploadedFile upload;
    private String fileName;
    private String description;
    private Date date;
    private DefaultStreamedContent download;
  
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

    public FileBean() {
    }

    

    
    
    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }
    
    

    public FileStatusBean getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatusBean fileStatus) {
        this.fileStatus = fileStatus;
    }

    public List<Files> getAllFiles() {
        allFiles = new FilesDaoImpl().getAllFiles();
        return allFiles;
    }

    public void setAllFiles(List<Files> allFiles) {
        this.allFiles = allFiles;
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

    public UploadedFile getUpload() {
        return upload;
    }

    public void setUpload(UploadedFile upload) {
        this.upload = upload;
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
        System.out.println(upload.getFileName());
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("Upload");
        upload = event.getFile();
        System.out.println(event.getFile().getFileName());

    }

    public String aktar() {
        System.out.println("Aktar");

        Files f = new Files();
        f.setFname(fileName);
        f.setFdescription(description);
        Timestamp t = new Timestamp(new Date().getTime());
        f.setFrdate(t);
        String path = fileUpload(upload);
        if (path != null) {
            f.setFpath(path);
        }
        System.out.println("File Status ID : " + fileStatus.getFileStatusId());

        f.setFfsid(new FileStatusDaoImp().getFileStatus(fileStatus.getFileStatusId()));

        new FilesDaoImpl().uploadFile(f);

        if (tags != null && tags.size() > 0) {
            for (String tag : tags) {
                new TagsDaoImpl().addTags(f, tag);
            }
        }

        List<String> target = group.getPermissions().getTarget();
        List<Groups> gr = new GroupsDaoImpl().getAllGroups();
        List<Groups> tmp = new ArrayList<Groups>();
        if (target.size() > 0) {
            for (String string : target) {
                for (Groups groups : gr) {
                    if (groups.getGname().equals(string)) {
                        tmp.add(groups);
                    }
                }
            }
            new GroupSharedDaoImpl().addGroupsShared(f, tmp);
        } else {

            new GroupSharedDaoImpl().addGroupsShared(f, user.getMyGroups());

        }
        return "admin?faces-redirect=true";
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
    
    
    public String showFileView() {
        if (fileID == null) {
            return "admin?faces-redirect=true";
        }
        return "fileView?fileID="+fileID+"faces-redirect=true";
    }
    
    public void remove(Files file){
        new FilesDaoImpl().deleteFile(file.getFid());
    }
}
