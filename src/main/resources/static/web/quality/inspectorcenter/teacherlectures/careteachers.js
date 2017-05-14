/**
 * Created by asus on 2017/5/5.
 */
(function(){
    'use strict';
    var careTeachers_module=angular.module("qm.careteachers",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    careTeachers_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.careteachers', {
            url: '/careteachers',
            title: '我关注的老师',
            templateUrl: "quality/inspectorcenter/teacherlectures/careteachers.html",
            controller:"careteachersController"

        });
    }]);

    careTeachers_module.controller("careteachersController",["$scope","gettermsResource","departmentResource","teacherNameService","$cookieStore","teacherNoResource",function ($scope,gettermsResource,departmentResource,teacherNameService,$cookieStore,teacherNoResource) {

        //定义一个存放关注老师的数组
        $scope.storageTeachers = [];

        $scope.selectedTerm=[];
        $scope.selectedDepartment =[];
            //获得所有学期
        gettermsResource.getTerms({},function(result){
            console.log("获取学期成功！");
            $scope.terms = result;
            $scope.selectedTerm =result[0].termNo;
        },function(){
            console.log("获取学期失败");
        });
        //查询所有部门
        departmentResource.getDepartment({}, function (result) {
            $scope.departments = result;
            $scope.selectedDepartment = $scope.departments[4].depNo;
        }, function () {
            console.log("获取部门信息失败");
        });

        //
        function renderData(data) {
            $scope.selectTeachersList =data;
            console.log($scope.selectTeachersList);

            //关注一个老师
            $scope.followTeacher = function (id,teacherName) {
                teacherNoResource.addCaredTeachers({
                    termNo:$cookieStore.get('currentTerm').termNo,
                    masterNo:$cookieStore.get('user').account,
                    teacherNo:id
                },function(result){
                 console.log("增加成功！");
                },function(result){
                    console.log("增加失败",result);
                });
                //根据id遍历关注的是哪个老师
                var index = -1;
                angular.forEach($scope.selectTeachersList,function (item,key) {
                    if(item.id == id ){
                        index = key;
                    };
                });
                if(index != -1){
                    $scope.selectTeachersList.splice(index,1);
                    $scope.storageTeachers.push({'id':id,'teacherName':teacherName});
                }
                return $scope.storageTeachers;
            };

            //关注所有的老师
            $scope.allFollowTeacher = function () {
                for(var i = 0 ; i < $scope.selectTeachersList.length ; i++){
                    $scope.storageTeachers.push($scope.selectTeachersList[i])
                }
                $scope.selectTeachersList = [];
            };

            // 取消关注的老师
            $scope.cancelFollowTeacher = function(id,teacherName){
                //根据id遍历取消关注的是哪个老师
                var index = -1;
                angular.forEach($scope.storageTeachers,function (item,key) {
                    if(item.id == id ){
                        index = key;
                    };
                });
                if(index != -1){
                    $scope.storageTeachers.splice(index,1);
                    $scope.selectTeachersList.push({'id':id,'teacherName':teacherName});
                }
                return $scope.storageTeachers;
            };
            // 取消关注的全部老师
            $scope.allCancelFollowTeacher = function () {
                for(var i = 0 ; i < $scope.storageTeachers.length ; i++){
                    $scope.selectTeachersList.push($scope.storageTeachers[i])
                }
                $scope.storageTeachers = [];
            };
        }

        /*teacherNameService.getTeacherName(termNo,depNo,renderData);*/

        teacherNameService.getTeacherName($scope.selectedTerm,$scope.selectedDepartment,renderData);


        $scope.termChange = function () {
            console.log( $scope.selectedTerm );
            console.log( $scope.selectedDepartment );
            teacherNameService.getTeacherName($scope.selectedTerm,$scope.selectedDepartment,renderData);
        };
        $scope.depChange = function () {
            console.log( $scope.selectedTerm );
            console.log( $scope.selectedDepartment );
            teacherNameService.getTeacherName($scope.selectedTerm,$scope.selectedDepartment,renderData);
        };



        /**
         * 选择不同的系
         */
       /* $scope.showDeps = false;
        $scope.selectDeps = function () {
            $scope.showDeps = !$scope.showDeps;
        };
        $scope.gainDeps = function (depName) {
            $scope.depName = depName;
            $scope.showDeps = false;
        };
        $scope.hideDep = function () {
            $scope.showDeps = false;
        };*/
        /**
         * 关注一个老师
         */
        /*$scope.followTeacher = function (id,teacherName) {
            //根据id遍历关注的是哪个老师
            var index = -1;
            angular.forEach($scope.selectTeachersList,function (item,key) {
                if(item.id == id ){
                    index = key;
                };
            });
            if(index != -1){
                $scope.selectTeachersList.splice(index,1);
                $scope.storageTeachers.push({'id':id,'teacherName':teacherName});
            }
            return $scope.storageTeachers;
        };
        /!**
         * 关注所有的老师
         *!/
        $scope.allFollowTeacher = function () {
            for(var i = 0 ; i < $scope.selectTeachersList.length ; i++){
                $scope.storageTeachers.push($scope.selectTeachersList[i])
            }
            $scope.selectTeachersList = [];
        };
        /!**
         * 取消关注的老师
         *!/
        $scope.cancelFollowTeacher = function(id,teacherName){
            //根据id遍历取消关注的是哪个老师
            var index = -1;
            angular.forEach($scope.storageTeachers,function (item,key) {
                if(item.id == id ){
                    index = key;
                };
            });
            if(index != -1){
                $scope.storageTeachers.splice(index,1);
                $scope.selectTeachersList.push({'id':id,'teacherName':teacherName});
            }
            return $scope.storageTeachers;
        }
        /!**
         * 取消关注的全部老师
         *!/
        $scope.allCancelFollowTeacher = function () {
            for(var i = 0 ; i < $scope.storageTeachers.length ; i++){
                $scope.selectTeachersList.push($scope.storageTeachers[i])
            }
            $scope.storageTeachers = [];
        };*/




    }]);

    /*//根据各系和学期筛选出老师的service
    careTeachers_module.service("selectTeachersService",[function () {
        this.loadTeachers = function () {
            var selectTeachersList = [{
                id:1,
                teacherName:"白顺科"
            },{
                id:2,
                teacherName:"卞昌军"
            },{
                id:3,
                teacherName:"曹晓燕"
            },{
                id:4,
                teacherName:"查莹花"
            },{
                id:5,
                teacherName:"柴锁住"
            },{
                id:6,
                teacherName:"陈潘"
            },{
                id:7,
                teacherName:"陈旭"
            },{
                id:8,
                teacherName:"崔群"
            },{
                id:9,
                teacherName:"丁长青"
            },{
                id:10,
                teacherName:"董彪"
            },{
                id:11,
                teacherName:"郭朝霞"
            },{
                id:12,
                teacherName:"郭凡"
            },{
                id:13,
                teacherName:"郭雷"
            },{
                id:14,
                teacherName:"胡光永"
            },{
                id:15,
                teacherName:"胡烈"
            },{
                id:16,
                teacherName:"黄继平"
            },{
                id:17,
                teacherName:"黄珏"
            },{
                id:18,
                teacherName:"贾利娟"
            },{
                id:19,
                teacherName:"蒋凌燕"
            },{
                id:20,
                teacherName:"李甲林"
            },{
                id:21,
                teacherName:"李中科"
            },{
                id:22,
                teacherName:"胡光勇"
            }];
            return selectTeachersList;
        }
    }]);
    //所有学期的service
    careTeachers_module.service("termService",[function () {
        this.loadTerm = function () {
            var terms = [{
                id:1,
                termNo:"2016学年第二学期"
            },{
                id:2,
                termNo:"2016学年第一学期"
            },{
                id:3,
                termNo:"2015学年第二学期"
            },{
                id:4,
                termNo:"2015学年第一学期"
            },{
                id:5,
                termNo:"2014学年第二学期"
            },{
                id:6,
                termNo:"2014学年第一学期"
            },{
                id:7,
                termNo:"2013学年第二学期"
            },{
                id:8,
                termNo:"2013学年第一学期"
            },{
                id:9,
                termNo:"2012学年第二学期"
            },{
                id:10,
                termNo:"2012学年第一学期"
            },{
                id:11,
                termNo:"2011学年第二学期"
            },{
                id:12,
                termNo:"2011学年第一学期"
            }];
            return terms;
        }
    }]);
    //各系的service
    careTeachers_module.service("depService",[function () {
        this.loadDep = function () {
            var deps = [{
                id:1,
                depName:'学工处'
            },{
                id:2,
                depName:'质量监控与评估处'
            },{
                id:3,
                depName:'机械工学院'
            },{
                id:4,
                depName:'电气工程学院'
            },{
                id:5,
                depName:'计算机与软件学院'
            },{
                id:6,
                depName:'经济管理学院'
            },{
                id:7,
                depName:'艺术设计学院'
            },{
                id:8,
                depName:'社会科学部'
            },{
                id:9,
                depName:'体育部'
            },{
                id:10,
                depName:'国际教育学院'
            },{
                id:11,
                depName:'外语系'
            },{
                id:12,
                depName:'文理学院'
            },{
                id:13,
                depName:'交通工程学院'
            },{
                id:14,
                depName:'商务贸易学院'
            },{
                id:15,
                depName:'公共基础部'
            },{
                id:16,
                depName:'工程技术实训中心'
            }];
            return deps;
        }
    }]);*/

    careTeachers_module.factory("teacherNoResource",["$resource",function ($resource) {
        var service = $resource('../qm-api/caredTeachers', {}, {
            getNotCaredTeachers: { method: 'GET',isArray:true},
            addCaredTeachers: { method: 'POST'}
        });
        return service;
        console.log(service);
    }]);
    careTeachers_module.service("teacherNameService",["teacherNoResource",function (teacherNoResource) {
        this.getTeacherName=function(termNo,depNo,callback){
            teacherNoResource.getNotCaredTeachers({
                termNo:termNo,
                depNo:depNo
            },function(result){
                console.log(result);
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("取失败",result);
            });
        }
    }])

})();
