package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static java.awt.font.TransformAttribute.IDENTITY;

/**
 * Created by Master QB on 2017/3/14.
 */
@ApiModel(value="菜单Entity")
@javax.persistence.Entity
@javax.persistence.Table(name = "tb_menu", schema = "qualitymonitor", catalog = "")
public class TbMenuEntity {
    @ApiModelProperty(value="菜单编码")
    private long menuId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "menu_id")

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @ApiModelProperty(value="菜单名称")
    private String menuName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @ApiModelProperty(value="菜单类型（标题、内部链接、外部链接、命令）")
    private String menuType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_type")
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @ApiModelProperty(value="父类编码")
    private Integer menuPid;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_pid")
    public Integer getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Integer menuPid) {
        this.menuPid = menuPid;
    }

    @ApiModelProperty(value="菜单内容（链接）")
    private String menuContent;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_content")
    public String getMenuContent() {
        return menuContent;
    }

    public void setMenuContent(String menuContent) {
        this.menuContent = menuContent;
    }

    @ApiModelProperty(value="菜单属性（预留）")
    private String menuAttr;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_attr")
    public String getMenuAttr() {
        return menuAttr;
    }

    public void setMenuAttr(String menuAttr) {
        this.menuAttr = menuAttr;
    }

    @ApiModelProperty(value="状态")
    private String menuStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_status")
    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    @ApiModelProperty(value="排序")
    private String menuSort;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "menu_sort")
    public String getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(String menuSort) {
        this.menuSort = menuSort;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        TbMenuEntity that = (TbMenuEntity) object;

        if (menuId != that.menuId) return false;
        if (menuName != null ? !menuName.equals(that.menuName) : that.menuName != null) return false;
        if (menuType != null ? !menuType.equals(that.menuType) : that.menuType != null) return false;
        if (menuPid != null ? !menuPid.equals(that.menuPid) : that.menuPid != null) return false;
        if (menuContent != null ? !menuContent.equals(that.menuContent) : that.menuContent != null) return false;
        if (menuAttr != null ? !menuAttr.equals(that.menuAttr) : that.menuAttr != null) return false;
        if (menuStatus != null ? !menuStatus.equals(that.menuStatus) : that.menuStatus != null) return false;
        if (menuSort != null ? !menuSort.equals(that.menuSort) : that.menuSort != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (menuId ^ (menuId >>> 32));
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (menuType != null ? menuType.hashCode() : 0);
        result = 31 * result + (menuPid != null ? menuPid.hashCode() : 0);
        result = 31 * result + (menuContent != null ? menuContent.hashCode() : 0);
        result = 31 * result + (menuAttr != null ? menuAttr.hashCode() : 0);
        result = 31 * result + (menuStatus != null ? menuStatus.hashCode() : 0);
        result = 31 * result + (menuSort != null ? menuSort.hashCode() : 0);
        return result;
    }
}
