/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "FileStatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileStatus.findAll", query = "SELECT f FROM FileStatus f"),
    @NamedQuery(name = "FileStatus.findByFsid", query = "SELECT f FROM FileStatus f WHERE f.fsid = :fsid"),
    @NamedQuery(name = "FileStatus.findByFsname", query = "SELECT f FROM FileStatus f WHERE f.fsname = :fsname")})
public class FileStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fsid")
    private Integer fsid;
    @Column(name = "fsname")
    private String fsname;
    @OneToMany(mappedBy = "ffsid")
    private Collection<Files> filesCollection;

    public FileStatus() {
    }

    public FileStatus(Integer fsid) {
        this.fsid = fsid;
    }

    public Integer getFsid() {
        return fsid;
    }

    public void setFsid(Integer fsid) {
        this.fsid = fsid;
    }

    public String getFsname() {
        return fsname;
    }

    public void setFsname(String fsname) {
        this.fsname = fsname;
    }

    @XmlTransient
    public Collection<Files> getFilesCollection() {
        return filesCollection;
    }

    public void setFilesCollection(Collection<Files> filesCollection) {
        this.filesCollection = filesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fsid != null ? fsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileStatus)) {
            return false;
        }
        FileStatus other = (FileStatus) object;
        if ((this.fsid == null && other.fsid != null) || (this.fsid != null && !this.fsid.equals(other.fsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.FileStatus[ fsid=" + fsid + " ]";
    }
    
}
