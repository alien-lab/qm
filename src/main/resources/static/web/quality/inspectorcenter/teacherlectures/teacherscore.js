/**
 * Created by Master QB on 2017/5/3.
 */
(function(){
    'use strict';
    var teacherscore_module=angular.module("qm.teacherscore",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    teacherscore_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.teacherscore', {
            url: '/teacherscore',
            title: '课堂教学质量评价表',
            templateUrl: "quality/inspectorcenter/teacherlectures/teacherscore.html",
            controller:"teacherscoreController"
        });
    }]);

    teacherscore_module.controller("teacherscoreController",["$scope","$uibModalInstance",function($scope,$uibModalInstance){
        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);


})();
