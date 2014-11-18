/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 10115701
 */
@Entity
@Table(name = "t_rfa_freqscantemplate")
public class RfaFreqscanTemplate extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "version")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "zh_file")
    private String zhFile;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "en_file")
    private String enFile;

    public RfaFreqscanTemplate() {
    }

    public RfaFreqscanTemplate(Long id) {
        this.id = id;
    }

    public RfaFreqscanTemplate(Long id, String version, String zhFile, String enFile) {
        this.id = id;
        this.version = version;
        this.zhFile = zhFile;
        this.enFile = enFile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getZhFile() {
        return zhFile;
    }

    public void setZhFile(String zhFile) {
        this.zhFile = zhFile;
    }

    public String getEnFile() {
        return enFile;
    }

    public void setEnFile(String enFile) {
        this.enFile = enFile;
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
        if (!(object instanceof RfaFreqscanTemplate)) {
            return false;
        }
        RfaFreqscanTemplate other = (RfaFreqscanTemplate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zte.gu.webtools.entity.RfaReqscanTemplate[ id=" + id + " ]";
    }
    
}
