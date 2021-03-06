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
@Table(name = "Files")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Files.findAll", query = "SELECT f FROM Files f WHERE f.fsubid = 0"),
    @NamedQuery(name = "Files.findByFid", query = "SELECT f FROM Files f WHERE f.fid = :fid"),
    @NamedQuery(name = "Files.findByFsubid", query = "SELECT f FROM Files f WHERE f.fsubid = :fsubid"),
    @NamedQuery(name = "Files.findByFname", query = "SELECT f FROM Files f WHERE f.fname = :fname"),
    @NamedQuery(name = "Files.findByFdeadline", query = "SELECT f FROM Files f WHERE f.fdeadline <= :fdeadline"),
    @NamedQuery(name = "Files.findByFrdate", query = "SELECT f FROM Files f WHERE f.frdate = :frdate"),
    @NamedQuery(name = "Files.findByFstatus", query = "SELECT f FROM Files f WHERE f.fstatus = :fstatus")})
public class Files implements Serializable {
    @Column(name = "fdeadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdeadline;
    @Column(name = "frdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frdate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fid")
    private Integer fid;
    @Column(name = "fsubid")
    private Integer fsubid;
    @Column(name = "fname")
    private String fname;
    @Lob
    @Column(name = "fpath")
    private String fpath;
    @Lob
    @Column(name = "fdescription")
    private String fdescription;
    @Column(name = "fstatus")
    private Integer fstatus;
    @Column(name = "ftype")
    private Integer ftype;
    @OneToMany(mappedBy = "tfid")
    private Collection<Tags> tagsCollection;
    @OneToMany(mappedBy = "cfid")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "fuid", referencedColumnName = "uid")
    @ManyToOne
    private Users fuid;
    @JoinColumn(name = "ffsid", referencedColumnName = "fsid")
    @ManyToOne
    private FileStatus ffsid;
    @OneToMany(mappedBy = "wfid")
    private Collection<Works> worksCollection;
    @OneToMany(mappedBy = "gsfid")
    private Collection<GroupShared> groupSharedCollection;

    public Files() {
    }

    public Files(Integer fid) {
        this.fid = fid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getFsubid() {
        return fsubid;
    }

    public void setFsubid(Integer fsubid) {
        this.fsubid = fsubid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }

    public Integer getFstatus() {
        return fstatus;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }
    

    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    @XmlTransient
    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Users getFuid() {
        return fuid;
    }

    public void setFuid(Users fuid) {
        this.fuid = fuid;
    }

    public FileStatus getFfsid() {
        return ffsid;
    }

    public void setFfsid(FileStatus ffsid) {
        this.ffsid = ffsid;
    }

    @XmlTransient
    public Collection<Works> getWorksCollection() {
        return worksCollection;
    }

    public void setWorksCollection(Collection<Works> worksCollection) {
        this.worksCollection = worksCollection;
    }

    @XmlTransient
    public Collection<GroupShared> getGroupSharedCollection() {
        return groupSharedCollection;
    }

    public void setGroupSharedCollection(Collection<GroupShared> groupSharedCollection) {
        this.groupSharedCollection = groupSharedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Files)) {
            return false;
        }
        Files other = (Files) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Files[ fid=" + fid + " ]";
    }

    public Date getFdeadline() {
        return fdeadline;
    }

    public void setFdeadline(Date fdeadline) {
        this.fdeadline = fdeadline;
    }

    public Date getFrdate() {
        return frdate;
    }

    public void setFrdate(Date frdate) {
        this.frdate = frdate;
    }
    
}
