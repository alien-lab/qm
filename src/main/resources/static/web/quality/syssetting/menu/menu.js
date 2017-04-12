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
        angular.module("qm.menu").factory("menuResource",["$resource",function($resource){
            var service = $resource('/qm-api/menus', {}, {
                getMenu: { method: 'GET', isArray:true},
                getMenuDto:{
                    method:'GET',
                    isArray:true,
                    url:'/qm-api/menus/dto'
                },
                savemenu: { method: 'POST'},
                switchMenu:{method:'PUT',url:'/qm-api/menus/switch'},
                deletemenu: { method: 'DELETE'},
                getrolerMenuDto:{
                    method:'GET',
                    isArray:true,
                    url:'/qm-api/menus/rolerdto'
                },
                setrolerMenuDto:{
                    method: 'POST',
                    url:'/qm-api/menus/setroleMenudto'

                }
            });
            return service;
        }]);
    })();



    menu_module.controller("MenuController",['$scope','$filter', '$http', '$q',"$uibModal",'SweetAlert',"menuResource",function($scope,$filter, $http,$q,$uibModal,SweetAlert,menuResource ){
        var vm = this;
        activate();
        function activate() {

            $scope.menuTypes = [{ id: 1, name: '模块' }, { id: 2, name: '子功能' }];
            $scope.pids = [ { id: 0, name: '父类编码0' },{ id: 79, name: '父类编码79' }, { id: 10, name: '父类编码10' }, { id: 11, name: '父类编码11' }, { id: 12, name: '父类编码12' }, { id: 13, name: '父类编码13' }
                , { id: 14, name: '父类编码14' }, { id: 15, name: '父类编码15' }];
           //获得菜单列表
            getmenu();
            //getMenuResource.getMenuDto({},function(result){console.log(result)});
            function getmenu() {
                menuResource.getMenu({

                },function(result){
                    console.log(result);
                    $scope.menus = result;
                },function(result){

                    console.log("菜单拉取失败");
                });

            }

            //保存菜单
            $scope.saveMenu = function(data, id) {
                if(id == undefined){
                    id = -1;
                }
                console.log("data+id！");
                console.log(data);
                console.log(id);
                menuResource.savemenu({
                    id:id,
                    name:data.menu_name,
                    type:data.menu_type,
                    pid:data.menu_pid,
                    content:data.menu_content,
                    attr:data.menu_attr
                },function(result){
                    console.log("保存menu成功！");
                    console.log(result);
                    getmenu();

                },function(result){
                    console.log("保存menu失败");
                });

            };


            // 增加菜单
            $scope.addMenu = function () {
               var inserted = {
                    id: $scope.menus.length+1,
                   menu_name: '',
                   menu_type: null,
                   menu_pid: null,
                   menu_content: null,
                   menu_attr:null
                };
                $scope.menus.push(vm.inserted);
            };

            //删除菜单
            $scope.removeMenu = function(index, id) {
                console.log(index);
                SweetAlert.swal({
                    title: '您确定要删除该菜单吗?',
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
                        menuResource.deletemenu({
                            id:id
                        },function(result){
                            console.log("删除menu成功！");
                            console.log(result);
                            $scope.menus.splice(index, 1);

                        },function(result){
                            console.log("删除menu失败");
                        });
                    }else {
                        swal({title:"已取消",
                            text:"您取消了删除操作！",
                            type:"error"})
                    }

                });
            };

            //菜单状态开关
            $scope.openSwitch = function (id) {
                console.log(id);
                menuResource.switchMenu({
                    id:id
                },function(result){
                    console.log("switch开关修改成功！");
                    console.log(result);
                },function(result){
                    console.log("switch开关修改失败");
                });

            }


        }



    }]);


})();
