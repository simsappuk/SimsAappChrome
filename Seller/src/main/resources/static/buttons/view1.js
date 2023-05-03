'use strict';

angular.module('myApp.user', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/buttons', {
    templateUrl: 'buttons/view1.html',
    controller: 'ButtonsCtrl'
  });
}])

.controller('UserCtrl', [function() {


}]);