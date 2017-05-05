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
            templateUrl: "quality/inspectorcenter/teacherlectures/teachtask.html",
            controller:"teachtaskscheController"
        });
    }]);

    addsche_module.controller("teachtaskscheController",["$scope","$state","$uibModal",function($scope,$state,$uibModal){

        $scope.goBack = function () {
            $state.go("qm.lecturesche");
        }

        $scope.addSche = function () {
            var addscheInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/inspectorcenter/teacherlectures/addsche.html",
                controller: 'addscheController',
                bindToController: true,
                size: "md",
                backdrop: false
            });

        }


    }]);


})();
