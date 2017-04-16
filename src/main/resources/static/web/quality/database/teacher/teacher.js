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

    product_module.factory("teacherResource",["$resource",function($resource){
        var service = $resource('../qm-api/teacher', {}, {
            'getDepartment': { method: 'GET',isArray:true,url:"../qm-api/department"},//得到所有部门信息
            'getTeacherByDepNoAndType':{method:'GET',url:"../qm-api/teacher/findTeacherByDepNoAndType"},
            'getAllTeacher':{method:'GET',url:"../qm-api/teacher/findTeacher"},
            'getTeacherByDepNoAndTypeAndKey':{method:'GET',url:"../qm-api/teacher/findTeacherByDepNoAndTypeAndKey"}
        });
        return service;
    }]);

    product_module.service("teacherService",["teacherResource",function(teacherResource){
        this.getTeacherBydepAndtype=function(depNo,teacherType,index,length,callback){
            teacherResource.getTeacherByDepNoAndType({
                depNo:depNo,
                teacherType:teacherType,
                index:index,
                length:length
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("用户获取失败",result);
            });
        }

        this.getTeacherDepNoAndTypeAndkey=function(depNo,teacherType,teacherKey,index,length,callback){
            teacherResource.getTeacherByDepNoAndTypeAndKey({
                depNo:depNo,
                teacherType:teacherType,
                teacherKey:teacherKey,
                index:index,
                length:length
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("用户获取失败",result);
            });
        }

        this.getTeacherAll=function(index,length,callback){
            teacherResource.getAllTeacher({
                index:index,
                length:length
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("用户获取失败",result);
            });
        }

    }]);


    product_module.controller('teacherController',["$scope","teacherResource","teacherService","$uibModal",function ($scope,teacherResource,teacherService,$uibModal){
        $scope.names = ["全部","校内教师","外聘"];

        var index = 0;
        var length = 20;
        teacherResource.getDepartment({}, function (result) {
            console.log(result);
            /*$scope.departments = result;*/
            $scope.departments = result;
        }, function () {
            console.log("获取部门信息失败");
        });

        function renderData(data){
            $scope.teachercontent=data.content;
            $scope.teacherArrays=data;
            console.log($scope.teachercontent);
            $scope.pagnumbers =[];
            for(var i = 1; i<$scope.teacherArrays.totalPages+1;i++){
                $scope.pagnumbers.push(i);
            }
        }

        teacherService.getTeacherAll(index,length,renderData);

            $scope.teacherdepNoChanged = teacherdepNoChanged;
            function teacherdepNoChanged(depNo) {
                $scope.depNo = depNo;
                console.log($scope.depNo);
            }

            $scope.typeChange = typeChange;
            function typeChange(selectedName) {
                $scope.teacherType = selectedName;
                console.log($scope.teacherType);
            }

            $scope.searchTeacher = function (teacherkey, index, length) {
                $scope.teacherkey = teacherkey;
                if ($scope.depNo != null && $scope.teacherType != null && $scope.teacherkey == null) {
                    teacherService.getTeacherBydepAndtype($scope.depNo,$scope.teacherType,index,length,renderData)
                } else if ($scope.depNo != null && $scope.teacherType != null && $scope.teacherkey != null) {
                    teacherService.getTeacherDepNoAndTypeAndkey($scope.depNo,$scope.teacherType,$scope.teacherkey,index,length,renderData)
                }
            }

        $scope.loadData = function (index,length) {
            if ($scope.depNo == null && $scope.teacherType == null && $scope.teacherkey == null){
                teacherService.getTeacherAll(index,length,renderData);
            }else if($scope.depNo != null && $scope.teacherType != null && $scope.teacherkey == null){
                teacherService.getTeacherBydepAndtype($scope.depNo,$scope.teacherType,index,length,renderData)
            }else {
                teacherService.getTeacherDepNoAndTypeAndkey($scope.depNo,$scope.teacherType,$scope.teacherkey,index,length,renderData)
            }
        }

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
            /*modalInstance.result.then(function (data) {
                if(data.result > 0) {
                    var farm = data.data;
                    $scope.farm_data.content.push(farm);
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })*/
        }
    }]);

    product_module.controller("addTeacherController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加学年学期";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

})();