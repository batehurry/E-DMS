/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.bean;

import com.erenerdogan.entities.Loggers;
import com.erenerdogan.service.LoggersDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class HareketlerBean implements Serializable{
    private List<Loggers> logList;

    public HareketlerBean() {
    }

    public List<Loggers> getLogList() {
        logList = new LoggersDaoImpl().getAllLoggers();
        return logList;
    }

    public void setLogList(List<Loggers> logList) {
        this.logList = logList;
    }
    
}
