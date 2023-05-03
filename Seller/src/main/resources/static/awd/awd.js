'use strict';

var controller1 = angular.module('myApp.awd', ['ngRoute'])

  controller1.directive('ngEnter', function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if (event.which === 13) {
                    scope.$apply(function () {
                        scope.$eval(attrs.ngEnter);
                    });
                    event.preventDefault();
                }
            });
        };
    })
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/awaitingDispatch/:id/onLine', {
    templateUrl: 'awd/list.html',
    controller: 'awdListCtrl',
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
   $routeProvider.when('/awaitingDispatch-new', {
       templateUrl: 'awd/_form.html',
       controller: 'awdNewCtrl'
   });
   $routeProvider.when('/orders/:appId', {
       templateUrl: 'awd/listFromDB.html',
       controller: 'awdEditCtrlDB'
   });
   $routeProvider.when('/orders/:appId/awaitingPayment', {
       templateUrl: 'awd/awaitingPayment.html',
       controller: 'awdAwaitingPaymentCtrl'
   });
   $routeProvider.when('/orders/:appId/awaitingDispatch', {
       templateUrl: 'awd/awaitingDispatch.html',
       controller: 'awdAwaitingDispatchCtrl'
   });
   $routeProvider.when('/orders/:appId/paidAndDispatched', {
       templateUrl: 'awd/paidAndDispatched.html',
       controller: 'awdPaidAndDispatchedCtrl'
   });
   $routeProvider.when('/orders/:appId/dropshipped', {
       templateUrl: 'awd/dropshipOrders.html',
       controller: 'awdDropshipOrdersCtrl'
   });
   $routeProvider.when('/orders/:appId/dropshipResetListingOrders', {
       templateUrl: 'awd/resetListingOrders.html',
       controller: 'resetListingOrdersCtrl'
   });
   $routeProvider.when('/awaitingDispatch/orders/list/:appId/refresh', {
       templateUrl: 'awd/listFromDB.html',
       controller: 'onlineRefreshOrderCtrl'
   });
   $routeProvider.when('/awaitingDispatch-edit/:appId/:orderId/:id', {
       templateUrl: 'awd/_form.html',
       controller: 'awdEditCtrl'
   });
   $routeProvider.when('/awaitingDispatch/orders/list', {
       templateUrl: 'awd/listFromDB.html',
       controller: 'awdOrderCtrl'
   });
   $routeProvider.when('/awaitingDispatch/orderContent/:appId/:orderRef', {
       templateUrl: 'awd/orderContent.html',
       controller: 'awdOrderContentCtrl'
   });
   $routeProvider.when('/orders/:accountType/:appId', {
       templateUrl: 'awd/amazonOrders.html',
       controller: 'awdAmazonCtrl'
   });
   $routeProvider.when('/orders/:accountType/:appId/refresh', {
       templateUrl: 'awd/amazonOrders.html',
       controller: 'awdAmazonRefreshCtrl'
   });

}])
.controller('awdOrderCtrl', ['$scope','$http','$controller',function($scope,$http,$controller) {
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadOrders();
}])

.controller('awdAmazonCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadAmazonOrders();
}])

.controller('awdAmazonRefreshCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.refreshAmazonOrders();
}])

.controller('awdAwaitingDispatchCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadAwaitingDispatchOrders();
}])

.controller('awdAwaitingPaymentCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadAwaitingPaymentOrders();
}])

.controller('awdPaidAndDispatchedCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadPaidAndDispatchedOrders();
}])

.controller('awdDropshipOrdersCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadDropshipOrders();
}])

.controller('resetListingOrdersCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
$scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadResetListingOrders();
}])


.controller('awdOrderContentCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.loadOrderContents($routeParams);
}])

