'use strict';

angular.module('myApp.accounts', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/accounts', {
    templateUrl: 'accounts/list.html',
    controller: 'AccountsCtrl'
  });
  $routeProvider.when('/accounts-new', {
      templateUrl: 'accounts/add.html',
      controller: 'AccountsNewCtrl'
    });
  $routeProvider.when('/accounts-edit/:id', {
          templateUrl: 'accounts/add.html',
          controller: 'AccountsEditCtrl'
        });


}])
.controller('AccountsNewCtrl', ['$scope','$http','$controller',function($scope,$http,$controller) {

 $scope.action="New"
 angular.extend(this, $controller('AccountsCURDCtrl', {$scope: $scope}));


}]).controller('AccountsCURDCtrl', ['$scope','$http','$routeParams','$rootScope','$uibModal',function($scope,$http,$routeParams,$rootScope,$uibModal) {
  $scope.accounts={};
   $scope.page={totalElements:0,currentPage:1,pageSize:10};
   $scope.tableVal=[ ];

   $rootScope.showCalculator = function() {
                  $rootScope.modalInstance = $uibModal.open({
                      controller: 'AccountsCURDCtrl',
                      ariaLabelledBy: 'modal-title',
                      ariaDescribedBy: 'modal-body',
                      templateUrl: '/view1/calculator.html',
                      size: 'lg'
                  });
              }

$scope.active = function(){[
      {
      "text": true,
      "inactive": true
      },
      {
      "text": false,
      "isGotten": false
      },
   ]};
 $scope.save=function(obj){
 $http.post("/api/accounts", obj)
            .then(function successCallback(response){
            window.location.href="#!/accounts";
                console.log("Successfully POST-ed data");
            }, function errorCallback(response){
            window.location.href="#!/accounts";
                console.log("POST-ing of data failed");
            });
 }
 $scope.loadAccount=function(obj){
   $http.get('api/accounts/'+(obj)).
         then(function(response) {
             $scope.accounts = response.data.results;
            // $scope.page.currentPage=response.data.totalPages;

         });
 }
 $scope.loadAccounts=function(){
   $http.get('api/accounts?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
            // $scope.page.currentPage=response.data.totalPages;

         });

}
}])
.controller('AccountsEditCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
 $scope.action="Edit";
 angular.extend(this, $controller('AccountsCURDCtrl', {$scope: $scope}));
$scope.loadAccount($routeParams.id);

$scope.delete=function(obj){
$http.delete('api/accounts/'+obj.id, {}).then(function successCallback(response){
    window.location.href="#!/accounts";
    console.log("Successfully POST-ed data");


if (response.data)

$scope.msg = "Data Deleted Successfully!";

}, function (response) {

$scope.msg = "Service not Exists";

$scope.statusval = response.status;

$scope.statustext = response.statusText;

$scope.headers = response.headers();

});
}
}])
.controller('AccountsCtrl', ['$scope','$http','$controller', function($scope,$http,$controller) {
angular.extend(this, $controller('AccountsCURDCtrl', {$scope: $scope}));
$scope.loadAccounts();
$scope.load=function(){$scope.loadAccounts();
}
}]);
