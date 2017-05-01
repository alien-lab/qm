package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.dto.CourseDetailDto;
import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.entity.dto.CourseListDto;
import org.omg.CORBA.PUBLIC_MEMBER;
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


    Page<CourseDto> findCourseBykeywordAndTermNoAndDepNo(String keyword, String termNo, String depNo, Pageable page)throws Exception;


    public CourseDetailDto getCourseDetailByTaskNo(long taskNo);

    //修改课程
    public  boolean updateCourse(long taskNo,  String courseNo,String courseName,int studentNumber,String department,
                              String courseType,String courseAttr,String courseWeeks,int courseHours,String courseTerm,String tealoginname,
                              String checkedclass, String checkedsections) throws  Exception;




    //根据学期，授课类型，课程周次，教师工号返回课程
    public List<CourseDetailDto> getCourseBytypeAndweekAndteacherNo(String termNo,String type,int week,String tascherNo);

    //根据学期，教工号返回课程
    public List<CourseListDto> getCourseByTermNoAndTeacherNo(String termNo,String tascherNo);



}
