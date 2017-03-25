/**
 * Created by Master QB on 2017/3/20.
 */

(function(){
    'use strict';
    var farm_module=angular.module("qm.index",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    farm_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.index', {
            url: '/index',
            title: '我的质控',
            templateUrl: "quality/index/loginindex.html",
        });
    }]);

})();