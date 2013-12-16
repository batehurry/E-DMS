/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Groups;
import com.erenerdogan.entities.Tags;
import com.erenerdogan.service.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class FileEditBean {

    private String fileID;
    @ManagedProperty(value = "#{fileStatusBean}")
    private FileStatusBean fileStatus;
    @ManagedProperty(value = "#{groupBean}")
    private GroupBean group;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    
    
    private Files file;
    private List<String> tags;
    private DualListModel<String> permissions;
    private String fileStatusName;
    private String fileName;
    private String fileDescription;
    private boolean fileArchive;

    public FileEditBean() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        fileID = params.get("fileID");
        System.out.println("File ID File Edit Bean : " + fileID);
        if (fileID != null) {
            file = new FilesDaoImpl().getFileView(Integer.parseInt(fileID));

            List<String> sourge = new ArrayList<String>();
            List<String> target = new ArrayList<String>();
            List<Groups> gr = new GroupsDaoImpl().getAllGroups();
            for (Groups groups : gr) {
                sourge.add(groups.getGname());
            }
            for (GroupShared g : file.getGroupSharedCollection()) {
                target.add(g.getGsgid().getGname());
                sourge.remove(g.getGsgid().getGname());
            }
            permissions = new DualListModel<String>(sourge, target);
            System.out.println("File Edit Bean Const 1");
            fileName = file.getFname();
            fileDescription = file.getFdescription();
            if(file.getFstatus()==1)
                fileArchive=true;
            else 
                fileArchive=false;
            if (file.getFfsid() != null) {
                fileStatusName = file.getFfsid().getFsname();
            }
            List<Tags> fileTag = (List<Tags>) file.getTagsCollection();

            tags = new ArrayList<String>();
            for (Tags t : fileTag) {
                tags.add(t.getTname());
            }

        }
    }

    public boolean isFileArchive() {
        return fileArchive;
    }

    public void setFileArchive(boolean fileArchive) {
        this.fileArchive = fileArchive;
    }
    
    

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
    
    

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileStatusName() {
        return fileStatusName;
    }

    public void setFileStatusName(String fileStatusName) {
        this.fileStatusName = fileStatusName;
    }

    public DualListModel<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(DualListModel<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public Files getFile() {
        return file;
    }

    public void setFile(Files file) {
        this.file = file;
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

    public String fileEditSave() {
        System.out.println("Save");
        System.out.println(file.getFname() + " " + file.getFdescription() + " " + fileStatus.getFileStatusId());

        if (new FileStatusDaoImp().getFileStatus(fileStatus.getFileStatusId()) != null) {
            file.setFfsid(new FileStatusDaoImp().getFileStatus(fileStatus.getFileStatusId()));
        }
        if(fileArchive== true)
            file.setFstatus(1);
        else
            file.setFstatus(0);
        
        new FilesDaoImpl().editFile(file);
        System.out.println("Save2");
        new TagsDaoImpl().removeFileTags(file);
        Set<String> st = new LinkedHashSet<String>();
        // tag ekleniyor db ye
        
        if (tags != null && tags.size() > 0) {
            System.out.println("Tag size " +tags.size());
            for (String tag : tags) {
                st.add(tag);
            }
            System.out.println("st size :" + st.size());
        }
        System.out.println("Save3");
        // title tag olarak db ye ekleniyor
        String[] titleTag = fileName.split(" ");
        System.out.println("filename " + fileName);

        if (titleTag.length == 1) {
            st.add(fileName);
        } else {
            st.add(fileName);
            for (String tag : titleTag) {
                st.add(tag);
            }
        }
        
        for (String s : st) {
            System.out.println("tags size " +st.size() );
            new TagsDaoImpl().addTags(file, s);
        }
        
        List<String> target = permissions.getTarget();
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
            new GroupSharedDaoImpl().editGroupsShared(file, tmp);
        } else {

            new GroupSharedDaoImpl().editGroupsShared(file, user.getMyGroups());

        }

        return "fileEdit?fileID=" + fileID + "faces-redirect=true";
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();
        if (!results.contains(query)) {
            results.add(query);
        }
        return results;
    }
}
