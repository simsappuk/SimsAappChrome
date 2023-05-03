'use strict';

angular.module('myApp.user', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/users/:id', {
    templateUrl: 'user/list.html',
    controller: 'UserCtrl'
  });
  $routeProvider.when('/users-new/:id', {
      templateUrl: 'user/form.html',
      controller: 'UserNewCtrl'
    });
$routeProvider.when('/users/:id/:accountId', {
      templateUrl: 'user/userDetailed.html',
      controller: 'UserEditCtrl'
    });

}])

.controller('UserCurdCtrl',['$scope','$http','$routeParams','$rootScope','$uibModal',function($scope,$http,$routeParams,$rootScope,$uibModal){
$rootScope.showCalculator = function() {
                                          $rootScope.modalInstance = $uibModal.open({
                                              controller: 'UserCurdCtrl',
                                              ariaLabelledBy: 'modal-title',
                                              ariaDescribedBy: 'modal-body',
                                              templateUrl: '/view1/calculator.html',
                                              size: 'lg'
                                          });
                                      }
$scope.page={totalElements:0,currentPage:1,pageSize:10};
$rootScope.params=$routeParams;
$scope.loadUser=function(){
   $http.get('api/users?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&accountId="+$routeParams.id).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             });
}

$scope.save=function(accountId,obj){
 $scope.params=$routeParams;
    $http.post('api/users/'+accountId,obj)
               .then(function successCallback(response){
               window.location.href="#!/users/"+$scope.params.id;
                   console.log("Successfully POST-ed data");
               }, function errorCallback(response){
               window.location.href="#!/users/"+$scope.params.id;
                   console.log("POST-ing of data failed");
               });
    }

    $scope.editLink=function(accountId,obj){
                   window.location.href="#!/users/"+obj+'/'+accountId;

    }
$scope.update=function(accountId,obj){
 $scope.params=$routeParams;
    $http.post('api/users/update/'+accountId,obj)
               .then(function successCallback(response){
               window.location.href="#!/users/"+$scope.params.accountId;
                   console.log("Successfully POST-ed data");
               }, function errorCallback(response){
               window.location.href="#!/users/"+$scope.params.accountId;
                   console.log("POST-ing of data failed");
               });
    }

    $scope.editLink=function(accountId,obj){
                   window.location.href="#!/users/"+obj+'/'+accountId;

    }
    $scope.deleteUser=function(accountId,userId){
     $http.post('api/users/delete/'+userId+'/'+accountId)
              .then(function successCallback(response){
               window.location.href="#!/users/"+$scope.params.accountId;
                   console.log("Successfully POST-ed data");
               }, function errorCallback(response){
               window.location.href="#!/users/"+$scope.params.accountId;
                   console.log("POST-ing of data failed");
               })
    }

    $scope.loadUserDetails=function(accountId,obj){
       $http.get('api/users/'+(obj)+'/'+accountId).
             then(function(response) {
                 $scope.user = response.data.results;
             });
     }

}])

.controller('UserCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {

    angular.extend(this, $controller('UserCurdCtrl', {$scope: $scope}));
$scope.loadUser();
}])

.controller('UserNewCtrl', ['$http','$scope','$routeParams','$controller',function($http,$scope,$routeParams,$controller) {
$scope.users={};
 $scope.params=$routeParams;
    angular.extend(this, $controller('UserCurdCtrl', {$scope: $scope}));
}])

.controller('UserEditCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
 angular.extend(this, $controller('UserCurdCtrl', {$scope: $scope}));
 $scope.loadUserDetails($routeParams.accountId,$routeParams.id);
 }]);

