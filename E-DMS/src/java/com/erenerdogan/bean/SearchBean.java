/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Files;
import com.erenerdogan.service.SearchDaoImpl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class SearchBean implements Serializable{
    
    private List<Files> files;
    @ManagedProperty(value="#{param.tag}")
    private String tag;

    public SearchBean() {
        System.out.println("Tag 1:" + tag);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        tag = params.get("tag");
        System.out.println("Tag 2:" + tag);
        Map <String,Object> m = fc.getExternalContext().getSessionMap();
        UserBean ub = (UserBean)m.get("userBean");
        files = new SearchDaoImpl().searchTag(ub.getId(),tag);
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
        
    public String search(){
        System.out.println("Tag search:" + tag);
        return "search?tag="+tag+"faces-redirect=true";
    }
}
