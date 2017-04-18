package com.alienlab.niit.qm.service;

/**
 * Created by Master QB on 2017/4/18.
 */
public interface CourseService {

    //新增课程
    public  boolean addCourse(String courseNo,String courseName,int studentNumber,String department,
     String courseType,String courseAttr,String courseWeeks,int courseHours,String courseTerm,String tealoginname,
                              String checkedclass, String checkedsections) throws  Exception;



}
