/**
 * Created by Master QB on 2017/3/23.
 */

(function(){
    'use strict';
    var termevaluation_module=angular.module("qm.termevaluation",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    termevaluation_module.factory("termevaluationinstance",function(){return {}});
    termevaluation_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.termevaluation', {
            url: '/termevaluation',
            title: '学年学期管理',
            templateUrl: "quality/database/termevaluation/termevaluation.html",
            controller:"termevaluationController"

        });
    }]);

    termevaluation_module.factory("termResource",["$resource",function($resource){
        var service = $resource('../qm-api/terms', {}, {
            'getAllTerms':{method:"GET",isArray:true},
            'updateSwitch':{method:"PUT",url:"../qm-api/term/switch"},
            'getTermByTermNo':{method:"POST",url:"../qm-api/term/TermByTermNo"}
        });
        return service;
    }]);

    termevaluation_module.controller("termevaluationController",["$scope","termevaluationinstance","$uibModal","termResource",function($scope,termevaluationinstance,$uibModal,termResource){
        $scope.pagetitle="学年学期管理";

        //得到所有学年学期
        termResource.getAllTerms({}, function (result) {
            console.log(result);
            $scope.terms = result;
        }, function () {
            console.log("获取部门信息失败");
        });

        //TermStuatus开关
        $scope.openTermSwitch = function (term) {
            console.log(term);
            termResource.updateSwitch({
                termNo:term.termNo,
                termStatus:term.termStatus
            },function(result){
                console.log("term开关修改成功！");
                console.log(result);
            },function(result){
                console.log("term开关修改失败");
            });

        }

        //各种学期开关
        $scope.termsetting = showTermSetting;
        function showTermSetting(termNo){
            termevaluationinstance.modify=termNo;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/termevaluation/termSetting.html",
                controller: 'termsettingController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            modalInstance.result.then(function (data) {
                //添加保存成功
                if(data.result > 0) {
                    var farm = data.data;
                    $scope.farm_data.content.push(farm);
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })
        }

        //添加学年学期
        $scope.addTerm = showAddTerm;
        function showAddTerm(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/termevaluation/addTerm.html",
                controller: 'addTermController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }
    }]);

    //增加学期controller
    termevaluation_module.controller("addTermController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加学年学期";

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);


    //开关controller
    termevaluation_module.controller("termsettingController",["$scope","termevaluationinstance","termResource","$uibModalInstance","$rootScope",function($scope,termevaluationinstance,termResource,$uibModalInstance,$rootScope){
        $scope.pagetitle="功能开关";
        $scope.form=termevaluationinstance.modify;

        //根据termNo查找Term
        termResource.getTermByTermNo({
            termNo:$scope.form,
        },function(result){
            console.log("Term获取成功");
            console.log(result);
            $scope.terms = result;
        },function(result){
            console.log("Term获取失败");
        });


        //听课开关
        $scope.openTermTkSwitch = function (term) {
            console.log(term);
            termResource.updateSwitch({
                termNo:term.termNo,
                termTk:term.termTk
            },function(result){
                console.log("听课开关修改成功！");
                console.log(result);
            },function(result){
                console.log("听课开关修改失败");
            });
        }

        //评教开关
        $scope.openTermPjSwitch = function (term) {
            console.log(term);
            termResource.updateSwitch({
                termNo:term.termNo,
                termPj:term.termPj
            },function(result){
                console.log("评教开关修改成功！");
                console.log(result);
            },function(result){
                console.log("评教开关修改失败");
            });
        }

        //考勤开关
        $scope.openTermKqSwitch = function (term) {
            console.log(term);
            termResource.updateSwitch({
                termNo:term.termNo,
                termKq:term.termKq
            },function(result){
                console.log("考勤开关修改成功！");
                console.log(result);
            },function(result){
                console.log("考勤开关修改失败");
            });
        }

        //考核开关
        $scope.openTermKhSwitch = function (term) {
            console.log(term);
            termResource.updateSwitch({
                termNo:term.termNo,
                termKh:term.termKh
            },function(result){
                console.log("考核开关修改成功！");
                console.log(result);
            },function(result){
                console.log("考核开关修改失败");
            });
        }

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }


    }]);

})();