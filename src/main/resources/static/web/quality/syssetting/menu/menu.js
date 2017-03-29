/**
 * Created by Master QB on 2017/3/23.
 */
(function(){
    'use strict';
    var menu_module=angular.module("qm.menu",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    menu_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.menu', {
            url: '/menu',
            title: '菜单管理',
            templateUrl: "quality/syssetting/menu/menu.html",
            controller:"MenuController"
        });
    }]);

    menu_module.factory('SweetAlert', [ '$rootScope', function ( $rootScope ) {

            var swal = window.swal;

            //public methods
            var self = {

                swal: function ( arg1, arg2, arg3 ) {
                    $rootScope.$evalAsync(function(){
                        if( typeof(arg2) === 'function' ) {
                            swal( arg1, function(isConfirm){
                                $rootScope.$evalAsync( function(){
                                    arg2(isConfirm);
                                });
                            }, arg3 );
                        } else {
                            swal( arg1, arg2, arg3 );
                        }
                    });
                },
                success: function(title, message) {
                    $rootScope.$evalAsync(function(){
                        swal( title, message, 'success' );
                    });
                },
                error: function(title, message) {
                    $rootScope.$evalAsync(function(){
                        swal( title, message, 'error' );
                    });
                },
                warning: function(title, message) {
                    $rootScope.$evalAsync(function(){
                        swal( title, message, 'warning' );
                    });
                },
                info: function(title, message) {
                    $rootScope.$evalAsync(function(){
                        swal( title, message, 'info' );
                    });
                }
            };

            return self;
        }]);

    /*获取menu*/
    (function() {
        'use strict';
        angular.module("qm.menu").factory("getMenuResource",["$resource",function($resource){
            var service = $resource('/menu-api/getMenus', {}, {
                'getMenu': { method: 'GET', isArray:true}
            });
            return service;
        }]);

    })();

    /*保存或增加menu*/
    (function() {
        'use strict';
        angular.module("qm.menu").factory("saveMenuResource",["$resource",function($resource){
            var service = $resource('/menu-api/saveMenu', {}, {
                'savemenu': { method: 'POST'}
            });
            return service;
        }]);

    })();
    menu_module.controller("MenuController",['$scope','$filter', '$http', '$q',"$uibModal",'SweetAlert',"getMenuResource",'saveMenuResource',function($scope,$filter, $http,$q,$uibModal,SweetAlert,getMenuResource,saveMenuResource){
        var vm = this;
        activate();
        function activate() {
            getMenuResource.getMenu({

            },function(result){
                console.log(result);
               $scope.menus = result;
            },function(result){

                console.log("菜单拉取失败");
            });

            //保存菜单
            $scope.saveMenu = function(data, id) {
                console.log("data+id！");
                console.log(data);
                console.log(id);
                saveMenuResource.savemenu({
                    id:id,
                    name:data.menu_name,
                    type:data.menu_type,
                    pid:data.menu_pid,
                    content:data.menu_content,
                    attr:data.menu_attr
                },function(result){
                    console.log("保存成功！");
                    console.log(result);

                },function(result){
                    console.log("保存失败");
                });


                //vm.user not updated yet
                angular.extend(data, {id: id});
                console.log('Saving user: ' + id);
                // return $http.post('/saveUser', data);
            };


            // 增加菜单
            $scope.addMenu = function () {
               var inserted = {
                    id: $scope.menus.length+1,
                    name: '',
                    status: null,
                    group: null,
                    isNew: true
                };
                $scope.menus.push(vm.inserted);
            };

            // editable column
            // -----------------------------------


            vm.saveColumn = function(column) {
                var results = [];
                angular.forEach(vm.users, function(/*user*/) {
                    // results.push($http.post('/saveColumn', {column: column, value: user[column], id: user.id}));
                    console.log('Saving column: ' + column);
                });
                return $q.all(results);
            };


            // cancel all changes
            vm.cancel = function() {
                for (var i = vm.users.length; i--;) {
                    var user = vm.users[i];
                    // undelete
                    if (user.isDeleted) {
                        delete user.isDeleted;
                    }
                    // remove new
                    if (user.isNew) {
                        vm.users.splice(i, 1);
                    }
                }
            };

            // save edits
            vm.saveTable = function() {
                var results = [];
                for (var i = vm.users.length; i--;) {
                    var user = vm.users[i];
                    // actually delete user
                    if (user.isDeleted) {
                        vm.users.splice(i, 1);
                    }
                    // mark as not new
                    if (user.isNew) {
                        user.isNew = false;
                    }

                    // send on server
                    // results.push($http.post('/saveUser', user));
                    console.log('Saving Table...');
                }

                return $q.all(results);
            };

        }

        $scope.removeMenu = function(index) {
            SweetAlert.swal({
                title: '确定要删除该菜单吗?',
                text: '菜单删除不可恢复！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText: '确认删除',
                closeOnConfirm: true
            },  function(){
                $scope.menus.splice(index, 1);
            });
        };

    }]);


})();
