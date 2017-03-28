/**
 * Created by Master QB on 2017/3/23.
 */

(function(){
    'use strict';
    var farm_module=angular.module("qm.department",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    farm_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.department', {
            url: '/department',
            title: '部门维护',
            templateUrl: "quality/database/department/department.html",
        });
    }]);

})();
