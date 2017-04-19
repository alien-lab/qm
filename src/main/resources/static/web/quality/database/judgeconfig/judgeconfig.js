/**
 * Created by Administrator on 2017/4/17.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_judgeconfig",['ui.router']);
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_judgeconfig',{
            url:'/database/judgeconfig',
            title:'学年设置',
            templateUrl:"quality/database/judgeconfig/judgeconfig.html",
            controller:"judgeconfigController"
        });
    }]);

    /*product_module.controller('SessionYear', function($scope) {
        $scope.namess = ["2016","2015","2014","2013","2012","2011","2010","2009"];
    });*/

    product_module.factory("qmjudgeconfigResource",["$resource",function($resource){
        var service = $resource('../qm-api/qmjudgeconfig', {}, {
            'getQmjudgeconfigByYearNo':{method:"GET",isArray:true},
            'getQmjudgeconfig':{method:"GET",isArray:true,url:"../qm-api/qmjudgeconfig/getQmjudgeConfig"}
        });
        return service;
    }]);
    product_module.controller("judgeconfigController",["$scope","qmjudgeconfigResource",function($scope,qmjudgeconfigResource){
        qmjudgeconfigResource.getQmjudgeconfig({}, function (result) {
            console.log(result);
            $scope.judgeconfigs = result;
        }, function () {
            console.log("获取部门信息失败");
        });

        $scope.judgeyearNoChanged = judgeyearNoChanged;
        function judgeyearNoChanged(yearNo) {
            $scope.YearNo = yearNo;
            console.log($scope.YearNo);

            qmjudgeconfigResource.getQmjudgeconfigByYearNo({
                yearNo:$scope.YearNo
            }, function (result) {
                console.log(result);
                $scope.judgeconfiges = result;
            }, function () {
                console.log("获取部门信息失败");
            });
        }



    }]);

    product_module.filter('unique', function () {
        return function (collection, keyname) {
            var output = [],
                keys = [];
            angular.forEach(collection, function (item) {
                var key = item[keyname];
                if (keys.indexOf(key) === -1) {
                    keys.push(key);
                    output.push(item);
                }
            });
            return output;
        };
    });

})();