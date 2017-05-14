/**
 * Created by Administrator on 2017/5/13.
 */
(function(){
    'use strict';
    var evaluation_module=angular.module("qm.stu_detailevaluation",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    evaluation_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stu_detailevaluation', {
            url: '/student/evaluation/:taskNoo/:teachTaskStatuss',
            title: '学生评教打分',
            templateUrl: "quality/student/evaluation/detailevaluation.html",
            controller:"detailevaluationController"
        });
    }]);

    evaluation_module.factory("detailevaluationResource",["$resource",function($resource){
        var service = $resource('../qm-api/teachtask', {}, {
            'getStuPjBytaskNoAndStatus': { method: 'GET',url:"../qm-api/teachtask/detailteachtask"},
            'updateStuPj':{method:"GET",url:"../qm-api/qmstupj/updatestupj"}
        });
        return service;
    }])

    evaluation_module.factory('SweetAlert', [ '$rootScope', function ( $rootScope ) {
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

    evaluation_module.controller("detailevaluationController",["$scope","SweetAlert","$cookieStore","$stateParams","detailevaluationResource",function($scope,SweetAlert,$cookieStore,$stateParams,detailevaluationResource){
        console.log($stateParams.teachTaskStatuss)

        detailevaluationResource.getStuPjBytaskNoAndStatus({
            teachTaskStatus:$stateParams.teachTaskStatuss,
            taskNo:$stateParams.taskNoo,
            stuNo:$cookieStore.get('user').account
        },function(result){
            $scope.teachtask=result;
            console.log(result)
        },function(result){
            console.log("获取打分评教课程失败",result);
        });

        $scope.modifyteachtask = showModifyteachtask;
        function showModifyteachtask(teachtask) {
            SweetAlert.swal({
                title: '您确定给老师评分吗？',
                type: 'info',
                showCancelButton: true,
                confirmButtonColor: '#8CD4F5',
                confirmButtonText: '是的，确定评分!',
                cancelButtonText:"让我再考虑一下..",
                closeOnConfirm:false,
            },  function(){
                    detailevaluationResource.updateStuPj({
                        stuNo:$cookieStore.get('user').account,
                        taskNo:teachtask.taskNo,
                        per11:teachtask.per11,
                        per12:teachtask.per12,
                        per13:teachtask.per13,
                        per14:teachtask.per14,
                        per15:teachtask.per15,
                        jxjy:teachtask.jxjy,
                        jxpj:teachtask.jxpj
                    },function(result){
                        $scope.teachtask=result;
                        console.log(result)
                    },function(result){
                        console.log("修改评教失败",result);
                    });
                   SweetAlert.swal('修改成功!');

            });

        }


    }]);



})();
