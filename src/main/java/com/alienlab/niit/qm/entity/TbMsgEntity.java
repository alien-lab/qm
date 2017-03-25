package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "tb_msg", schema = "qualitymonitor", catalog = "")
public class TbMsgEntity {
    @ApiModelProperty(value="消息id")
    private long msgId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "msg_id")
    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    @ApiModelProperty(value="消息类型")
    private String msgType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_type")
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @ApiModelProperty(value="消息标题")
    private String msgTitle;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_title")
    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    @ApiModelProperty(value="消息内容")
    private String msgContent;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_content")
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @ApiModelProperty(value="消息发送时间")
    private Timestamp msgTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_time")
    public java.sql.Timestamp getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(java.sql.Timestamp msgTime) {
        this.msgTime = msgTime;
    }

    @ApiModelProperty(value="消息发送人")
    private String msgFrom;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_from")
    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    @ApiModelProperty(value="消息链接")
    private String msgLink;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_link")
    public String getMsgLink() {
        return msgLink;
    }

    public void setMsgLink(String msgLink) {
        this.msgLink = msgLink;
    }

    @ApiModelProperty(value="消息读取状态")
    private String msgStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_status")
    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    @ApiModelProperty(value="消息接收人")
    private String msgTo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_to")
    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    @ApiModelProperty(value="消息接收类型")
    private String msgToType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "msg_to_type")
    public String getMsgToType() {
        return msgToType;
    }

    public void setMsgToType(String msgToType) {
        this.msgToType = msgToType;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbMsgEntity that = (TbMsgEntity) object;

        if (msgId != that.msgId) return false;
        if (msgType != null ? !msgType.equals(that.msgType) : that.msgType != null) return false;
        if (msgTitle != null ? !msgTitle.equals(that.msgTitle) : that.msgTitle != null) return false;
        if (msgContent != null ? !msgContent.equals(that.msgContent) : that.msgContent != null) return false;
        if (msgTime != null ? !msgTime.equals(that.msgTime) : that.msgTime != null) return false;
        if (msgFrom != null ? !msgFrom.equals(that.msgFrom) : that.msgFrom != null) return false;
        if (msgLink != null ? !msgLink.equals(that.msgLink) : that.msgLink != null) return false;
        if (msgStatus != null ? !msgStatus.equals(that.msgStatus) : that.msgStatus != null) return false;
        if (msgTo != null ? !msgTo.equals(that.msgTo) : that.msgTo != null) return false;
        if (msgToType != null ? !msgToType.equals(that.msgToType) : that.msgToType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (msgId ^ (msgId >>> 32));
        result = 31 * result + (msgType != null ? msgType.hashCode() : 0);
        result = 31 * result + (msgTitle != null ? msgTitle.hashCode() : 0);
        result = 31 * result + (msgContent != null ? msgContent.hashCode() : 0);
        result = 31 * result + (msgTime != null ? msgTime.hashCode() : 0);
        result = 31 * result + (msgFrom != null ? msgFrom.hashCode() : 0);
        result = 31 * result + (msgLink != null ? msgLink.hashCode() : 0);
        result = 31 * result + (msgStatus != null ? msgStatus.hashCode() : 0);
        result = 31 * result + (msgTo != null ? msgTo.hashCode() : 0);
        result = 31 * result + (msgToType != null ? msgToType.hashCode() : 0);
        return result;
    }
}
