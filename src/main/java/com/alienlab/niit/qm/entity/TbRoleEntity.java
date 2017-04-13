package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "tb_role", schema = "qualitymonitor", catalog = "")
public class TbRoleEntity {
    @ApiModelProperty(value="角色编码")
    private long roleId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @ApiModelProperty(value="角色名称")
    private String roleName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ApiModelProperty(value="角色首页")
    private String roleIndex;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "role_index")
    public String getRoleIndex() {
        return roleIndex;
    }

    public void setRoleIndex(String roleIndex) {
        this.roleIndex = roleIndex;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbRoleEntity that = (TbRoleEntity) object;

        if (roleId != that.roleId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (roleIndex != null ? !roleIndex.equals(that.roleIndex) : that.roleIndex != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleIndex != null ? roleIndex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TbRoleEntity{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleIndex='" + roleIndex + '\'' +
                '}';
    }
}
