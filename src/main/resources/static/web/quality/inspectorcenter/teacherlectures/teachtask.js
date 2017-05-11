/**
 * Created by Master QB on 2017/5/4.
 */
(function(){
    'use strict';
    var addsche_module=angular.module("qm.teachtasksche",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    addsche_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.teachtasksche', {
            url: '/teachtasksche',
            title: '教师授课计划',
            params:{"teacherNo":null},
            templateUrl: "quality/inspectorcenter/teacherlectures/teachtask.html",
            controller:"teachtaskscheController"
        });
    }]);

    addsche_module.controller("teachtaskscheController",["$scope","$state","$uibModal","$stateParams","qmMsterResource","$cookieStore","lecturescheinstance",function($scope,$state,$uibModal,$stateParams,qmMsterResource,$cookieStore,lecturescheinstance){

        qmMsterResource.getTeacherCourseList({
            teacherNo:$stateParams.teacherNo,
            termNo:$cookieStore.get('currentTerm').termNo,
        },function(result){
            console.log(result);
            $scope.courseList = result;

        },function(result){
            console.log("获取教师本学期课程列表获取失败",result);
        });






        $scope.goBack = function () {
            $state.go("qm.lecturesche");
        }

        $scope.addSche = function (taskNo,teacherName,courseName,className) {
            var addscheInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/inspectorcenter/teacherlectures/addsche.html",
                controller: 'addscheController',
                bindToController: true,
                size: "md",
                backdrop: false
            });

            lecturescheinstance.status="新增";
            lecturescheinstance.taskNo=taskNo;
            lecturescheinstance.teacherName=teacherName;
            lecturescheinstance.courseName=courseName;
            lecturescheinstance.className=className;

        }


    }]);


})();
