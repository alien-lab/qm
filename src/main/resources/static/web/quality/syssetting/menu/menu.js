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

    menu_module.controller("MenuController",['$scope','$filter', '$http', '$q',"$uibModal",'SweetAlert',function($scope,$filter, $http,$q,$uibModal,SweetAlert){


        var vm = this;

        activate();

        ////////////////

        function activate() {
            // editable row
            // -----------------------------------
            $scope.users = [
                {id: 1, name: 'awesome user1', status: 2, group: 4, groupName: 'admin'},
                {id: 2, name: 'awesome user2', status: undefined, group: 3, groupName: 'vip'},
                {id: 3, name: 'awesome user3', status: 2, group: null}
            ];

            //保存菜单
            $scope.saveMenu = function(data, id) {
                //vm.user not updated yet
                angular.extend(data, {id: id});
                console.log('Saving user: ' + id);
                // return $http.post('/saveUser', data);
            };


            // 增加菜单
            $scope.addMenu = function () {
               var inserted = {
                    id: $scope.users.length+1,
                    name: '',
                    status: null,
                    group: null,
                    isNew: true
                };
                $scope.users.push(vm.inserted);
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
                $scope.users.splice(index, 1);
            });
        };

    }]);


})();
