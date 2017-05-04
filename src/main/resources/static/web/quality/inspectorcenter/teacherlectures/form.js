/**
 * Created by Master QB on 2017/5/2.
 */
(function(){
    'use strict';
    var tealectureform_module=angular.module("qm.lectureform",['ui.router','ngDialog']);//ui.router模块作为主应用模块的依赖模块
    tealectureform_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lectureform', {
            url: '/lectureform',
            title: '填写听课表单',
            templateUrl: "quality/inspectorcenter/teacherlectures/form.html",
            controller:"lecturesformController"

        });
    }]);



    tealectureform_module.controller("lecturesformController",["$scope","$state","ngDialog","$cookieStore","$uibModal",function($scope,$state,ngDialog,$cookieStore,$uibModal){

        $scope.lectureWeeks=[];
        function  ObjweekStory(id,name) //创建对象function
        {
            this.weekid = id;
            this.weekname= name;
        }
        //从cookies中获得当前周次
        var  currentweek = $cookieStore.get('currentWeek').currentweek;
        for (var i=currentweek;i>=1;i--){
            var oneweek = new ObjweekStory(i,"第"+i+"周");
            $scope.lectureWeeks.push(oneweek);

        }
        $scope.scheWeek = $scope.lectureWeeks[0].weekid;

        //添加听课计划
        $scope.addSche = function () {
            $state.go("qm.lecturesche");
        }

        //返回上一步
        $scope.goback = function () {
            $state.go("qm.tealecture");
        }
        $scope.openTimed = function () {
            console.log($scope.scheWeek);
            var dialog = ngDialog.open({
                template: '<h4 style="text-align: center">对不起，您在第 '+$scope.scheWeek+' 周的没有听课计划</h4>',
                plain: true,
                closeByDocument: false,
                closeByEscape: false,
                controller:'lecturesformController'
            });

        };

        //
        $scope.teaScore =function () {
                var teacherInfo = $uibModal.open({
                    animation: true,
                    templateUrl: "quality/inspectorcenter/teacherlectures/teacherscore.html",
                    controller: 'teacherscoreController',
                    bindToController: true,
                    size: "sm",
                    backdrop: false
                });
        }


    }]);

})();