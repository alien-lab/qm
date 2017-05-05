/**
 * Created by Administrator on 2017/3/23.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_student",['ui.router']);
    product_module.factory("studentinstance",function(){return {}});
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
            'insertStudent':{method:"GET",url:"../qm-api/student/addStudent"},
            'getStudentDtoByStuNo':{method:"GET",url:"../qm-api/student/getstudentDtoBystuNo"},
            'getStudentTermByStuNo':{method:"POST",url:"../qm-api/term/studentTermBystuNo",isArray:true},
            'getAllClass':{method:"GET",url:"../qm-api/classes/findAllclassName",isArray:true},
            'updateStudent':{method:"POST",url:"../qm-api/student/updateStudent"},
            'exportStudent':{method:"POST",url:"../qm-api/student/ExcelexportStudent"}
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

    product_module.controller("studentController",["$uibModal","studentinstance","$stateParams","studentResource","studentService","$scope",function($uibModal,studentinstance,$stateParams,studentResource,studentService,$scope){
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

        $scope.termNo=false;
        $scope.termNoChanged = termNoChanged;
        function termNoChanged(termNo) {
            $scope.termNo = termNo;
            console.log($scope.termNo);
            studentService.findStudentByClassNameAndtermNo($stateParams.classNamee,$scope.termNo,index,length,renderData);
        }

        //导出
        $scope.excelexportstudent = excelexportstudent;
        function excelexportstudent() {
            if ($scope.termNo!=false){
                studentResource.exportStudent({
                    className:$stateParams.classNamee,
                    termNo:$scope.termNo
                },function(result){
                    console.log("导出成功")
                },function(result){
                    console.log("导出失败");
                });

            }else {
                /*var modalInstance = $uibModal.open({
                    url: '/database/class',
                    title: 'ngDialog',
                    templateUrl: helper.basepath('quality/database/class/student.html'),
                    resolve: angular.extend(helper.resolveFor('ngDialog'),{
                        tpl: function() { return { path: helper.basepath('quality/database/class/student-template.html') }; }
                    }),
                    controller: 'DialogIntroCtrl'
                });*/
                console.log("ssssssssssssssssss")
            }
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

        //修改学生信息
        $scope.modifystudent = showModifystudent;
        function showModifystudent(stuNo){
            studentinstance.modify=stuNo;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/class/modifyStudent.html",
                controller: 'updateStudentController',
                bindToController:true,
                size: "lg",
                backdrop:false
            });

        }


    }]);

    //修改单个学生信息
    product_module.controller("updateStudentController",["$scope","$state","$uibModalInstance","$rootScope","studentResource","studentinstance",function($scope,$state,$uibModalInstance,$rootScope,studentResource,studentinstance){
        $scope.ModTitle = "修改学生信息";
        $scope.form=studentinstance.modify;
        console.log($scope.form);
        studentResource.getStudentDtoByStuNo({
            stuNo:$scope.form
        },function(result){
            console.log("获取学生信息成功！");
            $scope.students = result;

            studentResource.getAllClass({}, function (result) {
                console.log(result);
                $scope.classNames = result;
            }, function () {
                console.log("获取班级信息失败");
            });

            studentResource.getStudentTermByStuNo({
                stuNo:$scope.form
            }, function (result) {
                console.log(result);
                $scope.studentTerms = result;
            }, function () {
                console.log("获取学生年级信息失败");
            });

            console.log(result);
        },function(result){
            console.log("获取班级信息失败");
        });

        $scope.save=function save(updatestudent){
            studentResource.updateStudent({
                stuNo:updatestudent.stuNo,
                stuName:updatestudent.stuName,
                stuBirthday:updatestudent.stuBirthday,
                stuYear:updatestudent.stuYear,
                className:updatestudent.className,
                stuPhone:updatestudent.stuPhone,
                termName:updatestudent.termName,
            },function(result){
                console.log(result);
                console.log("修改学生信息成功！");
                //刷新前台界面
                $uibModalInstance.dismiss('cancel');
                $state.go("qm.base_student");
            },function(result){
                console.log("修改学生信息失败");
            });
        }

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

    //增加单个学生信息
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

    //未输入学年学期controller
    /*product_module.controller("DialogIntroCtrl",["$scope","ngDialog","tpl",function($scope,ngDialog,tpl){
        activate();
        function activate() {
            // share with other controllers
            $scope.tpl = tpl;
            // open dialog window
            ngDialog.open({
                template: tpl.path,
                // plain: true,
                className: 'ngdialog-theme-default'
            });
        }
    }]);*/
})();