/**
 * Created by Master QB on 2017/5/4.
 */
(function(){
    'use strict';
    var addsche_module=angular.module("qm.addsche",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    addsche_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.addsche', {
            url: '/addsche',
            title: '督学听课计划',
            templateUrl: "quality/inspectorcenter/teacherlectures/addsche.html",
            controller:"addscheController"
        });
    }]);

    addsche_module.controller("addscheController",["$scope","$uibModalInstance",function($scope,$uibModalInstance){
        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

        activate();
        function activate() {
            $scope.today = function() {
                $scope.dt = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.dt = null;
            };

            $scope.toggleMin = function() {
                $scope.minDate = $scope.minDate ? null : new Date();
            };
            $scope.toggleMin();

            $scope.open = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
            };

            $scope.dateOptions = {
                formatYear: 'yy',
                startingDay: 1
            };
            $scope.initDate = new Date('2019-10-20');
            $scope.formats = [ 'yyyy/MM/dd', 'yyyy.MM.dd', 'shortDate'];
            $scope.format = $scope.formats[1];
        }

    }]);


})();
