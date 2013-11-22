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
@Table(name = "tags")
@NamedQueries({
    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t"),
    @NamedQuery(name = "Tags.findByTid", query = "SELECT t FROM Tags t WHERE t.tid = :tid"),
    @NamedQuery(name = "Tags.findByTname", query = "SELECT t FROM Tags t WHERE t.tname = :tname")})
public class Tags implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Integer tid;
    @Column(name = "tname")
    private String tname;
    @JoinColumn(name = "tfid", referencedColumnName = "fid")
    @ManyToOne
    private Files tfid;

    public Tags() {
    }

    public Tags(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Files getTfid() {
        return tfid;
    }

    public void setTfid(Files tfid) {
        this.tfid = tfid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erenerdogan.entities.Tags[ tid=" + tid + " ]";
    }
    
}
