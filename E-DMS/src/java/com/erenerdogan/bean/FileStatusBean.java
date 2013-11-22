/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.FileStatus;
import com.erenerdogan.service.FileStatusDaoImp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class FileStatusBean {
    private String fileStatusName;
    private List<FileStatus> fileStatusList;
    private Map<String,String> fileStatusMap;
    
    public FileStatusBean() {
        fileStatusList = new FileStatusDaoImp().getAllFileStatus();
        fileStatusMap = new Hashtable<String, String>();
        for (FileStatus fileStatus : fileStatusList) {
            fileStatusMap.put(fileStatus.getFsname(), fileStatus.getFsname());
        }
    }

    public String getFileStatusName() {
        return fileStatusName;
    }

    public void setFileStatusName(String fileStatusName) {
        this.fileStatusName = fileStatusName;
    }

    public List<FileStatus> getFileStatusList() {
        return fileStatusList;
    }

    public void setFileStatusList(List<FileStatus> fileStatusList) {
        this.fileStatusList = fileStatusList;
    }

    public Map<String, String> getFileStatusMap() {
        return fileStatusMap;
    }

    public void setFileStatusMap(Map<String, String> fileStatusMap) {
        this.fileStatusMap = fileStatusMap;
    }
   
    
    
}
