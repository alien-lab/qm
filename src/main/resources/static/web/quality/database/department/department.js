/**
 * Created by Administrator on 2017/3/23.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_department",['ui.router']);
    product_module.factory("departmentinstance",function(){return {}});
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_department',{
            url:'/database/department',
            title:'部门维护',
            templateUrl:"quality/database/department/department.html",
            controller:"departmentController"
        });
    }]);

    //查询所有部门controller
    (function() {
        'use strict';
        angular.module("qm.base_department").factory("getAllDepartment",["$resource",function($resource){
            var service = $resource('/qm-api/findAllDepartment', {}, {
                'getDepartment': { method: 'GET',isArray:true}
            });
            return service;
        }]);
    })();

    //获取单个部门信息controller
    (function() {
            'use strict';
            angular.module("qm.base_department").factory("updateDepartmentResource",["$resource",function($resource){
                var service = $resource('/qm-api/updateDepartment', {}, {
                    'upDepartment': { method: 'GET'}
                });
                return service;
            }]);
    })();

    //保存部门信息Controller
    (function() {
        'use strict';
        angular.module("qm.base_department").factory("saveDepartmentResource",["$resource",function($resource){
            var service = $resource('/qm-api/saveDepartment', {}, {
                'saveDepartment': { method: 'POST'}
            });
            return service;
        }]);
    })();

    product_module.controller("departmentController",["$scope","departmentinstance","getAllDepartment","$uibModal",function($scope,departmentinstance,getAllDepartment,$uibModal){
        $scope.pagetitle="部门维护";

        //查询所有部门
            getAllDepartment.getDepartment({}, function (result) {
                console.log(result);
                /*$scope.departments = result;*/
                $scope.departments = result;
            }, function () {
                console.log("获取部门信息失败");
            });

        //修改部门信息
        $scope.modifydepartment = showModifyDepartment;
        function showModifyDepartment(depNo){
            departmentinstance.modify=depNo;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/department/modifydepartment.html",
                controller: 'updateDepartmentController',
                bindToController:true,
                size: "lg",
                backdrop:false
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



    product_module.controller("updateDepartmentController",["$scope","$state","saveDepartmentResource","$uibModalInstance","$rootScope","updateDepartmentResource","getAllDepartment","departmentinstance",function($scope,$state,saveDepartmentResource,$uibModalInstance,$rootScope,updateDepartmentResource,getAllDepartment,departmentinstance){
        $scope.ModTitle = "修改部门信息";
        $scope.form=departmentinstance.modify;
        console.log($scope.form);
        updateDepartmentResource.upDepartment({
            id:$scope.form
        },function(result){
            console.log("获取部门信息成功！");
            $scope.department = result;
            console.log(result);
        },function(result){
            console.log("获取部门信息失败");
        });
        $scope.save=function save(savedepartment){
            saveDepartmentResource.saveDepartment({
                depAbbreviation:savedepartment.depAbbreviation,
                depCddwNo:savedepartment.depCddwNo,
                depName:savedepartment.depName,
                depNo:savedepartment.depNo,
                depSort:savedepartment.depSort,
                depType:savedepartment.depType
            },function(result){
                console.log("保存部门信息成功！");
                //刷新前台界面
                getAllDepartment.getDepartment({

                }, function (result) {
                    console.log(result);
                   $scope.departments = result;
                    console.log( $scope.departments);
                    $uibModalInstance.dismiss('cancel');
                    $state.go("qm.base_department");
                }, function () {
                    console.log("获取部门信息失败");
                });
                console.log(result);
            },function(result){
                console.log("获取部门信息失败");
            });
        }

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);





})();
