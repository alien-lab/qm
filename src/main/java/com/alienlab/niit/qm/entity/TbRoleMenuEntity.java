package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "tb_role_menu", schema = "qualitymonitor", catalog = "")
public class TbRoleMenuEntity {

    @ApiModelProperty(value="id主键")
    @Id
    private long id;
    @ApiModelProperty(value="用户编码")
    private long roleId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @ApiModelProperty(value="菜单编码")
    private String menuId;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_id")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbRoleMenuEntity that = (TbRoleMenuEntity) object;

        if (roleId != that.roleId) return false;
        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        return result;
    }
}
