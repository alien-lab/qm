/**
 * Created by asus on 2017/4/15.
 */
(function(){
    'use strict';
    var evaluation_module=angular.module("qm.stuevaluation",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    evaluation_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stuevaluation', {
            url: '/stuevaluation',
            title: '学生评教',
            templateUrl: "quality/student/evaluation/evaluation.html",
            controller:"evaluationController"
        });
    }]);

    evaluation_module.controller("evaluationController",["$scope","$uibModal","evaluationService",function($scope,$uibModal,evaluationService){
        evaluationService.loadCourses("stuno","20161",function(result){
            $scope.myCourses=result;
        });
        $scope.showeva = function () {
            var teacherInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/student/evaluation/detailevaluation.html",
                controller: 'detailevaluationController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }
    }]);

    evaluation_module.service("evaluationService",["evaluationResource",function(evaluationResource){
        this.loadCourses=function(stuNo,termNo,callback){
            //evaluationResource.query();
            var courses=[
                {
                    courseName:"轻量级框架技术",
                    teacherNo:"12001001",
                    teacherName:"许丽花",
                    courseNo:"12547",
                    status:"未评教"
                },{
                    courseName:"毛泽东思想政治理论课之概念",
                    teacherNo:"12001001",
                    teacherName:"黄英",
                    courseNo:"12547",
                    status:"已评教"
                },{
                    courseName:"轻量级框架技术",
                    teacherNo:"12001001",
                    teacherName:"许丽花",
                    courseNo:"12547",
                    status:"未评教"
                },{
                    courseName:"web前端框架技术",
                    teacherNo:"12001001",
                    teacherName:"凌方",
                    courseNo:"12547",
                    status:"未评教"
                },{
                    courseName:"移动开发技术",
                    teacherNo:"12001001",
                    teacherName:"王辰",
                    courseNo:"12547",
                    status:"未评教"
                },{
                    courseName:"【个性化】UE设计",
                    teacherNo:"12001001",
                    teacherName:"郭凡",
                    courseNo:"12547",
                    status:"未评教"
                },{
                    courseName:"【个性化】软件测试项目实战",
                    teacherNo:"12001001",
                    teacherName:"郭雷",
                    courseNo:"12547",
                    status:"未评教"
                }
            ];
            if(callback){
                callback(courses);
            }
        }
    }])

    evaluation_module.factory("evaluationResource",["$resource",function($resource){
        var service = $resource('../qm-api/stu/evaluation', {}, {

        });
        return service;
    }])


    evaluation_module.controller("detailevaluationController",["$scope","$uibModalInstance",function ($scope,$uibModalInstance) {
        //取消模态框
        $scope.cancel = function(){
            $uibModalInstance.dismiss('cancel');
        }
    }]);

})();
