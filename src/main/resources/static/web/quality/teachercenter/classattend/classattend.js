/**
 * Created by Master QB on 2017/4/27.
 */

(function(){
    'use strict';
    var classattend_module=angular.module("qm.classattend",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    classattend_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.classattend', {
            url: '/classattend',
            title: '课题考勤',
            templateUrl: "quality/teachercenter/classattend/classattend.html",
            controller:"classAttendController"
        });
    }]);

    classattend_module.controller("classAttendController",["$scope","$state","$uibModal","$cookieStore","CourseResource",function($scope,$state,$uibModal,$cookieStore,CourseResource){
        //从cookies获得当前用户
        $scope.classattendTeacher = {
            account : $cookieStore.get('user').account,
            name:$cookieStore.get('user').name,
            usertype: $cookieStore.get('user').type
        }
        $scope.sections = [
            {
                day:[{name:'1-2节',mian:true},{weekday:'周一',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'1-2节',courseNmae:'',shceNo:'',className:'',checked:false},{weekday:'周日',section:'1-2节',courseNmae:'',shceNo:'',className:'',checked:false}]},
            {
                day:[{name:'3-4节',mian:true},{weekday:'周一',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false}]},
            {
                day:[{name:'5-6节',mian:true},{weekday:'周一',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false}]},
            {
                day:[{name:'7-8节',mian:true},{weekday:'周一',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false}]},
            {
                day:[{name:'9-10节',mian:true},{weekday:'周一',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false}]},
        ];


        //课程周次
        $scope.subjectWeeks=[];

        //课程类型
        $scope.subjectTypes=[
            "讲授课","实训课","任选课"
        ];

        function  ObjweekStory(id,name) //创建对象function
        {
            this.weekid = id;
            this.weekname= name;
        }
        //默认为讲授课
        $scope.selectedSubjectType = $scope.subjectTypes[0];
        //从cookies中获得当前周次

        var  currentweek = $cookieStore.get('currentWeek').currentweek;
        for (var i=currentweek;i>=1;i--){
            var oneweek = new ObjweekStory(i,"第"+i+"周");
            $scope.subjectWeeks.push(oneweek);
        }
        //下拉菜单默认当前周
        $scope.selectedWeek = $scope.subjectWeeks[0].weekid;

        $scope.attendDetail = function (scheNo,courseName,className) {
            $state.go("qm.stuattendDetail",{scheNo:scheNo,className:className,courseName:courseName,type: $scope.selectedSubjectType,week:$scope.selectedWeek});
        }
        getCourse();

        function getCourse() {
            CourseResource.getteacherCourse({
                termNo:$cookieStore.get('currentTerm').termNo,
                type:$scope.selectedSubjectType,
                week:$scope.selectedWeek,
                teacherNo:$scope.classattendTeacher.account
            },function(result){
                console.log(result);
                for(var j =0;j<$scope.sections.length;j++){
                    for(var m = 0;m<8;m++){
                        var weekday = $scope.sections[j].day[m].weekday;
                        var section = $scope.sections[j].day[m].section;
                        for (var i=0;i<result.length;i++){
                            if (weekday==result[i].teacherName&&section==result[i].termName){
                                $scope.sections[j].day[m].courseNmae=result[i].courseName;
                                $scope.sections[j].day[m].className=result[i].className;
                                $scope.sections[j].day[m].shceNo=result[i].sche_no;
                                $scope.sections[j].day[m].checked = true;
                            }
                        }
                    }
                }
            },function(result){
                console.log("课程获取失败",result);
            });
        }


        //根据课程类型和周次查询本教师的所有课程
        $scope.searchCourse =function () {
            for(var j =0;j<$scope.sections.length;j++){
                for(var m = 0;m<8;m++){
                 $scope.sections[j].day[m].courseNmae='';
                 $scope.sections[j].day[m].className='';
                 $scope.sections[j].day[m].shceNo='';
                 $scope.sections[j].day[m].checked = false;
                }
            }
            getCourse();

        }
        $scope.attendrecord = function () {
            var addsubjectInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/teachercenter/classattend/attendrecord.html",
                controller: 'attendrecordController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }





    }]);




    classattend_module.controller("attendrecordController",["$scope","$uibModalInstance","$cookieStore","CourseResource","studentAttendResource","$state",function($scope,$uibModalInstance,$cookieStore,CourseResource,studentAttendResource,$state){
        $scope.courseList = [];
        $scope.attendanceList=[];
        $scope.currentWeek=$cookieStore.get("currentWeek").currentweek;
        function  ObjCourselistStory(scheno,name,week) //创建对象function
        {
            this.scheNo = scheno;
            this.name= name;
            this.week =week;

        }

        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.classattendTeacher = {
            account : $cookieStore.get('user').account,
            name:$cookieStore.get('user').name,
            usertype: $cookieStore.get('user').type
        }
        //根据学期，教师工号获得该教师在本学期的所有课程列表
        CourseResource.getTermteacherCourse({
            termNo:$cookieStore.get('currentTerm').termNo,
            teacherNo:$scope.classattendTeacher.account
        },function(result){
            console.log(result);
            for (var i=0;i<result.length;i++){
                var onecourse = new ObjCourselistStory(result[i].scheNo,result[i].courseName+"-"+result[i].courseType+"-"+result[i].className+"-"+result[i].scheSet,result[i].courseWeek);
                $scope.courseList.push(onecourse);
            }
            console.log($scope.courseList);
            $scope.selectedCourse = $scope.courseList[0].scheNo;
        },function(result){
            console.log("课程获取失败",result);
        });

        //初始列表展示
        studentAttendResource.AttendRecord({
            scheNo: $scope.selectedCourse,
            termNo:$cookieStore.get('currentTerm').termNo
        },function(result){
            console.log(result);
            $scope.attendanceList = result;
        },function(result){
            console.log("获取考勤记录列表失败",result);
        });


        //获取考勤记录列表
        $scope.chooseCourse = function () {
            studentAttendResource.AttendRecord({
                scheNo:$scope.selectedCourse,
                termNo:$cookieStore.get('currentTerm').termNo
            },function(result){
                console.log(result);
                $scope.attendanceList = result;
            },function(result){
                console.log("获取考勤记录列表失败",result);
            });

        }
      //进入考勤
        $scope.enterattend = function (courseName,className,scheNo,courseType,courseWeek) {
            $uibModalInstance.dismiss('cancel');
            $state.go("qm.stuattendDetail",{courseName:courseName,className:className,scheNo:scheNo,type: courseType,week:courseWeek});
        }
        
    }]);

})();
