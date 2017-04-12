/**
 * Created by Administrator on 2017/4/2.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_teacher",['ui.router']);
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_teacher',{
            url:'/data_base/teacher',
            title:'教工维护',
            templateUrl:"quality/database/teacher/teacher.html",
            controller:"teacherController"
        });
    }]);

    product_module.controller('transition', function($scope) {
        $scope.names = ["全部","校内教师","外聘"];
    });
    product_module.controller('transition1', function($scope) {
        $scope.names = ["全部","计算机与软件","能源与电气","机械工程","国际教育"];
    });

    product_module.controller('teacherController',["$scope","$uibModal",function ($scope,$uibModal){
        $scope.addTeacher = showAddTeacher;
        function showAddTeacher(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/teacher/addTeacher.html",
                controller: 'addTeacherController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            modalInstance.result.then(function (data) {
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

    product_module.controller("addTeacherController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加学年学期";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

})();