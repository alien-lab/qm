/**
 * Created by Master QB on 2017/4/27.
 */
(function(){
    'use strict';
    var stuattendDetail_module=angular.module("qm.stuattendDetail",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    stuattendDetail_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stuattendDetail', {
            url: '/stuattendDetail',
            title: '考勤详情',
            templateUrl: "quality/teachercenter/classattend/stuattendDeital.html",
            controller:"stuattendDeitalController"
        });
    }]);



    stuattendDetail_module.controller("stuattendDeitalController",["$scope","$state","$uibModal",function($scope,$state,$uibModal){
        $scope.goback = function () {
            $state.go("qm.classattend");
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


})();

