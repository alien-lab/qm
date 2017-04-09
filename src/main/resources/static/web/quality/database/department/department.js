/**
 * Created by Administrator on 2017/3/23.
 */
(function(){
    'use strict';
    var department_module=angular.module("qm.base_department",['ui.router']);
    department_module.factory("departmentinstance",function(){return {}});
    department_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_department',{
            url:'/database/department',
            title:'部门维护',
            templateUrl:"quality/database/department/department.html",
            controller:"departmentController"
        });
    }]);

    department_module.factory('SweetAlert', [ '$rootScope', function ( $rootScope ) {
        var swal = window.swal;
        //public methods
        var self = {
            swal: function ( arg1, arg2, arg3 ) {
                $rootScope.$evalAsync(function(){
                    if( typeof(arg2) === 'function' ) {
                        swal( arg1, function(isConfirm){
                            $rootScope.$evalAsync( function(){
                                arg2(isConfirm);
                            });
                        }, arg3 );
                    } else {
                        swal( arg1, arg2, arg3 );
                    }
                });
            },
            success: function(title, message) {
                $rootScope.$evalAsync(function(){
                    swal( title, message, 'success' );
                });
            },
            error: function(title, message) {
                $rootScope.$evalAsync(function(){
                    swal( title, message, 'error' );
                });
            },
            warning: function(title, message) {
                $rootScope.$evalAsync(function(){
                    swal( title, message, 'warning' );
                });
            },
            info: function(title, message) {
                $rootScope.$evalAsync(function(){
                    swal( title, message, 'info' );
                });
            }
        };
        return self;
    }]);


    (function() {
        'use strict';
        angular.module("qm.base_department").factory("departmentResource",["$resource",function($resource){
            var service = $resource('/qm-api/department', {}, {
                'getDepartment': { method: 'GET',isArray:true},//查询所有部门controller
                'upDepartment': { method: 'GET',url:"/qm-api/department/updateDepartment"},//获取单个部门信息controller
                'saveDepartment': { method: 'POST'},//保存部门信息Controller
                'deleteDepartment':{method:'DELETE'},//删除部门信息Controller
                'addDepartment':{method:'POST',url:"/qm-api/department/addDepartment"}//增加部门信息
            });
            return service;
        }]);
    })();


    department_module.controller("departmentController",["$scope","SweetAlert","departmentinstance","departmentResource","$uibModal",function($scope,SweetAlert,departmentinstance,departmentResource,$uibModal){
        $scope.pagetitle="部门维护";

        //查询所有部门
        departmentResource.getDepartment({}, function (result) {
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
                templateUrl: "quality/database/department/modifyDepartment.html",
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

        //删除部门信息
        $scope.deletedepartment = function(index, dep_no) {
            console.log(index);
            SweetAlert.swal({
                title: '您确定要删除该部门吗?',
                text: '删除后将无法恢复，请谨慎操作！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText:"是的，我要删除！",
                cancelButtonText:"让我再考虑一下…",
                closeOnConfirm:true,
                closeOnCancel:false
            },  function(isConfirm){
                if (isConfirm){
                    departmentResource.deleteDepartment({
                        dep_no:dep_no
                    },function(result){
                        console.log("删除menu成功！");
                        console.log(result);
                        swal({title:"成功",
                            text:"您成功删除该部门！",
                            type:"success",
                            })
                        $scope.departments.splice(index, 1);
                    },function(result){
                        console.log("删除menu失败");
                    });
                }else {
                    swal({title:"已取消",
                        text:"您取消了删除操作！",
                        type:"error"})
                }

            });
        };

        //增加部门信息
        $scope.adddepartment = showAddDepartment;
        function showAddDepartment(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/department/addDepartment.html",
                controller: 'addDepartmentController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }
    }]);

    department_module.controller("addDepartmentController",["$scope","$state","departmentResource","$uibModalInstance",function($scope,$state,departmentResource,$uibModalInstance){
        $scope.ModTitle = "增加部门信息";
        $scope.save=function save(savedepartment){
            departmentResource.addDepartment({
                depAbbreviation:savedepartment.depAbbreviation,
                depCddwNo:savedepartment.depCddwNo,
                depName:savedepartment.depName,
                depNo:savedepartment.depNo,
                depSort:savedepartment.depSort,
                depType:savedepartment.depType
            },function(result){
                departmentResource.getDepartment({
                }, function (result) {
                    console.log(result);
                    $scope.departments = result;
                    swal({title:"成功",
                        text:"您成功增加该部门！",
                        type:"success",
                    })
                    console.log( $scope.departments);
                    $uibModalInstance.dismiss('cancel');
                    $state.go("qm.base_department");
                }, function () {
                    console.log("获取部门信息失败");
                });
            },function(result){
                console.log("增加部门失败");
            });
        }
        //取消添加
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

    department_module.controller("updateDepartmentController",["$scope","$state","$uibModalInstance","$rootScope","departmentResource","departmentinstance",function($scope,$state,$uibModalInstance,$rootScope,departmentResource,departmentinstance){
        $scope.ModTitle = "修改部门信息";
        $scope.form=departmentinstance.modify;
        console.log($scope.form);
        departmentResource.upDepartment({
            id:$scope.form
        },function(result){
            console.log("获取部门信息成功！");
            $scope.department = result;
            console.log(result);
        },function(result){
            console.log("获取部门信息失败");
        });
        $scope.save=function save(savedepartment){
            departmentResource.saveDepartment({
                depAbbreviation:savedepartment.depAbbreviation,
                depCddwNo:savedepartment.depCddwNo,
                depName:savedepartment.depName,
                depNo:savedepartment.depNo,
                depSort:savedepartment.depSort,
                depType:savedepartment.depType
            },function(result){
                console.log("保存部门信息成功！");
                //刷新前台界面
                departmentResource.getDepartment({

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
