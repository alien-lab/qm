/**
 * Created by Master QB on 2017/5/2.
 */
(function(){
    'use strict';
    var tealecture_module=angular.module("qm.tealecture",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    tealecture_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.tealecture', {
            url: '/tealecture',
            title: '督学听课',
            templateUrl: "quality/inspectorcenter/teacherlectures/teacherlectures.html",
            controller:"teacherlecturesController"

        });
    }]);



    tealecture_module.controller("teacherlecturesController",["$scope","$state",function($scope,$state){

        //填写听课表单
        $scope.form = function () {
            $state.go("qm.lectureform");
        }
        //制定听课计划
        $scope.arranged = function () {
           $state.go("qm.lecturesche");
        }
        //关注的教师
        $scope.careteachers = function () {
           /* $state.go("qm.stuattendDetail");*/
        }
        //听课记录
        $scope.record = function () {
            /*$state.go("qm.stuattendDetail");*/
        }
        //常用语
        $scope.mychat = function () {
            /*$state.go("qm.stuattendDetail");*/
        }








    }]);

    })();