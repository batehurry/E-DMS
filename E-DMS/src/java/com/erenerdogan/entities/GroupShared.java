/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "groupshared")
@NamedQueries({
    @NamedQuery(name = "GroupShared.findAll", query = "SELECT g FROM GroupShared g"),
    @NamedQuery(name = "GroupShared.findByGsid", query = "SELECT g FROM GroupShared g WHERE g.gsid = :gsid")})
public class GroupShared implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gsid")
    private Integer gsid;
    @JoinColumn(name = "gsgid", referencedColumnName = "gid")
    @ManyToOne
    private Groups gsgid;
    @JoinColumn(name = "gsfid", referencedColumnName = "fid")
    @ManyToOne
    private Files gsfid;

    public GroupShared() {
    }

    public GroupShared(Integer gsid) {
        this.gsid = gsid;
    }

    public Integer getGsid() {
        return gsid;
    }

    public void setGsid(Integer gsid) {
        this.gsid = gsid;
    }

    public Groups getGsgid() {
        return gsgid;
    }

    public void setGsgid(Groups gsgid) {
        this.gsgid = gsgid;
    }

    public Files getGsfid() {
        return gsfid;
    }

    public void setGsfid(Files gsfid) {
        this.gsfid = gsfid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gsid != null ? gsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupShared)) {
            return false;
        }
        GroupShared other = (GroupShared) object;
        if ((this.gsid == null && other.gsid != null) || (this.gsid != null && !this.gsid.equals(other.gsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.GroupShared[ gsid=" + gsid + " ]";
    }
    
}
