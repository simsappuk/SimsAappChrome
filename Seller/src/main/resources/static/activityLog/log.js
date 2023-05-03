'use strict';

angular.module('myApp.log', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/log/:id', {
    templateUrl: 'activityLog/logDropships.html',
    controller: 'logCtrl'
  });
  $routeProvider.when('/log/addSpreadsheet/:id', {
      templateUrl: 'activityLog/add.html',
      controller: 'logCtrl'
    });
  $routeProvider.when('/log/:id/:spreadSheetId/:channelId/REPLY & ADJUSTMENT', {
      templateUrl: 'activityLog/replyAndAdjustments.html',
      controller: 'logCtrl'
    });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/DROPSHIPPED', {
        templateUrl: 'activityLog/logDropships.html',
        controller: 'logCtrl'
      });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/TRACKING NUMBERS', {
          templateUrl: 'activityLog/tracking.html',
          controller: 'logCtrl'
        });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/NOT SENT ITEMS', {
          templateUrl: 'activityLog/notSentItems.html',
          controller: 'logCtrl'
        });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/RETURNS', {
          templateUrl: 'activityLog/returns.html',
          controller: 'logCtrl'
        });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/REFUNDS', {
          templateUrl: 'activityLog/refunds.html',
          controller: 'logCtrl'
        });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/REPLACEMENTS', {
          templateUrl: 'activityLog/replacements.html',
          controller: 'logCtrl'
        });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/POSTAGE & GOODWILL GESTURE REFUNDS', {
            templateUrl: 'activityLog/gestureRefunds.html',
            controller: 'logCtrl'
          });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/AMAZON TRACKING', {
            templateUrl: 'activityLog/amazonTracking.html',
            controller: 'logCtrl'
          });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/FACEBOOK ORDERS', {
                          templateUrl: 'activityLog/facebookOrders.html',
                          controller: 'logCtrl'
                        });
    $routeProvider.when('/log/:id/:spreadSheetId/:channelId/RELIST ITEMS INFO', {
                              templateUrl: 'activityLog/relistInfo.html',
                              controller: 'logCtrl'
                            });
    $routeProvider.when('/log/dropshipPurchaseOrder/:logId/:orderId', {
                templateUrl: 'activityLog/dropshipPurchaseOrder.html',
                controller: 'logDropshipPurchaseOrderCtrl'
              });
    $routeProvider.when('/log/dropshipPurchaseOrderByUserId/:logId/:userId', {
                    templateUrl: 'activityLog/dropshipOrderByUserId.html',
                    controller: 'logDropshipPurchaseOrderCtrl'
                  });
 }])

