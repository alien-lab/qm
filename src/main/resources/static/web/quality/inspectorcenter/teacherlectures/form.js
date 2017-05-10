/**
 * Created by Master QB on 2017/5/2.
 */
(function(){
    'use strict';
    var tealectureform_module=angular.module("qm.lectureform",['ui.router','ngDialog']);//ui.router模块作为主应用模块的依赖模块
    tealectureform_module.factory("ruleinstance",function(){return {}});
    tealectureform_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lectureform', {
            url: '/lectureform',
            title: '填写听课表单',
            templateUrl: "quality/inspectorcenter/teacherlectures/form.html",
            controller:"lecturesformController"

        });
    }]);


    tealectureform_module.factory("qmMsterResource",["$resource",function($resource){
        var service = $resource('../qm-api/master', {}, {
            getCaredTeachers: { method: 'GET',isArray:true},
            getCaredTeacherCourse: { method: 'GET',isArray:true ,url:'../qm-api/master/caredteachercourse'},
            getRules: { method: 'GET',isArray:true ,url:'../qm-api/master/rule'},
            getConfig: { method: 'GET',isArray:true ,url:'../qm-api/master/config'},
            getSches: { method: 'GET',isArray:true,url:'../qm-api/master/listenplan'},
            getAllSches: { method: 'GET',isArray:true,url:'../qm-api/master/listenplans'},
            getTeacherCourseList: { method: 'GET',isArray:true,url:'../qm-api/master/termteachercourseDto'},
            getdepTeachers:{ method: 'GET',url:'../qm-api/master/teacherDto'},
            getdayCourses:{ method: 'GET',url:'../qm-api/master/daycourse'},


        });
        return service;

    }]);



    tealectureform_module.controller("lecturesformController",["$scope","$state","ngDialog","$cookieStore","$uibModal","qmMsterResource","ruleinstance",function($scope,$state,ngDialog,$cookieStore,$uibModal,qmMsterResource,ruleinstance){

        $scope.weekDay="";
        //从cookies获得当前用户
        $scope.masterTeacher = {
            account : $cookieStore.get('user').account,
            name:$cookieStore.get('user').name,
            usertype: $cookieStore.get('user').type
        }
        $scope.teacherString=0;
        $scope.dayString=0;
        $scope.lectureWeeks=[];
        //用于存放关注的教师
        $scope.caredteachers =[]
        function  ObjweekStory(id,name) //创建对象function
        {
            this.weekid = id;
            this.weekname= name;
        }
        function  teacherStory(id,name) //创建对象function
        {
            this.id = id;
            this.name= name;
            this.checked = false;
        }
        //从cookies中获得当前周次
        var  currentweek = $cookieStore.get('currentWeek').currentweek;
        for (var i=currentweek;i>=1;i--){
            var oneweek = new ObjweekStory(i,"第"+i+"周");
            $scope.lectureWeeks.push(oneweek);

        }
        $scope.scheWeek = $scope.lectureWeeks[0].weekid;
        //获得初始日期下的听课计划
        qmMsterResource.getSches({
            masterNo:$cookieStore.get('user').account,
            termNo:$cookieStore.get('currentTerm').termNo,
            selectWeek:$scope.scheWeek
        },function(result){
            console.log(result);
            if (result.length==0||result==null){
                $scope.tkjhPlan=[];
            }else {
                $scope.tkjhPlan = result;
            }
        },function(result){
            console.log("督学计划听课列表获取失败",result);
        });
        //添加听课计划
        $scope.addSche = function () {
            $state.go("qm.lecturesche");
        }

        //返回上一步
        $scope.goback = function () {
            $state.go("qm.tealecture");
        }
        //按照周次获得当前督学的计划听课记录
        function getSches() {
            qmMsterResource.getSches({
                masterNo:$cookieStore.get('user').account,
                termNo:$cookieStore.get('currentTerm').termNo,
                selectWeek:$scope.scheWeek
            },function(result){
                console.log(result);
                if (result.length==0||result==null){
                    $scope.tkjhPlan=[];
                    var dialog = ngDialog.open({
                        template: '<h4 style="text-align: center">对不起，您在第 '+$scope.scheWeek+' 周的没有听课计划</h4>',
                        plain: true,
                        closeByDocument: false,
                        closeByEscape: false,
                        controller:'lecturesformController'
                    });
                }else {
                    $scope.tkjhPlan = result;
                }
            },function(result){
                console.log("督学计划听课列表获取失败",result);
            });
        }

        $scope.openTimed = function () {
                $scope.tkjhPlan=[];
               getSches();
        };

        //
        $scope.teaScore =function (taskNo,courseName,teacherName) {
            ruleinstance.ruletype = 1;
            ruleinstance.courseName = courseName;
            ruleinstance.teacherName = teacherName;
            ruleinstance.taskNo = taskNo;
                var teaScoreinfo = $uibModal.open({
                    animation: true,
                    templateUrl: "quality/inspectorcenter/teacherlectures/teacherscore.html",
                    controller: 'teacherscoreController',
                    bindToController: true,
                    size: "md",
                    backdrop: false
                });
        }


        //查询教师姓名
        $scope.searchTeacher = function () {
            $scope.teacherString=1;
            $scope.dayString=0;
            $scope.searchteacherCourse=[];
            $scope.pageteacherCourse=[];
        }

        $scope.searchTeacherCourse =function () {
            $scope.searchteacherCourse=[];
            $scope.pageteacherCourse=[];
            //获得所点击的教师的课程
            qmMsterResource.getCaredTeacherCourse({
                teacherNo:$scope.selectedTeacher.teacherNo,
                termNo:$cookieStore.get('currentTerm').termNo
            },function(result){
                console.log(result);
                if (result.length!=0){
                    $scope.searchteacherCourse = result;
                }else {
                    var dialog = ngDialog.open({
                        template: '<h4 style="text-align: center">对不起，教师 '+teacherName+' 在本学期无课程</h4>',
                        plain: true,
                        closeByDocument: false,
                        closeByEscape: false,
                        controller:'lecturesformController'
                    });
                }
            },function(result){
                console.log("教师课程列表获取失败",result);
            });
        }

        //根据工作日查询
        $scope.searchDay = function () {
            $scope.searchteacherCourse=[];
            $scope.dayString=1;
            $scope.teacherString=0;
        }

        //获得当前学期 当前督学所关注的教师
        qmMsterResource.getCaredTeachers({
            masterNo:$scope.masterTeacher.account,
            termNo:$cookieStore.get('currentTerm').termNo
        },function(result){
            console.log(result);
            for (var i =0;i<result.length;i++){
                var teacher = new teacherStory(result[i].teacherNo,result[i].teacherName);
                $scope.caredteachers.push(teacher);
            }
        },function(result){
            console.log("教师列表获取失败",result);
        });

        //选择其中一个关注的教师
        $scope.chooseCaredTeacher = function (teacherNo,teacherName) {
            $scope.pageteacherCourse=[];
            for (var i=0;i<$scope.caredteachers.length;i++){
                if ($scope.caredteachers[i].id ==teacherNo) {
                $scope.caredteachers[i].checked = true;
            }
                $scope.caredteachers[i].checked = false;
            }
            //获得所点击的教师的课程
            qmMsterResource.getCaredTeacherCourse({
                teacherNo:teacherNo,
                termNo:$cookieStore.get('currentTerm').termNo
            },function(result){
                console.log(result);
                if (result.length!=0){
                    $scope.caredteacherCourse = result;
               }else {
                    var dialog = ngDialog.open({
                        template: '<h4 style="text-align: center">对不起，教师 '+teacherName+' 在本学期无课程</h4>',
                        plain: true,
                        closeByDocument: false,
                        closeByEscape: false,
                        controller:'lecturesformController'
                    });
               }
            },function(result){
                console.log("教师课程列表获取失败",result);
            });

        }


        //查询教师信息
        $scope.refreshTeachers=function(keyword){
            if (keyword!=null||keyword!=""){
                qmMsterResource.getdepTeachers({
                    keyword:keyword,
                    index:0,
                    length:20,
                    termNo:$cookieStore.get('currentTerm').termNo,
                    masterNo:$scope.masterTeacher.account,
                },function(result){
                    console.log(result)
                    return $scope.people=result.content;
                },function(result){
                    console.log("查询教师信息失败",result);
                });
            }
        }

        //根据周几查询
        $scope.searchweekDay= function (string,index,length) {
            $scope.caredteacherCourse=[];
            $scope.weekDay = string;
            qmMsterResource.getdayCourses({
                keyword:string,
                index:0,
                length:6,
                termNo:$cookieStore.get('currentTerm').termNo,
                masterNo:$scope.masterTeacher.account,
            },function(result){
                console.log(result)
                $scope.pageteacherCourse =result;
            },function(result){
                console.log("督导根据周几获取本部门的教师课程信息",result);
            });
        }

        $scope.loadData=function (index,length) {
            console.log($scope.weekDay);
            qmMsterResource.getdayCourses({
                keyword:$scope.weekDay,
                index:index,
                length:length,
                termNo:$cookieStore.get('currentTerm').termNo,
                masterNo:$scope.masterTeacher.account,
            },function(result){
                console.log(result)
                $scope.pageteacherCourse=[];
                $scope.pageteacherCourse =result;
            },function(result){
                console.log("督导根据周几获取本部门的教师课程信息",result);
            });
        }
    }]);
})();