package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.BaseTaskScheEntity;
import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Master QB on 2017/4/25.
 */
@ApiModel(value="课程详情Dto")
public class CourseDetailDto extends BaseTeachTaskEntity{

    @ApiModelProperty(value="课程表节次地点")
    private List<BaseTaskScheEntity> sectionses;
    private String teacherName;
    private String className;
    private int stuAmount;
    private String depName;
    private String termName;
    private long sche_no;


    public List<BaseTaskScheEntity> getSectionses() {
        return sectionses;
    }

    public void setSectionses(List<BaseTaskScheEntity> sectionses) {
        this.sectionses = sectionses;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStuAmount() {
        return stuAmount;
    }

    public void setStuAmount(int stuAmount) {
        this.stuAmount = stuAmount;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public long getSche_no() {
        return sche_no;
    }

    public void setSche_no(long sche_no) {
        this.sche_no = sche_no;
    }
}
