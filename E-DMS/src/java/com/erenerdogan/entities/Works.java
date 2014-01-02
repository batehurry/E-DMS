/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "Works")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Works.findAll", query = "SELECT w FROM Works w"),
    @NamedQuery(name = "Works.findMyAll", query = "SELECT w FROM Works w WHERE w.wuid = :wuid"),
    @NamedQuery(name = "Works.findMyWorkAll", query = "SELECT w FROM Works w WHERE w.wuid = :wuid AND w.wfid=:wfid"),
    @NamedQuery(name = "Works.findFileUserAll", query = "SELECT w FROM Works w WHERE w.wfid = :wfid"),
    @NamedQuery(name = "Works.findByWid", query = "SELECT w FROM Works w WHERE w.wid = :wid")})
public class Works implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wid")
    private Integer wid;
    @Column(name = "wstatus")
    private Integer wstatus;
    @JoinColumn(name = "wfid", referencedColumnName = "fid")
    @ManyToOne
    private Files wfid;
    @JoinColumn(name = "wuid", referencedColumnName = "uid")
    @ManyToOne
    private Users wuid;

    public Works() {
    }

    public Works(Integer wid) {
        this.wid = wid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Files getWfid() {
        return wfid;
    }

    public void setWfid(Files wfid) {
        this.wfid = wfid;
    }

    public Users getWuid() {
        return wuid;
    }

    public void setWuid(Users wuid) {
        this.wuid = wuid;
    }

    public Integer getWstatus() {
        return wstatus;
    }

    public void setWstatus(Integer wstatus) {
        this.wstatus = wstatus;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Works)) {
            return false;
        }
        Works other = (Works) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Works[ wid=" + wid + " ]";
    }
    
}
