'use strict';

var controller1 = angular.module('myApp.upload', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/upload/:id', {
        templateUrl: 'upload/upload.html',
        controller: 'labelCtrl'
    });
    $routeProvider.when('/replacements', {
            templateUrl: 'upload/replacement.html',
            controller: 'replacementCtrl'
        });
    $routeProvider.when('/list/replacementLabels', {
                templateUrl: 'upload/replacementLabels.html',
                controller: 'replacementCtrl'
            });
    $routeProvider.when('/upload/:id/:record1/:record2', {
        templateUrl: 'upload/upload.html',
        controller: 'postageLabelCtrl'
    });
    $routeProvider.when('/upload/list/:id/:record1/:record2', {
        templateUrl: 'upload/pickingList.html',
        controller: 'pickingListCtrl'
    });
}])

.controller('replacementCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        $scope.params = $routeParams;
        angular.extend(this, $controller('CURDCtrl', {
            $scope: $scope
        }));
        $scope.entireReplacements();
    }])

.controller('CURDCtrl', ['$scope','$http','$rootScope','$routeParams','$uibModal', function($scope,$http,$rootScope,$routeParams,$uibModal) {

 $rootScope.bundle=[];$rootScope.variants=[];$rootScope.labelUpdateList = [];$rootScope.printedData=[]
 $scope.page={totalElements:0,currentPage:1,pageSize:20,loading:false};var count=0;
 $rootScope.showCalculator = function() {
     $rootScope.modalInstance = $uibModal.open({
         controller: 'CURDCtrl',
         ariaLabelledBy: 'modal-title',
         ariaDescribedBy: 'modal-body',
         templateUrl: '/view1/calculator.html',
         size: 'lg'
     });
 }

 $scope.entireReplacements=function(){
          $http.get('api/awd/label/getReplacements').then(function(response) {
            $rootScope.replacements = response.data.results;
        });
 }

 $rootScope.close = function() {
             $rootScope.modalInstance.close();
         }

 $scope.saveReplacements = function() {
       $http.post("api/awd/label/saveReplacements",$scope.replacement).then(function successCallback(response) {
         $rootScope.replacements = response.data.results;
         $rootScope.modalInstance.close();
         noty({text: 'Data Saved Successfully',layout: 'topRight',type: 'success',killer: true,timeout: 3000});
         console.log("Successfully POST-ed data");
       }, function errorCallback(response) {
          $rootScope.modalInstance.close();
          noty({text: 'Data not saved',layout: 'topRight',type: 'error',killer: true,timeout: 3000})
          console.log("POST-ing of data failed");
       });
     }
  $rootScope.showReplacementPopupWindow=function(){
          $rootScope.modalInstance =  $uibModal.open({
                controller: 'replacementCtrl',
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: '/upload/enterReplacement.html',
            size: 'lg'
          });
      }

 $scope.list = function(record1, record2) {
   $rootScope.labelList=[];
     if (record2 != undefined)
         record2 = ((+record2) + 1).toString();
     if (record1 != undefined && record2 != undefined)
         $http.get('/api/awd/' + $routeParams.id + '/postage/pickingList?from=' + (record1 - 1) + "&to=" + record2).then(function(response) {
             $rootScope.labelList = response.data.results;
             if (window.location.href.indexOf("list") > -1)
                 for (var i = 0; i < response.data.results.length; i++) {
                     $scope.loadOrderContents($rootScope.labelList[i].orderRef);
                 }
             $rootScope.bundle = [];
             $rootScope.variants = [];
             $scope.page.totalElements = response.data.totalElements;
             $scope.page.firstClass= response.data.results.length;
             $scope.page.secondClass=0;
             $scope.page.loading = false;
         });
     if(window.location.href.includes('list')){
     var range=[];
     if($rootScope.labelList!=null){
     for(var i=0;i<$rootScope.labelList.length;i++)
       range.push($rootScope.labelList[i].orderRef);
     $rootScope.printed(record1,record2,'Pick',range);
     }
             }

 }

 $rootScope.first = true;
 $scope.addLabel = function(obj) {
     var exist = false;
     if (obj == "" || obj == undefined)
         noty({
             text: 'Enter Sales Record Number',
             layout: 'topRight',
             type: 'warning',
             killer: true,
             timeout: 2000
         });
     else
         $http.get('api/awd/extraLabel/' + obj + '?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize + "&accountId=" + $routeParams.id).
     then(function(response) {
         if (response.data.results[0] == undefined)
             noty({
                 text: 'Record Doesnt Exist',
                 layout: 'topRight',
                 type: 'warning',
                 killer: true,
                 timeout: 2000
             });
         else {
             for (var i = 0; i < $rootScope.labelList.length; i++)
                 if ($rootScope.labelList[i].orderRef == response.data.results[0].orderRef)
                     exist = true;
             if (exist == false && $rootScope.first != true)
                 $rootScope.labelList.push(response.data.results[0]);
             else if (exist == false && $rootScope.first == true) {
                 $rootScope.labelList = response.data.results;
                 $scope.page.totalElements = response.data.totalElements;
                 $scope.page.firstClass= $rootScope.labelList.length;
                 $scope.page.secondClass=0;
                 $rootScope.first = false;
             } else
                 noty({
                     text: 'Label Already Exist',
                     layout: 'topRight',
                     type: 'warning',
                     killer: true,
                     timeout: 2000
                 });
         }

     });
 }


 $rootScope.showPopupWindow=function(){
        $rootScope.modalInstance =  $uibModal.open({
              controller: 'StockAddCtrl',
          ariaLabelledBy: 'modal-title',
          ariaDescribedBy: 'modal-body',
          templateUrl: '/upload/popUp.html',
          size: 'lg'
        });
     $rootScope.loadPrintedData();
    }

 $rootScope.loadPrintedData=function(){
 $http.get('/api/awd/' + $routeParams.id + '/label/print/data').then(function(response) {
                 $rootScope.printedData = response.data.results;
             });
 }

 $rootScope.printedOnDate=function(obj){
  $http.get('/api/awd/label/printedData/date?accountId='+$routeParams.id+"&date="+$scope.formatDate(obj.selection)).then(function(response) {
                  $rootScope.printedData = response.data.results;
                  obj.selection=null;
              });
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

  $rootScope.printedRecord=function(obj){
    $http.get('/api/awd/label/printedData/record?accountId='+$routeParams.id+"&recordNum="+obj.selection).then(function(response) {
                    $rootScope.printedData = response.data.results;obj.selection=null;
                });
    }



 $scope.check = [];
 $scope.selected = function(obj1, obj) {
     if (obj1 == true && (obj != null && obj.length != 0))
         $scope.check.push(obj);
     else if (obj1 == false)
         $scope.check.pop(obj);
 }

 $scope.removeLabel = function(obj) {
         for (var i = 0; i < $rootScope.labelList.length; i++)
             if ($rootScope.labelList[i].orderRef == obj)
                 $rootScope.labelList.splice(i, 1);
 }

 $scope.replaceAllLabels = function(obj) {
          for (var i = 0; i < $rootScope.labelList.length; i++)
              $rootScope.labelList[i].switch=obj;
              if(obj=='FIRST_CLASS'){
                        $scope.page.firstClass=$rootScope.labelList.length;
                        $scope.page.secondClass=0;
                        }
              else{
               $scope.page.firstClass=0;
               $scope.page.secondClass=$rootScope.labelList.length;
               }
  }

 $scope.replaceLabel = function(obj) {
          for (var i = 0; i < $rootScope.labelList.length; i++)
              if ($rootScope.labelList[i].orderRef == obj)
              if($rootScope.labelList[i].switch==undefined || $rootScope.labelList[i].switch=='FIRST_CLASS'){
              $rootScope.labelList[i].switch='SECOND_CLASS';
              $scope.page.secondClass=$scope.page.secondClass+1;
              $scope.page.firstClass=$scope.page.firstClass-1;
              }
              else{
              $rootScope.labelList[i].switch='FIRST_CLASS';
              $scope.page.firstClass=$scope.page.firstClass+1;
              $scope.page.secondClass=$scope.page.secondClass-1;
              }
  }

 $scope.loadOrderContents = function(obj) {
    count=count+1; var end=0;
     $http.get('api/awd/orderContent/' + obj + '?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize + "&accountId=" + $routeParams.id).
     then(function(response) {
         end=count-1;count=count-1;
         $scope.EbayListing = response.data.results;
         var orderRef = "orderRef";
         for (var i = 0; i < response.data.results.length; i++) {
             $rootScope.bundle.push($scope.EbayListing[i]);
             if ($scope.EbayListing[i].orderSpecifics != null && $scope.EbayListing[i].orderSpecifics.length != 1){
                 if ($scope.EbayListing[i].totalOrderSpecifics.length==0 ||$scope.EbayListing[i].totalOrderSpecifics == null)
                         for (var j = 0; j < $scope.EbayListing[i].orderSpecifics.length; j++) {
                                 $scope.EbayListing[i].orderSpecifics[j][orderRef] = $scope.EbayListing[i].pOrderRef;
                                 if($scope.EbayListing[i].orderDropshipStatus!='DROPSHIPPED')
                                 $rootScope.variants.push($scope.EbayListing[i].orderSpecifics[j]);
                         }
                 else
                         for (var j = 0; j < $scope.EbayListing[i].totalOrderSpecifics.length; j++) {
                                 $scope.EbayListing[i].totalOrderSpecifics[j][orderRef] = $scope.EbayListing[i].pOrderRef;
                                 if($scope.EbayListing[i].orderDropshipStatus!='DROPSHIPPED')
                                 $rootScope.variants.push($scope.EbayListing[i].totalOrderSpecifics[j]);
                         }
                 }
         }
         if($rootScope.variants.length!=0 && ($scope.EbayListing[0].itemId=="363338093142" || $scope.EbayListing[0].itemId=="363378205968") && $scope.EbayListing.length!=1)
             $rootScope.setVariantData($rootScope.variants,$scope.EbayListing);
         if(end==0)
            noty({text: 'Bundle Variations Fetched Successfully',layout: 'topRight',type: 'success',killer: true,timeout: 5000});
     });
 }

$rootScope.setVariantData = function(obj,listingDetails) {
        $rootScope.newData = []; var orderRef=obj[0].orderRef; var loc=0;
        for (var j = 0; j <=obj.length; j++) {
                var data = {
                        name: "",
                        value: "500",
                        orderRef: 0
                };
                if (j!= 0 && j% 2 == 0 && j!=obj.length && $rootScope.newData.length% 2 == 0) {
                        data.name = "totalLineQuantity";
                        data.value = listingDetails[loc].quantity;loc=loc+1;
                        data.orderRef = orderRef;
                        $rootScope.newData.push(data);
                        j=j-1;
                }
                else if(j==obj.length){
                        data.name = "totalLineQuantity";
                        data.value = listingDetails[loc].quantity;;
                        data.orderRef = orderRef;
                        $rootScope.newData.push(data);
                }
                else {
                        data.name = obj[j].name;
                        data.value = obj[j].value[0];
                        data.orderRef = orderRef;
                        $rootScope.newData.push(data);
                }

        }
        $rootScope.variants = $rootScope.newData;
}
 $scope.addition = function(obj) {
     var a = obj + 10;
     $scope.finalRecordNumber = a;
 }

  $scope.labelPrint = function() {
      var initialRecordNum=''; var finalRecordNum=''; var range=[];
      finalRecordNum=$rootScope.labelList[0].orderRef;
      for(var i=0;i<$rootScope.labelList.length;i++)
       range.push($rootScope.labelList[i].extendedOrderId);
      if($rootScope.labelList.length!=1)
      initialRecordNum=$rootScope.labelList[$rootScope.labelList.length-1].orderRef;
      $rootScope.printed(initialRecordNum,finalRecordNum,'Print',range)
      var win = window.open('', 'printwindow');
      win.document.write('<html><head><title>Print Labels</title>');
      win.document.write('<link rel="stylesheet" href="css/theme-default.css">');
      win.document.write('<link rel="stylesheet" href="app.css">');
      win.document.write('</head><body>');
      win.document.write($("#printDiv").html());
      win.document.write('</body></html>');
      setTimeout(function() {
          win.print();
          win.close();
      }, 1000);
  };

  $scope.replacementsPrint=function(){
  var win = window.open('', 'printwindow');
        win.document.write('<html><head><title>Print Labels</title>');
        win.document.write('<link rel="stylesheet" href="css/theme-default.css">');
        win.document.write('<link rel="stylesheet" href="app.css">');
        win.document.write('</head><body>');
        win.document.write($("#printDiv").html());
        win.document.write('</body></html>');
        setTimeout(function() {
            win.print();
            win.close();
        }, 1000);
  }

  $rootScope.printed=function(initialRecordNum,finalRecordNum,selection,range){
  $http.post('/api/awd/' + $routeParams.id + '/label/Updates?initialRecordNumber='+initialRecordNum+"&finalRecordNumber="+finalRecordNum+"&selection="+selection+"&range="+range).then(function(response) {
                  $rootScope.labelUpdateList = response.data.results;
              });
  }

  $rootScope.openOrderData=function(obj){
  obj.showOrderData=true;
  }

  $rootScope.closeOrderData=function(obj){
    obj.showOrderData=false;
    }

  $scope.loadOrders = function() {
      var v = new Date();
      $scope.params = $routeParams;
      $rootScope.labelList = [];
      $http.get('/api/awd/' + $routeParams.id + '/postage?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize).then(function(response) {
          $rootScope.labelList = response.data.results;
          $scope.page.totalElements = response.data.totalElements;
          $scope.page.firstClass= response.data.results.length;
          $scope.page.secondClass=0;
          for (var i = 0; $rootScope.labelList[i] != null; i++) {
              $rootScope.labelList[i].replaceDate = v;
          }
          $scope.page.loading = false;
      });
  }


}])

.controller('labelCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$controller', function($scope, $http, $rootScope, $routeParams, $controller) {

    $scope.searchText = '';

    angular.extend(this, $controller('CURDCtrl', {
        $scope: $scope
    }));
    $scope.searchForLabel = function() {
        var v = new Date();
        $http.get('api/awd/search?q=' + $scope.searchText + "&id=" + ($routeParams.id) + "&page=" + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize).
        then(function(response) {
            $scope.labelList = response.data.results;
            $scope.page.totalElements = response.data.totalElements;
            for (var i = 0; i < response.data.totalElements; i++) {
                $scope.labelList[i].replaceDate = v;
            }
        })
    }
    $scope.loadOrders();
}])

.controller('postageLabelCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        $scope.params = $routeParams;
        angular.extend(this, $controller('CURDCtrl', {
            $scope: $scope
        }));

        $scope.list($routeParams.record1, $routeParams.record2);
    }])

