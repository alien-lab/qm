package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Master QB on 2017/3/31.
 */
public class MenuDto extends TbMenuEntity {
    @ApiModelProperty(value="子菜单项")
    private List <TbMenuEntity> childmenuEntity;


    public List<TbMenuEntity> getChildmenuEntity() {
        return childmenuEntity;
    }

    public void setChildmenuEntity(List<TbMenuEntity> childmenuEntity) {
        this.childmenuEntity = childmenuEntity;
    }


}
