/**
 * Created by Master QB on 2017/5/2.
 */
(function(){
    'use strict';
    var tealecture_module=angular.module("qm.lectureform",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    tealecture_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lectureform', {
            url: '/lectureform',
            title: '填写听课表单',
            templateUrl: "quality/inspectorcenter/teacherlectures/form.html",
            controller:"lecturesformController"

        });
    }]);



    tealecture_module.controller("lecturesformController",["$scope","$state",function($scope,$state){

    }]);

})();