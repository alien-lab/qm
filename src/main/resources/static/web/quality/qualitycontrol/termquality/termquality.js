/**
 * Created by Master QB on 2017/3/20.
 */

(function(){
    'use strict';
    var farm_module=angular.module("qm.termquality",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    farm_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.termquality', {
            url: '/termquality',
            title: '学期质量考核查询',
            templateUrl: "quality/qualitycontrol/termquality/termquality.html",
        });
    }]);

})();
