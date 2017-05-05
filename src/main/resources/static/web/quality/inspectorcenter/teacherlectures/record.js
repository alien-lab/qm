/**
 * Created by Master QB on 2017/5/4.
 */

(function(){
    'use strict';
    var lecturerecord_module=angular.module("qm.lecturerecord",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    lecturerecord_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lecturerecord', {
            url: '/lecturerecord',
            title: '听课记录',
            templateUrl: "quality/inspectorcenter/teacherlectures/record.html",
            controller:"lecturerecordController"
        });
    }]);

    lecturerecord_module.controller("lecturerecordController",["$scope","$state","gettermsResource","$uibModal",function($scope,$state,gettermsResource,$uibModal){

        $scope.goback = function () {
            $state.go("qm.tealecture");
        }
        $scope.update = function () {
            var updateScoreinfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/inspectorcenter/teacherlectures/teacherscore.html",
                controller: 'teacherscoreController',
                bindToController: true,
                size: "md",
                backdrop: false
            });
        }

        //获得所有学期
        gettermsResource.getTerms({
        },function(result){
            console.log("获取学期成功！");
            console.log(result);
            $scope.terms = result;
            $scope.selectedTerm =result[0].termNo;
        },function(result){
            console.log("获取学期失败");
        });
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
                $scope.opened2 = false;
            };

            $scope.open2 = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = false;
                $scope.opened2 = true;
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