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

    stusubject_module.controller("stuSubjectController",["$scope","stuSubjectResource","$cookieStore",function($scope,stuSubjectResource,$cookieStore){
        $scope.sections = [
            {
                day:[{name:'1-2节',mian:true},{weekday:'周一',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'1-2节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'1-2节',courseNmae:'',shceNo:'',className:'',checked:false},{weekday:'周日',section:'1-2节',courseNmae:'',shceNo:'',className:'',checked:false}]},
            {
                day:[{name:'3-4节',mian:true},{weekday:'周一',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'3-4节',courseNmae:'',className:'',shceNo:'',checked:false}]},
            {
                day:[{name:'5-6节',mian:true},{weekday:'周一',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'5-6节',courseNmae:'',className:'',shceNo:'',checked:false}]},
            {
                day:[{name:'7-8节',mian:true},{weekday:'周一',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'7-8节',courseNmae:'',className:'',shceNo:'',checked:false}]},
            {
                day:[{name:'9-10节',mian:true},{weekday:'周一',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周二',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周三',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周四',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周五',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周六',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false},{weekday:'周日',section:'9-10节',courseNmae:'',className:'',shceNo:'',checked:false}]},
        ];

        stuSubjectResource.getStuSubject({
            termNo:$cookieStore.get('currentTerm').termNo,
            stuNo:$cookieStore.get('user').account,
            courseCurrentWeek:$cookieStore.get("currentWeek").currentweek
        },function(result){
            /*for(var j =0;j<$scope.sections.length;j++){
                for(var m = 0;m<8;m++){
                    var weekday = $scope.sections[j].day[m].weekday;
                    var section = $scope.sections[j].day[m].section;
                    for (var i=0;i<result.length;i++){
                        if (weekday==result[i].scheSet_week&&section==result[i].scheSet_set){
                            $scope.sections[j].day[m].courseNmae=result[i].courseName;
                            $scope.sections[j].day[m].className=result[i].className;
                            $scope.sections[j].day[m].shceNo=result[i].sche_no;
                            $scope.sections[j].day[m].checked = true;
                        }
                    }
                }
            }*/
            console.log(result)
        },function(result){
            console.log("获取评教课程失败",result);
        });
    }]);


})();
