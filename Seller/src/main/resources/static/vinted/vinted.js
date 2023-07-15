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
        $routeProvider.when('/vintedListing/:id', {
                    templateUrl: 'vinted/vintedListing.html',
                    controller: 'vintedListCtrl'
                });
        $routeProvider.when('/vintedStockEdit/:accountId/:id', {
            templateUrl: 'vinted/vintedStockEdit.html',
            controller: 'vintedEditCtrl',
        });

        $routeProvider.when('/vintedItemDelete/:accountId/:id', {
            templateUrl: 'vinted/vintedStock.html',
            controller: 'vintedDeleteCtrl',
            params: {page: {value: '1',squash: true},pageSize: {value: '15',squash: true},totalElements: {value: '0',squash: true},filter: {value: 'MY',squash: true},q: {value: '',squash: true}}
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
        $rootScope.params = $routeParams.id;
        $scope.tableVal = [];
        $scope.myCategory = {};
        $scope.page = {totalElements: 0,currentPage: 1,pageSize: 20,loading: false,date: '',date1: ''};
        $http.get("/api/Vinted/vintedStock/" + $scope.params.id)
            .then(function successCallback(response) {
                $scope.tableVal = response.data.results;
            })
        $rootScope.check = [];
        $scope.selected = function(obj1, obj) {
         if (obj1 == true && (obj != null && obj.length != 0))
             $rootScope.check.push(obj);
         else if (obj1 == false)
             $rootScope.check.pop(obj);
         }
         $scope.getListings=function() {
               $scope.params = $routeParams;
               $http.get("api/Vinted/getVintedListing/" + $scope.params.id+"?status=not sent")
                                       .then(function successCallback(response) {
                                     window.location.href = "#!/VintedStockListing/" + $scope.params.id;
                                     console.log("Successfully POST-ed data");
                                 }, function errorCallback(response) {
                                     window.location.href = "#!/VintedStockListing/" + $scope.params.id;
                                     console.log("POST-ing of data failed");
                                 });
                         }
        $scope.postToListings=function(obj) {
                    $scope.params = $routeParams;
                    //$http.post('https://135.181.192.92:5000/vintedStockListing/listing',obj,$scope.params.id)
                    $http.post("api/Vinted/VintedStockListing/listing/" + $scope.params.id, obj)
                        .then(function successCallback(response) {
                            window.location.href = "#!/VintedStockListing/" + $scope.params.id;
                            console.log("Successfully POST-ed data");
                        }, function errorCallback(response) {
                            window.location.href = "#!/VintedStockListing/" + $scope.params.id;
                            console.log("POST-ing of data failed");
                        });
                }
        $rootScope.showItemToListOnVinted=function(obj){
                      for (let x = 0; x < obj.length; x++) {
                                $http.post('api/Vinted/postVintedListing/'+ obj[x]+'?status=not sent',$scope.tableVal).then(function(response) {
                                      $scope.tableVal = response.data.results;
                                      //$scope.student1 =
                                      window.location.href = "#!/vintedListing/" + $scope.params.id;
                                      console.log("Successfully POST-ed data");
                                      }, function errorCallback(response) {
                                      window.location.href = "#!/vintedListing/" + $scope.params.id;
                                      console.log("POST-ing of data failed");

                           })

                           }
                           }

    }])//https://www.vinted.co.uk/items/new//https://www.vinted.co.uk/
    .controller('ListNewCtrl', ['$scope', '$http', '$routeParams', '$controller', function($scope, $http, $routeParams, $controller) {

        $scope.params = $routeParams;
        if ($routeParams.itemId != null) {
            var itemId;
            $scope.EbayListing = {
                itemId: $routeParams.itemId
            };
        }
        angular.extend(this, $controller('ListCURDCtrl', {
            $scope: $scope
        }));


    }]).controller('ListCURDCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$uibModal', function($scope, $http, $routeParams, $rootScope, $uibModal) {
        $scope.stock = {};
        $scope.page = {totalElements: 0,currentPage: 1,pageSize: 200,loading: false};
        $scope.tableVal = [];
        $scope.deleteItem = function(accountId,id){
                alert("delete!")
                }
        $rootScope.showCalculator = function() {
            $rootScope.modalInstance = $uibModal.open({
                controller: 'ListCURDCtrl',
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/view1/calculator.html',
                size: 'lg'
            });
        }
    }])
    .controller('vintedNewCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$uibModal', function($scope, $http, $rootScope, $routeParams, $uibModal) {
        var list = [];
        $scope.tableVal = [];$scope.vintedStock = {};$scope.params = $routeParams;$scope.myCategory = {};$scope.myCondition = {};$scope.content = [];
        $scope.page = {totalElements: 0,currentPage: 1,pageSize: 200,loading: false};$scope.previewImages = [];$scope.vintedDataList = [];$scope.vintedDataList1 = [];
        $scope.vintedCategory = "";$scope.vintedLastCategory = "";$scope.sizeId = "";$scope.ratingValue = "";$scope.StockListing = [];$scope.StockListing.imageUrls = [];
        $scope.files ={};
        $scope.showHiding = function() {
            var flag = false;
            for (var i = 0; i < $scope.covers.length; i++) {
                if ($scope.covers[i].coverAmount > 0) {
                    flag = true;
                    break;
                }
            }
            return flag;
        };

       $scope.SelectFile = function(event) {
         $scope.files = event.target.files;
         $scope.StockListing.imageUrls = [];
         $scope.PreviewImages = []; // Array to store preview images

         for (var i = 0; i < $scope.files.length; i++) {
           var reader = new FileReader();
           reader.onload = (function(file) {
             return function(event) {
               $scope.$apply(function() {
                 $scope.PreviewImages.push(event.target.result); // Add the preview image URL to the array
               });
             };
           })($scope.files[i]);
           reader.readAsDataURL($scope.files[i]);
           $scope.StockListing.imageUrls.push(URL.createObjectURL($scope.files[i]));
         }
       };


        $scope.onCategoryChange = function(id) {
            $scope.selected = $scope.StockListing.category;
            $scope.vintedDataList.push($scope.selected)
            if ($scope.selected == "100000" | $scope.selected == "1000000" | $scope.selected == "10000000" | $scope.selected == "100000000" | $scope.selected == "1000000000") {
                $scope.tableVal[0].parent_id;
                for (let j = 0; j < 2800; j++) {
                    if ($scope.myCategory.catalog_children_tree[j] != null) {
                        for (let i = 0; i < $scope.myCategory.catalog_children_tree[j].length; i++) {
                            //if($scope.tableVal[0].parent_id==)
                            if ($scope.tableVal[0].parent_id == $scope.myCategory.catalog_children_tree[j][i]) {
                                $scope.tableVal = [];
                                for (let k = 0; k < $scope.myCategory.catalog_children_tree[j].length; k++) {
                                    $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[j][k]]);
                                }
                                if ($scope.selected == "100000")
                                    $scope.tableVal.push({id: 1000000,title: "Back"});
                                if ($scope.selected == "1000000")
                                    $scope.tableVal.push({id: 10000000,title: "Back"});
                                if ($scope.selected == "10000000")
                                    $scope.tableVal.push({id: 100000000,title: "Back"});
                                if ($scope.selected == "100000000")
                                    $scope.tableVal.push({id: 1000000000,title: "Back"});
                                if ($scope.selected == "1000000000")
                                    $scope.tableVal.push({id: 10000000000,title: "Back"});
                                return false;
                            }
                        }
                    }

                }
            } else {
                if ($scope.myCategory.catalog_children_tree[$scope.selected] != null) {
                    $scope.vintedDataList1.push($scope.myCategory.catalogs[$scope.selected].title);
                    $scope.tableVal = [];
                    for (let i = 0; i < $scope.myCategory.catalog_children_tree[$scope.selected].length; i++) {
                        $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[$scope.selected][i]]);
                    }
                    $scope.tableVal.push({
                        id: 100000,
                        title: "Back"
                    });
                } else {
                    if ($scope.myCategory.catalogs[$scope.selected].size_field_visibility == 1) {
                        $scope.sizeId = $scope.myCategory.catalogs[$scope.selected].size_group_id;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].author_field_visibility != 0) {
                       $scope.authorValue = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].book_title_field_visibility != 0) {
                        $scope.bookTitle = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].brand_field_visibility == 1) {
                        $scope.brandValue = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].color_field_visibility != 0) {
                        $scope.colorValue = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].isbn_field_visibility != null) {
                        $scope.isbnValue = $scope.myCategory.catalogs[$scope.selected].isbn_field_visibility;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].material_field_visibility != 0) {
                        $scope.materialFieldValue = $scope.myCategory.catalogs[$scope.selected].material_group_id;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].measurements_field_visibility == true) {
                        $scope.measurementsValue = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].restricted_to_status_id != null) {
                        if ($scope.myCategory.catalogs[$scope.selected].restricted_to_status_id == 6) {
                            $scope.restrictedStatusIdValue = $scope.myCategory.catalogs[$scope.selected].title;
                        }
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].shippable == true) {
                        $scope.shippableValue = $scope.myCategory.catalogs[$scope.selected].shippable;
                    }                    //
                    if ($scope.myCategory.catalogs[$scope.selected].video_game_rating_field_visibility == 1) {
                        $scope.gameRatingValue = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                    if ($scope.myCategory.catalogs[$scope.selected].package_size_group != null) {
                        $scope.packageSizeValue = $scope.myCategory.catalogs[$scope.selected].package_size_group;
                    }
                    return true;
                }
            }
        }
