/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "Comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByCid", query = "SELECT c FROM Comments c WHERE c.cid = :cid"),
    @NamedQuery(name = "Comments.findByCtitle", query = "SELECT c FROM Comments c WHERE c.ctitle = :ctitle"),
    @NamedQuery(name = "Comments.findByCdate", query = "SELECT c FROM Comments c WHERE c.cdate = :cdate"),
    @NamedQuery(name = "Comments.findByCstatus", query = "SELECT c FROM Comments c WHERE c.cstatus = :cstatus")})
public class Comments implements Serializable {
    @Column(name = "cdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cid")
    private Integer cid;
    @Column(name = "ctitle")
    private String ctitle;
    @Lob
    @Column(name = "cdescription")
    private String cdescription;
    @Column(name = "cstatus")
    private Integer cstatus;
    @JoinColumn(name = "cuid", referencedColumnName = "uid")
    @ManyToOne
    private Users cuid;
    @JoinColumn(name = "cfid", referencedColumnName = "fid")
    @ManyToOne
    private Files cfid;

    public Comments() {
    }

    public Comments(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCdescription() {
        return cdescription;
    }

    public void setCdescription(String cdescription) {
        this.cdescription = cdescription;
    }

    public Integer getCstatus() {
        return cstatus;
    }

    public void setCstatus(Integer cstatus) {
        this.cstatus = cstatus;
    }

    public Users getCuid() {
        return cuid;
    }

    public void setCuid(Users cuid) {
        this.cuid = cuid;
    }

    public Files getCfid() {
        return cfid;
    }

    public void setCfid(Files cfid) {
        this.cfid = cfid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Comments[ cid=" + cid + " ]";
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    
}
