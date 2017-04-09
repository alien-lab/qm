/**
 * Created by Administrator on 2017/3/25.
 */
(function(){
    'use strict';
    var product_module=angular.module("qm.base_classes",['ui.router']);
    product_module.factory("classesinstance",function(){return {}});
    product_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_classes',{
            url:'/database/class',
            title:'班级学生维护',
            templateUrl:"quality/database/class/classes.html",
            controller:"classesController"
        });
    }]);

    //根据部门简称和年级查询班级信息
    (function() {
        'use strict';
        angular.module("qm.base_classes").factory("classResource",["$resource",function($resource){
            var service = $resource('/qm-api/classes', {}, {
                'getClassesBydepAndyear': { method: 'GET',isArray:true},
                'getDepartment': { method: 'GET',isArray:true,url:"/qm-api/department"},//得到所有部门信息
                'getMajorBymajorNo':{method:'GET',url:"/qm-api/major"},
                'getTeacherByteachNo':{method:'GET',url:"/qm-api/teacher"},
                'getStudentBystudentNo':{method:'GET',url:"/qm-api/student"},
                'getDepartmentBydepNo':{method:"GET",url:""}
            });
            return service;
        }]);
    })();



    product_module.controller('classSessionYear', function($scope) {
        $scope.names = ["2016","2015","2014","2013","2012","2011","2010","2009"];
    });

    product_module.controller("classesController",["$scope","classesinstance","classResource","$uibModal",function($scope,classesinstance,classResource,$uibModal){
        $scope.pagetitle="班级学生维护";

        //查询部门简称
        classResource.getDepartment({}, function (result) {
            console.log(result);
            /*$scope.departments = result;*/
            $scope.departments = result;
        }, function () {
            console.log("获取部门信息失败");
        });

        //导入班级数据
        $scope.batch_classes = showBatchClass;
        function showBatchClass(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/class/batch_insertClass.html",
                controller: 'batchClassController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            modalInstance.result.then(function (data) {
                //添加保存成功
                if(data.result > 0) {
                    var farm = data.data;
                    $scope.farm_data.content.push(farm);
                }
            }, function(flag) {
                if(flag.indexOf("back") >= 0) {
                    return false;
                }
            })
        }

        //根据年级和学院查找班级
            $scope.classdepNoChanged = classdepNoChanged;
            function classdepNoChanged(depNo) {
                $scope.depNo = depNo;
                console.log($scope.depNo);
            }

            $scope.classYearChanged = classYearChanged;
            function classYearChanged(selectedName) {
                $scope.Year = selectedName;
                console.log($scope.Year);
            }

            $scope.searchbeforeClasses=function searchbeforeClasses() {
                console.log($scope.Year);
                console.log($scope.depNo);
                classResource.getClassesBydepAndyear({
                    depNo: $scope.depNo,
                    classSessionYear: $scope.Year
                }, function (result) {
                    console.log(result);
                    $scope.classes = result;

                    for (var n=0;n<$scope.classes.length;n++){
                        classResource.getMajorBymajorNo({
                            majorNo: $scope.classes[n].majorNo
                        },function (result) {
                            console.log("专业");
                            console.log(result);
                            $scope.majors=result;
                        },function () {
                            console.log("获取专业信息失败");
                        });

                        classResource.getTeacherByteachNo({
                            teacherNo: $scope.classes[n].teacherNo
                        },function (result) {
                            console.log("教师");
                            console.log(result);
                            $scope.teachers=result;
                        },function () {
                            console.log("获取教师信息失败");
                        });

                        classResource.getStudentBystudentNo({
                            stuNo: $scope.classes[n].stuNo
                        },function (result) {
                            console.log("学生");
                            console.log(result);
                            $scope.students=result;
                        },function () {
                            console.log("获取学生信息失败");
                        });
                    }


                }, function () {
                    console.log("获取班级信息失败");
                });
        }




    }]);

    product_module.controller("batchClassController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加学年学期";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);
})();