.controller('logCURDCtrl', ['$scope','$http','$routeParams','$rootScope','$uibModal',function($scope,$http,$routeParams,$rootScope,$uibModal) {

 $rootScope.sheet={sheetSize:2000,spreadSheetId:0,channelId:14};
  if($routeParams.sheetSize==null)
     $rootScope.sheet.sheetSize=2000;
  else
   $rootScope.sheet.sheetSize=$routeParams.sheetSize;
  $rootScope.sheet.spreadSheetId=$routeParams.spreadSheetId!=null?$routeParams.spreadSheetId:0;
  $rootScope.sheet.channelId=$routeParams.channelId!=null?$routeParams.channelId:14;
  $scope.page={totalElements:0,currentPage:1,pageSize:20};
  $rootScope.params=$routeParams;
  


    $scope.isObjectEmpty = function(card){
       return Object.keys(card).length === 0;
    }

$scope.unStuff=function(obj){
var dest=[];
if(obj.length==undefined)
dest.push(obj);
else
for(var i=0;i<obj.length;i++){
if(!$scope.isObjectEmpty(obj[i])) dest.push(obj[i]);
}
return dest;
}

$rootScope.showPopup=function(){
       $rootScope.modalInstance =  $uibModal.open({
             controller: 'logCtrl',
         ariaLabelledBy: 'modal-title',
         ariaDescribedBy: 'modal-body',
         templateUrl: '/activityLog/showSheets.html',
         size: 'lg'
       });
       document.getElementById("x-spreadsheet-demo").innerHTML = "";
   }

   $rootScope.close=function(){
   $rootScope.modalInstance.close();
   }

  $scope.stuffArray=function(obj,maxLength){
    if(obj==null) obj=[];
      var tot=obj.length;
    for(var i=tot;i<maxLength;i++) obj.push({});
      return obj;
  }


  $scope.loadChannel=function(obj){
    $http.get('api/channel/log').
          then(function(response) {
           $scope.channels = response.data.results;
          });
  }

  $rootScope.saveGdriveSheet=function(obj){
  $http.post("/api/log/saveGdriveSheet?sheetId="+obj.spreadsheetId+"&title="+obj.title)
               .then(function successCallback(response){
                   console.log("Successfully POST-ed data");
                   noty({text:'Data Saved', layout: 'topRight', type: 'success',killer : true,timeout: 2000});
               }, function errorCallback(response){
                  noty({text:'Unable to Save Data', layout: 'topRight', type: 'error',killer : true,timeout:2000});
               });
  }


  $rootScope.save=function(obj){
  $rootScope.items=[];
  var sheetId=($routeParams.spreadSheetId==null)?0:$rootScope.sheet.spreadSheetId;
  $http.post("/api/log/"+$routeParams.id+'/'+$rootScope.sheet.channelId+'/'+sheetId, $scope.unStuff(obj))
             .then(function successCallback(response){
                 console.log("Successfully POST-ed data");
                  $rootScope.items=$scope.stuffArray(response.data,$rootScope.sheet.sheetSize);
                  $scope.page.totalElements=response.data.totalElements;
                 noty({text:'Data Saved', layout: 'topRight', type: 'success',killer : true,timeout: 2000});
             }, function errorCallback(response){
                noty({text:'Unable to Save Data', layout: 'topRight', type: 'error',killer : true,timeout:2000});
             });
  }


   $rootScope.saveRowLog=function(element,row,value,title){
   $http.get("api/log/saveCellData?name="+element[1]+"&value="+value+"&title="+title+"&rowId="+$rootScope.items[row].id+"&channelId="+$rootScope.params.channelId+"&accountId="+$rootScope.params.id+"&spreadSheetId="+$rootScope.params.spreadSheetId).then(function(response){
   if(response.data.errors)
                     noty({text:response.data.messages[0].messageText, layout: 'topRight', type: 'error',killer : true});
                     else{
                     $rootScope.items[row]=response.data.results;
                     noty({text:'Data Saved', layout: 'topRight', type: 'success',killer : true,timeout: 1000});
                     }
                     });
   }


   $scope.loadActivityLog=function(obj){
   $rootScope.items=[];
   if(obj.duplicateId!=null)
   var sheetId=obj.duplicateId;
   else
   var sheetId=($routeParams.spreadSheetId!=null?$routeParams.spreadSheetId:0);
     $http.get('api/log/load/'+sheetId+'?channelId='+$rootScope.sheet.channelId+"&sheetNumber="+$routeParams.sheetNumber+"&accountId="+$routeParams.id).
           then(function(response) {
               $rootScope.items=$scope.stuffArray(response.data.results,$rootScope.sheet.sheetSize);
               $scope.page.totalElements=response.data.totalElements;
           });
  }

    $rootScope.content=function(){
     var name=[];
     $http.get('api/category/content').then(function(response){
    for(var i=0;i<response.data.results.length;i++)
          name[i]=response.data.results[i].category;
                   $rootScope.names = name;
     });
     }
    $rootScope.conditions=['New','Used'];

    $scope.check=[];
      $scope.selected=function(obj1,obj){
      if(obj1==true && (obj!=null && obj.length!=0))
      $scope.check.push(obj);
      else if(obj1==false)
      $scope.check.pop(obj);
      }

      $scope.dropshipPurchasedOrder=function(obj){
           if(obj.length==0||obj==undefined)
           alert("PLEASE SELECT AN ITEM TO MARK AS DROPSHIP");
           else{
            $scope.check=[];
            if($routeParams.userId==undefined){
               $http.post('api/dropship/dropshipPurchasedOrder/'+obj+'/'+$routeParams.logId).then(function successCallback(response){
               $scope.loadPurchaseOrderContent();
               setTimeout(function(){window.close();}, 2000);
               });
            }
            if($routeParams.userId!=undefined){
               $http.post('api/dropship/dropshipPurchasedOrder/'+obj+'/'+$routeParams.logId).then(function successCallback(response){
               noty({text:'Order Dropshipped Successfully', layout: 'topRight', type: 'success'});
               setTimeout(function(){window.close();}, 2000);
               });
            }

           }
           }
    $scope.loadPurchaseOrderContent=function(){
         $http.get('api/awd/dropshipPurchaseOrder?orderId='+$routeParams.orderId).then(function(response){
         if(response.data.errors) $rootScope.displayError(response.data.messages);
                      $scope.EbayListing = response.data.results;
      });
      }

   $scope.getOrderDetails=function(orderId){
            $http.get('api/awd/getOrderDetails?orderId='+orderId).then(function(response){
            if(response.data.errors) $rootScope.displayError(response.data.messages);
                         $scope.EbayListing = response.data.results;
         });
         }

    $scope.loadUserIdContent=function(){
             $http.get('api/awd/purchaseOrderContentByUserId?userId='+$routeParams.userId).then(function(response){
             if(response.data.errors) $rootScope.displayError(response.data.messages);
                          $scope.orders = response.data.results;
          });
          }

          $rootScope.getSheetName=function(){
          if(document.getElementById("x-spreadsheet-demo")!=null)
          document.getElementById("x-spreadsheet-demo").innerHTML = "";
          $http.get("api/log/getGdriveSheets").then(function(response){
          if(response.data!=null)
          $rootScope.spreadsheet=response.data.results;
          });
          }


}])

 .controller('logCtrl',['$scope','$routeParams','$rootScope','$controller','$http',function($scope,$routeParams,$rootScope,$controller,$http){
   angular.extend(this, $controller('logCURDCtrl', {$scope: $scope}));
   $rootScope.params=$routeParams;
   handleClientLoad();
   $rootScope.content();
   $rootScope.getSheetName();
   $scope.loadChannel();
   $scope.loadActivityLog($routeParams);
   /*var
       data = [
         ['Tesla', 2017, 'black', 'black'],
         ['Nissan', 2018, 'blue', 'blue'],
         ['Chrysler', 2019, 'yellow', 'black'],
         ['Volvo', 2020, 'yellow', 'gray']
       ],
       example = document.getElementById('example1'),
       searchFiled = document.getElementById('search_field'),
       hot;

     hot = new Handsontable(example, {
       data: data,
       colHeaders: true,
       search: true
     });

     Handsontable.dom.addEvent(searchFiled, 'keyup', function (event) {
      hot.getPlugin('search').query(this.value);
      hot.render();
     });*/

 }])

 .controller('logDropshipPurchaseOrderCtrl',['$scope','$routeParams','$rootScope','$controller','$http',function($scope,$routeParams,$rootScope,$controller,$http){
   angular.extend(this, $controller('logCURDCtrl', {$scope: $scope}));
  $rootScope.params=$routeParams;
     if($routeParams.userId==undefined)
  $scope.loadPurchaseOrderContent();
    if($routeParams.userId!=undefined)
  $scope.loadUserIdContent();
 }]);


