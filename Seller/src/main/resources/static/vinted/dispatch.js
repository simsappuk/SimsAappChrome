'use strict';

var controller1 = angular.module('myApp.vinted', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/vinted/:id', {
    templateUrl: 'vinted/dispatch.html',
    controller: 'vintedCtrl'
  });

 .controller('vintedCtrl', ['$scope','$http','$rootScope','$routeParams','$uibModal', function($scope,$http,$rootScope,$routeParams,$uibModal) {
  $scope.params=$routeParams;
    $scope.page={totalElements:0,currentPage:1,pageSize:200,loading:false};
 $rootScope.showCalculator = function() {
                                           $rootScope.modalInstance = $uibModal.open({
                                               controller: 'vintedCtrl',
                                               ariaLabelledBy: 'modal-title',
                                               ariaDescribedBy: 'modal-body',
                                               templateUrl: '/view1/calculator.html',
                                               size: 'lg'
                                           });
                                       }
  $scope.AllDispatchListing=function(){

$http.get('/api/Vinted/'+$scope.params.id+'/list/vinted?page='+($scope.page.currentPage)+"&pageSize="+$scope.page.pageSize).
  $scope.tableVal=response.data.results;
 $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
  }

  $scope.AllDispatchListing();
       }])
