'use strict';

var controller1=angular.module("myApp.compete",['ngRoute'])

.config(['$routeProvider',function($routeProvider) {
$routeProvider.when('/compete/:appId',{
templateUrl:'compete/list.html',
controller:'competeCtrl'
});

$routeProvider.when('/compete-edit/:appId/:id',{
templateUrl:'compete/update.html',
controller:'competeEditCtrl'
});

$routeProvider.when('/compete/form/:appId',{
templateUrl:'compete/form.html',
controller:'competeNewCtrl'
});
}])

.controller('competeCurdCtrl',['$scope','$http','$rootScope','$routeParams','$uibModal',function($scope,$http,$rootScope,$routeParams,$uibModal){
$rootScope.showCalculator = function() {
                                      $rootScope.modalInstance = $uibModal.open({
                                          controller: 'competeCurdCtrl',
                                          ariaLabelledBy: 'modal-title',
                                          ariaDescribedBy: 'modal-body',
                                          templateUrl: '/view1/calculator.html',
                                          size: 'lg'
                                      });
                                  }
$scope.compete={competeItemId:[]};
$scope.ebayValue=function(obj){
$scope.url=obj.competeItemId;
$http.get('api/compete/getBuyingAndSellingPrice?url='+escape($scope.url)+"&accountId="+$routeParams.appId).then(function(response){
             if(response.data!=""){
                obj.sellerPrice = response.data.results[0];
                if(response.data.results[0]!="The listing you're looking for has ended." && response.data.results[0]!="We're sorry, something went wrong. Please try again." && response.data.results[0]!="Please Enter a valid Item ID.")
                obj.ebayPrice=response.data.results[1];
                $scope.compete=obj;
             }
             else{
                  obj.sellerPrice="Please Enter a Item ID";
                  $scope.compete=obj;
             }

});
}

$scope.replaceId=function(obj){
$scope.editId=false;
 $http.get('api/compete/competeItemId?itemId='+obj).then(function(response){
 if(response.data=="" || response.data.id==$scope.compete.id && response.data.results==null)
    $scope.compete.competeItemId.push(obj);
    $scope.itemId="";
 });
 }

 $scope.addId=function(obj){
    $http.get('api/compete/competeItemId?itemId='+obj+"&accountId="+$routeParams.id).then(function(response){
    var i=0;
    if(response.data=="" && obj!=undefined && obj!=""){
      if($scope.compete.competeItemId.length==0||$scope.compete.competeItemId.indexOf(obj)==-1)
          $scope.compete.competeItemId.push(obj);
       else
                  noty({text: 'ID Exist in the Field', layout: 'topRight', type: 'warning',killer : true,timeout:2000});

         }
    else if(obj==undefined||obj=="")
                 noty({text: 'PLEASE ENTER ID', layout: 'topRight', type: 'warning',killer : true,timeout:2000});

    else
                 noty({text: 'ID already Exist', layout: 'topRight', type: 'warning',killer : true,timeout:2000});

    })
    }

$scope.pushId=function(obj){
if(!$scope.editId){
$scope.editId=true;
   $scope.competeItemId=(obj);
    var index = $scope.compete.competeItemId.indexOf(obj);
   if (index !== -1) {
   $scope.compete.competeItemId.splice(index, 1);
}}
}



$scope.calculate=function(obj){
$scope.content=obj;
$http.get('api/compete/calculate/getNewSellingPrice?bp='+obj.sellerPrice+"&subtract="+obj.subtractValue).then(function(response){
            obj.ebayPrice=response.data.results;
            $scope.compete=obj;
});
}


$scope.getcompeteData=function(){
$http.get('api/compete/getData?accountId='+$routeParams.appId).then(function(response){
$scope.compete=response.data.results;
});
}

$scope.getItemRelatedcompeteData=function(obj){
$scope.editId=false;
$http.get('api/compete/'+obj).then(function(response){
$scope.compete=response.data.results;
});
}

}])


.controller('competeCtrl',['$scope','$http','$controller','$routeParams',function($scope,$http,$controller,$routeParams){
$scope.params=$routeParams;
 angular.extend(this, $controller('competeCurdCtrl', {$scope: $scope}));
 $scope.getcompeteData();
}])

.controller('competeEditCtrl',['$scope','$http','$controller','$routeParams','$rootScope',function($scope,$http,$controller,$routeParams,$rootScope){
$scope.compete={};
$scope.params=$routeParams;
$scope.deleteItem=function(){
$http.post("api/compete/delete",$scope.compete).then(function successCallback(response){
                                                                  window.location.href="#!/compete/"+$scope.params.appId;
                                                                  console.log("Successfully POST-ed data");
                                                              }, function errorCallback(response){
                                                              window.location.href="#!/compete/"+$scope.params.appId;
                                                                  console.log("POST-ing of data failed");
                                                  });
}

$scope.updateItem=function(){
$.mpb("show",{value: [0,500],speed: 0});
$http.post("api/compete/update?accountId="+$scope.params.appId,$scope.compete).then(function successCallback(response){
                                                      if(response.data.errors) $rootScope.displayError(response.data.messages);
                                                                  window.location.href="#!/compete/"+$scope.params.appId;
                                                                  console.log("Successfully POST-ed data");
                                                              }, function errorCallback(response){
                                                      if(response.data.errors) $rootScope.displayError(response.data.messages);
                                                              window.location.href="#!/compete/"+$scope.params.appId;
                                                                  console.log("POST-ing of data failed");
                                                  });
}
 angular.extend(this, $controller('competeCurdCtrl', {$scope: $scope}));
 $scope.getItemRelatedcompeteData($routeParams.id);
}])


.controller('competeNewCtrl',['$scope','$http','$controller','$routeParams','$rootScope',function($scope,$http,$controller,$routeParams,$rootScope){
$scope.compete={};
$scope.params=$routeParams;
 $scope.save=function(){
 $.mpb("show",{value: [0,500],speed: 0});
        $http.post("api/compete/save?accountId="+$scope.params.appId,$scope.compete).then(function successCallback(response){
           if(response.data.errors) $rootScope.displayError(response.data.messages);
                 window.location.href="#!/compete/"+$scope.params.appId;
                 console.log("Successfully POST-ed data");
             }, function errorCallback(response){
               if(response.data.errors) $rootScope.displayError(response.data.messages);
             window.location.href="#!/compete/"+$scope.params.appId;
                 console.log("POST-ing of data failed");
 });
 }
  angular.extend(this, $controller('competeCurdCtrl', {$scope: $scope}));
}])
