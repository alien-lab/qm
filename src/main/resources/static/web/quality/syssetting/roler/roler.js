/**
 * Created by Master QB on 2017/3/23.
 */
(function(){
    'use strict';
    var roler_module=angular.module("qm.roler",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    roler_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.roler', {
            url: '/roler',
            title: '角色管理',
            templateUrl: "quality/syssetting/roler/roler.html",
            controller:"RolerController"
        });
    }]);

    roler_module.factory('SweetAlert', [ '$rootScope', function ( $rootScope ) {

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


    /*获取角色*/
    (function() {
        'use strict';
        angular.module("qm.roler").factory("getRolerResource",["$resource",function($resource){
            var service = $resource('/roler-api/getRolers', {}, {
                'getRoler': { method: 'GET', isArray:true}
            });
            return service;
        }]);

    })();



    roler_module.controller("RolerController",['$scope','$filter', '$http','$q',"$uibModal",'SweetAlert','getRolerResource',function($scope,$filter, $http,$q,$uibModal,SweetAlert,getRolerResource){

        var vm = this;

        activate();

        ////////////////

        function activate() {

            getRolerResource.getRoler({

            },function(result){
                console.log(result);
                $scope.rolers = result;
            },function(result){

                console.log("角色拉取失败");
            });

            //保存用户角色
            vm.saveRoler = function(data, id) {
                //vm.user not updated yet
                angular.extend(data, {id: id});
                console.log('Saving user: ' + id);
                // return $http.post('/saveUser', data);
            };

            // 增加角色
            $scope.addRoler = function () {
                var inserted = {
                    name: '',
                    status: null,
                    group: null,
                    isNew: true
                };
                $scope.rolers.push(vm.inserted);
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

            // editable table
            // -----------------------------------

            // filter users to show
            vm.filterUser = function(user) {
                return user.isDeleted !== true;
            };

            // mark user as deleted
            vm.deleteUser = function(id) {
                var filtered = $filter('filter')(vm.users, {id: id});
                if (filtered.length) {
                    filtered[0].isDeleted = true;
                }
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


        //用户角色设置
        $scope.roler_menusetting = showRoler_menuSetting;
        function showRoler_menuSetting(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/syssetting/roler/roler_menu.html",
                controller: 'roler_menuController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }

        //删除角色
       $scope.removeRoler = function(index) {
            SweetAlert.swal({
                title: '确定要删除该角色吗?',
                text: '角色删除不可恢复！',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText: '确认删除',
                closeOnConfirm: true
            },  function(){
                $scope.rolers.splice(index, 1);
            });
        };

    }]);



    roler_module.controller("roler_menuController",["$scope","$uibModalInstance","$rootScope",function($scope,$uibModalInstance,$rootScope){
        $scope.ModTitle = "角色菜单设置";
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }

    }]);

})();
