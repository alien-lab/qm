package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 橘 on 2017/4/7.
 */
@ApiModel(value="角色菜单Dto")
public class RoleMenuDto extends TbMenuEntity {
    @ApiModelProperty(value="子菜单项")
    private List<RoleMenuDto> childmenuEntity;
    public List<RoleMenuDto> getChildmenuEntity() {
        return childmenuEntity;
    }

    public void setChildmenuEntity(List<RoleMenuDto> childmenuEntity) {
        this.childmenuEntity = childmenuEntity;
    }
    @ApiModelProperty(value="角色是否拥有该菜单项权限")
    boolean checked=false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "RoleMenuDto{" +
                "子菜单" + childmenuEntity +
                ", 是否有权限=" + checked +
                '}';
    }
}
