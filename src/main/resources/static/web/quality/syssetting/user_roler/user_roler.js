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
        });
    }]);

})();