/**
 * Created by Master QB on 2017/3/23.
 */
(function(){
    'use strict';
    var roler_module=angular.module("qm.roler",['ui.router','qm.menu']);//ui.router模块作为主应用模块的依赖模块
    roler_module.factory("rolerinstance",function(){return {}});
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



    roler_module.factory("rolerResource",["$resource",function($resource){
            var service = $resource('../qm-api/rolers', {}, {
                getroler: { method: 'GET', isArray:true},
                saveroler: { method: 'POST'},
                deleteroler: { method: 'DELETE'}
            });
            return service;
        }]);





    roler_module.controller("RolerController",['$scope','$filter', '$http','$q',"$uibModal",'SweetAlert','rolerResource','menuResource','rolerinstance',function($scope,$filter, $http,$q,$uibModal,SweetAlert,rolerResource,menuResource,rolerinstance){

        var vm = this;
        $scope.menus=menuResource.getMenuDto({},function(result){console.log(result)});
        activate();

        ////////////////

        function activate() {
            getrolers();

            function getrolers() {
                rolerResource.getroler({

                },function(result){
                    console.log(result);
                    $scope.rolers = result;
                },function(result){

                    console.log("角色拉取失败");
                });
            }



            //保存用户角色
            $scope.saveRoler = function(data, id) {
                //vm.user not updated yet
                if(id == undefined){
                    id = -1;
                }
                console.log("data+id！");
                console.log(data);
                console.log(id);
                rolerResource.saveroler({
                    id:id,
                    name:data.roleName
                },function(result){
                    console.log("保存roler成功！");
                    getrolers();
                    console.log(result);

                },function(result){
                    console.log("保存roler失败");
                });


                angular.extend(data, {id: id});
                console.log('Saving user: ' + id);
                // return $http.post('/saveUser', data);
            };

            // 增加角色
            $scope.addRoler = function () {
                var inserted = {
                    roleName: ''
                };
                $scope.rolers.push(vm.inserted);
            };

            //删除角色
            $scope.removeRoler = function(index,id) {
                SweetAlert.swal({
                    title: '您确定要删除该角色吗?',
                    text: '删除后将无法恢复，请谨慎操作！',
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText:"是的，我要删除！",
                    cancelButtonText:"让我再考虑一下…",
                    closeOnConfirm: true,
                    closeOnCancel:false
                },  function(isConfirm){
                    if(isConfirm){
                        rolerResource.deleteroler({
                            id:id
                        },function(result){
                            console.log("删除roler成功！");
                            console.log(result);
                            $scope.rolers.splice(index, 1);
                        },function(result){
                            console.log("删除roler失败");
                        });
                    }else {
                        swal({title:"已取消",
                            text:"您取消了删除操作！",
                            type:"error"})
                    }
                });
            };


        }


        //用户角色设置
        $scope.rolerMenusetting = showRoler_menuSetting;
        function showRoler_menuSetting(id,name){
            rolerinstance.rolerid = id;
            rolerinstance.rolername = name;
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: "quality/syssetting/roler/roler_menu.html",
                controller: 'roler_menuController',
                bindToController: true,
                size: "lg",
                backdrop: false
            });
        }



    }]);

    /*获取所有菜单*/
    (function() {
        'use strict';
        angular.module("qm.roler").factory("getrolerMenuResource",["$resource",function($resource){
            var service = $resource('../menu-api/getMenus', {}, {
                'getrolerMenu': { method: 'GET', isArray:true}
            });
            return service;
        }]);

    })();


    //角色菜单设置的controller
    roler_module.controller("roler_menuController",["$scope","$uibModalInstance","$rootScope",'menuResource','rolerinstance',function($scope,$uibModalInstance,$rootScope,menuResource,rolerinstance){
        $scope.rolename =  rolerinstance.rolername;
        $scope.ModTitle = $scope.rolename+"--角色菜单设置" ;
        $scope.cancel = function cancel(flag){
            $uibModalInstance.dismiss('cancel');
        }
        $scope.roleid = rolerinstance.rolerid;
        //获取该角色下的权限菜单
        menuResource.getrolerMenuDto({
            id:$scope.roleid
        },function(result){
            console.log(result)
            $scope.checkedMenus = result;
        },function(result){
            console.log("菜单拉取失败");
        });

        //点击事件增加或删除该角色菜单
        $scope.oksubMenu = function (menuid) {
            menuResource.setrolerMenuDto({
                menuid:menuid,
                roleid:$scope.roleid
            },function(result){
                console.log(result)

            },function(result){
                console.log("对角色菜单操作失败");
            });

        }






    }]);

})();
