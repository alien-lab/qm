/**
 * Created by Master QB on 2017/4/15.
 */
(function(){
    'use strict';
    var accountset_module=angular.module("qm.accountset",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    accountset_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.accountset', {
            url: '/accountset',
            title: '用户密码修改',
            templateUrl: "quality/syssetting/accountsetting/accountsetting.html",
            controller:"AccountsetController"
        });
    }]);


    accountset_module.factory("updatepwdResource",["$resource",function($resource){
        var service = $resource('../qm-api/user/updatepwd', {}, {
            updateuserPwd: { method: 'POST'},
        });
        return service;

    }]);

    //用户密码修改的controller
    accountset_module.controller("AccountsetController",["$scope","updatepwdResource",function($scope,updatepwdResource){
        $scope.updatepwd = function () {
            updatepwdResource.updateuserPwd({
                oldpwd:$scope.password,
                newpwd:$scope.password2
            },function(result){
                console.log(result)
            },function(result){
                $scope.update = result.data;
                console.log("用户原密码输入错误");
            });

        }

    }]);


})();
