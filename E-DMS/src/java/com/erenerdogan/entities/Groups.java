/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "groups")
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g ORDER BY g.gname ASC"),
    @NamedQuery(name = "Groups.findByGid", query = "SELECT g FROM Groups g WHERE g.gid = :gid"),
    @NamedQuery(name = "Groups.findByGsubid", query = "SELECT g FROM Groups g WHERE g.gsubid = :gsubid"),
    @NamedQuery(name = "Groups.findByGname", query = "SELECT g FROM Groups g WHERE g.gname = :gname"),
    @NamedQuery(name = "Groups.findByGstatus", query = "SELECT g FROM Groups g WHERE g.gstatus = :gstatus")})
public class Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Column(name = "gsubid")
    private Integer gsubid;
    @Column(name = "gname")
    private String gname;
    @Column(name = "gstatus")
    private Integer gstatus;
    @JoinTable(name = "usergroups", joinColumns = {
        @JoinColumn(name = "uggid", referencedColumnName = "gid")}, inverseJoinColumns = {
        @JoinColumn(name = "uguid", referencedColumnName = "uid")})
    @ManyToMany
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "gsgid")
    private Collection<GroupShared> groupSharedCollection;

    public Groups() {
    }

    public Groups(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getGsubid() {
        return gsubid;
    }

    public void setGsubid(Integer gsubid) {
        this.gsubid = gsubid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getGstatus() {
        return gstatus;
    }

    public void setGstatus(Integer gstatus) {
        this.gstatus = gstatus;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<GroupShared> getGroupSharedCollection() {
        return groupSharedCollection;
    }

    public void setGroupSharedCollection(Collection<GroupShared> groupSharedCollection) {
        this.groupSharedCollection = groupSharedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Groups[ gid=" + gid + " ]";
    }
    
}
