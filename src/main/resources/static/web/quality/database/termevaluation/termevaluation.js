/**
 * Created by Master QB on 2017/3/23.
 */

(function(){
    'use strict';
    var termevaluation_module=angular.module("qm.termevaluation",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    termevaluation_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.termevaluation', {
            url: '/termevaluation',
            title: '学年学期管理',
            templateUrl: "quality/database/termevaluation/termevaluation.html",
            controller:"termevaluationController"

        });
    }]);

    termevaluation_module.controller("termevaluationController",["$scope","$uibModal",function($scope,$uibModal){
        $scope.pagetitle="学年学期管理";

        //学期开关
        $scope.termsetting = showTermSetting;
        function showTermSetting(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/termevaluation/termSetting.html",
                controller: 'termsettingController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            modalInstance.result.then(function (data) {
                //添加保存成功
                if(data.result > 0) {
                    var farm = data.data;
                    $scope.farm_data.content.push(farm);
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })
        }

        //添加学年学期
        $scope.addterm = showAddTerm;
        function showAddTerm(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/termevaluation/addTerm.html",
                controller: 'addTermController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            modalInstance.result.then(function (data) {
                //添加保存成功
                if(data.result > 0) {
                    var farm = data.data;
                    $scope.farm_data.content.push(farm);
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })
        }
    }]);

    termevaluation_module.controller("addTermController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加学年学期";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

    termevaluation_module.controller("termsettingController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.pagetitle="功能开关";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }


    }]);

})();