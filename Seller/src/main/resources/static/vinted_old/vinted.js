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
                    templateUrl: 'vinted/stock.html',
                    controller: 'vintedListCtrl'
                });
                $routeProvider.when('/listing-new/:id', {
                      templateUrl: 'listing/addForm.html',
                      controller: 'ListNewCtrl'
                    });
                $routeProvider.when('/vintedStock/:id/new', {
                    templateUrl: 'listing/addForm.html',
                    controller: 'vintedNewCtrl'
                });


//        $routeProvider.when('/vintedStock/:id', {
//            templateUrl: 'vinted/vintedStock.html',
//            controller: 'vintedListCtrl'
//        });

//        $routeProvider.when('/vintedStock/:id/new', {
//            templateUrl: 'vinted/new.html',
//            controller: 'vintedNewCtrl'
//        });

            $routeProvider.when('/vintedSaveStock/:id', {
                    templateUrl: 'vinted/stock.html',
                    controller: 'vintedListCtrl',
                    params: { page: {value: '1',squash: true},pageSize: {value: '15',squash: true},totalElements: {value: '0',squash: true},filter: {value: 'MY',squash: true},q: {value: '',squash: true}}
                });
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

    .controller('vintedNewCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {

         $scope.params=$routeParams;
         if($routeParams.itemId!=null){
         var itemId;
         $scope.EbayListing={itemId:$routeParams.itemId};
         }
          angular.extend(this, $controller('ListCURDCtrl', {$scope: $scope}));


        }]).controller('ListCURDCtrl', ['$scope','$http','$routeParams','$rootScope','$uibModal', function($scope,$http,$routeParams,$rootScope,$uibModal) {
           var list = [];
           $scope.page = {totalElements: 0, currentPage: 1, pageSize: 200 };
           $scope.tableVal = [];
           $scope.stock = {sku: [], itemId: [], ean: [] };
           $rootScope.params = $routeParams.id;
           $scope.myCategory={};
           $scope.page={totalElements:0,currentPage:1,pageSize:200,loading:false};
           $scope.vintedDataList = [];
           $scope.vintedDataList1 = [];
           $scope.vintedCategory = "";


        $rootScope.showCalculator = function() {
            $rootScope.modalInstance = $uibModal.open({
                controller: 'ListCURDCtrl',
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/view1/calculator.html',
                size: 'lg'
            });
        }

        $scope.onCategoryChange = function(id) {
                $scope.selected = $scope.StockListing.category;
                $scope.vintedDataList.push($scope.selected)
                if($scope.myCategory.catalog_children_tree[$scope.selected]!=null){
                    $scope.vintedDataList1.push($scope.myCategory.catalogs[$scope.selected].title);
                    $scope.tableVal=[];
                    for (let i = 0; i < $scope.myCategory.catalog_children_tree[$scope.selected].length; i++) {
                        $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[$scope.selected][i]]);
                        }
                }else
                    return true;
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


         $scope.searchText='';

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



        }])
