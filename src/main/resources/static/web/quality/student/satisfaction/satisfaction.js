/**
 * Created by Administrator on 2017/5/15.
 */
(function(){
    'use strict';
    var satisfaction_module=angular.module("qm.stu_satisfaction",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    satisfaction_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.stu_satisfaction', {
            url: '/satisfaction',
            title: '学生评教',
            templateUrl: "quality/student/satisfaction/satisfaction.html",
            controller:"satisfactionController"
        });
    }]);
    /*satisfaction_module.constant('APP_COLORS', {
                'primary':'#5d9cec',
                'success':'#27c24c',
                'info':'#23b7e5',
                'warning':'#ff902b',
                'danger':'#f05050',
                'inverse':'#131e26',
                'green':'#37bc9b',
                'pink':'#f532e5',
                'purple':'#7266ba',
                'dark':'#3a3f51',
                'yellow':'#fad732',
                'gray-darker':'#232735',
                'gray-dark':'#3a3f51',
                'gray':'#dde6e9',
                'gray-light':'#e4eaec',
                'gray-lighter':'#edf1f2'
            });


    satisfaction_module.service("Colors",["APP_COLORS",function(APP_COLORS){
        this.byName = byName;
        function byName(name) {
            return (APP_COLORS[name] || '#fff');
        }
    }]);*/

    satisfaction_module.controller("satisfactionController",["$scope",function($scope){
        console.log("sss");


    }]);




})();
