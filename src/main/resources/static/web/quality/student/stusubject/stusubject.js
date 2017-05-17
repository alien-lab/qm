/**
 * Created by Administrator on 2017/5/16.
 */

(function(){
    'use strict';
    var stusubject_module=angular.module("qm.stusubject",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    stusubject_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stusubject', {
            url: '/stusubject',
            title: '我的课程',
            templateUrl: "quality/student/stusubject/stusubject.html",
            controller:"stuSubjectController"
        });
    }]);

    stusubject_module.factory("stuSubjectResource",["$resource",function($resource){
        var service = $resource('../qm-api/tasksche', {}, {
            'getStuSubject': { method: 'GET',isArray:true},
        });
        return service;
    }])

    stusubject_module.controller("stuSubjectController",["$scope","studentResource","$state","stuSubjectResource","$cookieStore",function($scope,studentResource,$state,stuSubjectResource,$cookieStore){
        $scope.sections = [
            {
                day:[{name:'1-2节',mian:true},{weekday:'周一',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周二',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周三',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周四',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周五',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周六',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周日',section:'1-2节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false}]},
            {
                day:[{name:'3-4节',mian:true},{weekday:'周一',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周二',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周三',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周四',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周五',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周六',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周日',section:'3-4节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false}]},
            {
                day:[{name:'5-6节',mian:true},{weekday:'周一',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周二',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周三',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周四',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周五',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周六',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周日',section:'5-6节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false}]},
            {
                day:[{name:'7-8节',mian:true},{weekday:'周一',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周二',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周三',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周四',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周五',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周六',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周日',section:'7-8节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false}]},
            {
                day:[{name:'晚自习',mian:true},{weekday:'周一',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周二',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周三',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周四',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周五',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周六',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false},{weekday:'周日',section:'9-10节',taskNames:'',teacherNames:'',scheAddrs:'',courseWeeks:'',scheNos:'',teachtaskStatuss:'',checked:false}]},
        ];

        studentResource.findStudentDtoByStuNoAndTermNo({
            stuNo:$cookieStore.get('user').account,
            termNo:$cookieStore.get('currentTerm').termNo
        },function(result){
            console.log("获取学生信息成功！");
            console.log(result);
            $scope.students = result;
            $scope.currentWeek = $cookieStore.get("currentWeek").currentweek
        },function(result){
            console.log("获取班级信息失败");
        });

        stuSubjectResource.getStuSubject({
            termNo:$cookieStore.get('currentTerm').termNo,
            stuNo:$cookieStore.get('user').account,
            courseCurrentWeek:$cookieStore.get("currentWeek").currentweek
        },function(result){
            for(var j =0;j<$scope.sections.length;j++){
                for(var m = 0;m<8;m++){
                    var weekday = $scope.sections[j].day[m].weekday;
                    var section = $scope.sections[j].day[m].section;
                    for (var i=0;i<result.length;i++){
                        if (weekday==result[i].scheSet_week&&section==result[i].scheSet_set){
                            $scope.sections[j].day[m].taskNames=result[i].taskName;
                            $scope.sections[j].day[m].teacherNames=result[i].teacherName;
                            $scope.sections[j].day[m].scheAddrs=result[i].scheAddr;
                            $scope.sections[j].day[m].courseWeeks = result[i].courseWeek;
                            $scope.sections[j].day[m].scheNos = result[i].scheNo;
                            $scope.sections[j].day[m].checked = true;
                            $scope.sections[j].day[m].teachtaskStatuss = result[i].teachtaskStatus;
                            $scope.sections[j].day[m].taskNos = result[i].taskNo;
                        }
                    }
                }
            }
            console.log(result)
        },function(result){
            console.log("获取评教课程失败",result);
        });

        $scope.findteachTask = showFindteachTask;
        function showFindteachTask(teachTaskStatus,taskNo) {
            $state.go("qm.stu_detailevaluation",{
                taskNoo:taskNo,
                teachTaskStatuss:teachTaskStatus
            });
        }
    }]);


})();