.controller('pickingListCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
     angular.extend(this, $controller('CURDCtrl', {
            $scope: $scope
        }));
        $scope.params = $routeParams;
        $scope.list($routeParams.record1, $routeParams.record2);
    }]);


 /*controller1.directive('fileReader', function() {
   return {
     scope: {
       fileReader:"=",
       fileContent:"="
     },
     link: function(scope, element) {
       $(element).on('change', function(changeEvent) {
         var files = changeEvent.target.files;
         if (files.length) {
           var r = new FileReader();
           r.onload = function(e) {
               var contents = e.target.result;
               scope.$apply(function () {
                 scope.fileReader = contents;
                 var lines=contents.split("\n");

                   var result = [];

                   var headers=lines[1].split(",");
                   var prev=null;
                     for(var i=3;i<lines.length-3;i++){

                 	  var obj = {};
                 	  var currentline=lines[i].split('","');

                 	  for(var j=0;j<headers.length;j++){
                            		  obj[convertHeader(headers[j])] = convertData(currentline[j]);
                 	  }
                      if(obj.ItemTitle!='')
                 	  result.push(obj);
                 	  scope.card=result;
                 	  if(prev==null) prev=obj;
                 	  else if(obj.userId !=prev.userId) prev=obj;
                 	  else if(obj.userId ==prev.userId)
                 	  {

                 	  obj.salesRecordNumber=prev.salesRecordNumber;
                      obj.itemTitle=prev.itemTitle;
                      obj.buyerFullname=prev.buyerFullname;
                      obj.buyerAddress1=prev.buyerAddress1;
                      obj.buyerAddress2=prev.buyerAddress2;
                      obj.buyerTownCity=prev.buyerTownCity;
                      obj.buyerCounty=prev.buyerCounty;
                      obj.buyerPostcode=prev.buyerPostcode;
                      obj.buyerCountry=prev.buyerCountry;
                      obj.userId=prev.userId;

                 	  }
                   }
                   //return result; //JavaScript object
                   scope.fileContent=result;


               });
           };

           r.readAsText(files[0]);
         }
       });
     }
   };
 });
 function convertHeader(obj){
 var obj= obj.trim();
 if(obj=="Sales Record Number")
   return "salesRecordNumber";
 else if(obj=="Item Title")
   return "itemTitle";
 else if(obj=="Buyer Full name")
   return "buyerFullname";
 else if(obj=="Buyer Address 1")
   return "buyerAddress1";
 else if(obj=="Buyer Address 2")
   return "buyerAddress2";
 else if(obj=="Buyer Town/City")
   return "buyerTownCity";
 else if(obj=="Buyer County")
   return "buyerCounty";
 else if(obj=="Buyer Postcode")
   return "buyerPostcode";
 else if(obj=="Buyer Country")
    return "buyerCountry";
 else if(obj=="User Id") return "userId";
 }

 function convertData(obj){
 if(obj!=null){
 var str =obj.replace('"','');//.replace(/^"(.*)"$/, '$1');
 if(str.includes("Super")){
 return str.split("Super")[0];
 }
 else if(str.includes("Same")){
 return str.split("Same")[0];
 }
 else if(str.includes("- 1st")){
 return str.split("- 1st")[0];
 }
 else if(str.includes("- Fast")){
  return str.split("-")[0];
  }
 else
 return str;
 }
 }*/