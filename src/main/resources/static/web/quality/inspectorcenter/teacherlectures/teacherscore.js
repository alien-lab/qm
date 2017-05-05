/**
 * Created by Master QB on 2017/5/3.
 */
(function(){
    'use strict';
    var teacherscore_module=angular.module("qm.teacherscore",['ui.router','ui.bootstrap-slider']);//ui.router模块作为主应用模块的依赖模块
    teacherscore_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.teacherscore', {
            url: '/teacherscore',
            title: '课堂教学质量评价表',
            templateUrl: "quality/inspectorcenter/teacherlectures/teacherscore.html",
            controller:"teacherscoreController"
        });
    }]);

    teacherscore_module.service("loadRuleService",["qmMsterResource",function(qmMsterResource){
        this.loadRule=function(rule_version_flag,rule_table,callback){
            qmMsterResource.getRules({
                rule_version_flag:rule_version_flag,
                rule_table:rule_table
            },function(result){
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("评价表获取失败",result);
            });
        }
    }]);

    teacherscore_module.controller("teacherscoreController",["$scope","$uibModalInstance","loadRuleService","ruleinstance","ngDialog",function($scope,$uibModalInstance,loadRuleService,ruleinstance,ngDialog){

        //各项初始值
        $scope.slidervalue=10;

        //课程名称
        $scope.courseName = ruleinstance.courseName;
        //教师姓名
        $scope.teacherName = ruleinstance.teacherName;

        $scope.PER10=10;
        $scope.PER11=10;
        $scope.PER12=10;
        $scope.PER13=10;
        $scope.PER14=10;
        $scope.PER15=10;
        $scope.PER16=10;
        $scope.PER17=10;
        $scope.PER18=10;
        $scope.PER19=10;

        //课堂教学质量评价表
        if (ruleinstance.ruletype==1){
            loadRuleService.loadRule("TK_JS","QM_MASTER_LISTEN",renderRuleData);

            //综合实训课程教学质量评价表
        }else if(ruleinstance.ruletype==2) {
            loadRuleService.loadRule("TK_SX","QM_MASTER_LISTEN",renderRuleData);

            //任课教师评学表
        }else if(ruleinstance.ruletype==3) {
            loadRuleService.loadRule("PJ_PX","qm_tea_px",renderRuleData);

        }//学生评教表
        else if(ruleinstance.ruletype==4) {
            loadRuleService.loadRule("PJ_PJ","qm_tea_px",renderRuleData);

        }
        function renderRuleData(data){
            $scope.rules=data;
            $scope.totalvalue = 10*data.length;
            $scope.versionTitle = data[0].ruleVersionTitle;
            console.log(data);
        }

        $scope.myFunc = function (ruleField,slidervalue) {
            if (ruleinstance.ruletype==1){
               if (ruleField=="PER11"){
                    $scope.PER11 = slidervalue;
                }else  if (ruleField=="PER12"){
                    $scope.PER12 = slidervalue;
                }else  if (ruleField=="PER13"){
                    $scope.PER13 = slidervalue;
                }else  if (ruleField=="PER14"){
                    $scope.PER14 = slidervalue;
                }else  if (ruleField=="PER15"){
                    $scope.PER15 = slidervalue;
                }else  if (ruleField=="PER16"){
                    $scope.PER16 = slidervalue;
                }
                $scope.totalvalue = $scope.PER11+$scope.PER12+$scope.PER13+$scope.PER14+$scope.PER15+$scope.PER16;
            } else   if (ruleinstance.ruletype==2){
                if (ruleField=="PER11"){
                    $scope.PER11 = slidervalue;
                }else  if (ruleField=="PER12"){
                    $scope.PER12 = slidervalue;
                }else  if (ruleField=="PER13"){
                    $scope.PER13 = slidervalue;
                }else  if (ruleField=="PER14"){
                    $scope.PER14 = slidervalue;
                }else  if (ruleField=="PER15"){
                    $scope.PER15 = slidervalue;
                }else  if (ruleField=="PER16"){
                    $scope.PER16 = slidervalue;
                }
            } else if (ruleinstance.ruletype==3) {
                if (ruleField == "PER10") {
                    $scope.PER10 = slidervalue;
                } else if (ruleField == "PER11") {
                    $scope.PER11 = slidervalue;
                } else if (ruleField == "PER12") {
                    $scope.PER12 = slidervalue;
                } else if (ruleField == "PER13") {
                    $scope.PER13 = slidervalue;
                } else if (ruleField == "PER14") {
                    $scope.PER14 = slidervalue;
                } else if (ruleField == "PER15") {
                    $scope.PER15 = slidervalue;
                } else if (ruleField == "PER16") {
                    $scope.PER16 = slidervalue;
                } else if (ruleField == "PER17") {
                    $scope.PER14 = slidervalue;
                } else if (ruleField == "PER18") {
                    $scope.PER15 = slidervalue;
                } else if (ruleField == "PER19") {
                    $scope.PER16 = slidervalue;
                }
            }else if (ruleinstance.ruletype==4){
                    if (ruleField=="PER10"){
                        $scope.PER10 = slidervalue;
                    } else if (ruleField=="PER11"){
                        $scope.PER11 = slidervalue;
                    }else  if (ruleField=="PER12"){
                        $scope.PER12 = slidervalue;
                    }else  if (ruleField=="PER13"){
                        $scope.PER13 = slidervalue;
                    }else  if (ruleField=="PER14"){
                        $scope.PER14 = slidervalue;
                    }else  if (ruleField=="PER15"){
                        $scope.PER15 = slidervalue;
                    }else  if (ruleField=="PER16"){
                        $scope.PER16 = slidervalue;
                    }else  if (ruleField=="PER17"){
                        $scope.PER14 = slidervalue;
                    }else  if (ruleField=="PER18"){
                        $scope.PER15 = slidervalue;
                    }
            }


        }
        function formatTen(num) {
            return num > 9 ? (num + "") : ("0" + num);
        }
        $scope.submit = function () {
            if (ruleinstance.ruletype==1){
                console.log($scope.PER11);
                console.log($scope.PER12);
                console.log($scope.PER13);
                console.log($scope.PER14);
                console.log($scope.PER15);
                console.log($scope.PER16);
                console.log("听课评价："+$scope.skpj);
                console.log("教学建议："+$scope.jxjy);
                console.log("日期："+$scope.date.getFullYear() + "-" + formatTen($scope.date.getMonth() + 1) + "-" + formatTen($scope.date.getDate()));

            }


        }

        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

        activate();
        function activate() {
            $scope.today = function() {
                $scope.dt = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.dt = null;
            };

            $scope.toggleMin = function() {
                $scope.minDate = $scope.minDate ? null : new Date();
            };
            $scope.toggleMin();

            $scope.open = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
            };

            $scope.dateOptions = {
                formatYear: 'yy',
                startingDay: 1
            };
            $scope.initDate = new Date('2019-10-20');
            $scope.formats = [ 'yyyy/MM/dd', 'yyyy.MM.dd', 'shortDate'];
            $scope.format = $scope.formats[1];
        }

        $scope.showngdialog = function (index) {
           if (index==1){
               ngDialog.openConfirm({
                   template: 'dialogWithNestedConfirmDialogId',
                   className: 'ngdialog-theme-default custom-width-70 ',
                   scope: $scope,
                   controller:'ngdialogController'
               })
                   .then(function(value){
                       console.log('保存成功' + value);
                       // Perform the save here
                   }, function(value){
                       console.log('rejected:' + value);

                   });
           }else {
               ngDialog.openConfirm({
                   template: 'dialogWithNestedConfirmDialogId',
                   className: 'ngdialog-theme-default custom-width-70',
                   scope: $scope,
                   controller:'ngdialogController'
               })
                   .then(function(value){
                       console.log('保存成功' + value);
                       // Perform the save here
                   }, function(value){
                       console.log('rejected:' + value);
                   });
           }
        }



    }]);

    teacherscore_module.controller("ngdialogController",["$scope",function($scope){
        $scope.wechat ="4546546";


    }]);


    })();
