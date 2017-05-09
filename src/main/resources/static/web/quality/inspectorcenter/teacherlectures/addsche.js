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

    addsche_module.factory("schePlanResource",["$resource",function($resource){
        var service = $resource('../qm-api/master', {}, {
            updateSche:{method: 'POST',url:'../qm-api/master/updatelistenplan'},
            deleteSchePlan:{method: 'POST',url:'../qm-api/master/deletelistenplan'}

        });
        return service;

    }]);

    addsche_module.controller("addscheController",["$scope","$uibModalInstance","lecturescheinstance","schePlanResource","SweetAlert","$rootScope",function($scope,$uibModalInstance,lecturescheinstance,schePlanResource,SweetAlert,$rootScope){
        $scope.planNo = lecturescheinstance.planNo;
        $scope.teacherName = lecturescheinstance.teacherName ;
        $scope.courseName =lecturescheinstance.courseName;
        $scope.className=lecturescheinstance.className;


        function formatTen(num) {
            return num > 9 ? (num + "") : ("0" + num);
        }

        //提交按钮
        $scope.submitSche = function () {
            schePlanResource.updateSche({
                planNo:$scope.planNo,
                listetime:$scope.date.getFullYear() + "-" + formatTen($scope.date.getMonth() + 1) + "-" + formatTen($scope.date.getDate())
            },function(result){
                SweetAlert.swal({
                    title: '听课计划修改成功',
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: '好的',
                    closeOnConfirm: true
                },function () {
                    $uibModalInstance.dismiss('cancel');
                    $rootScope.$broadcast("reloadSche");
                });
            },function(result){
                console.log("督学评价保存失败",result);
            });
        }

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
