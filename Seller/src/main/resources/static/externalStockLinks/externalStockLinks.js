'use strict';

var controller1=angular.module("myApp.externalStockLinks",['ngRoute'])

.config(['$routeProvider',function($routeProvider) {
$routeProvider.when('/externalStockLinks/:appId',{
templateUrl:'externalStockLinks/externalStockLinks.html',
controller:'externalStockLinksCtrl'
});
$routeProvider.when('/externalStockLinksForm/:appId',{
templateUrl:'externalStockLinks/externalStockLinks_form.html',
controller:'externalStockLinksCtrl'
});
}])

.controller('externalStockLinksCurdCtrl',['$scope','$http','$rootScope','$routeParams','$uibModal',function($scope,$http,$rootScope,$routeParams,$uibModal){
$rootScope.showCalculator = function() {
                                          $rootScope.modalInstance = $uibModal.open({
                                              controller: 'externalStockLinksCurdCtrl',
                                              ariaLabelledBy: 'modal-title',
                                              ariaDescribedBy: 'modal-body',
                                              templateUrl: '/view1/calculator.html',
                                              size: 'lg'
                                          });
                                      }


$scope.getData=function(){
$http.get('api/externalStockLinks/getData?accountId='+$routeParams.appId).then(function(response){
$scope.links=response.data.results;
});
}

$scope.refreshContent=function(){
noty({text: 'Please wait for a while', layout: 'topRight', type: 'warning'});
$http.get('api/externalStockLinks/updateContent?accountId='+$routeParams.appId).then(function(response){
$scope.links=response.data.results;
noty({text: 'Updated Successfully', layout: 'topRight', type: 'success',killer : true,timeout:2000});
});
}

$scope.check=[];
$scope.selected=function(obj1,obj){
 if(obj1==true && (obj!=null && obj.length!=0))
 $scope.check.push(obj);
 else if(obj1==false)
 $scope.check.pop(obj);
 }


$scope.deleteContent=function(obj){
   if(obj.length==0||obj==undefined)
             noty({text: 'Please Select an Item To Delete', layout: 'topRight', type: 'warning',killer : true,timeout:2000});
   else{
    $scope.check=[];
       $http.post('api/externalStockLinks/delete/item/'+obj+'/'+$routeParams.appId).then(function successCallback(response){
        noty({text: 'Deleted Successfully', layout: 'topRight', type: 'success',killer : true,timeout:2000});
       $scope.loadStocks();
   });}
   }

$scope.magpieOrEbayData=function(obj){
$scope.url=obj.link;
$http.get('api/externalStockLinks/getStockData?url='+escape($scope.url)+"&accountId="+$routeParams.appId).then(function(response){
             var data= response.data.results;
             if(data.includes("%")){
              var res = data.split("%");
              obj.price=res[0]; obj.title=res[1];
             }
             else
                obj.price=data;
                $scope.externalStockLinks=obj;
});
}

$scope.cexData=function(obj){
$scope.id=obj.cexId;
$http.get('api/externalStockLinks/getStockData?url='+'https://wss2.cex.uk.webuy.io/v3/boxes/'+escape($scope.id)+'/detail').then(function(response){
             var data= response.data.results;
             if(data.includes("%")){
             var res = data.split("%");
             obj.price=res[0]; obj.title=res[1];
             }
             else
             obj.price=data;
             $scope.externalStockLinks=obj;
});
}

 $scope.save=function(){
 if($scope.externalStockLinks.check=='ebay')
    $scope.externalStockLinks.link="www.ebay.co.uk/itm/"+$scope.externalStockLinks.link;
        $http.post("api/externalStockLinks/save?accountId="+$scope.params.appId,$scope.externalStockLinks).then(function successCallback(response){
           if(response.data.errors) $rootScope.displayError(response.data.messages);
                 window.location.href="#!/externalStockLinks/"+$scope.params.appId;
             }, function errorCallback(response){
               if(response.data.errors) $rootScope.displayError(response.data.messages);
             window.location.href="#!/externalStockLinks/"+$scope.params.appId;
 });
 }



}])





.controller('externalStockLinksCtrl',['$scope','$http','$controller','$routeParams',function($scope,$http,$controller,$routeParams){
$scope.params=$routeParams;
 angular.extend(this, $controller('externalStockLinksCurdCtrl', {$scope: $scope}));
 $scope.getData();
}])