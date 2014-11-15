/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johnny
 */
@Entity
@Table(name = "t_ddm_version")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DdmVersion.findAll", query = "SELECT d FROM DdmVersion d"),
    @NamedQuery(name = "DdmVersion.findById", query = "SELECT d FROM DdmVersion d WHERE d.id = :id"),
    @NamedQuery(name = "DdmVersion.findByVersion", query = "SELECT d FROM DdmVersion d WHERE d.version = :version"),
    @NamedQuery(name = "DdmVersion.findByDisplay", query = "SELECT d FROM DdmVersion d WHERE d.display = :display")})
public class DdmVersion extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Size(max = 10)
    @Column(name = "version")
    private String version;
    @Size(max = 20)
    @Column(name = "display")
    private String display;

    public DdmVersion() {
    }

    public DdmVersion(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DdmVersion)) {
            return false;
        }
        DdmVersion other = (DdmVersion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zte.gu.webtools.entity.DdmVersion[ id=" + id + " ]";
    }
    
}
