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
    evaluation_module.controller("evaluationController",["$scope","$uibModal",function($scope,$uibModal){
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

    evaluation_module.controller("detailevaluationController",["$scope","$uibModalInstance",function ($scope,$uibModalInstance) {
        //取消模态框
        $scope.cancel = function(){
            $uibModalInstance.dismiss('cancel');
        }
    }]);
})();