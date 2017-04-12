/**
 * Created by Administrator on 2017/3/23.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_student",['ui.router']);
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_student',{
            url:'/database/student',
            title:'班级学生维护',
            templateUrl:"quality/database/student/student.html",
        });
    }]);
   /* product_module.controller('myCtrl', function($scope) {
        $scope.names = ["学院","机械工程学院", "电气工程学院", "计算机与软件学院","社会科学学院","艺术与设计学院"];
    });
    product_module.controller('myCtrl1', function($scope) {
        $scope.names = ["年级","2016级", "2015级", "2014级"];
    });
    product_module.controller('myCtrl2', function($scope) {
        $scope.names = ["班级","软件1431", "软件1421", "电商1431"];
    });
    product_module.controller('myCtrl3', function($scope) {
        $scope.names = ["学期","2016学年第一学期", "2016学年第二学期", "2015学年第一学期","2015学年第二学期"];
    });*/
})();