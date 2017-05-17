/**
 * Created by Administrator on 2017/5/15.
 */
(function(){
    'use strict';
    var satisfaction_module=angular.module("qm.stu_satisfaction",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    satisfaction_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stu_satisfaction', {
            url: '/satisfaction',
            title: '学生评教',
            templateUrl: "quality/student/satisfaction/satisfaction.html",
            controller:"satisfactionController"
        });
    }]);

    satisfaction_module.factory("satisfactionResource",["$resource",function($resource){
        var service = $resource('../qm-api/qmstusurvet', {}, {
            'getQmSurveyByTermNoAndStuNo1': { method: 'GET',url:"../qm-api/qmstusurvet/findQmStuSurvey1"},
            'getQmSurveyByTermNoAndStuNo2': { method: 'GET',url:"../qm-api/qmstusurvet/findQmStuSurvey2"},
            'modQmSurveyByTermNoAndStuNo1': { method: 'GET',url:"../qm-api/qmstusurvet/modifyQmStuSurvey1"},
            'modQmSurveyByTermNoAndStuNo2': { method: 'GET',url:"../qm-api/qmstusurvet/modifyQmStuSurvey2"},
        });
        return service;
    }])

    satisfaction_module.controller("satisfactionController",["$scope","satisfactionResource","$cookieStore",function($scope,satisfactionResource,$cookieStore){
        console.log("sss");
        satisfactionResource.getQmSurveyByTermNoAndStuNo1({
            termNo:$cookieStore.get('currentTerm').termNo,
            stuNo:$cookieStore.get('user').account
        },function(result){
            $scope.stuSurvey1=result;
            console.log(result)
        },function(result){
            console.log("获取课程设置满意度失败",result);
        });

        satisfactionResource.getQmSurveyByTermNoAndStuNo2({
            termNo:$cookieStore.get('currentTerm').termNo,
            stuNo:$cookieStore.get('user').account
        },function(result){
            $scope.stuSurvey2=result;
            console.log(result)
        },function(result){
            console.log("获取课程教学失满意度失败",result);
        });

        $scope.modifySatisfaction = showModifySatisfaction;
        function showModifySatisfaction() {
            satisfactionResource.modQmSurveyByTermNoAndStuNo1({
                termNo:$cookieStore.get('currentTerm').termNo,
                stuNo:$cookieStore.get('user').account,
                surveyResult:$scope.stuSurvey1.surveyResult
            },function(result){
                $scope.stuSurvey2=result;
                console.log(result)
            },function(result){
                console.log("获取课程教学失满意度失败",result);
            });

            satisfactionResource.modQmSurveyByTermNoAndStuNo2({
                termNo:$cookieStore.get('currentTerm').termNo,
                stuNo:$cookieStore.get('user').account,
                surveyResult:$scope.stuSurvey2.surveyResult
            },function(result){
                $scope.stuSurvey2=result;
                console.log(result)
            },function(result){
                console.log("获取课程教学失满意度失败",result);
            });
        }


    }]);




})();
