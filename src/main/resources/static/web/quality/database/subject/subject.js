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
        courseMaintenance_module.filter('propsFilter', propsFilter);
        function propsFilter() {
            return filterFilter;
            ////////////////
            function filterFilter(items, props) {
                var out = [];

                if (angular.isArray(items)) {
                    items.forEach(function(item) {
                        var itemMatches = true;

                        var keys = Object.keys(props);
                        for (var i = 0; i < keys.length; i++) {
                            var prop = keys[i];
                            var text = props[prop].toLowerCase();
                          /*  if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                                itemMatches = true;
                                break;
                            }*/
                        }

                        if (itemMatches) {
                            out.push(item);
                        }
                    });
                } else {
                    // Let the output be the input untouched
                    out = items;
                }

                return out;
            }
        }




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
            updateCourse: { method: 'POST', url:'../qm-api/updatecourse'},
            getCourses:{ method: 'GET'},
            getDetailCourse:{ method: 'GET', url:'../qm-api/detailcourse'},
            deleteCourse:{method: 'DELETE'},
            saveLogicCourse:{ method: 'POST', url:'../qm-api/classlogic' },
            searchLogicStudent:{method: 'GET',isArray:true, url:'../qm-api/classlogic'},
            getCourseByKey:{method: 'GET', url:'../qm-api/keywordcourse'}

        });
        return service;

    }]);

    courseMaintenance_module.factory("subStudentResource",["$resource",function($resource){
        var service = $resource('../qm-api/student', {}, {
            getallgxhStudent:{method: 'GET', url:'../qm-api/pagestudent'},
            getkeyStudents:{ method: 'GET', url:'../qm-api/pagekeystudent'},
            getallStudent:{ method: 'GET',isArray:true, url:'../qm-api/allstudent'},
            deleteStudent:{method: 'DELETE' , url:'../qm-api/gxhstudent'   },
            getkeywordStudents:{ method: 'GET', url:'../qm-api/keywordtudentpage'}

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

    courseMaintenance_module.service("getkeywordCourseService",["CourseResource",function(CourseResource){
        this.loadkeywordCourse=function(keyword,selectedTerm,selectedDepartment,index,length,callback){
            CourseResource.getCourseByKey({
                keyword:keyword,
                termNo:selectedTerm,
                depNo:selectedDepartment,
                index:index,
                length:length
            },function(result){
                console.log("1111");
                if(callback){
                    callback(result);
                }
            },function(result){
                console.log("关键字课程获取失败",result);
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





    courseMaintenance_module.controller("courseMaintenanceController",["$scope","$uibModal","gettermsResource","departmentResource","courseinstance","CourseResource","loadCourseService","SweetAlert","getkeywordCourseService",function($scope,$uibModal,gettermsResource,departmentResource,courseinstance,CourseResource,loadCourseService,SweetAlert,getkeywordCourseService){

        $scope.selectedTerm="";
        $scope.selectedDepartment="";

        $scope.addSubject=function () {
            courseinstance.chooseTaskNo=[];
                var addsubjectInfo = $uibModal.open({
                    animation: true,
                    templateUrl: "quality/database/subject/addsubject.html",
                    controller: 'addcourseController',
                    bindToController: true,
                    size: "lg",
                    backdrop: false
                });

        }



        //根据关键字查询课程信息
        $scope.keywordSearchCourse = function (index,length) {
            console.log($scope.coursekeyWords);
            getkeywordCourseService.loadkeywordCourse($scope.coursekeyWords,$scope.selectedTerm,$scope.selectedDepartment,index,length,renderkeywordCourseData);

        }
        function renderkeywordCourseData(data){
           $scope.courseArrays=[];
            $scope.searchcourseArrays=data;
            console.log(data);

        }

        //修改课程详情
        $scope.modifySubject = function (index,taskNo) {
            var modifysubjectInfo = $uibModal.open({
                animation: true,
                templateUrl: "quality/database/subject/addsubject.html",
                controller: 'addcourseController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
            courseinstance.chooseTaskNo = taskNo;

        }

        //显示个性化课程的学生信息
        $scope.showSubject=function (coursename,taskNo) {
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

        }
        //获得所有学期
        gettermsResource.getTerms({
        },function(result){
            console.log("获取学期成功！");
            console.log(result);
            $scope.terms = result;
            $scope.selectedTerm =result[8].termNo;
            courseinstance.showterms = result;
        },function(result){
            console.log("获取学期失败");
        });

        //查询所有部门
        departmentResource.getDepartment({}, function (result) {
            console.log(result);
            $scope.departments = result;
            courseinstance.showdepartments = $scope.departments;
            $scope.selectedDepartment = $scope.departments[4].depNo;
        }, function () {
            console.log("获取部门信息失败");
        });

        console.log("这里");
        console.log($scope.selectedTerm);
        console.log($scope.selectedDepartment);
/*
        loadCourseService.loadCourse($scope.selectedTerm,$scope.selectedDepartment,0,9,renderCourseData);*/


        function renderCourseData(data){
            $scope.searchcourseArrays=[];
            $scope.courseArrays=data;
            console.log($scope.courseArrays);
        }

        //根据学期和部门查询课程
        $scope.loadData = function (index,length) {
                loadCourseService.loadCourse($scope.selectedTerm,$scope.selectedDepartment,index,length,renderCourseData);
        }


        //删除课程
        $scope.removeCourse = function (index,taskId) {
            SweetAlert.swal({
                title: '您确定要删除该课程吗?',
                text: '删除后将无法恢复，请谨慎操作！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText:"是的，我要删除！",
                cancelButtonText:"让我再考虑一下…",
                closeOnConfirm:true,
                closeOnCancel:false
            },  function(isConfirm){
                if (isConfirm){
                    CourseResource.deleteCourse({
                        taskId:taskId
                    },function(result){
                        loadCourseService.loadCourse($scope.selectedTerm,$scope.selectedDepartment,"0","9",renderCourseData);
                    },function(result){
                        console.log("课程删除失败",result);
                    });
                }else {
                    swal({title:"已取消",
                        text:"您取消了删除操作！",
                        type:"error"})
                }
            });




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



        //判断是否为修改课程信息
        if (courseinstance.chooseTaskNo!=undefined && courseinstance.chooseTaskNo!=null &&courseinstance.chooseTaskNo!=""){

            showModify();
        }
        function showModify() {
            console.log(courseinstance.chooseTaskNo);
            CourseResource.getDetailCourse({
             taskNo:courseinstance.chooseTaskNo
                //成功获得课程详情数据
            },function(result){
                console.log("获取课程详情");
                $scope.locations =[];
                console.log(result);
                    $scope.courseNo = result.courseNo,
                    $scope.courseName = result.courseName,
                    $scope.studentNumber = result.stuAmount,
                    $scope.department = result.depNo,
                    $scope.courseType = result.courseType,
                    $scope.courseAttr = result.courseAttr,
                    $scope.courseWeeks = result.courseWeek,
                    $scope.courseHours = result.courseCcount,
                    $scope.termNo = result.termNo,
                    $scope.courseTerm = result.termNo
                var oneteacher= new teacherObjStory(result.teacherName,result.teacherNo);//声明对象
                $scope.teachers.push(oneteacher);
                var oneclass= new classObjStory(result.className,result.classNo);//声明对象
                $scope.classes.push(oneclass);
                    for(var i=0;i<result.sectionses.length;i++){
                        var onelocation= new ObjStory(result.sectionses[i].scheSet,result.sectionses[i].scheAddr);//声明对象
                        $scope.locations.push(onelocation);
                    }
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
            },function(result){
                console.log("课程详情获取失败",result);
            });
        }


        //定义一个数组，用于装载节次信息
        $scope.locations =[];
        //定义一个数组，用于授课教师信息
        $scope.teachers = [];
        //定义一个数组，用于授课班级信息
        $scope.classes = [];

        //定义一个String，用于存放班级代码
        $scope.checkedclass="";

        //定义一个String，用于存放节次代码
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

            if (courseinstance.chooseTaskNo!=undefined && courseinstance.chooseTaskNo!=null &&courseinstance.chooseTaskNo!=""){
               console.log("执行更新");
                CourseResource.updateCourse({
                    taskNo:courseinstance.chooseTaskNo,
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
                    console.log("课程修改成功！",result);
                    SweetAlert.swal({
                        title: '课程修改成功',
                        type: 'success',
                        showCancelButton: false,
                        confirmButtonColor: '#DD6B55',
                        confirmButtonText: 'ok',
                        closeOnConfirm: true
                    },function () {
                        $uibModalInstance.dismiss('cancel');
                    });

                },function(result){
                    console.log("课程修改失败",result);
                    SweetAlert.swal({
                        title: '课程修改失败',
                        text:'请确认所有数据填写完毕！',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#DD6B55',
                        confirmButtonText: 'ok',
                        closeOnConfirm: true
                    },function () {
                        $uibModalInstance.dismiss('cancel');
                    });
                });

            }else {
                console.log("执行新增");
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
                    SweetAlert.swal({
                        title: '课程保存失败',
                        text:'请确认所有数据填写完毕！',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#DD6B55',
                        confirmButtonText: 'ok',
                        closeOnConfirm: true
                    },function () {
                        $uibModalInstance.dismiss('cancel');
                    });
                });
            }


        }

    }]);



    //显示任选课学生信息
    courseMaintenance_module.controller("showcourseController",["$scope","$uibModalInstance","SweetAlert","loadStudentService","courseinstance","subStudentResource","CourseResource",function($scope,$uibModalInstance,SweetAlert,loadStudentService,courseinstance,subStudentResource,CourseResource){

       /* console.log($scope.$select.search);*/
        $scope.serachergxhstudentArrays=[];
        //弹出层关闭按钮
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        //查询学生信息
        $scope.refreshStudents=function(keyword){
            console.log("keyword",keyword);
            if (keyword!=null||keyword!=""){
                subStudentResource.getkeywordStudents({
                    keyword:keyword,
                    index:0,
                    length:20
                },function(result){
                    return $scope.people=result.content;
                },function(result){
                    console.log("个性化课程中的查询学生信息获取失败",result);
                });
            }
        }

        //删除学生信息
        $scope.removeSudent = function (index,stuNo) {
            SweetAlert.swal({
                title: '您确定要删除该学生信息吗?',
                text: '删除后将无法恢复，请谨慎操作！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText:"是的，我要删除！",
                cancelButtonText:"让我再考虑一下…",
                closeOnConfirm:true,
                closeOnCancel:false
            },  function(isConfirm){
                if (isConfirm){
                    subStudentResource.deleteStudent({
                        stuNo:stuNo,
                        taskNo:courseinstance.gxhtaskNo
                    },function(result){
                        $scope.currentgxhstudentNumber =  $scope.currentgxhstudentNumber-1;
                        subStudentResource.getallgxhStudent({
                            taskNo: $scope.currentgxhtaskNo,
                            index:0,
                            length:4
                        },function(result){
                            for(var i =0;i<result.content.length;i++){
                                if (result.content[i].stuStatus=="1"){
                                    result.content[i].stuStatus = "正常";
                                }else {
                                    result.content[i].stuStatus = "失效";
                                }
                            }
                            $scope.gxhstudentArrays=result;
                        },function(result){
                            console.log("个性化课程中的学生信息获取失败",result);
                        });
                    },function(result){
                        console.log("课程删除失败",result);
                    });
                }else {
                    swal({title:"已取消",
                        text:"您取消了删除操作！",
                        type:"error"})
                }
            });
        }

        //显示当前个性化课程页面的课程信息
        $scope.currentgxhcoursename = courseinstance.gxhcoursename;
        $scope.currentgxhtaskNo  = courseinstance.gxhtaskNo ;
        $scope.currentgxhTermNo = courseinstance.gxhTerm;
        $scope.currentgxhTermArrays = courseinstance.showterms;
        for (var i =0;i<$scope.currentgxhTermArrays.length;i++){
            if($scope.currentgxhTermNo==$scope.currentgxhTermArrays[i].termNo){
                $scope.currentgxhTermName = $scope.currentgxhTermArrays[i].termName;
            }
        }

        //显示个性化课程学生信息
        subStudentResource.getallgxhStudent({
            taskNo: $scope.currentgxhtaskNo,
            index:0,
            length:4
        },function(result){
            for(var i =0;i<result.content.length;i++){
                if (result.content[i].stuStatus=="1"){
                    result.content[i].stuStatus = "正常";
                }else {
                    result.content[i].stuStatus = "失效";
                }
            }
            console.log(result);
            $scope.gxhstudentArrays=result;
            $scope.currentgxhstudentNumber = result.totalElements;
        },function(result){
            console.log("个性化课程中的学生信息获取失败",result);
        });

        //返回个性化课程中的学生信息
        function renderstudentData(data){
            console.log(data);
            if (data.content.length==0){
                $scope.detailStudentinfo="";
                SweetAlert.swal({
                    title: '本课程无该学生信息',
                    text: '请重新输入关键字查询',
                    type: 'warning',
                    showCancelButton: false,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: 'ok',
                    closeOnConfirm: true
                });
            }else {
                $scope.serachergxhstudentArrays = data;
                console.log( $scope.serachergxhstudentArrays);
            }

        }

        //搜索班级学生信息
        $scope.searchgxhStu = function (index,length) {
            console.log($scope.detailStudentinfo);
            if($scope.detailStudentinfo==undefined ||$scope.detailStudentinfo == ""|| $scope.detailStudentinfo == null){
                subStudentResource.getallgxhStudent({
                    taskNo: $scope.currentgxhtaskNo,
                    index:index,
                    length:length
                },function(result){
                    console.log(result);
                    for(var i =0;i<result.content.length;i++){
                        if (result.content[i].stuStatus=="1"){
                            result.content[i].stuStatus = "正常";
                        }else {
                            result.content[i].stuStatus = "禁用";
                        }
                    }
                    $scope.serachergxhstudentArrays=[];
                   /* $scope.gxhstudentArrays=[];*/
                    console.log(result);
                    $scope.gxhstudentArrays=result;
                },function(result){
                    console.log("个性化课程中的学生信息获取失败",result);
                });
            }else {
                loadStudentService.getStudent($scope.detailStudentinfo, $scope.currentgxhtaskNo,index,length,renderstudentData);
            }
        }


        //增加学生信息
        $scope.addStudent = function () {
            console.log($scope.selectedPeople);
            var stuNo = $scope.selectedPeople.stuNo;
            console.log(stuNo);
            CourseResource.searchLogicStudent({
                stuNo:stuNo,
                taskNo: $scope.currentgxhtaskNo,
                termNo:$scope.currentgxhTermNo
            },function(result){
                console.log(result);
                if (result.length>0){
                    SweetAlert.swal({
                        title: '新增学生信息失败',
                        text:'该课程中已有该学生信息，不可重复添加',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#DD6B55',
                        confirmButtonText: 'ok',
                        closeOnConfirm: true
                    });
                }else {
                    CourseResource.saveLogicCourse({
                        stuNo:stuNo,
                        taskNo: $scope.currentgxhtaskNo,
                        termNo:$scope.currentgxhTermNo
                    },function(result){
                        $scope.currentgxhstudentNumber =  $scope.currentgxhstudentNumber+1;
                        SweetAlert.swal({
                            title: '新增学生信息成功',
                            type: 'success',
                            showCancelButton: false,
                            confirmButtonColor: '#DD6B55',
                            confirmButtonText: 'ok',
                            closeOnConfirm: true
                        });
                    },function(result){
                        console.log("课程学生信息保存失败",result);
                    });
                }

            },function(result){
                console.log(result);

            });
        }
    }]);





})();