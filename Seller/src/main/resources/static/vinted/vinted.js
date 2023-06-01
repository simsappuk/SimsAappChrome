'use strict';

var controller1 = angular.module('myApp.vinted', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/vinted/:id', {
            templateUrl: 'vinted/vinted.html',
            controller: 'vintedCtrl'
        });
        $routeProvider.when('/vintedSold/:id', {
            templateUrl: 'vinted/sold.html',
            controller: 'soldCtrl'
        });
        $routeProvider.when('/vintedDispatch/:id', {
            templateUrl: 'vinted/dispatch.html',
            controller: 'dispatchCtrl'
        });
        $routeProvider.when('/vintedUpdateDispatch/:id', {
            templateUrl: 'vinted/dispatch.html',
            controller: 'dispatchCtrl'
        });
        $routeProvider.when('/vintedStock/:id', {
            templateUrl: 'vinted/vintedStock.html',
            controller: 'vintedListCtrl'
        });
        $routeProvider.when('/listing-new/:id', {
              templateUrl: 'listing/addForm.html',
              controller: 'ListNewCtrl'
            });
        $routeProvider.when('/vintedStock/:id/new', {
            templateUrl: 'vinted/new.html',
            controller: 'vintedNewCtrl'
        });
//        $routeProvider.when('/vintedSaveStock/:id', {
//                    templateUrl: 'vinted/stock.html',
//                    controller: 'vintedListCtrl',
//                    params: { page: {value: '1',squash: true},pageSize: {value: '15',squash: true},totalElements: {value: '0',squash: true},filter: {value: 'MY',squash: true},q: {value: '',squash: true}}
//                });

    }])

    .controller('vintedCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$uibModal', function($scope, $http, $rootScope, $routeParams, $uibModal) {
        $scope.params = $routeParams;
        $scope.page = {
            totalElements: 0,
            currentPage: 1,
            pageSize: 20,
            loading: false
        };
        $rootScope.showCalculator = function() {
            $rootScope.modalInstance = $uibModal.open({
                controller: 'vintedCtrl',
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/view1/calculator.html',
                size: 'lg'
            });
        }
        $scope.loadActiveList = function() {

            $http.get('/api/Vinted/' + $scope.params.id + '/list/vinted?page=' + ($scope.page.currentPage) + "&pageSize=" + $scope.page.pageSize).
            then(function(response) {
                $scope.tableVal = response.data.results;
                $scope.page.totalElements = response.data.results.length;
                $scope.page.loading = false;
            });

        }
        $scope.loadActiveList();
    }])
    .controller('soldCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$uibModal', function($scope, $http, $rootScope, $routeParams, $uibModal) {
        $scope.params = $routeParams;
        $scope.page = {
            totalElements: 0,
            currentPage: 1,
            pageSize: 20,
            loading: false
        };
        $scope.showDispatched = false;
        $scope.dispatchCount = 0;
        $scope.dispatchIDs = "";
        $scope.selectAll1 = false;
        $scope.check = [];
        $scope.sendtoDB = function() {
            var ids = "";
            for (i = 0; i < $scope.check.length; i++) {
                if ($scope.check[i])
                    ids += $scope.tableVal[i].id + ",";
            }
            $scope.updateIDS(ids)
        }
        $scope.updateIDS = function(id) {
            $scope.page.loading = true;

            $http.get('/api/Vinted/' + $scope.params.id + '/list/vintedUpdateDispatch?data=' + id).
            then(function(response) {
                $scope.page.loading = false;
                $scope.check = [];
                $scope.init();

            });
        }
        $scope.selectedAll1 = function(a) {
            $scope.showDispatched = a;
            for (i = 0; i < $scope.tableVal.length; i++) $scope.check[i] = a;
        }
        $scope.selected = function(a) {
            var count = 0;
            for (i = 0; i < $scope.check.length; i++)
                if ($scope.check[i]) count++;
            if (count > 0) $scope.showDispatched = true;
            else $scope.showDispatched = false;
        }
        $scope.init = function() {
            $http.get('/api/Vinted/' + $scope.params.id + '/list/vintedSold').
            then(function(response) {
                $scope.tableVal = response.data.results;
                $scope.page.totalElements = response.data.results.length;
                for (i = 0; i < response.data.results.length; i++) $scope.check.push(false);
                $scope.page.loading = false;
            });
        }
        $scope.init();
    }])


    .controller('dispatchCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$uibModal', function($scope, $http, $rootScope, $routeParams, $uibModal) {
        $scope.params = $routeParams;
        $scope.page = {
            totalElements: 0,
            currentPage: 1,
            pageSize: 20,
            loading: false,
            date: '',
            date1: ''
        };

        $scope.ititData = function() {

            var now = new Date();
            var current = new Date(now.getFullYear(), now.getMonth() - 2, 1);
            if ($scope.page.date1 == '') $scope.page.date1 = now;
            if ($scope.page.date == '') $scope.page.date = current;
            console.log($scope.page.date);
            $http.get('/api/Vinted/' + $scope.params.id + '/list/vintedDispatch?startDate=' + $scope.page.date + '&endDate=' + $scope.page.date1).
            then(function(response) {
                $scope.tableVal = response.data.results;
                $scope.page.totalElements = response.data.results.length;
                // console.log(response.results)
                $scope.page.loading = false;
            });
        }
        $scope.ititData();
    }])
    .controller('vintedListCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$uibModal', function($scope, $http, $rootScope, $routeParams, $uibModal) {
        $scope.params = $routeParams;
                $scope.tableVal = [];
                $scope.myCategory={};
                $scope.page = {totalElements: 0,currentPage: 1,pageSize: 20,loading: false,date: '',date1: ''};

                $http.get("/api/Vinted/vintedStock/"+$scope.params.id)
                                            .then(function successCallback(response){
                                            $scope.tableVal = response.data.results;
                                            })

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
    .controller('vintedNewCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$uibModal', function($scope, $http, $rootScope, $routeParams, $uibModal) {
        var list = [];
        $scope.page = {totalElements: 0, currentPage: 1, pageSize: 200 };
        $scope.tableVal = [];
        $scope.stock = {sku: [], itemId: [], ean: [] };
        $scope.params = $routeParams;
        $scope.myCategory={};
        $scope.page={totalElements:0,currentPage:1,pageSize:200,loading:false};
        $scope.vintedDataList = [];
        $scope.vintedDataList1 = [];
        $scope.vintedCategory = "";
        $scope.vintedValue = "";
        $scope.onCategoryChange = function(id) {
                        $scope.selected = $scope.StockListing.category;
                        $scope.vintedDataList.push($scope.selected)
                        if($scope.myCategory.catalog_children_tree[$scope.selected]!=null){
                            $scope.vintedDataList1.push($scope.myCategory.catalogs[$scope.selected].title);
                            $scope.tableVal=[];
                            for (let i = 0; i < $scope.myCategory.catalog_children_tree[$scope.selected].length; i++) {
                                $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[$scope.selected][i]]);
                                }
                        }else {
                            $scope.tableValue=$scope.myCategory.catalogs[$scope.selected].title;
                            return true;
                            }
                     }
        $scope.postListing=function(obj,accountId){
                 $scope.params=$routeParams;
                 for(let i= 0;i<$scope.vintedDataList1.length;i++){
                    $scope.vintedCategory = $scope.vintedCategory+"-->"+$scope.vintedDataList1[i];
                 }
                 $scope.student = {"errors": false,"messages": [],"requestId": null,"results": {"id": obj.id,"itemId": obj.itemId,"url": obj.url,"category": $scope.vintedCategory,"imageUrl": obj.imageUrl,"description": obj.description,"platform": obj.platform,"genre": obj.genre,"brand": obj.brand,"mpn": obj.mpn,"colour": obj.colour,"size": obj.size,"price": obj.price,"quantity": obj.quantity,"title": obj.title,"ean": obj.ean,"condition": obj.condition,"sku": obj.sku,"ownerId": obj.ownerId,"accountId": obj.accountId,"createdAt": obj.createdAt,"originalPriceNumeric": obj.originalPriceNumeric,"itemClosingAction": obj.itemClosingAction,"modifiedDate": obj.modifiedDate },"totalElements": null,"totalPages": null  }
                 //$scope.student = {"id": obj.id,"itemId": obj.itemId,"url": obj.url,"category": obj.category,"imageUrl": obj.imageUrl,"description": obj.description,"platform": obj.platform,"genre": obj.genre,"brand": obj.brand,"mpn": obj.mpn,"colour": obj.colour,"size": obj.size,"price": obj.price,"quantity": obj.quantity,"title": obj.title,"ean": obj.ean,"condition": obj.condition,"sku": obj.sku,"ownerId": obj.ownerId,"accountId": obj.accountId,"createdAt": obj.createdAt,"originalPriceNumeric": obj.originalPriceNumeric,"itemClosingAction": obj.itemClosingAction,"modifiedDate": obj.modifiedDate }
                          $http.post("/api/Vinted/post/"+$scope.params.id,$scope.student.results)
                             .then(function successCallback(response){
                             window.location.href="#!/vintedStock/"+$scope.params.id;
                                  console.log("Successfully POST-ed data");
                             },function errorCallback(response){
                             window.location.href="#!/vintedStock/"+$scope.params.id;
                                 console.log("POST-ing of data failed");
                             });
                 }
        $http.get("/vintedData/"+$scope.params.id+"/category" )
                            .then(function successCallback(response){
                            var s =response.data.description;
                            $scope.myCategory = eval(s)
                            for (let i = 0; i < $scope.myCategory.catalog_children_tree[0].length; i++) {
                                $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[0][i]]);
                                }
                            window.location.href="#!/vintedStock/"+$scope.params.id+"/new";
                                console.log("Successfully POST-ed data");
                            }, function errorCallback(response){
                            window.location.href="#!/vintedStock/"+$scope.params.id+"/new";
                                console.log("POST-ing of data failed");
                            });


    }])