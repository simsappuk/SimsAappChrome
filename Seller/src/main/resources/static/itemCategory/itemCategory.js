'use strict';

var controller1 = angular.module('myApp.itemCategory', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/category', {
    templateUrl: 'itemCategory/itemCategory.html',
    controller: 'categoryCtrl'
  });
}])

 .controller('categoryCtrl', ['$scope','$http','$rootScope','$uibModal', function($scope,$http,$rootScope,$uibModal) {
 $rootScope.showCalculator = function() {
                                           $rootScope.modalInstance = $uibModal.open({
                                               controller: 'categoryCtrl',
                                               ariaLabelledBy: 'modal-title',
                                               ariaDescribedBy: 'modal-body',
                                               templateUrl: '/view1/calculator.html',
                                               size: 'lg'
                                           });
                                       }
 $scope.content=function(obj){
 if(window.location.href.includes("stock-log"))
  selectedHref="stockLog";
  else
  selectedHref="category";
 $http.get('api/category/content?selection='+selectedHref+"&console="+obj).then(function(response){
for(var i=0;i<response.data.results.length;i++)
      name[i]=response.data.results[i].category;
               $scope.names = name;
 })
 }
 $scope.content();
 var name=[];var selectedHref="";
 $scope.categoryContent=function(obj,console){
 if(window.location.href.includes("stock-log"))
 selectedHref="stockLog";
 else
 selectedHref="category";
 if(obj==undefined||obj=="")
 alert("Please Enter Item Category");
 else{
     $scope.tableVal=[ ];
     $http.get('/api/category/content/save?selection='+selectedHref+"&category="+obj+"&console="+console).then(function(response) {
     for(var i=0;i<response.data.results.length;i++)
      name[i]=response.data.results[i].category;
               $scope.names = name;
                  });
      }
                 }
  }]);