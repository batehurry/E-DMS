/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Users;
import com.erenerdogan.entities.Works;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class WorkBean {

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    @ManagedProperty(value = "#{fileStatusBean}")
    private FileStatusBean fileStatus;
    @ManagedProperty(value = "#{param.fileID}")
    private String fileID;
    private List<Works> myAllWork;
    private DualListModel<String> permissions;
    private Users user;
    private UploadedFile upload;
    private List<String> tags;
    private String workTitle;
    private String workDescription;
    private Date workDeadline;
    private DefaultStreamedContent download;

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        System.out.println("GET = " + download.getName());
        return download;
    }

    public void prepDownload(String fileName) throws Exception {
        if (!fileName.equals("")) {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            File file = new File(path + "files/" + fileName);
            InputStream input = new FileInputStream(file);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
            System.out.println("PREP = " + download.getName());
        }
    }

    public WorkBean() {
        List<String> sourge = new ArrayList<String>();
        List<String> target = new ArrayList<String>();
        List<Users> u = new UsersDaoImpl().getAllUsers();
        for (Users us : u) {
            if (us.getUstatus() == 1) {
                sourge.add(us.getUname() + " " + us.getUsurname());
            }
        }
        permissions = new DualListModel<String>(sourge, target);

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

    public DualListModel<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(DualListModel<String> permissions) {
        this.permissions = permissions;
    }

    public FileStatusBean getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatusBean fileStatus) {
        this.fileStatus = fileStatus;
    }

    public List<Works> getMyAllWork() {
        myAllWork = new WorksDaoImpl().getMyWorks(userBean.getId());

        return myAllWork;

    }

    public void setMyAllWork(List<Works> myAllWork) {
        this.myAllWork = myAllWork;
    }

    public Date getWorkDeadline() {
        return workDeadline;
    }

    public void setWorkDeadline(Date workDeadline) {
        this.workDeadline = workDeadline;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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

    public String add() {
        System.out.println("work add");

        Files f = new Files();
        f.setFname(workTitle);
        f.setFdescription(workDescription);
        f.setFdeadline(workDeadline);
        f.setFstatus(0);
        Users u = new UsersDaoImpl().getUser(userBean.getId());
        f.setFuid(u);
        f.setFsubid(0);
        f.setFtype(0);
        Timestamp t = new Timestamp(new Date().getTime());
        f.setFrdate(t);
        if (upload.getSize()!=0) {
            String path = fileUpload(upload);
            if (path != null) {
                f.setFpath(path);
            }
        }
        System.out.println("Work File Status ID : " + fileStatus.getFileStatusId());

        f.setFfsid(new FileStatusDaoImp().getFileStatus(fileStatus.getFileStatusId()));

        new FilesDaoImpl().uploadFile(f);
        // tag ekleniyor db ye
        if (tags != null && tags.size() > 0) {
            for (String tag : tags) {
                new TagsDaoImpl().addTags(f, tag);
            }
        }

        // title tag olarak db ye ekleniyor
        String[] titleTag = f.getFname().split(" ");
        new TagsDaoImpl().addTags(f, f.getFname()); // tamamı
        for (String tag : titleTag) {
            new TagsDaoImpl().addTags(f, tag); // parçası
        }


        List<String> target = permissions.getTarget();
        System.out.println("Work Target Size :" + target.size());
        List<Users> assignUserList = new UsersDaoImpl().getAllUsers();
        for (Users us : assignUserList) {
            for (String string : target) {
                if ((us.getUname() + " " + us.getUsurname()).equalsIgnoreCase(string)) {
                    System.out.println("Work Target User : " + us.getUname() + " " + us.getUsurname());
                    Works w = new Works();
                    w.setWfid(f);
                    w.setWuid(us);
                    w.setWstatus(0);
                    new WorksDaoImpl().addWork(w);
                }
            }
        }

        return "admin?faces-redirect=true";
    }

    public String showFileView() {
        if (fileID == null) {
            return "admin?faces-redirect=true";
        }
        return "workView?fileID=" + fileID + "faces-redirect=true";
    }

    public void remove(Works w) {
        // Yazilacak
    }

    public String fileEdit() {
        // Yazılacak
        return null;
    }
}
