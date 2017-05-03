/**
 * Created by Administrator on 2017/4/2.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_teacher",['ui.router']);
    product_module.factory("teacherinstance",function(){return {}});
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_teacher',{
            url:'/data_base/teacher',
            title:'教工维护',
            templateUrl:"quality/database/teacher/teacher.html",
            controller:"teacherController"
        });
    }]);

    product_module.factory('SweetAlert', [ '$rootScope', function ( $rootScope ) {
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

    product_module.factory("teacherResource",["$resource",function($resource){
        var service = $resource('../qm-api/teacher', {}, {
            'getDepartment': { method: 'GET',isArray:true,url:"../qm-api/department"},//得到所有部门信息
            'getTeacherByDepNoAndType':{method:'GET',url:"../qm-api/teacher/findTeacherByDepNoAndType"},
            'getAllTeacher':{method:'GET',url:"../qm-api/teacher/findTeacher"},
            'getTeacherByDepNoAndTypeAndKey':{method:'GET',url:"../qm-api/teacher/findTeacherByDepNoAndTypeAndKey"},
            'addTeacher':{method:'POST'},
            'getTeacherByTeacherNo':{method:'GET',url:"../qm-api/teacher/findOneTeacher"},
            'updateOneTeacher':{method:'GET',url:"../qm-api/teacher/updateTeacher"},
            'switchMenu':{method:'PUT',url:'../qm-api/teacher/switch'},
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


    product_module.controller('teacherController',["$scope","teacherinstance","teacherResource","teacherService","$uibModal",function ($scope,teacherinstance,teacherResource,teacherService,$uibModal){
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

            //开关设置
        $scope.openTeachSwitch = function (teacherNo) {
            console.log(teacherNo);
            teacherResource.switchMenu({
                teacherNo:teacherNo
            },function(result){
                console.log("switch开关修改成功！");
                console.log(result);
            },function(result){
                console.log("switch开关修改失败");
            });

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
        }

        $scope.modifyteacher = showModifyteacher;
        function showModifyteacher(teacherNo){
            teacherinstance.modify=teacherNo;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/teacher/modifyTeacher.html",
                controller: 'updateTeacherController',
                bindToController:true,
                size: "lg",
                backdrop:false
            });

        }
    }]);

    product_module.controller("addTeacherController",["$state","$scope","teacherResource","$uibModalInstance","$rootScope",function($state,$scope,teacherResource,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加教师信息";

        teacherResource.getDepartment({}, function (result) {
            console.log(result);
            /*$scope.departments = result;*/
            $scope.departments = result;
        }, function () {
            console.log("获取部门信息失败");
        });

        $scope.save=function save(saveteacher){
            console.log(saveteacher);
            teacherResource.addTeacher({
                teacherNo:saveteacher.teacherNo,
                teacherName:saveteacher.teacherName,
                depNo:saveteacher.depNo,
                teacherTitle:saveteacher.teacherTitle,
                teacherType:saveteacher.teacherType,
            },function(result){
                teacherResource.getAllTeacher({
                    index:0,
                    length:20
                }, function (result) {
                    $scope.teachercontent=result.content;
                    $scope.teacherArrays=result;
                    console.log($scope.teachercontent);
                    $scope.pagnumbers =[];
                    for(var i = 1; i<$scope.teacherArrays.totalPages+1;i++){
                        $scope.pagnumbers.push(i);
                    }
                    console.log(result);
                    swal({title:"成功",
                        text:"您成功增加该教师！",
                        type:"success",
                    })
                    $uibModalInstance.dismiss('cancel');
                    $state.go("qm.base_teacher");
                }, function () {
                    console.log("获取部门信息失败");
                });
            },function(result){
                console.log("增加部门失败");
            });
        }


        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);


    product_module.controller("updateTeacherController",["$scope","$state","$uibModalInstance","$rootScope","teacherResource","teacherinstance",function($scope,$state,$uibModalInstance,$rootScope,teacherResource,teacherinstance){
        $scope.ModTitle = "修改部门信息";
        $scope.form=teacherinstance.modify;
        console.log($scope.form);
        teacherResource.getTeacherByTeacherNo({
            teacherNo:$scope.form
        },function(result){
            console.log("获取部门信息成功！");
            $scope.teachers = result;
            console.log(result);
        },function(result){
            console.log("获取部门信息失败");
        });

        $scope.save=function save(updateteacher){
            teacherResource.updateOneTeacher({
                teacherNo:updateteacher.teacherNo,
                teacherName:updateteacher.teacherName,
                teacherTitle:updateteacher.teacherTitle,
                teacherType:updateteacher.teacherType,
            },function(result){
                console.log("保存部门信息成功！");
                //刷新前台界面
                teacherResource.getAllTeacher({
                    index:0,
                    length:20
                }, function (result) {
                    $scope.teachercontent=result.content;
                    $scope.teacherArrays=result;
                    console.log($scope.teachercontent);
                    $scope.pagnumbers =[];
                    for(var i = 1; i<$scope.teacherArrays.totalPages+1;i++){
                        $scope.pagnumbers.push(i);
                    }
                    console.log(result);
                    swal({title:"成功",
                        text:"教师修改成功！",
                        type:"success",
                    })
                    $uibModalInstance.dismiss('cancel');
                    $state.go("qm.base_teacher");
                }, function () {
                    console.log("获取部门信息失败");
                });
            },function(result){
                console.log("获取部门信息失败");
            });
        }

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

})();