/**
 * Created by Administrator on 2017/4/2.
 */
(function(){
    'use strict';
    var courseMaintenance_module=angular.module("qm.base_subject",['ui.router','ui.select']);
    courseMaintenance_module.factory("courseinstance",function(){return {}});
    courseMaintenance_module.config(["$stateProvider",function ($stateProvider) {
        $stateProvider.state('qm.base_subject',{
            url:'/database/subject',
            title:'课程维护',
            templateUrl:"quality/database/subject/subject.html",
            controller:"courseMaintenanceController"
        });
    }]);
    courseMaintenance_module.factory("gettermsResource",["$resource",function($resource){
        var service = $resource('../qm-api/terms', {}, {
            getTerms: { method: 'GET',isArray:true},
        });
        return service;

    }]);

    courseMaintenance_module.factory("subteacherResource",["$resource",function($resource){
        var service = $resource('../qm-api/teacher', {}, {
            getTeachers: { method: 'GET', url:'../qm-api/teacher/findTeacherpage'},
        });
        return service;

    }]);

    courseMaintenance_module.factory("CourseResource",["$resource",function($resource){
        var service = $resource('../qm-api/course', {}, {
            saveCourse: { method: 'POST'},
            getCourses:{ method: 'GET'
            }

        });
        return service;

    }]);

    courseMaintenance_module.factory("subStudentResource",["$resource",function($resource){
        var service = $resource('../qm-api/student', {}, {
            getallgxhStudent:{method: 'GET', url:'../qm-api/pagestudent'},
           /* getkeyStudents:{ method: 'GET'}*/

        });
        return service;

    }]);



    courseMaintenance_module.service("loadCourseService",["CourseResource","courseinstance",function(CourseResource,courseinstance){
        this.loadCourse=function(selectedTerm,selectedDepartment,index,length,callback){
            CourseResource.getCourses({
                selectedTerm:selectedTerm,
                selectedDepartment:selectedDepartment,
                index:index,
                length:length
            },function(result){
                courseinstance.gxhTerm = selectedTerm;
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("课程获取失败",result);
            });
        }
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





    courseMaintenance_module.controller("courseMaintenanceController",["$scope","$uibModal","gettermsResource","departmentResource","courseinstance","CourseResource","loadCourseService",function($scope,$uibModal,gettermsResource,departmentResource,courseinstance,CourseResource,loadCourseService){

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

        //显示个性化课程的学生信息
        $scope.showSubject=function (coursename,taskNo,studentNumber) {
            var showSjectInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/subject/subjectDetail.html",
                controller: 'showcourseController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            courseinstance.gxhcoursename = coursename;
            courseinstance.gxhtaskNo = taskNo;
            courseinstance.gxhstudentNumber = studentNumber;


        }

        getTermsAndDepartments();

        //获得学期与部门
        function getTermsAndDepartments() {
            gettermsResource.getTerms({
            },function(result){
                console.log("获取学期成功！");
                console.log(result);
                $scope.terms = result;
                $scope.selectedTerm = $scope.terms[0].termName;
                courseinstance.showterms = $scope.terms;
            },function(result){
                console.log("获取学期失败");
            });

            //查询所有部门
            departmentResource.getDepartment({}, function (result) {
                console.log(result);
                $scope.departments = result;
                courseinstance.showdepartments = $scope.departments;
                $scope.selectedDepartment = $scope.departments[0].depName;
            }, function () {
                console.log("获取部门信息失败");
            });


        }
        function renderCourseData(data){
            $scope.courseArrays=data;
            console.log($scope.courseArrays);
        }

        //根据学期和部门查询课程
        $scope.loadData = function (index,length) {
            loadCourseService.loadCourse($scope.selectedTerm,$scope.selectedDepartment,index,length,renderCourseData);
        }


        $scope.removeCourse = function (index) {
          /*  $scope.menus.splice(index, 1);*/
        }


    }]);
    courseMaintenance_module.factory("subgetclassResource",["$resource",function($resource){
        var service = $resource('../qm-api/classes/page', {}, {
            getsubClasses: { method: 'GET'},
        });
        return service;

    }]);

    courseMaintenance_module.service("subgetteacherService",["subteacherResource",function(subteacherResource){
        this.getTeachers=function(keyword,index,length,callback){
            subteacherResource.getTeachers({
                keyword:keyword,
                index:index,
                length:length
            },function(result){
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("教师列表获取失败",result);
            });
        }
    }]);

    courseMaintenance_module.service("subgetclassService",["subgetclassResource",function(subgetclassResource){
        this.getClasses=function(keyword,index,length,callback){
            subgetclassResource.getsubClasses({
                keyword:keyword,
                index:index,
                length:length
            },function(result){
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("班级列表获取失败",result);
            });
        }
    }]);

    courseMaintenance_module.service("loadStudentService",["subStudentResource",function(subStudentResource){
        this.getStudent=function(studentkeyword,taskNo,index,length,callback){
            subStudentResource.getkeyStudents({
                studentkeyword:studentkeyword,
                taskNo:taskNo,
                index:index,
                length:length
            },function(result){
                if(callback){
                    callback(result);}
            },function(result){
                console.log("个性化课程中的学生信息获取失败",result);
            });
        }
    }]);


    courseMaintenance_module.controller("addcourseController",["$scope","$uibModalInstance","SweetAlert","userService","courseinstance","subgetclassService","CourseResource","subgetteacherService",function($scope,$uibModalInstance,SweetAlert,userService,courseinstance,subgetclassService,CourseResource,subgetteacherService){


        //定义一个数组，用于装载节次信息
        $scope.locations =[];
        //定义一个数组，用于授课教师信息
        $scope.teachers = [];
        //定义一个数组，用于授课班级信息
        $scope.classes = [];

        //定义一个String，用于存放班级代码
        $scope.checkedclass="";

        //定义一个String，用于存放班级代码
        $scope.checkedsections="";


        //定义一个数组，用于承担部门信息
        $scope.depatments =  courseinstance.showdepartments;

        //定义一个数组，用于课程类型（3公选课、1讲授课、2实训课）
        $scope.courseTypes = [
            {id: 1, name: '讲授课'},
            {id: 2, name: '实训课'},
            {id: 3, name: '公选课'}
        ];

        //定义一个数组，用于课程学期（3公选课、1讲授课、2实训课）
        $scope.courseTerms = courseinstance.showterms;


        function  ObjStory(section,location) //创建对象function
        {
            this.section = section;
            this.location= location;
        }
        function  teacherObjStory(username,loginname) //创建对象function
        {
            this.userName = username;
            this.userLoginname= loginname;
        }

        function  classObjStory(classname,classnumber) //创建对象function
        {
            this.className = classname;
            this.classNo= classnumber;
        }
        $scope.addplace =function (sectionString,thischeck) {
            if(thischeck==false){
                SweetAlert.swal({
                    title: '请输入上课地点',
                    text: '当前选择的节次为  '+sectionString,
                    type: "input",
                    confirmButtonColor: '#DD6B55',
                    showCancelButton: true,
                    confirmButtonText: '确定',
                    closeOnCancel: true,
                    closeOnConfirm: false
                },function (inputValue) {
                    if (inputValue === false) return false;
                    if (inputValue === "") {
                        swal.showInputError("您还未输入上课地点!");
                        return false;
                    }
                    swal("恭喜!", "您已经成功添加上课地点。");
                    var onelocation= new ObjStory(sectionString,inputValue);//声明对象
                    $scope.locations.push(onelocation);
                    console.log("push后");
                    console.log( $scope.locations);
                    for(var i =0; i<$scope.locations.length;i++){
                        var sec = $scope.locations[i].section;
                        for(var j =0;j<$scope.sections.length;j++){
                            for(var m = 0;m<8;m++){
                                var thisname = $scope.sections[j].day[m].name;
                                if (sec==thisname){
                                    $scope.sections[j].day[m].checked = true;
                                }
                            }
                        }
                    }
                });
            }else {
                SweetAlert.swal({
                    title: '该节次信息已被添加',
                    text: '若想进行修改，请先删除已添加的节次信息',
                    type: 'warning',
                    showCancelButton: false,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: '知道了!',
                    closeOnConfirm: true
                });
            }


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


        function renderteacherData(data){
            $scope.teaArrays=data;
            console.log($scope.teaArrays);
        }
        //搜索教师信息
        $scope.loadteacherData = function (index,length) {
            if($scope.teachers.length!=0){
                SweetAlert.swal({
                    title: '您已经选择一名授课教师',
                    text: '若想进行修改，请先删除已添加的教师',
                    type: 'warning',
                    showCancelButton: false,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: '知道了!',
                    closeOnConfirm: true
                });
            }else {
                subgetteacherService.getTeachers($scope.teacherString,index,length,renderteacherData);

               /* userService.getUsers($scope.teacherString,index,length,renderteacherData);*/
            }
        }

        //选择教师
        $scope.chooseTeacher = function (username,loginnam) {
                if($scope.teachers.length==0){
                    var oneteacher= new teacherObjStory(username,loginnam);//声明对象
                    $scope.teachers.push(oneteacher);
                    courseinstance.teacherloginname = loginnam;
                }else {
                    SweetAlert.swal({
                        title: '你已选择一名教师',
                        text: '只能选择一名授课教师',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#DD6B55',
                        confirmButtonText: '知道了!',
                        closeOnConfirm: true
                    });
                }
        }

        function renderclassData(data){
            $scope.classArrays=data;
            console.log($scope.classArrays);
        }

        //搜索班级信息
        $scope.loadclassData = function (index,length) {
            console.log($scope.classString);
            subgetclassService.getClasses($scope.classString,index,length,renderclassData);

        }
        $scope.chooseClass = function (classname,classnumber) {
            var oneteacher= new classObjStory(classname,classnumber);//声明对象
            $scope.classes.push(oneteacher);
            console.log($scope.classes);

        }



        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        //删除选择的教师标签
        $scope.removeteacher = function (index,teacherid) {
            $scope.teachers.splice(index, 1);
            console.log(teacherid);
        }

        //删除选择的教师标签
        $scope.removeclass = function (index,classid) {
            $scope.classes.splice(index, 1);
            console.log(classid);
        }

        //删除选中的上课节次信息
        $scope.removesection = function (index,thissection) {
            $scope.locations.splice(index, 1);
                for(var j =0;j<$scope.sections.length;j++){
                    for(var m = 0;m<8;m++){
                        var thisname = $scope.sections[j].day[m].name;
                        if (thissection==thisname){
                            $scope.sections[j].day[m].checked = false;
                        }
                    }
                }


        }

        //提交新增的课程信息
        $scope.submitCourse = function () {
            console.log($scope.courseNo);
            console.log($scope.courseName);
            console.log($scope.studentNumber);
            console.log($scope.department);
            console.log($scope.courseType);
            console.log($scope.courseAttr);
            console.log($scope.courseWeeks);
            console.log($scope.courseHours);
            console.log($scope.courseTerm);
            $scope.tealoginname=courseinstance.teacherloginname;
            console.log($scope.tealoginname);

            for(var i = 0; i<$scope.classes.length;i++){
                $scope.checkedclass = $scope.checkedclass+$scope.classes[i].classNo+'-';
            }
            console.log($scope.checkedclass);


            for (var j =0;j<$scope.locations.length;j++){
                $scope.checkedsections=$scope.checkedsections+$scope.locations[j].section+','+$scope.locations[j].location+';';
            }
            console.log($scope.checkedsections);


            CourseResource.saveCourse({
                courseNo:$scope.courseNo,
                courseName:$scope.courseName,
                studentNumber:$scope.studentNumber,
                department:$scope.department,
                courseType:$scope.courseType,
                courseAttr:$scope.courseAttr,
                courseWeeks:$scope.courseWeeks,
                courseHours:$scope.courseHours,
                courseTerm:$scope.courseTerm,
                tealoginname:$scope.tealoginname,
                checkedclass:$scope.checkedclass,
                checkedsections:$scope.checkedsections
            },function(result){
                console.log("课程保存成功！",result);
                SweetAlert.swal({
                    title: '课程保存成功',
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: 'ok',
                    closeOnConfirm: true
                },function () {
                    $uibModalInstance.dismiss('cancel');
                });

            },function(result){
                console.log("课程保存失败",result);
            });











        }




    }]);



    //显示任选课学生信息
    courseMaintenance_module.controller("showcourseController",["$scope","$uibModalInstance","SweetAlert","loadStudentService","courseinstance","subStudentResource",function($scope,$uibModalInstance,SweetAlert,loadStudentService,courseinstance,subStudentResource){



        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        $scope.person = {};
        $scope.people = [
            { name: 'Adam',      email: 'adam@email.com',      age: 10 },
            { name: 'Amalie',    email: 'amalie@email.com',    age: 12 },
            { name: 'Wladimir',  email: 'wladimir@email.com',  age: 30 },
            { name: 'Samantha',  email: 'samantha@email.com',  age: 31 },
            { name: 'Estefanía', email: 'estefanía@email.com', age: 16 },
            { name: 'Natasha',   email: 'natasha@email.com',   age: 54 },
            { name: 'Nicole',    email: 'nicole@email.com',    age: 43 },
            { name: 'Adrian',    email: 'adrian@email.com',    age: 21 }
        ];

        $scope.address = {};
        $scope.refreshAddresses = function(address) {
            var params = {address: address, sensor: false};
            return $http.get(
                '//maps.googleapis.com/maps/api/geocode/json',
                {params: params}
            ).then(function(response) {
                $scope.addresses = response.data.results;
            });
        };

        $scope.counter = 0;
        $scope.someFunction = function (item, model){
            vm.counter++;
            vm.eventResult = {item: item, model: model};
        };

        $scope.selectedPeople = [$scope.people[5]];

        //显示当前页面的课程信息
        $scope.currentgxhcoursename = courseinstance.gxhcoursename;
        $scope.currentgxhtaskNo  = courseinstance.gxhtaskNo ;
        $scope.currentgxhstudentNumber = courseinstance.gxhstudentNumber ;
        $scope.currentgxhTermNo = courseinstance.gxhTerm
        $scope.currentgxhTermArrays = courseinstance.showterms;
        for (var i =0;i<$scope.currentgxhTermArrays.length;i++){
            if($scope.currentgxhTermNo==$scope.currentgxhTermArrays[i].termNo){
                $scope.currentgxhTermName = $scope.currentgxhTermArrays[i].termName;
            }
        }

        subStudentResource.getallgxhStudent({
            taskNo:38163,
            index:0,
            length:4
        },function(result){
            console.log(result);
        },function(result){
            console.log("个性化课程中的学生信息获取失败",result);
        });

        //返回个性化课程中的学生信息
        function renderstudentData(data){
            $scope.gxhstudentArrays=data;
            console.log($scope.classArrays);
        }

        //搜索班级信息
        $scope.searchgxhStu = function (index,length) {
            console.log($scope.detailStudentinfo);
            loadStudentService.getStudent($scope.detailStudentinfo, $scope.currentgxhtaskNo,index,length,renderstudentData);

        }







    }]);





})();