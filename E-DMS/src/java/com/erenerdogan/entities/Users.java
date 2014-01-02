/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUid", query = "SELECT u FROM Users u WHERE u.uid = :uid"),
    @NamedQuery(name = "Users.findByUname", query = "SELECT u FROM Users u WHERE u.uname = :uname"),
    @NamedQuery(name = "Users.findByUsurname", query = "SELECT u FROM Users u WHERE u.usurname = :usurname"),
    @NamedQuery(name = "Users.findByUemail", query = "SELECT u FROM Users u WHERE u.uemail = :uemail"),
    @NamedQuery(name = "Users.findByUpassword", query = "SELECT u FROM Users u WHERE u.upassword = :upassword"),
    @NamedQuery(name = "Users.findByUrdate", query = "SELECT u FROM Users u WHERE u.urdate = :urdate"),
    @NamedQuery(name = "Users.findByUstatus", query = "SELECT u FROM Users u WHERE u.ustatus = :ustatus"),
    @NamedQuery(name = "Users.findByUauthorized", query = "SELECT u FROM Users u WHERE u.uauthorized = :uauthorized")})
public class Users implements Serializable {
    @Column(name = "urdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date urdate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Integer uid;
    @Column(name = "uname")
    private String uname;
    @Column(name = "usurname")
    private String usurname;
    @Column(name = "uemail")
    private String uemail;
    @Column(name = "upassword")
    private String upassword;
    @Column(name = "ustatus")
    private Integer ustatus;
    @Column(name = "uauthorized")
    private Integer uauthorized;
    @JoinTable(name = "UserGroups", joinColumns = {
        @JoinColumn(name = "uguid", referencedColumnName = "uid")}, inverseJoinColumns = {
        @JoinColumn(name = "uggid", referencedColumnName = "gid")})
    @ManyToMany
    private Collection<Groups> groupsCollection;
    @OneToMany(mappedBy = "cuid")
    private Collection<Comments> commentsCollection;
    @OneToMany(mappedBy = "fuid")
    private Collection<Files> filesCollection;
    @OneToMany(mappedBy = "wuid")
    private Collection<Works> worksCollection;

    public Users() {
    }

    public Users(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsurname() {
        return usurname;
    }

    public void setUsurname(String usurname) {
        this.usurname = usurname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Integer getUauthorized() {
        return uauthorized;
    }

    public void setUauthorized(Integer uauthorized) {
        this.uauthorized = uauthorized;
    }

    @XmlTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<Files> getFilesCollection() {
        return filesCollection;
    }

    public void setFilesCollection(Collection<Files> filesCollection) {
        this.filesCollection = filesCollection;
    }

    @XmlTransient
    public Collection<Works> getWorksCollection() {
        return worksCollection;
    }

    public void setWorksCollection(Collection<Works> worksCollection) {
        this.worksCollection = worksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Users[ uid=" + uid + " ]";
    }

    public Date getUrdate() {
        return urdate;
    }

    public void setUrdate(Date urdate) {
        this.urdate = urdate;
    }
    
}
