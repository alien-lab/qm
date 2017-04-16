/**
 * Created by Administrator on 2017/4/2.
 */
(function(){
    'use strict';
    var courseMaintenance_module=angular.module("qm.base_subject",['ui.router']);
    courseMaintenance_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_subject',{
            url:'/database/subject',
            title:'课程维护',
            templateUrl:"quality/database/subject/subject.html",
            controller:"courseMaintenanceController"
        });
    }]);



    courseMaintenance_module.controller('subject', function($scope) {
        $scope.names = ["学年","2016年第二学期","2016年第一学期","2015年第二学期","2015年第一学期"];
    });

    courseMaintenance_module.controller('subject1', function($scope) {
        $scope.names = ["部门","机械工程","计算机与软件","能源与电气工程","文理学院"];
    });


    courseMaintenance_module.directive('formWizard',formWizard)
        formWizard.$inject = ['$parse'];
        function formWizard ($parse) {
            var directive = {
                link: link,
                restrict: 'A',
                scope: true
            };
            return directive;

            function link(scope, element, attrs) {
                var validate = $parse(attrs.validateSteps)(scope),
                    wiz = new Wizard(attrs.steps, !!validate, element);
                scope.wizard = wiz.init();
            }

            function Wizard (quantity, validate, element) {

                var self = this;
                self.quantity = parseInt(quantity,10);
                self.validate = validate;
                self.element = element;

                self.init = function() {
                    self.createsteps(self.quantity);
                    self.go(1); // always start at fist step
                    return self;
                };

                self.go = function(step) {

                    if ( angular.isDefined(self.steps[step]) ) {

                        if(self.validate && step !== 1) {
                            var form = $(self.element),
                                group = form.children().children('div').get(step - 2);

                          /*  if (false === form.parsley().validate( group.id )) {
                                return false;
                            }*/
                        }

                        self.cleanall();
                        self.steps[step] = true;
                    }
                };

                self.active = function(step) {
                    return !!self.steps[step];
                };

                self.cleanall = function() {
                    for(var i in self.steps){
                        self.steps[i] = false;
                    }
                };

                self.createsteps = function(q) {
                    self.steps = [];
                    for(var i = 1; i <= q; i++) self.steps[i] = false;
                };

            }
        }





    courseMaintenance_module.controller("courseMaintenanceController",["$scope","$uibModal",function($scope,$uibModal){
        $scope.addSubject=function () {
                var addsubjectInfo = $uibModal.open({
                    animation: true,
                    templateUrl: "quality/database/subject/addsubject.html",
                    controller: 'addcourseController',
                    bindToController: true,
                    size: "lg",
                    backdrop: false
                });

        }



    }]);

    courseMaintenance_module.controller("addcourseController",["$scope","$uibModalInstance","SweetAlert",function($scope,$uibModalInstance,SweetAlert){

        $scope.addplace =function (sectionString) {
            SweetAlert.swal({
                title: '请输入上课地点',
                text: '当前选择的节次为  '+sectionString,
                type: "input",
                confirmButtonColor: '#DD6B55',
                confirmButtonText: '确定',
                closeOnCancel: false
            },function () {
            /*  var m = angular.element('fieldset').find('input').val();
                console.log(m);*/
                
            });
            console.log(sectionString);
        }
        $scope.sections = [
            {
                day:[{name:'1-2节',mian:true},{name:'周一：1-2节',checked:false},{name:'周二：1-2节',checked:false},{name:'周三：1-2节',checked:false},{name:'周四：1-2节',checked:false},{name:'周五：1-2节',checked:false},{name:'周六：1-2节',checked:false},{name:'周日：1-2节',checked:false}]},
            {
                day:[{name:'3-4节',mian:true},{name:'周一：3-4节',checked:false},{name:'周二：3-4节',checked:false},{name:'周三：3-4节',checked:false},{name:'周四：3-4节',checked:false},{name:'周五：3-4节',checked:false},{name:'周六：3-4节',checked:false},{name:'周日：3-4节',checked:false}]},
            {
                day:[{name:'5-6节',mian:true},{name:'周一：5-6节',checked:false},{name:'周二：5-6节',checked:false},{name:'周三：5-6节',checked:false},{name:'周四：5-6节',checked:false},{name:'周五：5-6节',checked:false},{name:'周六：5-6节',checked:false},{name:'周日：5-6节',checked:false}]},
            {
                day:[{name:'7-8节',mian:true},{name:'周一：7-8节',checked:false},{name:'周二：7-8节',checked:false},{name:'周三：7-8节',checked:false},{name:'周四：7-8节',checked:false},{name:'周五：7-8节',checked:false},{name:'周六：7-8节',checked:false},{name:'周日：7-8节',checked:false}]},
            {
                day:[{name:'9-10节',mian:true},{name:'周一：9-10节',checked:false},{name:'周二：9-10节',checked:false},{name:'周三：9-10节',checked:false},{name:'周四：9-10节',checked:false},{name:'周五：9-10节',checked:false},{name:'周六：9-10节',checked:false},{name:'周日：9-10节',checked:false}]},
        ];

        $scope.teachers = [
            {id: 1, name: 'awesome user1'},
            {id: 2, name: 'awesome user2'},
            {id: 3, name: 'awesome user3'},
            {id: 4, name: 'awesome user1'},
            {id: 5, name: 'awesome user2'},
            {id: 6, name: 'awesome user3'}
        ];
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        //删除选择的标签
        $scope.removeteacher = function (index) {
            $scope.teachers.splice(index, 1);
        }





    }]);





})();