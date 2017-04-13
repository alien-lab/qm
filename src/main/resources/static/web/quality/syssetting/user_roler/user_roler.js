/**
 * Created by Master QB on 2017/3/24.
 */

(function(){
    'use strict';
    var user_roler_module=angular.module("qm.user_roler",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    user_roler_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.user_roler', {
            url: '/user_roler',
            title: '用户角色设置',
            templateUrl: "quality/syssetting/user_roler/user_roler.html",
            controller:"userRolerController"
        });
    }]);

    user_roler_module.factory("userResource",["$resource",function($resource){
        var service = $resource('../qm-api/users', {}, {
            getUsers: { method: 'GET', isArray:true},
        });
        return service;

    }]);

    user_roler_module.factory("userRolerResource",["$resource",function($resource){
        var service = $resource('../qm-api/rolers', {}, {
            getRolers:{method: 'GET', isArray:true, url:'../qm-api/rolers/getUserRolers'},
            setuserRolers:{method: 'POST', url:'../qm-api/rolers/setuserRolers'}
        });
        return service;

    }]);


    user_roler_module.controller("userRolerController",['$scope','$filter', '$http', '$q',"$uibModal","userResource","userRolerResource",function($scope,$filter, $http,$q,$uibModal,userResource,userRolerResource){
        //获取用户列表
        $scope.searchButton = function (searchString) {
            userResource.getUsers({
                keyword:searchString
            },function(result){
                console.log(result);
                $scope.userArrays = result;
            },function(result){
                console.log("用户获取失败");
            });
        }

        //获得该用户的角色信息
        $scope.getRoler = function (userId,username,loginname) {
            $scope.currentid = loginname;
            $scope.currentname = username;
            userRolerResource.getRolers({
                userid:userId
            },function(result){
               console.log(result);
                $scope.rolersArrays = result;
            },function(result){
                console.log("用户角色获取失败");
            });

            //点击事件增加或删除该用户角色
            $scope.oksubRoler = function (rolerid) {
                console.log(rolerid);
                console.log(userId);
                userRolerResource.setuserRolers({
                    userid:userId,
                    rolerid:rolerid
                },function(result){
                    console.log(result);
                },function(result){
                    console.log("增加或删除该用户角色失败");
                });
            }

        }


    }]);






})();