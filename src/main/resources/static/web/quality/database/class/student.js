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
            'getStudentByClassNameAndTermNoAndstuName':{method:"GET",url:"../qm-api/student/findStudentByClassNameAndTermNoAndstuName"}
        });
        return service;
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

    product_module.controller("studentController",["$stateParams","studentResource","studentService","$scope",function($stateParams,studentResource,studentService,$scope){
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


    }]);

})();