.controller('onlineRefreshOrderCtrl', ['$scope','$http','$controller','$routeParams',function($scope,$http,$controller,$routeParams) {

 $scope.params=$routeParams;
    angular.extend(this,$controller('AwatingDispatchCURDCtrl',{$scope: $scope}));
          $scope.refreshOrders();
}])
.controller('awdNewCtrl', ['$scope','$http','$controller',function($scope,$http,$controller) {

 //$scope.action="New"
 //angular.extend(this, $controller('AwatingDispatchCURDCtrl', {$scope: $scope}));


}]).controller('AwatingDispatchCURDCtrl', ['$scope','$http','$routeParams','$rootScope','$uibModal', function($scope,$http,$routeParams,$rootScope,$uibModal) {
  $scope.awaiting={};
  $scope.page={totalElements:0,currentPage:1,pageSize:200,loading:false};
  $scope.tableVal=[ ];

$rootScope.showCalculator = function() {
    $rootScope.modalInstance = $uibModal.open({
        controller: 'AwatingDispatchCURDCtrl',
        ariaLabelledBy: 'modal-title',
        ariaDescribedBy: 'modal-body',
        templateUrl: '/view1/calculator.html',
        size: 'lg'
    });
}

$rootScope.close = function() {
    $rootScope.modalInstance.close();
}

$rootScope.calculateChange = function(obj){
if(obj.sponsor==null)
obj.sponsor=0;
if(obj.postage==null)
obj.postage=0;
if(obj.sp==null)
obj.sp==0;
obj.profit =parseFloat((obj.sp - obj.bp - obj.fee - obj.vat - obj.sponsor - obj.postage).toFixed(2));
obj.profitPercent=parseFloat(((obj.profit/obj.sp)*100).toFixed(2));
}

$rootScope.profit = function(obj) {
    var vat = 0;var sponsor = 0;var postage = 0;var numerator=0;var denominator=0; var split=[];var fixedFee=0; var fixedProfit=0;
    if (obj.selected=='profit' && obj.sp!=null && obj.sp!=undefined && obj.bp != null && obj.bp != undefined && obj.condition != undefined && obj.condition != null) {
        var fee = obj.sp * 13.5 / 100 + 0.35;
        obj.fee = parseFloat(fee.toFixed(2));
        var difference = obj.sp - obj.bp;
        if (obj.condition == 'used')
            vat = difference * 16.66 / 100;
        else
            vat = obj.sp / 6;
        obj.vat =parseFloat(vat.toFixed(2));
        if (obj.sponsor != null && obj.sponsor != undefined)
            sponsor = obj.sp * (obj.sponsor / 100);
        if (obj.postage != null && obj.postage != undefined)
            postage = obj.postage;
        obj.profit =parseFloat((obj.sp - obj.bp - fee - vat - sponsor - postage).toFixed(2));
        obj.profitPercent=parseFloat(((obj.profit/obj.sp)*100).toFixed(2));
    }

    else if(obj.selected!='profit' && obj.bp != null && obj.bp != undefined && obj.condition != undefined && obj.condition != null){
    if(obj.vat==undefined || obj.vat!=0)
    obj.vat=0.83;
    if(obj.sponsor==null || obj.sponsor==undefined)
    obj.sponsor=0;
    if(obj.fee==undefined || obj.fee!=0)
    obj.fee=(13.5/100);
    obj.profit=(10/100);
    if(obj.postage==null || obj.postage==undefined)
    obj.postage=0;
    obj.sp=parseFloat((((obj.bp*(0.83)+0.35)/(0.83-(obj.fee+(obj.sponsor/100))-obj.profit))+parseFloat(obj.postage)).toFixed(2));
    }
}
$rootScope.refreshCal = function(obj) {
    if(Object.keys(obj).length==1)
    $scope.cal =obj;
    else
    $scope.cal={selected:obj.selected};
}


 $scope.saveAwaiting=function(obj){
 $http.post("/api/awd", obj)
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
 $http.get('api/awd/search?q='+$scope.searchText+"&id="+($scope.params.appId)+"&page="+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
            then(function(response){
               $scope.EbayListing = response.data.results;
               })
 }

 $scope.searchDispatched=function(){
  $http.get('api/awd/search/paidAndDispatched?q='+$scope.searchText+"&id="+($scope.params.appId)+"&page="+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
             then(function(response){
                $scope.EbayListing = response.data.results;
                })
  }
 
 $scope.check=[];
  $scope.selected=function(obj1,obj){
  if(obj1==true && (obj!=null && obj.length!=0))
  $scope.check.push(obj);
  else if(obj1==false)
  $scope.check.pop(obj);
  }

  $scope.setOrderToDropshipped=function(obj){
     if(obj.length==0||obj==undefined)
     alert("PLEASE SELECT AN ITEM TO MARK AS DROPSHIP");
     else{
      $scope.check=[];
         $http.post('api/dropship/dropship/order/'+obj).then(function successCallback(response){
         if(window.location.href.includes("dropshipResetListingOrders"))
         $scope.loadResetListingOrders();
          else
          $scope.loadAwaitingDispatchOrders($routeParams.appId,null);
     });}
     }

   $scope.setOrderToReset=function(obj){
        if(obj.length==0||obj==undefined)
        alert("PLEASE SELECT AN ITEM TO MARK AS RESET");
        else{
         $scope.check=[];
            $http.post('api/reset/SetOrderToReset/'+obj).then(function successCallback(response){
            if(window.location.href.includes("dropshipResetListingOrders"))
            $scope.loadResetListingOrders();
             else
             $scope.loadAwaitingDispatchOrders($routeParams.appId,null);
        });}
        }

   $scope.setOrderToAwaitingDispatch=function(obj){
        if(obj.length==0||obj==undefined)
        alert("PLEASE SELECT AN ITEM TO MARK AS AWAITING DISPATCH");
        else{
         $scope.check=[];
            $http.post('api/awd/toAwaitingDispatch/order/'+obj).then(function successCallback(response){
            $scope.loadPaidAndDispatchedOrders($routeParams.appId,null);
        });}
        }
   $scope.setResetOrderToAwaitingDispatch=function(obj){
           if(obj.length==0||obj==undefined)
           alert("PLEASE SELECT AN ITEM TO MARK AS AWAITING DISPATCH");
           else{
            $scope.check=[];
               $http.post('api/awd/orders/resetListing/revert/'+obj).then(function successCallback(response){
               $scope.loadResetListingOrders();
           });}
           }

$scope.undoDropshippedItem=function(obj){
     if(obj.length==0||obj==undefined)
     alert("PLEASE SELECT AN ITEM TO UNDO DROPSHIP");
     else{
      $scope.check=[];
         $http.post('api/dropship/undoDropship/order/'+obj).then(function successCallback(response){
         $scope.loadDropshipOrders();
     });}
     }
 $rootScope.showDataInPopup=function(obj){
     $rootScope.modalInstance =  $uibModal.open({
       ariaLabelledBy: 'modal-title',
       ariaDescribedBy: 'modal-body',
       templateUrl: '/awd/dialog.html',
       scope:$rootScope,
       size: 'lg'
     });
     $rootScope.listing=obj;
  }

 $rootScope.close=function(){
 $rootScope.modalInstance.close();
 }

$scope.isValid=function(obj){
if(obj!=null) return obj;
else return '';
}
$scope.sort=function(id,variable){
 $scope.accountId=id;
$http.get('api/awd/sorting?page='+($scope.page.currentPage-1)+"&pageSize="+($scope.page.pageSize)+"&accountId="+($scope.accountId)+"&sortBy="+variable).then(function(response){
  if(response.data.errors) $rootScope.displayError(response.data.messages);
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
});
}

 $scope.loadAwaiting=function(obj,value){
 if(value!=undefined)
 $scope.sort(obj,value);
 else{
   $http.get('/api/awd/'+($scope.params.appId)+'?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&date="+$scope.formatDate($scope.isValid($scope.params.date))+"&dateTo="+$scope.formatDate($scope.isValid($scope.params.date1))).
         then(function(response) {
         if(response.data.errors) $rootScope.displayError(response.data.messages);
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
           //  $scope.getOrder=(obj.orderId);
            // $scope.page.currentPage=response.data.totalPages;
             setTimeout(
               function()
               {
                 $scope.feSelect();
               }, 50);
         });
   }
 }

 $scope.loadAmazonOrders=function(){
   $http.get('/api/awd/amazon/'+($routeParams.appId)+'?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
          then(function(response) {
          if(response.data.errors) $rootScope.displayError(response.data.messages);
              $scope.amazonOrders = response.data.results;
              $scope.page.totalElements=response.data.totalElements;
              setTimeout(
                function()
                {
                  $scope.feSelect();
                }, 50);
          });
      }

   $scope.refreshAmazonOrders=function(){
      $http.get('/api/awd/amazon/'+($routeParams.appId)+'/refresh?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
             then(function(response) {
             if(response.data.errors) $rootScope.displayError(response.data.messages);
                 $scope.amazonOrders = response.data.results;
                 $scope.page.totalElements=response.data.totalElements;
                 setTimeout(
                   function()
                   {
                     $scope.feSelect();
                   }, 50);
             });
         }


 $scope.loadAwaitingDispatchOrders=function(obj,value){
  if(value!=undefined)
  $scope.sort(obj,value);
  else{
       $scope.tableVal=[ ];
       if($scope.params==undefined){
           $scope.params = {appId:obj}; var bundle=true;
           }
   $http.get('/api/awd/'+$scope.params.appId+'/awaitingDispatch?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
             if(bundle==undefined){
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
             }
             else{
             window.location.href="http://localhost:8080/#!/orders/"+obj+"/awaitingDispatch";
              noty({
                                         text: 'Successful Dropship',
                                         layout: 'topRight',
                                         type: 'success',
                                         killer: true,
                                         timeout: 2000
                                     });
             }
         });
      }
    }
 $scope.loadDropshipOrders=function(){
 /* if(value!=undefined)
  $scope.sort(obj,value);
  else
       $scope.tableVal=[ ];*/
   $http.get('/api/dropship/'+$scope.params.appId+'/dropshipOrders?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
         });
    }

  $scope.loadResetListingOrders=function(){
  $http.get('/api/awd/'+$scope.params.appId+'/resetListing/orders?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
               $scope.EbayListing = response.data.results;
               $scope.page.totalElements=response.data.totalElements;
               $scope.page.loading=false;
           });

  }

  $scope.loadResetListingOrdersByDate=function(){
    $http.get('/api/awd/'+$scope.params.appId+'/resetListing/date/orders?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&date="+$scope.formatDate($scope.params.date)).then(function(response) {
                 $scope.EbayListing = response.data.results;
                 $scope.page.totalElements=response.data.totalElements;
                 $scope.page.loading=false;
             });

    }
  $scope.loadDropshipOrdersByDate=function(){
      $http.get('/api/dropship/'+$scope.params.appId+'/dropshipOrders/byDate?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&date="+$scope.formatDate($scope.params.date)).then(function(response) {
                   $scope.EbayListing = response.data.results;
                   $scope.page.totalElements=response.data.totalElements;
                   $scope.page.loading=false;
               });

      }

 $scope.loadAwaitingPaymentOrders=function(obj,value){
  if(value!=undefined)
  $scope.sort(obj,value);
  else{
         $scope.tableVal=[ ];
   $http.get('/api/awd/'+$scope.params.appId+'/awaitingPayment?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
         });
      }
    }

 $scope.loadPaidAndDispatchedOrders=function(obj,value){
  if(value!=undefined)
  $scope.sort(obj,value);
  else{
         $scope.tableVal=[ ];
   $http.get('/api/awd/'+$scope.params.appId+'/paidAndDispatched?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
             });
      }
    }

  $scope.loadOrderContents=function(obj){
$http.get('api/awd/orderContent/'+obj.orderRef+'?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&accountId="+obj.appId).
  then(function(response) {
              $scope.EbayListing = response.data.results;
              $scope.page.totalElements=response.data.totalElements;
              setTimeout(
                function()
                {
                  $scope.feSelect();
                }, 50);
          });
  }

  $scope.feSelect = function(){
              if($(".select").length > 0){
                  $(".select").selectpicker();

                  $(".select").on("change", function(){
                      if($(this).val() == "" || null === $(this).val()){
                          if(!$(this).attr("multiple"))
                              $(this).val("").find("option").removeAttr("selected").prop("selected",false);
                      }else{
                          $(this).find("option[value="+$(this).val()+"]").attr("selected",true);
                      }
                  });
              }
          }
           $scope.formatDate=function(date) {
           if(date=='') return '';
              var d = new Date(date),
                  month = '' + (d.getMonth() + 1),
                  day = '' + d.getDate(),
                  year = d.getFullYear();

              if (month.length < 2) month = '0' + month;
              if (day.length < 2) day = '0' + day;

              return [year, month, day].join('-');
          }
 $scope.loadAwaitings=function(id){
     $scope.page.loading=true;

     $scope.tableVal=[ ];
   $http.get('/api/awd/'+id+'/list?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&date="+$scope.formatDate($scope.params.date)+"&dateTo="+$scope.formatDate($scope.params.date1)).
         then(function(response) {
                  if(response.data.errors) $rootScope.displayError(response.data.messages);

             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;

         });

}
    $rootScope.changeDate=function(){
    $scope.loadAwaitings($routeParams.appId);
    }
/*
    $scope.dateToDatabase=function(){
    }*/

     $rootScope.changeDateTo=function(){
        $scope.loadAwaitings($routeParams.appId);
    }
 $scope.loadOrders=function(){
         $scope.tableVal=[ ];
   $http.get('/api/awd/'+$scope.params.appId+'?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
            // $scope.page.currentPage=response.data.totalPages;

         });

}



 $scope.refreshOrders=function(){
 $.mpb("show",{value: [0,500],speed:0});
         $scope.tableVal=[ ];
   $scope.params=$routeParams;
   $scope.page.loading=true;
   $http.get('/api/awd/'+$scope.params.appId+'/online/acct/refresh?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
    if(response.data.errors){
    $rootScope.displayError(response.data.messages);
    window.location.href="#!/orders/"+$scope.params.appId;
    }
             $scope.EbayListing = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
             $scope.page.loading=false;
    window.location.href="#!/orders/"+$scope.params.appId;
         });

}
}])
.controller('awdEditCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
 $scope.action="Edit";
 $scope.params=$routeParams;

//$scope.tableVal=[];
// angular.extend(this, $controller('StockCURDCtrl', {$scope: $scope}));

//$scope.loadStocks();
 angular.extend(this, $controller('AwatingDispatchCURDCtrl', {$scope: $scope}));
//$scope.getStock=function(){
//}
//$scope.loadOrders();
$scope.loadAwaiting($scope.params);

 // $scope.feSelect();
}])
.controller('awdEditCtrlDB', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
 $scope.action="Edit";
 $scope.params=$routeParams;

//$scope.tableVal=[];
// angular.extend(this, $controller('StockCURDCtrl', {$scope: $scope}));

//$scope.loadStocks();
 angular.extend(this, $controller('AwatingDispatchCURDCtrl', {$scope: $scope}));
//$scope.getStock=function(){
//}
//$scope.loadOrders();
$scope.loadAwaiting($scope.params);

 // $scope.feSelect();
}])
.controller('awdListCtrl', ['$scope','$http','$controller','$routeParams', function($scope,$http,$controller,$routeParams) {
angular.extend(this, $controller('AwatingDispatchCURDCtrl', {$scope: $scope}));
$scope.params=$routeParams;

$scope.params.date='';
$scope.params.date1='';
$scope.loadAwaitings($routeParams.id);
    $scope.load=function(){
        $.mpb("show",{value: [0,500],speed: 0});
        $scope.loadAwaitings($routeParams.id);
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