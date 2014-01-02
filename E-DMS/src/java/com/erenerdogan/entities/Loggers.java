/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erenerdogan.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author eren
 */
@Entity
@Table(name = "Loggers", catalog = "edys", schema = "")
@NamedQueries({
    @NamedQuery(name = "Loggers.findAll", query = "SELECT l FROM Loggers l"),
    @NamedQuery(name = "Loggers.findByLid", query = "SELECT l FROM Loggers l WHERE l.lid = :lid"),
    @NamedQuery(name = "Loggers.findByLname", query = "SELECT l FROM Loggers l WHERE l.lname = :lname"),
    @NamedQuery(name = "Loggers.findByLdate", query = "SELECT l FROM Loggers l WHERE l.ldate = :ldate"),
    @NamedQuery(name = "Loggers.findByLtype", query = "SELECT l FROM Loggers l WHERE l.ltype = :ltype")})
public class Loggers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lid")
    private Integer lid;
    @Column(name = "lname")
    private String lname;
    @Lob
    @Column(name = "ldescription")
    private String ldescription;
    @Column(name = "ldate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ldate;
    @Column(name = "ltype")
    private Integer ltype;

    public Loggers() {
    }

    public Loggers(Integer lid) {
        this.lid = lid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLdescription() {
        return ldescription;
    }

    public void setLdescription(String ldescription) {
        this.ldescription = ldescription;
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public Integer getLtype() {
        return ltype;
    }

    public void setLtype(Integer ltype) {
        this.ltype = ltype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lid != null ? lid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loggers)) {
            return false;
        }
        Loggers other = (Loggers) object;
        if ((this.lid == null && other.lid != null) || (this.lid != null && !this.lid.equals(other.lid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Loggers[ lid=" + lid + " ]";
    }
    
}
