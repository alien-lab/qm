package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;

import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.entity.dto.TeachTaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Master QB on 2017/4/24.
 */
public interface BaseTeachTaskService {
    //根据年级和学生编号查询评教信息
    public List<TeachTaskDto> findByTermNoAndStuNo(String termNo,String stuNo);
}
