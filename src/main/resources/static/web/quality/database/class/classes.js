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

    product_module.factory("classResource",["$resource",function($resource){
        var service = $resource('../qm-api/classes', {}, {
            'getClassesBydepAndyear': { method: 'GET'},
            'getDepartment': { method: 'GET',isArray:true,url:"../qm-api/department"},//得到所有部门信息
            'getClassesBykey':{method:"GET",url:"../qm-api/classes/getClassBykey"},
            'getStudentByClassName':{method:"POST",url:"../qm-api/student"},
            'getClassesByclassNo':{method:"GET",url:"../qm-api/classes/getClassByclassNo"},
            'getAllMajor':{method:"GET",url:"../qm-api/major/getMajor",isArray:true},
            'updateClasses':{method:"GET",url:"../qm-api/classes/updateClases"}
        });
        return service;
    }]);

    product_module.service("classService",["classResource",function(classResource){
        this.getClassesBydepAndyear=function(depNo,classSessionYear,index,length,callback){
            classResource.getClassesBydepAndyear({
                depNo:depNo,
                classSessionYear:classSessionYear,
                index:index,
                length:length
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("用户获取失败",result);
            });
        }

        this.getClassesBykey=function(depNo,classSessionYear,key,index,length,callback){
            classResource.getClassesBykey({
                depNo:depNo,
                classSessionYear:classSessionYear,
                key:key,
                index:index,
                length:length
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("用户获取失败",result);
            });
        }

        this.getStudentByClassName=function(className,index,length,callback){
            classResource.getStudentByClassName({
                className:className,
                index:index,
                length:length
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("用户获取失败",result);
            });
        }
    }]);


    product_module.controller("classesController",["$scope","$state","classService","classesinstance","classResource","$uibModal",function($scope,$state,classService,classesinstance,classResource,$uibModal){
        $scope.names = ["2016","2015","2014","2013","2012","2011","2010","2009"];
        $scope.pagetitle="班级学生维护";

        function renderData(data){
            $scope.userArrays=data.content;
            console.log($scope.userArrays);
            $scope.pagnumbers =[];
            for(var i = 1; i<$scope.userArrays.totalPages+1;i++){
                $scope.pagnumbers.push(i);
            }
        }
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

        $scope.searchClasses = function (classkey,index,length) {
            $scope.classkey=classkey;
            if (classkey==null){
                classService.getClassesBydepAndyear($scope.depNo,$scope.selectedName,index,length,renderData);
            }else {
                classService.getClassesBykey($scope.depNo,$scope.selectedName,$scope.classkey,index,length,renderData)
            }
        }

        //根据班级名称查找学生
        $scope.findStudentByClassName = showfindStudentByClassName;
        function showfindStudentByClassName(className,index,length) {
            $scope.className=className;
            //classService.getStudentByClassName(className,index,length,renderData);
            $state.go("qm.base_student",{
                classNamee:className,
            });
        }

        //修改班级信息
        $scope.modifyclasses = showModifyclasses;
        function showModifyclasses(classNo){
            classesinstance.modify=classNo;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/class/modifyClasses.html",
                controller: 'updateClassesController',
                bindToController:true,
                size: "lg",
                backdrop:false
            });

        }

    }]);

    product_module.controller("batchClassController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "增加学年学期";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

    //修改班级信息controller
    product_module.controller("updateClassesController",["$scope","$state","$uibModalInstance","$rootScope","classResource","classesinstance",function($scope,$state,$uibModalInstance,$rootScope,classResource,classesinstance){
        $scope.ModTitle = "修改部门信息";
        $scope.form=classesinstance.modify;
        console.log($scope.form);
        classResource.getClassesByclassNo({
            classNo:$scope.form
        },function(result){
            classResource.getDepartment({}, function (result) {
                console.log(result);
                $scope.departments = result;
            }, function () {
                console.log("获取部门信息失败");
            });
            classResource.getAllMajor({}, function (result) {
                console.log(result);
                $scope.majors = result;
            }, function () {
                console.log("获取部门信息失败");
            });
            console.log("获取班级信息成功！");
            $scope.classes = result;
            console.log(result);
        },function(result){
            console.log("获取班级信息失败");
        });

        $scope.save=function save(updateclass){
            classResource.updateClasses({
                classNo:updateclass.classNo,
                classIsover:updateclass.classIsover,
                className:updateclass.className,
                depName:updateclass.depName,
                majorName:updateclass.majorName,
                stuName:updateclass.stuName,
                teacherName:updateclass.teacherName,
                classStuAmount:updateclass.classStuAmount,
                classSessionYear:updateclass.classSessionYear
            },function(result){
                console.log(result);
                console.log("保存部门信息成功！");
                //刷新前台界面
                $uibModalInstance.dismiss('cancel');
                $state.go("qm.base_classes");
            },function(result){
                console.log("获取部门信息失败");
            });
        }

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

})();