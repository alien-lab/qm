/**
 * Created by Master QB on 2017/3/23.
 */
(function(){
    'use strict';
    var farm_module=angular.module("qm.menu",['ui.router']);//ui.router模块作为主应用模块的依赖模块
    farm_module.config(["$stateProvider",function($stateProvider){//配置$stateProvider，用来定义路由规则
        $stateProvider.state('qm.menu', {
            url: '/menu',
            title: '菜单管理',
            templateUrl: "quality/syssetting/menu/menu.html",
            controller:"TablexEditableController"
        });
    }]);

    farm_module.factory('editablePromiseCollection', ['$q', function($q) {

        function promiseCollection() {
            return {
                promises: [],
                hasFalse: false,
                hasString: false,
                when: function(result, noPromise) {
                    if (result === false) {
                        this.hasFalse = true;
                    } else if (!noPromise && angular.isObject(result)) {
                        this.promises.push($q.when(result));
                    } else if (angular.isString(result)){
                        this.hasString = true;
                    } else { //result === true || result === undefined || result === null
                        return;
                    }
                },
                //callbacks: onTrue, onFalse, onString
                then: function(callbacks) {
                    callbacks = callbacks || {};
                    var onTrue = callbacks.onTrue || angular.noop;
                    var onFalse = callbacks.onFalse || angular.noop;
                    var onString = callbacks.onString || angular.noop;
                    var onWait = callbacks.onWait || angular.noop;

                    var self = this;

                    if (this.promises.length) {
                        onWait(true);
                        $q.all(this.promises).then(
                            //all resolved
                            function(results) {
                                onWait(false);
                                //check all results via same `when` method (without checking promises)
                                angular.forEach(results, function(result) {
                                    self.when(result, true);
                                });
                                applyCallback();
                            },
                            //some rejected
                            function(error) {
                                onWait(false);
                                onString();
                            }
                        );
                    } else {
                        applyCallback();
                    }

                    function applyCallback() {
                        if (!self.hasString && !self.hasFalse) {
                            onTrue();
                        } else if (!self.hasString && self.hasFalse) {
                            onFalse();
                        } else {
                            onString();
                        }
                    }

                }
            };
        }

        return promiseCollection;

    }]);
    farm_module.value('editableOptions', {
        /**
         * Theme. Possible values `bs3`, `bs2`, `default`.
         *
         * @var {string} theme
         * @memberOf editable-options
         */
        theme: 'default',
        /**
         * Icon Set. Possible values `font-awesome`, `default`.
         *
         * @var {string} icon set
         * @memberOf editable-options
         */
        icon_set: 'default',
        /**
         * Whether to show buttons for single editalbe element.
         * Possible values `right` (default), `no`.
         *
         * @var {string} buttons
         * @memberOf editable-options
         */
        buttons: 'right',
        /**
         * Default value for `blur` attribute of single editable element.
         * Can be `cancel|submit|ignore`.
         *
         * @var {string} blurElem
         * @memberOf editable-options
         */
        blurElem: 'cancel',
        /**
         * Default value for `blur` attribute of editable form.
         * Can be `cancel|submit|ignore`.
         *
         * @var {string} blurForm
         * @memberOf editable-options
         */
        blurForm: 'ignore',
        /**
         * How input elements get activated. Possible values: `focus|select|none`.
         *
         * @var {string} activate
         * @memberOf editable-options
         */
        activate: 'focus',
        /**
         * Whether to disable x-editable. Can be overloaded on each element.
         *
         * @var {boolean} isDisabled
         * @memberOf editable-options
         */
        isDisabled: false,

        /**
         * Event, on which the edit mode gets activated.
         * Can be any event.
         *
         * @var {string} activationEvent
         * @memberOf editable-options
         */
        activationEvent: 'click'

    });

    farm_module.controller('TablexEditableController', TablexEditableController);
    TablexEditableController.$inject = ['$filter', '$http', 'editableOptions','$q'];
    function TablexEditableController($filter, $http, editableOptions,  $q) {
        var vm = this;

        activate();

        ////////////////

        function activate() {

            // editable row
            // -----------------------------------
            vm.users = [
                {id: 1, name: 'awesome user1', status: 2, group: 4, groupName: 'admin'},
                {id: 2, name: 'awesome user2', status: undefined, group: 3, groupName: 'vip'},
                {id: 3, name: 'awesome user3', status: 2, group: null}
            ];

            vm.statuses = [
                {value: 1, text: 'status1'},
                {value: 2, text: 'status2'},
                {value: 3, text: 'status3'},
                {value: 4, text: 'status4'}
            ];

            vm.groups = [];
            vm.loadGroups = function() {
                return vm.groups.length ? null : $http.get('server/xeditable-groups.json').success(function(data) {
                    vm.groups = data;
                });
            };

            vm.showGroup = function(user) {
                if(user.group && vm.groups.length) {
                    var selected = $filter('filter')(vm.groups, {id: user.group});
                    return selected.length ? selected[0].text : 'Not set';
                } else {
                    return user.groupName || 'Not set';
                }
            };

            vm.showStatus = function(user) {
                var selected = [];
                if(user.status) {
                    selected = $filter('filter')(vm.statuses, {value: user.status});
                }
                return selected.length ? selected[0].text : 'Not set';
            };

            vm.checkName = function(data, id) {
                if (id === 2 && data !== 'awesome') {
                    return 'Username 2 should be `awesome`';
                }
            };

            vm.saveUser = function(data, id) {
                //vm.user not updated yet
                angular.extend(data, {id: id});
                console.log('Saving user: ' + id);
                // return $http.post('/saveUser', data);
            };

            // remove user
            vm.removeUser = function(index) {
                vm.users.splice(index, 1);
            };

            // add user
            vm.addUser = function() {
                vm.inserted = {
                    id: vm.users.length+1,
                    name: '',
                    status: null,
                    group: null,
                    isNew: true
                };
                vm.users.push(vm.inserted);
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
    }

})();
