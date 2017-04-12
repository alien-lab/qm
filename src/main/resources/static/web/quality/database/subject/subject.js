/**
 * Created by Administrator on 2017/4/2.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_subject",['ui.router']);
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_subject',{
            url:'/database/subject',
            title:'课程维护',
            templateUrl:"quality/database/subject/subject.html",
        });
    }]);

    product_module.controller('subject', function($scope) {
        $scope.names = ["学年","2016年第二学期","2016年第一学期","2015年第二学期","2015年第一学期"];
    });

    product_module.controller('subject1', function($scope) {
        $scope.names = ["部门","机械工程","计算机与软件","能源与电气工程","文理学院"];
    });

})();