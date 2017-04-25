package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/4/26.
 */
@ApiModel(value="班级名称维护Dto")
public class ClassNameDto {
    @ApiModelProperty(value="班级编号")
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassNameDto that = (ClassNameDto) o;

        return className.equals(that.className);
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    @Override
    public String toString() {
        return "ClassNameDto{" +
                "className='" + className + '\'' +
                '}';
    }
}
