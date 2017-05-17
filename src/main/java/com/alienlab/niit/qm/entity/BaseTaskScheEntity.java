package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_task_sche", schema = "qualitymonitor", catalog = "")
public class BaseTaskScheEntity {
    @ApiModelProperty(value="课程表编号")
    private long scheNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "sche_no")
    @GeneratedValue
    public long getScheNo() {
        return scheNo;
    }

    public void setScheNo(long scheNo) {
        this.scheNo = scheNo;
    }

    @ApiModelProperty(value="教学任务编号")
    private Long taskNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "task_no")
    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    @ApiModelProperty(value="节次（K31,K44）")
    private String scheSet;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "sche_set")
    public String getScheSet() {
        return scheSet;
    }

    public void setScheSet(String scheSet) {
        this.scheSet = scheSet;
    }

    @ApiModelProperty(value="上课地点")
    private String scheAddr;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "sche_addr")
    public String getScheAddr() {
        return scheAddr;
    }

    public void setScheAddr(String scheAddr) {
        this.scheAddr = scheAddr;
    }

    private Timestamp dataTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "data_time")
    public java.sql.Timestamp getDataTime() {
        return dataTime;
    }

    public void setDataTime(java.sql.Timestamp dataTime) {
        this.dataTime = dataTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseTaskScheEntity that = (BaseTaskScheEntity) object;

        if (scheNo != that.scheNo) return false;
        if (taskNo != null ? !taskNo.equals(that.taskNo) : that.taskNo != null) return false;
        if (scheSet != null ? !scheSet.equals(that.scheSet) : that.scheSet != null) return false;
        if (scheAddr != null ? !scheAddr.equals(that.scheAddr) : that.scheAddr != null) return false;
        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (scheNo ^ (scheNo >>> 32));
        result = 31 * result + (taskNo != null ? taskNo.hashCode() : 0);
        result = 31 * result + (scheSet != null ? scheSet.hashCode() : 0);
        result = 31 * result + (scheAddr != null ? scheAddr.hashCode() : 0);
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseTaskScheEntity{" +
                "scheNo=" + scheNo +
                ", taskNo=" + taskNo +
                ", scheSet='" + scheSet + '\'' +
                ", scheAddr='" + scheAddr + '\'' +
                ", dataTime=" + dataTime +
                '}';
    }
}
