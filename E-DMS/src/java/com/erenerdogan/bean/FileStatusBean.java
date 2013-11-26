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
    private int fileStatusId;
    private String fileStatusName;
    private List<FileStatus> fileStatusList;
    private Map<String,Integer> fileStatusMap;
    
    public FileStatusBean() {
        fileStatusList = new FileStatusDaoImp().getAllFileStatus();
        fileStatusMap = new Hashtable<String, Integer>();
        for (FileStatus fileStatus : fileStatusList) {
            fileStatusMap.put(fileStatus.getFsname(), fileStatus.getFsid());
        }
    }

    public int getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(int fileStatusId) {
        this.fileStatusId = fileStatusId;
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

    public Map<String, Integer> getFileStatusMap() {
        return fileStatusMap;
    }

    public void setFileStatusMap(Map<String, Integer> fileStatusMap) {
        this.fileStatusMap = fileStatusMap;
    }
   
    
    
}
