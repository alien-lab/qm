package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Master QB on 2017/4/18.
 */
public interface CourseService {

    //新增课程
    public  boolean addCourse(String courseNo,String courseName,int studentNumber,String department,
     String courseType,String courseAttr,String courseWeeks,int courseHours,String courseTerm,String tealoginname,
                              String checkedclass, String checkedsections) throws  Exception;

    //根据学期和部门获取课程
    List<CourseDto> getCoursesByTermAndDepartment(String termNo, String depNo,Pageable page)throws  Exception;

    public boolean deleteCourseByTaskNo(long taskNo) throws  Exception;


}
