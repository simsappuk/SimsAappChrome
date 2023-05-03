'use strict';

var controller1 = angular.module('myApp.view2', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/stock/:id', {
            templateUrl: 'view2/view2.html',
            controller: 'View2Ctrl'
        });
        $routeProvider.when('/stock-new/:id', {
            templateUrl: 'view2/_form.html',
            controller: 'StockNewCtrl'
        });
        $routeProvider.when('/stock-automatic/:id', {
            templateUrl: 'view2/automaticallyAddStockForm.html',
            controller: 'StockAutomaticCtrl'
        });
        $routeProvider.when('/stock-edit/:id/:stockId', {
            templateUrl: 'view2/_form.html',
            controller: 'StockEditCtrl'
        });

        $routeProvider.when('/stock-edit/thirdParty/:id/:orderRefId/:source', {
            templateUrl: 'view2/editThirdPartyStockAdded.html',
            controller: 'EditThirdPartyStockCtrl'
        });

        $routeProvider.when('/stock-history/:id', {
            templateUrl: 'view2/history.html',
            controller: 'StockHistoryCtrl'
        });
        $routeProvider.when('/limitedStock/:id', {
            templateUrl: 'view2/limitedStock.html',
            controller: 'LimitedStockCtrl'
        });
        $routeProvider.when('/stock-add', {
            templateUrl: 'view2/add.html',
            controller: 'StockAddCtrl'
        });
        $routeProvider.when('/stock-log/:id/:logId', {
            templateUrl: 'view2/list.html',
            controller: 'StockLogCtrl'
        });
        $routeProvider.when('/stock-log/:id/Facebook/Data', {
            templateUrl: 'view2/list.html',
            controller: 'StockLogRespCtrl'
        });
        $routeProvider.when('/stock-log/:id/CEX/Data', {
            templateUrl: 'view2/list.html',
            controller: 'StockLogCexRespCtrl'
        });
        $routeProvider.when('/stock/:id/thirdPartyStock/Data', {
            templateUrl: 'view2/thirdPartyStock.html',
            controller: 'StockThirdPartyCtrl'
        });
        $routeProvider.when('/stock/:id/listing/thirdPartyStock/eBay', {
            templateUrl: 'view2/listFacebook.html',
            controller: 'ListThirdPartyStockCtrl'
        });

    }])

    .controller('StockThirdPartyCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', '$http', function($scope, $routeParams, $rootScope, $controller, $http) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $rootScope.showThirdPartyStock();
    }])

    .controller('ListThirdPartyStockCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', '$http', function($scope, $routeParams, $rootScope, $controller, $http) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $rootScope.showListThirdPartyStock();
    }])
    .controller('EditThirdPartyStockCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', '$http', function($scope, $routeParams, $rootScope, $controller, $http) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $scope.updateThirdPartyStock = function() {
            $http.get('api/stock/updateThirdPartyStock/added?accountId=' + $routeParams.id + "&orderRefId=" + $routeParams.orderRefId + "&source=" + $routeParams.source).
            then(function(response) {
                $rootScope.consoleData = response.data.results;
            });
        }
        $scope.updateThirdPartyStock();
    }])


    .controller('StockLogRespCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        var data = {};
        var reset = [];
        noty({
            text: 'Data Fetched Successfully Please wait for a while',
            layout: 'topRight',
            type: 'success',
            killer: true,
            timeout: 7000
        });
        if (facebook[facebook.length - 1][1] == "Date")
            reset = facebook.reverse();
        else
            reset = facebook;
        for (var i = 1; i < reset.length; i++) {
            for (var j = 0; j < reset[i].length; j++) {
                data[reset[0][j].replace(/ /g, '')] = reset[i][j];
            }
            $rootScope.checkThirdPartyStock(data);
            data = {};
        }
    }])

    .controller('StockLogCexRespCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        var cexData = {};
        noty({
            text: 'Data Fetched Successful',
            layout: 'topRight',
            type: 'success',
            killer: true,
            timeout: 3000
        });
        for (var i = 1; i < cex.length; i++) {
            for (var j = 0; j < cex[i].length; j++) {
                cexData[cex[0][j].replace(/ /g, '')] = cex[i][j];
            }
            $rootScope.checkCexThirdPartyStock(cexData);
            cexData = {};
        }
    }])

    .controller('StockLogCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', function($scope, $routeParams, $rootScope, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        noty({
            text: 'Please Stay on the page while we fetch Gdrive details',
            layout: 'topRight',
            type: 'warning',
            killer: true,
            timeout: 7000
        });
        handleClientLoad();
    }])

    .controller('StockModalCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', '$http', function($scope, $routeParams, $rootScope, $controller, $http) {}])


    .controller('StockAddCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', function($scope, $routeParams, $rootScope, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
    }])

    .controller('LimitedStockCtrl', ['$scope', '$routeParams', '$rootScope', '$controller', function($scope, $routeParams, $rootScope, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $scope.limitedStock();
    }])

    .controller('StockNewCtrl', ['$scope', '$http', '$controller', function($scope, $http, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
    }])

    .controller('StockAutomaticCtrl', ['$scope', '$http', '$rootScope', '$controller', function($scope, $http, $rootScope, $controller) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
    }])

    .controller('StockCURDCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$uibModal', function($scope, $http, $routeParams, $rootScope, $uibModal) {
                $scope.stock = {};
                var list = [];
                $scope.page = {
                    totalElements: 0,
                    currentPage: 1,
                    pageSize: 200
                };
                $scope.tableVal = [];
                $scope.stock = {
                    sku: [],
                    itemId: [],
                    ean: []
                };
                $rootScope.params = $routeParams.id;

                $rootScope.redirect = function() {
                    window.location.href = "https://simsapp.co.uk/#!/stock-log/" + $routeParams.id + "/Facebook/Data";
                }

                $rootScope.reviseItem = function(obj) {
                    $http.post("api/stock/revise/ListItem/manually/", obj)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                            },
                            function errorCallback(response) {
                                noty({
                                    text: response,
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });

                }

                $scope.fetchAndAddToStock = function(obj) {
                    $http.get('api/stock/addStock/automatic?itemList=' + obj.itemId + "&accountId=" + $routeParams.id + "&sku=" + obj.sku)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 8000
                                });
                                window.location.href = "#!/stock/" + $routeParams.id;
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 8000
                                });
                            });
                }

                $rootScope.showListThirdPartyStock = function() {
                    $http.get('api/stock/listThirdPartyStock/ebay?accountId=' + $routeParams.id).
                    then(function(response) {
                        $rootScope.consoleData = response.data.results;
                        $scope.page.totalElements = response.data.totalElements;
                    });
                }

                $rootScope.showThirdPartyStock = function() {
                    $http.get('api/stock/showThirdPartyData/console?accountId=' + $routeParams.id).
                    then(function(response) {
                        $rootScope.consoleData = response.data.results;
                        $scope.page.totalElements = response.data.totalElements;
                    });
                }

                $rootScope.deleteStockByAccountId = function() {
                    $http.get('api/stock/entireData/delete/' + $routeParams.id)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });
                }

                $rootScope.showCalculator = function() {
                    $rootScope.modalInstance = $uibModal.open({
                        controller: 'StockCURDCtrl',
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: '/view1/calculator.html',
                        size: 'lg'
                    });
                }

                $rootScope.checkThirdPartyStock = function(data) {
                    $http.get('api/stock/checkThirdPartyData/console?accountId=' + $routeParams.id + "&orderId=" + data.OrderNo).
                    then(function(response) {
                        data.OrderNo = parseInt(data.OrderNo);
                        if (response.data.results.length == 0 && (/^\d+$/.test(data.OrderNo) || data.OrderNo == '') && data.Item != null)
                            list.push(data);
                        else if (response.data.results.length != 0) {
                            data['added'] = "true";
                            list.push(data);
                        }
                        $rootScope.facebook = list;
                    });
                }
                $rootScope.checkCexThirdPartyStock = function(cexData) {
                    $http.get('api/stock/checkThirdPartyData/console?accountId=' + $routeParams.id + "&orderId=" + cexData.OrderNo).
                    then(function(response) {
                        cexData.OrderNo = parseInt(cexData.OrderNo);
                        if (response.cexData.results.length == 0 && (/^\d+$/.test(cexData.OrderNo) || cexData.OrderNo == '') && cexData.Item != null)
                            list.push(cexData);
                        else if (response.cexData.results.length != 0) {
                            data['added'] = "true";
                            list.push(cexData);
                        }
                        $rootScope.cex = list;
                    });
                }




                $rootScope.getStock = function(obj) {
                    $http.get('api/stock/sku?sku=' + obj.sku + "&accountId=" + $routeParams.id).then(function(response) {
                        if (response.data.results != null) {
                            for (var i = 0; i < $rootScope.consoleData.length; i++) {
                                if (obj.lineId == $rootScope.consoleData[i].lineId) {
                                    $rootScope.consoleData[i] = response.data.results[0];
                                    $rootScope.consoleData[i].sku = obj.sku;
                                    $rootScope.consoleData[i].id = null;
                                    $rootScope.consoleData[i].entireTitle = obj.entireTitle;
                                    $rootScope.consoleData[i].orderRefId = obj.orderRefId;
                                    $rootScope.consoleData[i].stockCode = obj.stockCode;
                                    $rootScope.consoleData[i].lineId = obj.lineId;
                                    break;
                                }
                            }
                        }
                        if (response.data.results == undefined) {
                            for (var i = 0; i < $rootScope.consoleData.length; i++) {
                                if (obj.lineId == $rootScope.consoleData[i].lineId) {
                                    $rootScope.consoleData[i].title = "";
                                    $rootScope.consoleData[i].itemCategory = null;
                                    $rootScope.consoleData[i].itemSubCategory = null;
                                    $rootScope.consoleData[i].itemCondition = null;
                                    $rootScope.consoleData[i].ean = [];
                                    $rootScope.consoleData[i].itemId = [];
                                    $rootScope.consoleData[i].buyItNowPrice = 0;
                                    $rootScope.consoleData[i].quantityAvailable = 0;
                                    $rootScope.consoleData[i].accountId = $routeParams.id;
                                    $rootScope.consoleData[i].ownerId = "1";
                                    $rootScope.consoleData[i].id = null;
                                    break;
                                }
                            }
                            obj.OrderNo = obj.OrderNo.toString();

                        }
                    })
                }


                $rootScope.pushId = function(obj) {
                    if (obj.id == null || obj.id == undefined) {
                        $scope.stock.orderRefId = obj.OrderNo;
                        $scope.stock.createdDate = obj.Date;
                        $scope.stock.title = obj.Item;
                        $scope.stock.entireTitle = obj.Item;
                        $scope.stock.paypalEmailId = obj.Paypalemail;
                        $scope.stock.stockSource = obj.stockCode;
                    } else {
                        $scope.stock.orderRefId = obj.extendedOrderId;
                        $scope.stock.createdDate = obj.createdDate;
                        $scope.stock.title = obj.title;
                        $scope.stock.entireTitle = obj.title;
                        $scope.stock.buyItNowPrice = obj.totalAmount;
                        $scope.stock.stockSource = obj.stockCode;
                    }
                    $rootScope.showAddedItem($scope.stock);
                }

                $rootScope.data = [];
                $rootScope.showAddedItem = function(obj) {
                    var length = $rootScope.data.length;
                    if (obj.quantityAvailable == undefined)
                        obj.quantityAvailable = 1;
                    for (var i = 0; i <= length; i++)
                        if ($rootScope.data[i] != undefined && $rootScope.data[i].stockCode == obj.stockCode && obj.itemCategory == 'games' && obj.ean != undefined && obj.ean == $rootScope.data[i].ean) {
                            $rootScope.data[i].quantityAvailable = $rootScope.data[i].quantityAvailable + 1;
                            break;
                        }
                    else if ($rootScope.data[i] != undefined && $rootScope.data[i].stockCode == obj.stockCode && obj.itemCategory != 'games' && obj.title == $rootScope.data[i].title && obj.modelName == $rootScope.data[i].modelName) {
                        $rootScope.data[i].quantityAvailable = $rootScope.data[i].quantityAvailable + 1;
                        break;
                    } else if ($rootScope.data[i] == undefined || ($rootScope.data[i] != undefined && i == length)) {
                        if (obj.buyItNowPrice == null || obj.buyItNowPrice == undefined)
                            obj.buyItNowPrice = 0.00;
                        obj.sku = ['SKU EDITABLE'];
                        obj.lineId = $rootScope.data.length;
                        $rootScope.data.push(obj);
                    }
                    $rootScope.showConsoleAddedData(obj.stockCode);
                }

                $rootScope.saveChanges = function(obj) {
                    for (var i = 0; i < $rootScope.data.length; i++) {
                        if (obj.lineId == $rootScope.data[i].lineId)
                            $rootScope.data[i] = obj;
                    }
                }

                $rootScope.updateAddedStock = function(obj, position) {
                    $http.post('api/stock/updateAddedStock/thirdParty?accountId=' + $routeParams.id + "&position=" + position, obj)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });
                }

                $rootScope.removeAddedItem = function(obj) {
                    for (var i = 0; i < $rootScope.consoleData.length; i++) {
                        if (obj.lineId == $rootScope.consoleData[i].lineId) {
                            $rootScope.consoleData.splice(i, 1);
                            break;
                        }
                    }
                }

                $rootScope.removeFromListIt = function(obj) {
                    $http.get('api/stock/remove/ListIt/stock?id=' + obj.id).then(function(response) {
                        $rootScope.showListThirdPartyStock();
                    });
                }

                $rootScope.removeFromStockAddedList = function(obj) {
                    $http.get('api/stock/remove/ListIt/stock?id=' + obj.id).then(function(response) {
                        $rootScope.showThirdPartyStockInModal();
                    });
                }

                $rootScope.removeFromCompleteStockAddedList = function(obj) {
                    $http.get('api/stock/remove/ListIt/stock?id=' + obj.id).then(function(response) {
                        $rootScope.showThirdPartyStock();
                    });
                }

                $rootScope.removeFromStock = function(obj) {
                    var id = "";
                    if (obj.id != undefined)
                        id = obj.id;
                    else
                        id = obj;
                    $http.get('api/stock/removeThirdPartyData/stock?id=' + id + "&accountId=" + $routeParams.id).then(function(response) {
                        $rootScope.consoleData = response.data.results;
                        $scope.page.totalElements = response.data.totalElements;
                        $rootScope.check = [];
                    });
                }

                $rootScope.listItem = function(obj) {
                    $http.post('api/stock/listFacebookItem/save?id=' + obj + "&accountId=" + $routeParams.id)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });
                }

                $rootScope.saveThirdPartyItemToRevise = function(obj) {
                    obj.id = null;
                    obj.createdDate = null;
                    $http.post("api/stock/thirdPartyItemToRevise/revise/", obj)
                        .then(function successCallback(response) {
                        if(obj.stockCode=='Facebook')
                                $rootScope.callFacebookExtension();
                                else
                                $rootScope.callEbayListingExtension();
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });

                    $rootScope.modalInstance.close();
                }

                $rootScope.callFacebookExtension = function() {
                    window.open("https://bulksell.ebay.co.uk/ws/eBayISAPI.dll?SingleList&sellingMode=SellSimilarItem&lineID=114245247845");
                }

                $rootScope.callEbayListingExtension = function() {
                     window.open("https://bulksell.ebay.co.uk/ws/eBayISAPI.dll?SingleList&sellingMode=SellSimilarItem&lineID=115217895585");
                }


                $rootScope.removeDuplicates = function() {
                    $http.post('api/stock/remove/duplicates?accountId=' + $routeParams.id)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Removed Successfully',
                                    layout: 'topRight',
                                    type: 'success'
                                });
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error'
                                });
                            });
                }

                $rootScope.addMoreConsoleItems = function() {
                    $scope.saveHere = $rootScope.consoleData[$rootScope.consoleData.length - 1];
                    $scope.addObject = [{
                        buyItNowPrice: 0,
                        createdDate: $scope.saveHere.createdDate,
                        itemCategory: $scope.saveHere.itemCategory,
                        itemCondition: $scope.saveHere.itemCondition,
                        lineId: $rootScope.consoleData.length + 1,
                        modelName: $scope.saveHere.modelName,
                        orderRefId: $scope.saveHere.orderRefId,
                        paypalEmailId: $scope.saveHere.paypalEmailId,
                        quantityAvailable: $scope.saveHere.quantityAvailable,
                        sku: ["SKU-EDITABLE"],
                        ean: [],
                        stockCode: "Facebook",
                        stockSource: undefined,
                        title: "Title Editable",
                        accountId: $scope.saveHere.accountId,
                        entireTitle: $scope.saveHere.entireTitle
                    }];
                    $rootScope.consoleData.push($scope.addObject[0]);
                }


                $rootScope.removeConsoleItems = function(obj) {
                    for (var i = 0; i < $rootScope.data.length; i++) {
                        if (obj = $rootScope.data[i].stockCode)
                            $rootScope.data.splice(i, 1);
                    }
                }


                $rootScope.showTableOnClear = function(obj) {
                    if (obj.OrderNo == null || obj.OrderNo == undefined) {
                        $scope.stock.orderNumber = null;
                        $rootScope.check = [];
                    }
                }

                $rootScope.saveToStock = function() {
                    var skuList = [];
                    var stockSource = $rootScope.consoleData[0].stockCode;
                    for (var i = 0; i < $rootScope.consoleData.length; i++) {
                        if (!Array.isArray($rootScope.consoleData[i].sku)) {
                            skuList.push($rootScope.consoleData[i].sku);
                            $rootScope.consoleData[i].sku = skuList;
                            skuList = [];
                        }
                        if ($rootScope.consoleData[i].newQty != undefined)
                            $rootScope.consoleData[i].quantityAvailable = $rootScope.consoleData[i].newQty;
                        if ($rootScope.consoleData[i].newPrice != undefined)
                            $rootScope.consoleData[i].buyItNowPrice = $rootScope.consoleData[i].newPrice;
                        var date = new Date($rootScope.consoleData[i].createdDate);
                        $rootScope.consoleData[i].createdDate = date;
                    }
                    $http.post("api/stock/setThirdPartyData/save/" + $routeParams.id, $rootScope.consoleData)
                        .then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                                $rootScope.consoleData = [];
                                $rootScope.removeConsoleItems(stockSource);
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });
                    $scope.stock = null;
                }

                $rootScope.getSelectedData = function(obj) {
                    $http.get("api/orders/getThirdPartyData/response/" + $routeParams.id + '/' + obj).then(function(response) {
                        $rootScope.orderInfo = response.data.results;
                        $rootScope.showConsoleAddedData(obj);
                    });
                }

                $rootScope.showConsoleAddedData = function(obj) {
                    $rootScope.consoleData = [];
                    for (var i = 0; i < $rootScope.data.length; i++) {
                        if ($rootScope.data[i] != undefined && $rootScope.data[i].stockCode != undefined && $rootScope.data[i].stockCode == obj)
                            $rootScope.consoleData.push($rootScope.data[i]);
                    }
                }

                $scope.updateStockQuantity = function(obj) {
                    if (obj.quantity == "undefined")
                        obj.quantity = 1;
                    if (obj.ean != "undefined" && obj.ean != "") {
                        $http.get('api/stock/updateStockQuantity?ean=' + obj.ean + "&quantity=" + obj.quantity + "&condition=" + obj.itemCondition + "&accountId=" + $routeParams.id).then(function(response) {
                            $scope.stock.description = response.data.results.description;
                            $scope.stock.ean = null;
                        })
                    }
                }

                $rootScope.showPopup = function() {
                    $rootScope.modalInstance = $uibModal.open({
                        controller: 'StockAddCtrl',
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: '/view2/add.html',
                        size: 'lg'
                    });
                }


                $rootScope.showLogPopup = function() {
                    $rootScope.modalInstance = $uibModal.open({
                        controller: 'StockModalCtrl',
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: '/view2/modal.html',
                        size: 'lg'
                    });
                }

                $rootScope.showRevisePopup = function(obj) {
                    if(obj.stockCode!='eBay'||obj.stockCode==undefined||obj.stockCode==null)
                       obj.stockCode='Facebook';
                    $rootScope.modalInstance = $uibModal.open({
                        controller: 'StockModalCtrl',
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: '/view2/revisePopup.html',
                        size: 'lg'
                    });
                    $rootScope.reviseContent = obj;
                }

                $rootScope.showItemToListOnEbay=function(obj){
                   $http.get('api/stock/'+ obj).then(function(response) {
                                           response.data.results.stockCode='eBay';
                                           $rootScope.showRevisePopup(response.data.results);
                                       })

                }

                var selectedHref = "";
                var name = [];
                $rootScope.categoryContent = function(obj, console) {
                    if (window.location.href.includes("stock-log"))
                        selectedHref = "stockLog";
                    else
                        selectedHref = "category";
                    if (obj == undefined || obj == "")
                        alert("Please Enter Model Name");
                    else {
                        $http.get('/api/category/content/save?selection=' + selectedHref + "&category=" + obj + "&console=" + console).then(function(response) {
                            for (var i = 0; i < response.data.results.length; i++)
                                name[i] = response.data.results[i].category;
                            $rootScope.names = name;
                            noty({
                                text: 'Saved Successfully',
                                layout: 'topRight',
                                type: 'success',
                                killer: true,
                                timeout: 3000
                            });
                        });
                    }
                }

                $rootScope.submit = function(obj) {
                    $http.get('api/orders/?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize + "&accountId=" + $routeParams.id + "&channelId=" + obj).
                    then(function(response) {
                        $scope.tableVal = response.data.results;
                        $scope.page.totalElements = response.data.totalElements;
                    });

                }

                $rootScope.close = function() {
                    $rootScope.modalInstance.close();
                }

                $scope.AddSku = function(obj) {
                    $http.get('api/stock/sku?sku=' + obj + "&accountId=" + $routeParams.id).then(function(response) {
                        var i = 0;
                        if (response.data == "" && obj != undefined && obj != "") {
                            if ($scope.stock.sku.length == 0 || $scope.stock.sku.indexOf(obj) == -1)
                                $scope.stock.sku.push(obj);
                            else
                                noty({
                                    text: 'Sku exist in the Field',
                                    layout: 'topRight',
                                    type: 'warning',
                                    killer: true,
                                    timeout: 2000
                                });

                        } else if (obj == undefined || obj == "")
                            noty({
                                text: 'PLEASE ENTER SKU',
                                layout: 'topRight',
                                type: 'warning',
                                killer: true,
                                timeout: 2000
                            });

                        else
                            noty({
                                text: 'SKU already Exist',
                                layout: 'topRight',
                                type: 'warning',
                                killer: true,
                                timeout: 2000
                            });

                    })
                }

                $scope.AddItemId = function(obj) {
                    $http.get('api/stock/itemId/existence?itemId=' + obj + "&accountId=" + $routeParams.id).then(function(response) {
                        var i = 0;
                        if (response.data == "" && obj != undefined && obj != "") {
                            if ($scope.stock.itemId.length == 0 || $scope.stock.itemId.indexOf(obj) == -1)
                                $scope.stock.itemId.push(obj);
                            else
                                noty({
                                    text: 'ItemID exist in the Field',
                                    layout: 'topRight',
                                    type: 'warning',
                                    killer: true,
                                    timeout: 2000
                                });

                        } else if (obj == undefined || obj == "")
                            noty({
                                text: 'PLEASE ENTER ITEM ID',
                                layout: 'topRight',
                                type: 'warning',
                                killer: true,
                                timeout: 2000
                            });

                        else
                            noty({
                                text: 'Item ID already Exist',
                                layout: 'topRight',
                                type: 'warning',
                                killer: true,
                                timeout: 2000
                            });

                    })
                }

                $scope.AddEan = function(obj) {
                    $http.get('api/stock/ean/existence?ean=' + obj + "&accountId=" + $routeParams.id).then(function(response) {
                        var i = 0;
                        if (response.data == "" && obj != undefined && obj != "") {
                            if ($scope.stock.ean.length == 0 || $scope.stock.ean.indexOf(obj) == -1)
                                $scope.stock.ean.push(obj);
                            else
                                noty({
                                    text: 'EAN exist in the Field',
                                    layout: 'topRight',
                                    type: 'warning',
                                    killer: true,
                                    timeout: 2000
                                });

                        } else if (obj == undefined || obj == "")
                            noty({
                                text: 'PLEASE ENTER EAN',
                                layout: 'topRight',
                                type: 'warning',
                                killer: true,
                                timeout: 2000
                            });

                        else
                            noty({
                                text: 'EAN already Exist',
                                layout: 'topRight',
                                type: 'warning',
                                killer: true,
                                timeout: 2000
                            });

                    })
                }

                $scope.replaceSku = function(obj) {
                    $scope.editSKU = false;
                    $http.get('api/stock/sku?sku=' + obj + "&accountId=" + $routeParams.id).then(function(response) {
                        if (response.data == "" || response.data.id == $scope.stock.id && response.data.results == null)
                            $scope.stock.sku.push(obj);
                        $scope.sku = "";
                    })
                }

                $scope.replaceItemId = function(obj) {
                    $scope.editItemId = false;
                    $http.get('api/stock/itemId/existence?itemId=' + obj + "&accountId=" + $routeParams.id).then(function(response) {
                        if (response.data == "" || response.data.id == $scope.stock.id && response.data.results == null)
                            $scope.stock.itemId.push(obj);
                        $scope.itemId = "";
                    })
                }


                $scope.replaceEan = function(obj) {
                    $scope.editEan = false;
                    $http.get('api/stock/ean/existence?ean=' + obj + "&accountId=" + $routeParams.id).then(function(response) {
                        if (response.data == "" || response.data.id == $scope.stock.id && response.data.results == null)
                            $scope.stock.ean.push(obj);
                        $scope.ean = "";
                    })
                }

                $scope.StockInLimits = function(obj) {
                    if (obj == undefined)
                        obj = 5;
                    $http.get('api/stock/StockInLimit/' + $routeParams.id + '?quantityAvailable=' + obj + "&page=" + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize).then(function(response) {
                        $scope.tableVal = response.data.results.content;
                        $scope.page.totalElements = response.data.totalElements;
                    })
                }

                $scope.limitedStock = function() {
                    $http.get('api/stock/limitedStock/' + $routeParams.id).then(function(response) {
                        $scope.tableVal = response.data.results;
                        $scope.page.totalElements = response.data.totalElements;
                    })
                }

                $scope.setMinLimit = function(obj, value) {
                    $scope.params = $routeParams;
                    if (obj.length == 0 || obj == undefined)
                        noty({
                            text: 'Please Select an Item To Limit',
                            layout: 'topRight',
                            type: 'warning',
                            killer: true,
                            timeout: 2000
                        });
                    else {
                        $http.post("/api/stock/setLimit?stockId=" + obj + "&limit=" + value + "&accountId=" + $scope.params.id)
                            .then(function successCallback(response) {
                                noty({
                                    text: 'Successful action',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                                window.location.href = "#!/stock/" + $scope.params.id;
                                console.log("Successfully POST-ed data");
                            }, function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                                window.location.href = "#!/stock/" + $scope.params.id;
                                console.log("POST-ing of data failed");
                            });
                    }
                }

                $scope.saveSku = function(obj) {
                    $scope.params = $routeParams;
                    if (obj.sku != "") {
                        $http.post('api/stock/saveSku?stockId=' + obj.id + '&sku=' + obj.sku + '&accountId=' + $scope.params.id)
                            .then(function successCallback(response) {
                                if (response.data.errors && response.data.messages.length != 0)
                                    noty({
                                        text: 'Sku was assigned to another Item',
                                        layout: 'topRight',
                                        type: 'warning',
                                        killer: true,
                                        timeout: 2000
                                    });
                                else if (response.data.errors)
                                    noty({
                                        text: 'There was an error,Please try updating others or try after clicking the refresh icon',
                                        layout: 'topRight',
                                        type: 'error',
                                        killer: true,
                                        timeout: 5000
                                    });
                                else
                                    noty({
                                        text: 'Update Successful',
                                        layout: 'topRight',
                                        type: 'success',
                                        killer: true,
                                        timeout: 2000
                                    });
                                window.location.href = "#!/stock/" + $scope.params.id;;
                            }, function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                                window.location.href = "#!/stock/" + $scope.params.id;;
                                console.log("POST-ing of data failed");
                            });
                    }
                }

                $scope.pushSku = function(obj) {
                    if (!$scope.editSKU) {
                        $scope.editSKU = true;
                        $scope.sku = (obj);
                        var index = $scope.stock.sku.indexOf(obj);
                        if (index !== -1) {
                            $scope.stock.sku.splice(index, 1);

                        }
                    }
                }

                $scope.pushItemId = function(obj) {
                    if (!$scope.editItemId) {
                        $scope.editItemId = true;
                        $scope.itemId = (obj);
                        var index = $scope.stock.itemId.indexOf(obj);
                        if (index !== -1) {
                            $scope.stock.itemId.splice(index, 1);

                        }
                    }
                }

                $scope.pushEan = function(obj) {
                    if (!$scope.editEan) {
                        $scope.editEan = true;
                        $scope.ean = (obj);
                        var index = $scope.stock.ean.indexOf(obj);
                        if (index !== -1) {
                            $scope.stock.ean.splice(index, 1);

                        }
                    }
                }

                $scope.getListings = function() {
                    if (confirm("WARNING:You are about to override stock with Active Listings.Please click ok to Continue"))
                        $http.get('api/stock/listings/database?accountId=' + $routeParams.id).then(function(response) {
                            $scope.page.loading = true;
                            noty({
                                text: 'Please wait while updating data',
                                layout: 'topRight',
                                type: 'success',
                                killer: true,
                                timeout: 2000
                            });
                            if (response.data.errors)
                                $scope.displayError(response.data.messages);
                            else {
                                $scope.loadStocks();
                                noty({
                                    text: 'Stock Updated',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                                $scope.page.loading = false;
                            }
                        });
                }

                $rootScope.check = [];
                $scope.selected = function(obj1, obj) {
                    if (obj1 == true && (obj != null && obj.length != 0))
                        $rootScope.check.push(obj);
                    else if (obj1 == false)
                        $rootScope.check.pop(obj);
                }

                $scope.deleteItemFromStock = function(obj) {
                    if (obj.length == 0 || obj == undefined)
                        noty({
                            text: 'Please Select an Item To Delete',
                            layout: 'topRight',
                            type: 'warning',
                            killer: true,
                            timeout: 2000
                        });
                    else {
                        $rootScope.check = [];
                        $http.post('api/stock/delete/item/' + obj + '/' + $routeParams.id).then(function successCallback(response) {
                            noty({
                                text: 'Deleted Successfully',
                                layout: 'topRight',
                                type: 'success',
                                killer: true,
                                timeout: 2000
                            });
                            $scope.loadStocks();
                        });
                    }
                }

                $scope.attachToAwaitingDispatch = function(obj) {
                    if (obj.length == 0 || obj == undefined)
                        noty({
                            text: 'Please Select an Item To Delete',
                            layout: 'topRight',
                            type: 'warning',
                            killer: true,
                            timeout: 2000
                        });
                    else {
                        $rootScope.check = [];
                        $http.get('api/stock/attach/item/toAwaitingDispatch/' + obj + '/' + $routeParams.id).then(function successCallback(response) {
                                noty({
                                    text: 'Update Successful',
                                    layout: 'topRight',
                                    type: 'success',
                                    killer: true,
                                    timeout: 2000
                                });
                            },
                            function errorCallback(response) {
                                noty({
                                    text: 'There was an error',
                                    layout: 'topRight',
                                    type: 'error',
                                    killer: true,
                                    timeout: 5000
                                });
                            });
                        $scope.loadStocks();
                        }
        }


        $scope.save = function(obj) {
            $scope.params = $routeParams;
            $http.post("/api/stock?accountId=" + $scope.params.id, obj)
                .then(function successCallback(response) {
                    noty({
                        text: 'Update Successful',
                        layout: 'topRight',
                        type: 'success',
                        killer: true,
                        timeout: 2000
                    });
                    window.location.href = "#!/stock/" + $scope.params.id;
                    console.log("Successfully POST-ed data");
                }, function errorCallback(response) {
                    noty({
                        text: 'There was an error',
                        layout: 'topRight',
                        type: 'error',
                        killer: true,
                        timeout: 5000
                    });
                    window.location.href = "#!/stock/" + $scope.params.id;
                    console.log("POST-ing of data failed");
                });
        }

        $scope.updateListDetails = function(obj) {
            $scope.params = $routeParams;
            $http.post("/api/stock?accountId=" + $scope.params.id, obj)
                .then(function successCallback(response) {
                    noty({
                        text: 'Update Successful',
                        layout: 'topRight',
                        type: 'success',
                        killer: true,
                        timeout: 2000
                    });
                    window.location.href = "#!/stock/" + $scope.params.id + '/listing/thirdPartyStock/eBay';
                    console.log("Successfully POST-ed data");
                }, function errorCallback(response) {
                    noty({
                        text: 'There was an error',
                        layout: 'topRight',
                        type: 'error',
                        killer: true,
                        timeout: 5000
                    });
                    window.location.href = "#!/stock/" + $scope.params.id + '/listing/thirdPartyStock/eBay';
                    console.log("POST-ing of data failed");
                });
        }



        $scope.saveLimitedStock = function(obj) {
            $scope.params = $routeParams;
            $http.post("/api/stock?accountId=" + $scope.params.id, obj)
                .then(function successCallback(response) {
                    noty({
                        text: 'Update Successful',
                        layout: 'topRight',
                        type: 'success',
                        killer: true,
                        timeout: 2000
                    });
                    $scope.limitedStock();
                    console.log("Successfully POST-ed data");
                }, function errorCallback(response) {
                    noty({
                        text: 'There was an error',
                        layout: 'topRight',
                        type: 'error',
                        killer: true,
                        timeout: 5000
                    });
                    $scope.limitedStock();
                    console.log("POST-ing of data failed");
                });
        }

        $scope.loadStock = function(obj) {
            $http.get('api/stock/' + (obj)).
            then(function(response) {
                $scope.stock = response.data.results;
                $rootScope.getCategories("category");
                // $scope.page.currentPage=response.data.totalPages;

            });
        }

        $rootScope.getCategories = function(obj, console) {
            $http.get('api/category/content?selection=' + obj + "&console=" + console).then(function(response) {
                name = [];
                for (var i = 0; i < response.data.results.length; i++)
                    name[i] = response.data.results[i].category;
                $rootScope.names = name;
            });
        }

        $rootScope.viewMissingText = function(obj) {
            document.getElementById("missing").style.visibility = 'hidden';
            if (obj == 'games')
                document.getElementById("missingText").style.visibility = 'visible';
            else
                document.getElementById("buttonText").style.visibility = 'visible';
        }

        $rootScope.deleteModalName = function(console, obj) {
            $http.get('api/category/content/delete?modelName=' + obj + "&console=" + console).then(function(response) {
                name = [];
                for (var i = 0; i < response.data.results.length; i++)
                    name[i] = response.data.results[i].category;
                $rootScope.names = name;
            });
        }

        $scope.searchText = ''; $scope.search = function() {
            $http.get('api/stock/search?q=' + $scope.searchText + "&accountId=" + $routeParams.id).
            then(function(response) {
                $scope.tableVal = response.data.results;
            })
        }
        $scope.edit = function(obj) {
            $scope.params = $routeParams;
            $http.post("/api/stock", obj).then(function successCallback(response) {
                window.location.href = "#!/stock/" + $scope.params.id;;
                console.log("Successfully POST-ed data");
            }, function errorCallback(response) {
                window.location.href = "#!/stock/" + $scope.params.id;;
                console.log("POST-ing of data failed");
            });
        }
        $scope.sortBy = function(value) {
            if (value == undefined)
                $scope.loadStocks();
            else
                $http.get('api/stock/sorting?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize + "&sortBy=" + value + "&accountId=" + $routeParams.id).then(function(response) {
                    $scope.tableVal = response.data.results;
                    $scope.page.totalElements = response.data.totalElements;
                });
        }

        $scope.loadStocks = function() {
            $http.get('api/stock?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize + "&accountId=" + $routeParams.id).
            then(function(response) {
                $scope.tableVal = response.data.results;
                $scope.page.totalElements = response.data.totalElements;
                // $scope.page.currentPage=response.data.totalPages;
            });
        }
        $scope.loadHistoryStock = function(id) {
            $http.get('api/stock/stock-history/' + id + '?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize).
            then(function(response) {
                $scope.tableVal = response.data.results;
                $scope.page.totalElements = response.data.totalElements;
                // $scope.page.currentPage=response.data.totalPages;
            });
        }
    }])

.controller('StockEditCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        $scope.action = "Edit";
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $scope.loadStock($routeParams.stockId);
    }])
    .controller('StockHistoryCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$controller', function($scope, $http, $routeParams, $rootScope, $controller) {
        $scope.action = "History";
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $scope.loadHistoryStock($routeParams.id);
        $scope.load = function() {
            $scope.loadHistoryStock($routeParams.id);
        }
    }])
    .controller('View2Ctrl', ['$scope', '$http', '$controller', '$rootScope', '$routeParams', function($scope, $http, $controller, $rootScope, $routeParams) {
        angular.extend(this, $controller('StockCURDCtrl', {
            $scope: $scope
        }));
        $scope.loadStocks();
        $scope.load = function() {
            $scope.loadStocks();
        }
        $scope.loadStock("");
        $scope.data = {
            upload: false
        };
        $scope.getFromEbay = function() {
            $scope.data.upload = true;
            ///alert("Now i will call ebay from restful webservice...");
            $http.get('api/stock/loadEbay').
            then(function(response) {
                $scope.data.upload = false;
            });
        }
    }]);