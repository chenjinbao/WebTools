/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author chen
 */
@Entity
@Table(name = "t_ddm_action")
public class DdmAction extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "version")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "action_type")
    private String actionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "field_name")
    private String fieldName;

    public DdmAction() {
    }

    public DdmAction(Long id) {
        this.id = id;
    }

    public DdmAction(Long id, String version, String actionType, String fieldName) {
        this.id = id;
        this.version = version;
        this.actionType = actionType;
        this.fieldName = fieldName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
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
        if (!(object instanceof DdmAction)) {
            return false;
        }
        DdmAction other = (DdmAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zte.gu.webtools.entity.DdmAction[ id=" + id + " ]";
    }
    
}
