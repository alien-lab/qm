/**
 * Created by Master QB on 2017/5/3.
 */
(function(){
    'use strict';
    var lecturesche_module=angular.module("qm.lecturesche",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    lecturesche_module.factory("lecturescheinstance",function(){return {}});
    lecturesche_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lecturesche', {
            url: '/lecturesche',
            title: '督学听课计划',
            templateUrl: "quality/inspectorcenter/teacherlectures/sche.html",
            controller:"lecturesScheController"
        });
    }]);

    lecturesche_module.controller("lecturesScheController",["$scope","$state","$uibModal","qmMsterResource","$cookieStore","lecturescheinstance","$rootScope","schePlanResource","SweetAlert",function($scope,$state,$uibModal,qmMsterResource,$cookieStore,lecturescheinstance,$rootScope,schePlanResource,SweetAlert){

        $scope.teacherlist=[];
        //从cookies获得当前用户
        $scope.masterTeacher = {
            account : $cookieStore.get('user').account,
            name:$cookieStore.get('user').name,
            usertype: $cookieStore.get('user').type
        }
        $scope.currentTermName = $cookieStore.get('currentTerm').termName;
        function  caredteacherStory(termName,teacherNo,teacheName) //创建对象function
        {
            this.termName = termName;
            this.teacherNo= teacherNo;
            this.teacherName = teacheName;
        }
        $scope.tkjhAllPlan=[];
        $scope.changeStatus =0;
        $rootScope.$on("reloadSche",function (event, msg) {
            $scope.tkjhAllPlan=[];
            getAllSches();
        });
        $scope.changeSche = function (index) {
            $scope.changeStatus = index;
        }
        $scope.searchTask = function (teacherNo) {
            $state.go("qm.teachtasksche",{teacherNo:teacherNo});
        }

        $scope.addSche = function (planNo,teacherName,courseName,className) {
            var addscheInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/inspectorcenter/teacherlectures/addsche.html",
                controller: 'addscheController',
                bindToController: true,
                size: "md",
                backdrop: false
            });
            lecturescheinstance.status="修改";
            lecturescheinstance.planNo=planNo;
            lecturescheinstance.teacherName=teacherName;
            lecturescheinstance.courseName=courseName;
            lecturescheinstance.className=className;

        }

        $scope.goBack = function () {
            $state.go("qm.tealecture");
        }
        $scope.deleteSche = function (index,planNo) {
            SweetAlert.swal({
                title: '您确定要删除该听课计划吗?',
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
                    schePlanResource.deleteSchePlan({
                        planNo:planNo
                    },function(result){
                        $scope.tkjhAllPlan.splice(index, 1);
                        console.log(result);
                    },function(result){
                        console.log("听课计划删除失败",result);
                    });
                }else {
                    swal({title:"已取消",
                        text:"您取消了删除操作！",
                        type:"error"})
                }
            });
        }

        function  scheplanStory(planNo,teacherName,courseName,className,planTime,planWeek,setTime,day) //创建对象function
        {
            this.planNo = planNo;
            this.teacherName = teacherName;
            this.courseName= courseName;
            this.className = className;
            this.planTime = planTime;
            this.planWeek= planWeek;
            this.setTime = setTime;
            this.day = day;
        }

        function formatTen(num) {
            return num > 9 ? (num + "") : ("0" + num);
        }
        function checkTime(i){ //将0-9的数字前面加上0，例1变为01
            if(i<10)
            {
                i = + i;
            }
            return i;
        }
        getAllSches();
        getCaredTeachers();
        function getAllSches() {
            qmMsterResource.getAllSches({
                masterNo:$cookieStore.get('user').account,
                termNo:$cookieStore.get('currentTerm').termNo,
            },function(result){
                console.log(result);
                for (var i=0;i<result.length;i++){
                    var strs= new Array(); //定义一数组
                    strs=result[i].planTime.split("-"); //字符分割
                    var leftTime = (new Date(strs[0],strs[1]-1,strs[2],0,0,0)) - (new Date());
                    var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数
                    days = checkTime(days+1);
                    var  onesche =new scheplanStory(result[i].planNo,result[i].teacherName,result[i].courseName,result[i].className,result[i].planTime,result[i].planWeek,result[i].setTime,days);
                    $scope.tkjhAllPlan.push(onesche);
                }
            },function(result){
                console.log("督学所有计划听课列表获取失败",result);
            });
        }

        function getCaredTeachers() {
            //获得当前学期 当前督学所关注的教师
            qmMsterResource.getCaredTeachers({
                masterNo:$scope.masterTeacher.account,
                termNo:$cookieStore.get('currentTerm').termNo
            },function(result){
                console.log(result);
                for (var i =0;i<result.length;i++){
                    var teacher = new caredteacherStory($scope.currentTermName,result[i].teacherNo,result[i].teacherName);
                    $scope.teacherlist.push(teacher);
                }
            },function(result){
                console.log("教师列表获取失败",result);
            });


        }


    }]);

})();