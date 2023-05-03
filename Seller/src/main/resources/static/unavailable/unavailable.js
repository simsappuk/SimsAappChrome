'use strict';

var controller1 = angular.module('myApp.unavailable', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/unavailable', {
    templateUrl: 'unavailable/unavailable.html',
    controller: 'StockUnavailableCtrl'
  });
}])
  .controller('StockUnavailableCtrl', ['$scope','$http', function($scope,$http) {
  $scope.page={totalElements:0,currentPage:1,pageSize:10};
    $scope.loadStockUnavailable=function(){
       $http.get('api/unavailable?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             });
  }
    $scope.loadStockUnavailable();
  }]);