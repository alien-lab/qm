/**
 * Created by Master QB on 2017/4/27.
 */
(function(){
    'use strict';
    var stuattendDetail_module=angular.module("qm.stuattendDetail",['ui.router','ui.bootstrap-slider']);//ui.router模块作为主应用模块的依赖模块
    stuattendDetail_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stuattendDetail', {
            url: '/stuattendDetail',
            title: '考勤详情',
            params:{"scheNo":null,"className":null,"courseName":null,"type":null,"week":null},
            templateUrl: "quality/teachercenter/classattend/stuattendDeital.html",
            controller:"stuattendDeitalController"
        });
    }]);

    stuattendDetail_module.factory("studentAttendResource",["$resource",function($resource){
        var service = $resource('../qm-api/stucheck', {}, {
            getstuAttend: { method: 'GET'},
        });
        return service;

    }]);



    stuattendDetail_module.controller("stuattendDeitalController",["$scope","$state","$uibModal","$stateParams","$cookieStore","studentAttendResource",function($scope,$state,$uibModal,$stateParams,$cookieStore,studentAttendResource){
       //定义一个数组，用来承载学生数据
        $scope.Students=[];
        $scope.goback = function () {
            $state.go("qm.classattend");
        }

        $scope.attendrecord = function () {
            var addsubjectInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/teachercenter/classattend/attendrecord.html",
                controller: 'attendrecordController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }
        //获得课程表信息
        $scope.selectedClassname = $stateParams.className;
        $scope.selectedCoursename = $stateParams.courseName;
        $scope.selectedCourseType = $stateParams.type;
        $scope.selectedCurrentweek = $stateParams.week;
        //从cookies获得当前学期编码
       var termNo = $cookieStore.get('currentTerm').termNo;

        //教室卫生
        $scope.slidervalue = 8;
        //课堂纪律
        $scope.slidervalue2 = 8;

        function  studentStory(name,id,state) //创建对象function
        {
            this.name = name;
            this.id=id ;
            this.state = state;
        }
        studentAttendResource.getstuAttend({
            scheNo:$stateParams.scheNo,
            week:$stateParams.week,
            termNo:termNo
        },function(result){

            for(var i=0;i<result.checkStus.length;i++){
                var oneStudent = new studentStory(result.checkStus[i].stuName,result.checkStus[i].stuNo,result.checkStus[i].checkStatus);
                $scope.Students.push(oneStudent);
            }
            console.log($scope.Students);
        },function(result){
            console.log("学生获取失败",result);
        });

        $scope.changeStu =function (num,id) {
          for (var i=0;i<$scope.Students.length;i++){
              if (id==$scope.Students[i].id){
                  if (num==0){
                      $scope.Students[i].state="旷课";
                  }else if (num==1){
                      $scope.Students[i].state="迟到";
                  }else if (num==2){
                      $scope.Students[i].state="早退";
                  }else if (num==3){
                      $scope.Students[i].state="请假";
                  }
              }
          }

        }


    }]);


})();

