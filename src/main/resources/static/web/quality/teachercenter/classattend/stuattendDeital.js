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
            saveoneAttend:{ method: 'POST'},
            updajswsAndktjl:{method: 'POST',url:'../qm-api/updatejsws'},
            submitAttendance:{method: 'POST',url:'../qm-api/submitattend'}
        });
        return service;

    }]);





    stuattendDetail_module.controller("stuattendDeitalController",["$scope","$state","$uibModal","$stateParams","$cookieStore","studentAttendResource","SweetAlert",function($scope,$state,$uibModal,$stateParams,$cookieStore,studentAttendResource,SweetAlert){
        $scope.kk=0;
        $scope.cd=0;
        $scope.zt=0;
        $scope.qj=0;


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



        function  studentStory(name,id,state) //创建对象function
        {
            this.name = name;
            this.id=id ;
            this.state = state;
        }
        //提交考勤
        $scope.submitAttend = function (checkMainNo) {
            studentAttendResource.submitAttendance({
                checkMainNo:checkMainNo
            },function(result){
                $scope.Attend.checkMainStatus='1';
                console.log(result);
                SweetAlert.swal({
                    title: '考勤提交成功！',
                    type: 'success',
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText:"好的",
                    closeOnConfirm:true
                });
            },function(result){
                console.log("提交考勤失败",result);
            });
        }
        //修改jsws和ktjl
        $scope.myFunc =function (checkMainNo) {
            studentAttendResource.updajswsAndktjl({
                checkMainNo:checkMainNo,
                jsws:$scope.slidervalue,
                ktjl:$scope.slidervalue2
            },function(result){
                console.log(result);
            },function(result){
                console.log("修改课堂纪律和卫生失败",result);
            });
        }

        //获得考勤详情
        studentAttendResource.getstuAttend({
            scheNo:$stateParams.scheNo,
            week:$stateParams.week,
            termNo:termNo,
            type:$scope.selectedCourseType
        },function(result){
            console.log(result);
            $scope.Attend = result;
            //教室卫生
            $scope.slidervalue = result.checkJsws;
            //课堂纪律
            $scope.slidervalue2 = result.checkKtjl;

            for(var i=0;i<result.checkStus.length;i++){
                var oneStudent = new studentStory(result.checkStus[i].stuName,result.checkStus[i].stuNo,result.checkStus[i].checkStatus);
                $scope.Students.push(oneStudent);
            }
            for(var m=0 ;m<$scope.Students.length;m++){
                if ($scope.Students[m].state=="旷课"){
                    $scope.kk= $scope.kk+1;
                }else if ( $scope.Students[m].state=="迟到"){
                    $scope.cd=$scope.cd+1;
                }else if ($scope.Students[m].state=="早退"){
                    $scope.zt=$scope.zt+1;
                }else if ( $scope.Students[m].state=="请假"){
                    $scope.qj= $scope.qj+1;
                }
            }

            console.log($scope.Students);
        },function(result){
            console.log("学生获取失败",result);
        });

        //修改学生考勤信息
        $scope.changeStu =function (checkNo,num,id,string,status) {
            console.log(string);
            studentAttendResource.saveoneAttend({
                checkMainNo:checkNo,
                string:string,
                id:id
            },function(result){
               console.log("学生考勤修改成功！")
                if (status=="旷课"){
                    $scope.kk= $scope.kk-1;
                }else if (status=="迟到"){
                    $scope.cd=$scope.cd-1;
                }else if (status=="早退"){
                    $scope.zt=$scope.zt-1;
                }else if (status=="请假"){
                    $scope.qj= $scope.qj-1;
                }
                for (var i=0;i<$scope.Students.length;i++){
                    if (id==$scope.Students[i].id){
                        if (num==0){
                            $scope.Students[i].state="旷课";
                            $scope.kk= $scope.kk+1;
                        }else if (num==1){
                            $scope.Students[i].state="迟到";
                            $scope.cd=$scope.cd+1;

                        }else if (num==2){
                            $scope.Students[i].state="早退";
                            $scope.zt=$scope.zt+1;

                        }else if (num==3){
                            $scope.Students[i].state="请假";
                            $scope.qj= $scope.qj+1;
                        }else if (num==4){
                            $scope.Students[i].state="正常";
                        }
                    }
                }
            },function(result){
                console.log("学生考勤修改失败",result);
            });
        }
    }]);


})();

