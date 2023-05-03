'use strict';

angular.module('myApp.listing', ['ngRoute'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/ActiveListing/:id', {
    templateUrl: 'listing/list.html',
    controller: 'ListingListCtrl',
     params: {
                page: {
                    value: '1',
                    squash: true
                },
                pageSize: {
                    value: '15',
                    squash: true
                },
                totalElements: {
                    value: '0',
                    squash: true
                },
                filter: {
                    value: 'MY',
                    squash: true
                },
                q: {
                    value: '',
                    squash: true
                }
            }
  });
  $routeProvider.when('/listing-new/:id', {
      templateUrl: 'listing/addForm.html',
      controller: 'ListNewCtrl'
    });

   $routeProvider.when('/reviseListing/:id', {
         templateUrl: 'listing/reviseForm.html',
         controller: 'ListNewCtrl'
       });
    $routeProvider.when('/reviseListing/:id/:itemId', {
         templateUrl: 'listing/reviseForm.html',
         controller: 'ListNewCtrl'
       });
  $routeProvider.when('/listing-edit/:appId/:id', {
          templateUrl: 'listing/_form.html',
          controller: 'ListingEditCtrl'
        });


}])
.controller('ListNewCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {

 $scope.params=$routeParams;
 if($routeParams.itemId!=null){
 var itemId;
 $scope.EbayListing={itemId:$routeParams.itemId};
 }
  angular.extend(this, $controller('ListCURDCtrl', {$scope: $scope}));


}]).controller('ListCURDCtrl', ['$scope','$http','$routeParams','$rootScope','$uibModal', function($scope,$http,$routeParams,$rootScope,$uibModal) {
   $scope.stock={};
   $scope.page={totalElements:0,currentPage:1,pageSize:200,loading:false};
   $scope.tableVal=[ ];

$rootScope.showCalculator = function() {
    $rootScope.modalInstance = $uibModal.open({
        controller: 'ListCURDCtrl',
        ariaLabelledBy: 'modal-title',
        ariaDescribedBy: 'modal-body',
        templateUrl: '/view1/calculator.html',
        size: 'lg'
    });
}

 $scope.save=function(obj){
 $http.post("/api/stock", obj)
            .then(function successCallback(response){
            window.location.href="#!/stock";
                console.log("Successfully POST-ed data");
            }, function errorCallback(response){
            window.location.href="#!/stock";
                console.log("POST-ing of data failed");
            });
 }

 $scope.searchText='';
 $scope.search=function(){
 $http.get('api/ActiveListing/search?q='+$scope.searchText+"&id="+($routeParams.id)+"&page="+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
            then(function(response){
               $scope.tableVal = response.data.results;
               })
 }

 $scope.postListing=function(obj,accountId){
 $scope.params=$routeParams;
 $http.post("api/ActiveListing/post/"+accountId,obj)
             .then(function successCallback(response){
             window.location.href="#!/ActiveListing/"+$scope.params.id;
                  console.log("Successfully POST-ed data");
             },function errorCallback(response){
             window.location.href="#!/ActiveListing/"+$scope.params.id;
                 console.log("POST-ing of data failed");
             });
 }

  $scope.reviseListing=function(obj,accountId){
   $.mpb("show",{value: [0,500],speed:0});
   $scope.params=$routeParams;
    $http.get("api/ActiveListing/revise/"+accountId+'?itemId='+(obj.itemId)+"&amount="+(obj.startPriceValue)+"&quantity="+(obj.quantityAvailable)+"&listingType="+(obj.listingType)+"&firstVariationName="+(obj.firstVariationName)+"&firstVariationValue="+(obj.firstVariationValue)+"&variantSku="+(obj.variantSku)+"&secondVariationName="+(obj.secondVariationName)+"&secondVariationValue="+(obj.secondVariationValue))
              .then(function (response){
              if(response.data.errors) $rootScope.displayError(response.data.messages);
              $scope.EbayListing=response.data.results;
              });
 }

  $scope.check=[];
  $scope.selected=function(obj1,obj){
  if(obj1==true && (obj!=null && obj.length!=0))
  $scope.check.push(obj);
  else if(obj1==false)
  $scope.check.pop(obj);
  }

   $scope.activeRelist=function(obj){
   $scope.params=$routeParams;
   if(obj.length==0||obj==undefined)
               noty({text: 'Please Select an Item To ReList', layout: 'topRight', type: 'warning',killer :true,timeout:2000});
      else{
              $http.post("/api/ActiveListing/pushListing?listingId="+obj+"&accountId="+$scope.params.id)
              .then(function successCallback(response){
                if(response.data.errors){
                  noty({text:response.data.messages[0].messageText, layout: 'topRight', type: 'error',killer : true});
                  $scope.check=[];
                  }
                else
                  noty({text: 'Successfully Added to Relist', layout: 'topRight', type: 'success',killer : true,timeout:2000});
                  window.location.href="#!/ActiveListing/"+$routeParams.id;
                  $scope.check=[];
                  console.log("Successfully POST-ed data");
              }, function errorCallback(response){
              noty({text: response.data.messages[0].messageText, layout: 'topRight', type: 'error',killer : true});
                  window.location.href="#!/ActiveListing/"+$routeParams.id;
                  $scope.check=[];
                  console.log("POST-ing of data failed");
              });
              }
   }


$scope.reviseSku=function(obj){
     $http.get('api/ActiveListing/reviseSetNewSku?accountId='+(obj.accountId)+"&itemId="+(obj.itemId)+"&sku="+(obj.sku)).then(function(response){
              if(response.data.errors) $rootScope.displayError(response.data.messages);
});
}


 $scope.loadListing=function(obj){
 $.mpb("show",{value: [0,500],speed:0});
   $http.get('api/ActiveListing/'+(obj.appId)+'/'+obj.id).
         then(function(response) {
          if(response.data.errors) $rootScope.displayError(response.data.messages);
             $scope.EbayListing = response.data.results;
              $scope.getEbay();
            // $scope.page.currentPage=response.data.totalPages;

         });
 }
 $scope.sortBy=function(id,variable){
 $scope.accountId=id;
$http.get('api/ActiveListing/sorting?page='+($scope.page.currentPage-1)+"&pageSize="+($scope.page.pageSize)+"&accountId="+($scope.accountId)+"&sortBy="+variable).then(function(response){
  if(response.data.errors) $rootScope.displayError(response.data.messages);
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
});
}
 $scope.loadActiveList=function(id){
 $scope.page.loading=true;
       noty({text: 'Please Wait for a While....... ', layout: 'topRight', type: 'warning',killer : true});
 $scope.tableVal=[ ];
   $http.get('/api/ActiveListing/'+id+'/list?page='+($scope.page.currentPage)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
         if(response.data.errors){
         $rootScope.displayError(response.data.messages);
         window.location.href="#!/ActiveListing/"+$scope.params.id;         }
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
         window.location.href="#!/ActiveListing/"+$scope.params.id;
         });

}
 $scope.loadListingData=function(id,obj){
 if(obj!=undefined)
 $scope.sortBy(id,obj);
 else{
   $scope.page.loading=true;
   $scope.tableVal=[ ];
   $http.get('/api/ActiveListing/'+id+'/database?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
         if(response.data.errors) $rootScope.displayError(response.data.messages);
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
         });
   }

}
}])
.controller('ListingEditCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
 $scope.action="Edit";
 $scope.params=$routeParams;
 $scope.stuff=function(obj){
 obj=obj.replace('href="/gp/offer-listing/',' target="_blank" href="http://www.amazon.co.uk/gp/offer-listing/');
 return obj;
 }
 $scope.getEbay=function(){
    $http.get('api/stock/getAmazonData').
          then(function(response) {
          if(response.data.errors) $rootScope.displayError(response.data.messages);
          $("#ebayOutput").html($scope.stuff(response.data.results));
          });
 }
 angular.extend(this, $controller('ListCURDCtrl', {$scope: $scope}));
$scope.loadListing($scope.params);
}])
.controller('ListingListCtrl', ['$scope','$http','$controller','$routeParams', function($scope,$http,$controller,$routeParams) {
angular.extend(this, $controller('ListCURDCtrl', {$scope: $scope}));
$scope.params=$routeParams;
$scope.loadListingData($routeParams.id);
$scope.load=function(){
    $.mpb("show",{value: [0,500],speed:0});
    $scope.loadActiveList($routeParams.id);
}
$scope.data={upload:false};
$scope.getFromEbay=function(){
$scope.data.upload=true;
$scope.page={page:0,pageSize:50};
///alert("Now i will call ebay from restful webservice...");
   $http.get('api/stock/loadEbay').
         then(function(response) {
$scope.data.upload=false;
         });
}

}]);
