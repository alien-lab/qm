/**
 * Created by Administrator on 2017/3/23.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_student",['ui.router']);
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_student',{
            url:'/database/student:classNamee',
            title:'班级学生维护',
            templateUrl:"quality/database/class/student.html",
            controller:"studentController"
        });
    }]);

    product_module.factory("studentResource",["$resource",function($resource){
        var service = $resource('../qm-api/student', {}, {
            'getTerm':{method:"GET",url:"../qm-api/term",isArray:true},
            'getStudentByClassName':{method:"POST"},
            'getStudentByClassNameAndtermNo':{method:"POST",url:"../qm-api/student/findStudentByClassNameAndTermNo"},
            'getStudentByClassNameAndTermNoAndstuName':{method:"GET",url:"../qm-api/student/findStudentByClassNameAndTermNoAndstuName"},
            'insertStudent':{method:"GET",url:"../qm-api/student/addStudent"}
        });
        return service;
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

    product_module.service("studentService",["studentResource","$stateParams",function(studentResource,$stateParams){
        this.findStudentByClassName=function(className,index,length,callback){
            studentResource.getStudentByClassName({
                className:className,
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

        this.findStudentByClassNameAndtermNo=function(className,termNo,index,length,callback){
            studentResource.getStudentByClassNameAndtermNo({
                className:className,
                termNo:termNo,
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

        this.findStudentByClassNameAndTermNoAndstuName=function(className,termNo,stuName,index,length,callback){
            studentResource.getStudentByClassNameAndTermNoAndstuName({
                className:className,
                termNo:termNo,
                stuName:stuName,
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

    product_module.controller("studentController",["$uibModal","$stateParams","studentResource","studentService","$scope",function($uibModal,$stateParams,studentResource,studentService,$scope){
        var className = $stateParams.classNamee;
        var index = 0;
        var length = 10;
        console.log(className);

        studentResource.getTerm({}, function (result) {
            console.log(result);
            $scope.terms = result;
        }, function () {
            console.log("获取部门信息失败");
        });

        function renderData(data){
            $scope.studentcontent=data.content;
            $scope.studentArrays=data;
            console.log($scope.studentcontent);
            $scope.pagnumbers =[];
            for(var i = 1; i<$scope.studentArrays.totalPages+1;i++){
                $scope.pagnumbers.push(i);
            }
        }
        $scope.loadData = function (index,length) {
            studentService.findStudentByClassName($stateParams.classNamee,index,length,renderData);
        }

        studentService.findStudentByClassName($stateParams.classNamee,index,length,renderData);


        $scope.termNoChanged = termNoChanged;
        function termNoChanged(termNo) {
            $scope.termNo = termNo;
            //console.log($scope.termNo);
            studentService.findStudentByClassNameAndtermNo($stateParams.classNamee,$scope.termNo,index,length,renderData);
        }

       //根据姓名查新学生
        $scope.searchStudentByName = function (stukey) {
            $scope.stukey=stukey;
            console.log($scope.stukey);
            console.log($scope.termNo);
            console.log($stateParams.classNamee);
            if (stukey==null){
                studentService.findStudentByClassNameAndtermNo($stateParams.classNamee,$scope.termNo,index,length,renderData);
            }else {
                studentService.findStudentByClassNameAndTermNoAndstuName($stateParams.classNamee,$scope.termNo,$scope.stukey,index,length,renderData);
            }
        }

        //增加学生信息
        $scope.addstudent = showAddStudent;
        function showAddStudent(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/class/addStudent.html",
                controller: 'addStudentController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }


    }]);

    product_module.controller("addStudentController",["$scope","$rootScope","$stateParams","$state","studentService","studentResource","$uibModalInstance",function($scope,$rootScope,$stateParams,$state,studentService,studentResource,$uibModalInstance){
        $scope.ModTitle = "增加学生信息";
        $scope.save=function save(savedepartment){
            studentResource.insertStudent({
                stuNo:savedepartment.stuNo,
                stuName:savedepartment.stuName,
                stuBirthday:savedepartment.stuBirthday,
                stuPhone:savedepartment.stuPhone,
                stuStatus:savedepartment.stuStatus,
            },function(result){
                studentResource.getStudentByClassName({
                    className:$stateParams.classNamee,
                    index:0,
                    length:20,
                }, function (result) {
                    $scope.studentcontent=data.content;
                    $scope.studentArrays=data;
                    console.log($scope.studentcontent);
                    $scope.pagnumbers =[];
                    for(var i = 1; i<$scope.studentArrays.totalPages+1;i++){
                        $scope.pagnumbers.push(i);
                    }
                    console.log(result);
                    swal({title:"成功",
                        text:"您成功增加该教师！",
                        type:"success",
                    })
                    $uibModalInstance.dismiss('cancel');
                    $state.go("qm.base_student");
                }, function () {
                    console.log("获取部门信息失败");
                });
            },function(result){
                console.log("增加学生失败");
            });
        }


        //取消添加
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

})();