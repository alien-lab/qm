/**
 * Created by Master QB on 2017/5/4.
 */

(function(){
    'use strict';
    var lecturerecord_module=angular.module("qm.lecturerecord",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    lecturerecord_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lecturerecord', {
            url: '/lecturerecord',
            title: '听课记录',
            templateUrl: "quality/inspectorcenter/teacherlectures/record.html",
            controller:"lecturerecordController"
        });
    }]);
    lecturerecord_module.factory("qmMsterListenPlanResource",["$resource",function($resource){
        var service = $resource('../qm-api/master', {}, {
            getlistenplans: { method: 'GET',isArray:true,url:'../qm-api/master/listenPlanDto'},


        });
        return service;

    }]);


    lecturerecord_module.controller("lecturerecordController",["$scope","$state","gettermsResource","$uibModal","qmMsterListenPlanResource","$cookieStore","ruleinstance","teacherScoreResource","SweetAlert","ngDialog",function($scope,$state,gettermsResource,$uibModal,qmMsterListenPlanResource,$cookieStore,ruleinstance,teacherScoreResource,SweetAlert,ngDialog){

        $scope.listenPlans=[];
        $scope.teacherkeyWords="";
        $scope.goback = function () {
            $state.go("qm.tealecture");
        }
        $scope.updatePlan = function (planNo,teacherName,courseName,courseType) {
            var updateScoreinfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/inspectorcenter/teacherlectures/teacherscore.html",
                controller: 'teacherscoreController',
                bindToController: true,
                size: "md",
                backdrop: false
            });
            if (courseType=="实训课"){
                ruleinstance.ruletype = 2;
            }else {
                ruleinstance.ruletype = 1;
            }
            ruleinstance.type = '修改';
            ruleinstance.courseName = courseName;
            ruleinstance.teacherName = teacherName;
            ruleinstance.planNo = planNo;
        }

        //获得所有学期
        gettermsResource.getTerms({
        },function(result){
            console.log("获取学期成功！");
            console.log(result);
            $scope.terms = result;
            $scope.selectedTerm =result[0].termNo;
        },function(result){
            console.log("获取学期失败");
        });
        activate();
        function activate() {
            $scope.today = function() {
                $scope.dt = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.dt = null;
            };

            $scope.toggleMin = function() {
                $scope.minDate = $scope.minDate ? null : new Date();
            };
            $scope.toggleMin();

            $scope.open = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
                $scope.opened2 = false;
            };

            $scope.open2 = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = false;
                $scope.opened2 = true;
            };

            $scope.dateOptions = {
                formatYear: 'yy',
                startingDay: 1
            };
            $scope.initDate = new Date('2019-10-20');
            $scope.formats = [ 'yyyy/MM/dd', 'yyyy.MM.dd', 'shortDate'];
            $scope.format = $scope.formats[1];
        }
        function formatTen(num) {
            return num > 9 ? (num + "") : ("0" + num);
        }
        $scope.searchPlan = function () {
            $scope.listenPlans=[];
            qmMsterListenPlanResource.getlistenplans({
                masterNo:$cookieStore.get('user').account,
                termNo:$scope.selectedTerm,
                teracherName:$scope.teacherkeyWords,
                startTime:$scope.date.getFullYear() + "-" + formatTen($scope.date.getMonth() + 1) + "-" + formatTen($scope.date.getDate())+' 00:00:00',
                endTime:$scope.date2.getFullYear() + "-" + formatTen($scope.date2.getMonth() + 1) + "-" + formatTen($scope.date2.getDate())+' 00:00:00',
            },function(result){
                console.log(result);
                if (result.length==0){
                    var dialog = ngDialog.open({
                        template: '<h4 style="text-align: center">对不起，没有查询到您在该时间段内的听课记录！</h4>',
                        plain: true,
                        closeByDocument: false,
                        closeByEscape: false,
                        controller:'lecturesformController'
                    });
                }else {
                    $scope.listenPlans=result;
                }

            },function(result){
                console.log("督学听课记录列表获取失败",result);
            });
        }

        $scope.removePlan = function (index,listenNo) {
            SweetAlert.swal({
                title: '您确定要删除该记录吗?',
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
                    teacherScoreResource.deletePlan({
                        listenNo:listenNo
                    },function(result){
                        $scope.listenPlans.splice(index, 1);
                    },function(result){
                        console.log("督学听课记录删除失败",result);
                    });
                }else {
                    swal({title:"已取消",
                        text:"您取消了删除操作！",
                        type:"error"})
                }

            });
        }
    }]);

})();