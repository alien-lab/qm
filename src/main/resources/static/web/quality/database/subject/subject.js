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

    courseMaintenance_module.controller("addcourseController",["$scope","$uibModalInstance",function($scope,$uibModalInstance){

        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }



    }]);


    courseMaintenance_module.controller('subject', function($scope) {
        $scope.names = ["学年","2016年第二学期","2016年第一学期","2015年第二学期","2015年第一学期"];
    });

    courseMaintenance_module.controller('subject1', function($scope) {
        $scope.names = ["部门","机械工程","计算机与软件","能源与电气工程","文理学院"];
    });



})();