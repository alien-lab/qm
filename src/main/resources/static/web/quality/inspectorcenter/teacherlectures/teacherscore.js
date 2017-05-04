/**
 * Created by Master QB on 2017/5/3.
 */
(function(){
    'use strict';
    var teacherscore_module=angular.module("qm.teacherscore",['ui.router','ui.bootstrap-slider']);//ui.router模块作为主应用模块的依赖模块
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

        $scope.slidervalue1 = 5;

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
            $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
            $scope.format = $scope.formats[0];
        }


    }]);


})();
