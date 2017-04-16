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
            'getStudentByClassName':{method:"POST"}
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
    }]);

    product_module.controller("studentController",["$stateParams","studentService","$scope",function($stateParams,studentService,$scope){
        var className = $stateParams.classNamee;
        var index = 0;
        var length = 10;
        console.log(className);

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



    }]);

})();