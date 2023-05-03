'use strict';
angular.module('myApp.scheduler', ['ngRoute'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/scheduler', {
    templateUrl: 'scheduler/scheduler.html',
    controller: 'SchedulerCtrl'
  });
  $routeProvider.when('/scheduler-changes/:id',{
  templateUrl:'scheduler/schedulerChanges.html',
  controller:'SchedulerChangeCtrl'
  });
 }])

.controller('SchedulerCtrl', ['$scope','$http','$controller','$rootScope','$uibModal', function($scope,$http,$controller,$rootScope,$uibModal) {
   $scope.page={totalElements:0,currentPage:1,pageSize:10};
   $scope.tableVal=[ ];
   $rootScope.showCalculator = function() {
               $rootScope.modalInstance = $uibModal.open({
                   controller: 'SchedulerCtrl',
                   ariaLabelledBy: 'modal-title',
                   ariaDescribedBy: 'modal-body',
                   templateUrl: '/view1/calculator.html',
                   size: 'lg'
               });
           }

 $scope.loadScheduler=function(){
   $http.get('api/scheduler?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
                      });

}
$scope.loadScheduler();
}])
.controller('SchedulerChangeCtrl',['$scope','$http','$routeParams',function($scope,$http,$routeParams){
$scope.page={totalElements:0,currentPage:1,pageSize:10};
$scope.tableVal=[ ];
 $scope.loadSchedulerChanges=function(){
   $http.get('api/scheduler/'+$routeParams.id+'?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
                      });

}
$scope.loadSchedulerChanges();

}])
