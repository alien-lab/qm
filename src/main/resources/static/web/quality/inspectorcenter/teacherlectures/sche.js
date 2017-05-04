/**
 * Created by Master QB on 2017/5/3.
 */
(function(){
    'use strict';
    var lecturesche_module=angular.module("qm.lecturesche",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    lecturesche_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.lecturesche', {
            url: '/lecturesche',
            title: '督学听课计划',
            templateUrl: "quality/inspectorcenter/teacherlectures/sche.html",
        });
    }]);

})();