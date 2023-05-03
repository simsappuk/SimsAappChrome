'use strict';

angular.module('myApp.usergroup', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/usersgroup', {
    templateUrl: 'usergroup/view1.html',
    controller: 'UserGroupCtrl'
  });
}])

.controller('UserGroupCtrl', [function() {


}]);