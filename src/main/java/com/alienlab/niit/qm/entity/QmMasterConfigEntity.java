package com.alienlab.niit.qm.entity;

import javax.persistence.GeneratedValue;
import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_master_config", schema = "qualitymonitor", catalog = "")
public class QmMasterConfigEntity {
    private long configNo;

    @GeneratedValue
    @javax.persistence.Id
    @javax.persistence.Column(name = "config_no")
    public long getConfigNo() {
        return configNo;
    }

    public void setConfigNo(long configNo) {
        this.configNo = configNo;
    }

    private String masterNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "master_no")
    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo;
    }

    private String content;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Timestamp configTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "config_time")
    public java.sql.Timestamp getConfigTime() {
        return configTime;
    }

    public void setConfigTime(java.sql.Timestamp configTime) {
        this.configTime = configTime;
    }

    private String configType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "config_type")
    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmMasterConfigEntity that = (QmMasterConfigEntity) object;

        if (configNo != that.configNo) return false;
        if (masterNo != null ? !masterNo.equals(that.masterNo) : that.masterNo != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (configTime != null ? !configTime.equals(that.configTime) : that.configTime != null) return false;
        if (configType != null ? !configType.equals(that.configType) : that.configType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (configNo ^ (configNo >>> 32));
        result = 31 * result + (masterNo != null ? masterNo.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (configTime != null ? configTime.hashCode() : 0);
        result = 31 * result + (configType != null ? configType.hashCode() : 0);
        return result;
    }
}
