'use strict';

var controller1 = angular.module('myApp.sales', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/sales', {
    templateUrl: 'sales/sales.html',
    controller: 'StockSalesCtrl'
  });
}])
  .controller('StockSalesCtrl', ['$scope','$http', function($scope,$http) {
    $scope.page={totalElements:0,currentPage:1,pageSize:10};
    $scope.loadStockSales=function(){
   $http.get('api/sales?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             });
  }
  $scope.loadStockSales();
  }]);