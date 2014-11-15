/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johnny
 */
@Entity
@Table(name = "t_ddm_rru")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DdmRru.findAll", query = "SELECT d FROM DdmRru d"),
    @NamedQuery(name = "DdmRru.findById", query = "SELECT d FROM DdmRru d WHERE d.id = :id"),
    @NamedQuery(name = "DdmRru.findByVersion", query = "SELECT d FROM DdmRru d WHERE d.version = :version"),
    @NamedQuery(name = "DdmRru.findByColumnName", query = "SELECT d FROM DdmRru d WHERE d.columnName = :columnName"),
    @NamedQuery(name = "DdmRru.findByFieldName", query = "SELECT d FROM DdmRru d WHERE d.fieldName = :fieldName")})
public class DdmRru extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "version")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "column_name")
    private String columnName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "field_name")
    private String fieldName;

    public DdmRru() {
    }

    public DdmRru(Long id) {
        this.id = id;
    }

    public DdmRru(Long id, String version, String columnName, String fieldName) {
        this.id = id;
        this.version = version;
        this.columnName = columnName;
        this.fieldName = fieldName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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
        if (!(object instanceof DdmRru)) {
            return false;
        }
        DdmRru other = (DdmRru) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zte.gu.webtools.entity.DdmRru[ id=" + id + " ]";
    }
    
}
