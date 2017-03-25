package com.alienlab.niit.qm.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@ApiModel(value="用户实体类")
@Entity
@Table(name = "tb_user")
public class TbUserEntity {
    @ApiModelProperty(value="用户编码")
    private long userId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    @ApiModelProperty(value="用户登录名")
    private String userLoginname;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_loginname")
    public String getUserLoginname() {
        return userLoginname;
    }

    public void setUserLoginname(String userLoginname) {
        this.userLoginname = userLoginname;
    }

    @ApiModelProperty(value="用户密码")
    private String userPwd;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_pwd")
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @ApiModelProperty(value="用户名称")
    private String userName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ApiModelProperty(value="用户创建时间")
    private Timestamp userCreatetime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_createtime")
    public java.sql.Timestamp getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(java.sql.Timestamp userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    @ApiModelProperty(value="最后一次登录时间")
    private Timestamp userLastlogin;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_lastlogin")
    public java.sql.Timestamp getUserLastlogin() {
        return userLastlogin;
    }

    public void setUserLastlogin(java.sql.Timestamp userLastlogin) {
        this.userLastlogin = userLastlogin;
    }

    @ApiModelProperty(value="用户类型（管理员，普通用户）")
    private String userType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @ApiModelProperty(value="权限标识（ALL表示所有权限）")
    private String userPurview;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_purview")
    public String getUserPurview() {
        return userPurview;
    }

    public void setUserPurview(String userPurview) {
        this.userPurview = userPurview;
    }

    @ApiModelProperty(value="用户状态")
    private String userStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_status")
    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @ApiModelProperty(value="用户微信id")
    private String userWxid;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_wxid")
    public String getUserWxid() {
        return userWxid;
    }

    public void setUserWxid(String userWxid) {
        this.userWxid = userWxid;
    }

    @ApiModelProperty(value="用户微信头像地址")
    private String userWximg;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_wximg")
    public String getUserWximg() {
        return userWximg;
    }

    public void setUserWximg(String userWximg) {
        this.userWximg = userWximg;
    }

    @ApiModelProperty(value="用户邮箱地址")
    private String userEmail;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @ApiModelProperty(value="用户手机号码")
    private String userPhone;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_phone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @ApiModelProperty(value="用户qq号码")
    private String userQq;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_qq")
    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbUserEntity that = (TbUserEntity) object;

        if (userId != that.userId) return false;
        if (userLoginname != null ? !userLoginname.equals(that.userLoginname) : that.userLoginname != null)
            return false;
        if (userPwd != null ? !userPwd.equals(that.userPwd) : that.userPwd != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userCreatetime != null ? !userCreatetime.equals(that.userCreatetime) : that.userCreatetime != null)
            return false;
        if (userLastlogin != null ? !userLastlogin.equals(that.userLastlogin) : that.userLastlogin != null)
            return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (userPurview != null ? !userPurview.equals(that.userPurview) : that.userPurview != null) return false;
        if (userStatus != null ? !userStatus.equals(that.userStatus) : that.userStatus != null) return false;
        if (userWxid != null ? !userWxid.equals(that.userWxid) : that.userWxid != null) return false;
        if (userWximg != null ? !userWximg.equals(that.userWximg) : that.userWximg != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userQq != null ? !userQq.equals(that.userQq) : that.userQq != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userLoginname != null ? userLoginname.hashCode() : 0);
        result = 31 * result + (userPwd != null ? userPwd.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userCreatetime != null ? userCreatetime.hashCode() : 0);
        result = 31 * result + (userLastlogin != null ? userLastlogin.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (userPurview != null ? userPurview.hashCode() : 0);
        result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
        result = 31 * result + (userWxid != null ? userWxid.hashCode() : 0);
        result = 31 * result + (userWximg != null ? userWximg.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userQq != null ? userQq.hashCode() : 0);
        return result;
    }
}
