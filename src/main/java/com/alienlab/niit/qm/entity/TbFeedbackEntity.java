package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "tb_feedback", schema = "qualitymonitor", catalog = "")
public class TbFeedbackEntity {
    @ApiModelProperty(value="反馈id")
    private long id;
    @Id
    @javax.persistence.Basic
    @javax.persistence.Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ApiModelProperty(value="反馈者用户名")
    private String username;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty(value="反馈内容")
    private String content;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ApiModelProperty(value="反馈人qq")
    private String qq;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @ApiModelProperty(value="反馈人邮箱地址")
    private String email;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbFeedbackEntity that = (TbFeedbackEntity) object;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
