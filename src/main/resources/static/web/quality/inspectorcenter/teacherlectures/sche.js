/**
 * Created by Master QB on 2017/5/3.
 */
(function(){
    'use strict';
    var lecturesche_module=angular.module("qm.lecturesche",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    lecturesche_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lecturesche', {
            url: '/lecturesche',
            title: '督学听课计划',
            templateUrl: "quality/inspectorcenter/teacherlectures/sche.html",
            controller:"lecturesScheController"
        });
    }]);

    lecturesche_module.controller("lecturesScheController",["$scope","$state","$uibModal",function($scope,$state,$uibModal){
        $scope.changeStatus =0;
        $scope.changeSche = function (index) {
            $scope.changeStatus = index;
        }
        $scope.searchTask = function () {
            $state.go("qm.teachtasksche");
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

        $scope.goBack = function () {
            $state.go("qm.tealecture");
        }
        $scope.deleteSche = function (index) {
         /*   vm.users.splice(index, 1);*/
        }

    }]);

})();