//        $scope.showStatus = function(a,b,c) {
//                            if (confirm("WARNING:You are about to override stock with Active Listings.Please click ok to Continue"))
//                                $http.get('api/Vinted/listings/database?accountId=' + $routeParams.id).then(function(response) {
//                                    $scope.page.loading = true;
//                                    noty({text: 'Please wait while updating data',layout: 'topRight',type: 'success',killer: true,timeout: 2000});
//                                    if (response.data.errors)
//                                        $scope.displayError(response.data.messages);
//                                    else {
//                                        //$scope.loadStocks();
//                                        noty({text: 'Stock Updated',layout: 'topRight',type: 'success',killer: true,timeout: 2000});
//                                        $scope.page.loading = false;
//                                    }
//                                });
//                        }
        $scope.postListing = function(obj, accountId) {
            $scope.params = $routeParams;
            for (let i = 0; i < $scope.vintedDataList1.length; i++) {
                $scope.vintedLastCategory = $scope.vintedDataList1[i];
                $scope.vintedCategory = $scope.vintedCategory + "--" + $scope.vintedDataList1[i];
            }
            $scope.student = {"id": null,"itemId": obj.itemId,"url": obj.url,"category": $scope.vintedLastCategory,"tooltip": $scope.vintedCategory,"imageUrl": obj.imageUrl,"image": obj.image,"description": obj.description,"platform": obj.platform,"isbn": obj.isbn,"brand": obj.brand,"mpn": obj.mpn,"color": obj.color,"size": obj.size,"parcelSize": obj.parcelSize,"price": obj.buyItNowPriceValue,"quantity": obj.quantityAvailable,"title": obj.title,"ean": obj.ean,"conditionId": obj.conditionID,"sku": obj.sku,"rating": obj.rating,"measurements": obj.measurements,"ownerId": obj.ownerId,"accountId": accountId,"createdAt": null,"originalPriceNumeric": 0.0,"itemClosingAction": null,"modifiedDate": null,"status":"not sent"}
            if(obj.status===true){
                //$scope.student.status = "not sent";
                $http.post("/api/Vinted/post/" + $scope.params.id, $scope.student)
                            .then(function successCallback(response) {
                                //formData.imageUrls = $scope.StockListing.imageUrls;
                                window.location.href = "#!/vintedStock/" + $scope.params.id;
                                console.log("Successfully POST-ed data");
                            }, function errorCallback(response) {
                                window.location.href = "#!/vintedStock/" + $scope.params.id;
                                console.log("POST-ing of data failed");
                            });
            }else{
                $scope.student.status = "submitted";
                $http.post("/api/Vinted/post/" + $scope.params.id, $scope.student)
                            .then(function successCallback(response) {
                                //formData.imageUrls = $scope.StockListing.imageUrls;
                                window.location.href = "#!/vintedStock/" + $scope.params.id;
                                console.log("Successfully POST-ed data");
                            }, function errorCallback(response) {
                                window.location.href = "#!/vintedStock/" + $scope.params.id;
                                console.log("POST-ing of data failed");
                            });
            }

        }
        $http.get("/vintedData/" + $scope.params.id + "/category")
            .then(function successCallback(response) {
                var s = response.data.description;
                $scope.myCategory = eval(s);
                for (let i = 0; i < $scope.myCategory.catalog_children_tree[0].length; i++) {
                    $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[0][i]]);
                }
                window.location.href = "#!/vintedStock/" + $scope.params.id + "/new";
                console.log("Successfully POST-ed data");
            }, function errorCallback(response) {
                window.location.href = "#!/vintedStock/" + $scope.params.id + "/new";
                console.log("POST-ing of data failed");
            });


    }])

    .controller('vintedEditCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        $scope.action = "Edit";
        $scope.StockListing = {};
        $scope.params = $routeParams;
        $scope.vintedStock = {};
        angular.extend(this, $controller('ListCURDCtrl', {
            $scope: $scope
        }));


        $scope.onCategoryChange = function(id) {
            $scope.selected = $scope.StockListing.category;
            $scope.vintedDataList.push($scope.selected)
            if ($scope.myCategory.catalog_children_tree[$scope.selected] != null) {
                $scope.vintedDataList1.push($scope.myCategory.catalogs[$scope.selected].title);
                $scope.tableVal = [];
                for (let i = 0; i < $scope.myCategory.catalog_children_tree[$scope.selected].length; i++) {
                    $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[$scope.selected][i]]);
                }
            } else {
                if ($scope.myCategory.catalogs[$scope.selected].size_field_visibility == 1) {
                    $scope.sizeId = $scope.myCategory.catalogs[$scope.selected].size_group_id;
                }

                if ($scope.myCategory.catalogs[$scope.selected].author_field_visibility != 0) {
                    $scope.authorValue = $scope.myCategory.catalogs[$scope.selected].title;

                }
                if ($scope.myCategory.catalogs[$scope.selected].book_title_field_visibility != 0) {
                    $scope.bookTitle = $scope.myCategory.catalogs[$scope.selected].title;

                }
                if ($scope.myCategory.catalogs[$scope.selected].brand_field_visibility == 1) {
                    $scope.brandValue = $scope.myCategory.catalogs[$scope.selected].title;
                }
                if ($scope.myCategory.catalogs[$scope.selected].color_field_visibility != 0) {
                    $scope.colorValue = $scope.myCategory.catalogs[$scope.selected].title;
                }
                if ($scope.myCategory.catalogs[$scope.selected].isbn_field_visibility != null) {
                    $scope.isbnValue = $scope.myCategory.catalogs[$scope.selected].isbn_field_visibility;
                }
                if ($scope.myCategory.catalogs[$scope.selected].material_field_visibility != 0) {
                    $scope.materialFieldValue = $scope.myCategory.catalogs[$scope.selected].material_group_id;
                }

                if ($scope.myCategory.catalogs[$scope.selected].measurements_field_visibility == true) {
                    $scope.measurementsValue = $scope.myCategory.catalogs[$scope.selected].title;
                }
                if ($scope.myCategory.catalogs[$scope.selected].restricted_to_status_id != null) {
                    if ($scope.myCategory.catalogs[$scope.selected].restricted_to_status_id == 6) {
                        $scope.restrictedStatusIdValue = $scope.myCategory.catalogs[$scope.selected].title;
                    }
                }
                if ($scope.myCategory.catalogs[$scope.selected].shippable == true) {
                    $scope.shippableValue = $scope.myCategory.catalogs[$scope.selected].shippable;
                }
                if ($scope.myCategory.catalogs[$scope.selected].video_game_rating_field_visibility == 1) {
                    $scope.gameRatingValue = $scope.myCategory.catalogs[$scope.selected].title;

                }
                if ($scope.myCategory.catalogs[$scope.selected].package_size_group != null) {
                    $scope.packageSizeValue = $scope.myCategory.catalogs[$scope.selected].package_size_group;
                }

                return true;
            }
        }

        $http.get("/api/Vinted/" + ($scope.params.id))
            .then(function(response) {
                $scope.StockListing = response.data.results;
                $http.get("/vintedData/" + $scope.params.accountId + "/category")
                    .then(function successCallback(response) {
                        var s = response.data.description;
                        $scope.myCategory = eval(s);
                        for (let i = 0; i < $scope.myCategory.catalog_children_tree[0].length; i++) {
                            $scope.tableVal.push($scope.myCategory.catalogs[$scope.myCategory.catalog_children_tree[0][i]]);
                        }
                    });
                window.location.href = "#!/vintedStockEdit/" + $scope.params.accountId + "/" + $scope.params.id;
                console.log("Successfully POST-ed data");
            }, function errorCallback(response) {
                window.location.href = "#!/vintedStockEdit/" + $scope.params.accountId + "/" + $scope.params.id;
                console.log("POST-ing of data failed");
            });



    }])
    .controller('vintedDeleteCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
            $scope.action = "Delete";
            $scope.StockListing = {};
                        $scope.params = $routeParams;
                        $scope.vintedStock = {};
                        angular.extend(this, $controller('ListCURDCtrl', {
                            $scope: $scope
                        }));
            let confirmAction = confirm("Are you sure to execute this"+$scope.action+"?");
                    if (confirmAction) {
                      $http.get("/api/Vinted/vintedItemDelete/" + $scope.params.accountId+"/"+$scope.params.id)
                                      .then(function(response) {
                                          $scope.StockListing = response.data.results;

                                          window.location.href = "#!/vintedStock/" + $scope.params.accountId;
                                          console.log("Successfully POST-ed data");
                                      }, function errorCallback(response) {
                                          window.location.href = "#!/vintedStock/" + $scope.params.accountId;
                                          console.log("POST-ing of data failed");
                                      });
                    } else {
                      $http.get("/api/Vinted/vintedStock/" + $scope.params.accountId)
                                      .then(function successCallback(response) {
                                          window.location.href = "#!/vintedStock/" + $scope.params.accountId;
                                          console.log("Successfully POST-ed data");
                                      }, function errorCallback(response) {
                                          window.location.href = "#!/vintedStock/" + $scope.params.accountId;
                                          console.log("POST-ing of data failed");
                                      });
                    }
      }])
