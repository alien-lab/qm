package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.TbRoleEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/4/13.
 */
public class UserRolerDto extends TbRoleEntity {
    @ApiModelProperty(value="用户是否拥有该角色项权限")
    boolean checked=false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    @Override
    public String toString() {
        return "UserRolerDto{" +
                "checked=" + checked +
                '}';
    }
}
