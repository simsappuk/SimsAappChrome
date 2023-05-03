'use strict';


angular.module('myApp.orders', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/orders/:spreadSheetId/:channelId/:sheetSize/:id', {
            templateUrl: 'orders/list.html',
            controller: 'OrdersCtrl'
        });
        $routeProvider.when('/orders/:spreadSheetId/:sheetNumber/:id', {
            templateUrl: 'orders/sheet.html',
            controller: 'OrdersCtrl'
        });
        $routeProvider.when('/purchaseOrders/:id/:spreadSheetId', {
            templateUrl: 'orders/list.html',
            controller: 'OrdersCtrl'
        });
        $routeProvider.when('/orders/id', {
            templateUrl: 'orders/list.html',
            controller: 'OrdersCtrl'
        });
        $routeProvider.when('/orders-new', {
            templateUrl: 'orders/add.html',
            controller: 'OrdersNewCtrl'
        });
        $routeProvider.when('/orders-edit/:id', {
            templateUrl: 'orders/add.html',
            controller: 'OrdersEditCtrl'
        });
        $routeProvider.when('/ordersPdf/invoices/:id/:spreadSheetId', {
            templateUrl: 'orders/pdfList.html',
            controller: 'OrdersInvoiceCtrl'
        });
    }])
    .controller('OrdersNewCtrl', ['$scope', '$http', '$controller', function($scope, $http, $controller) {
        $scope.action = "New"
        angular.extend(this, $controller('OrdersCURDCtrl', {
            $scope: $scope
        }));
    }])

    .controller('OrdersInvoiceCtrl', ['$scope', '$http', '$controller', function($scope, $http, $controller) {
        angular.extend(this, $controller('OrdersCURDCtrl', {
            $scope: $scope
        }));
    }])
    .controller('OrdersCURDCtrl', ['$scope', '$http', '$routeParams', '$rootScope', '$uibModal', function($scope, $http, $routeParams, $rootScope, $uibModal) {
        $scope.orders = {};
        $rootScope.sheet = {
            sheetSize: 20,
            spreadSheetId: 0,
            channelId: 1,
            folderParentId: 0,
            accountId: ($routeParams.id == undefined) ? $routeParams.accountId : $routeParams.id
        }
        $rootScope.showCalculator = function() {
            $rootScope.modalInstance = $uibModal.open({
                controller: 'OrdersCURDCtrl',
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/view1/calculator.html',
                size: 'lg'
            });
        }


        if ($routeParams.sheetSize == null)
            $rootScope.sheet.sheetSize = 20;
        else $rootScope.sheet.sheetSize = $routeParams.sheetSize;
        $rootScope.sheet.spreadSheetId = $routeParams.spreadSheetId != null ? $routeParams.spreadSheetId : 0;
        $rootScope.sheet.channelId = $routeParams.channelId != null ? $routeParams.channelId : 1;
        $rootScope.sheet.folderParentId = 0;
        $scope.loadURL = function() {
            window.location.href = "#!/orders/" + $rootScope.sheet.spreadSheetId + "/" + $rootScope.sheet.channelId + "/" + $rootScope.sheet.sheetSize + "/" + $rootScope.sheet.accountId;
        }
        $rootScope.showPopup = function() {
            $rootScope.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/orders/dialog.html',
                size: 'lg'
            });
        }

        $rootScope.popupPdf = function(obj) {
            window.open("api/orders/popup/generatePdf/"+obj);

        }

        $rootScope.showFoldersPopup = function() {
            $rootScope.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/orders/folderDialog.html',
                size: 'lg'
            });
        }
        $rootScope.createSheet = function() {
            $http.get("api/folders/sheet/new?accountId=" + $routeParams.id).then(function(response) {
                if (response.data != null)
                    $rootScope.sheet.spreadSheetId = response.data.id;
                $rootScope.items = [];
                $rootScope.sheetId = 1;
                $scope.loadURL();
            });
        }
        $rootScope.getName = function(id) {
            $http.get("api/folders/" + id + '/' + $routeParams.id).then(function(response) {
                if (response.data != null)
                    $rootScope.folder = response.data;
            });
        }
        $scope.loadSheet = function(sheet, obj) {
            if ($routeParams.duplicateId != undefined)
                var spreadSheetId = $routeParams.duplicateId;
            else if ($routeParams.spreadSheetId != undefined)
                spreadSheetId = $routeParams.spreadSheetId;
            else
                spreadSheetId = sheet.spreadSheetId;

            $http.get("api/orders/loadSheet/" + spreadSheetId + '/' + sheet.channelId + '/' + obj.sheetNumber + '/' + sheet.accountId).then(function(response) {
                $scope.loadSheetNumbers(sheet, obj);
            });
        }
        $rootScope.getSheetName = function() {
            $http.get("api/folders?accountId=" + $routeParams.id).then(function(response) {
                if (response.data != null)
                    $rootScope.spreadsheet = response.data;
            });
        }

        $rootScope.close = function() {
            $rootScope.modalInstance.close();
        }


        $rootScope.saveRowOrder = function(element, row, value, title) {
            var channelId = ($routeParams.channelId == undefined) ? $routeParams.sheetNumber : $routeParams.channelId
            $http.get("api/orders/saveCellData?name=" + element[1] + "&value=" + value + "&rowId=" + $rootScope.items[row].id + "&channelId=" +channelId + "&accountId=" + $routeParams.id + "&spreadSheetId=" + $routeParams.spreadSheetId).then(function(response) {
                if (response.data.errors)
                    noty({
                        text: response.data.messages[0].messageText,
                        layout: 'topRight',
                        type: 'error',
                        killer: true
                    });
                else {
                    $rootScope.items[row] = response.data.results;
                    noty({
                        text: 'Data Saved',
                        layout: 'topRight',
                        type: 'success',
                        killer: true,
                        timeout: 1000
                    });
                }
            });
        }

        $rootScope.getSheet = function(obj) {
            $rootScope.modalInstance.close();
            $rootScope.sheet.sheetId = 1;
            $rootScope.sheet.spreadSheetId = obj.id;
            $scope.loadURL();
            /*$rootScope.orderEdit=true;
             $rootScope.items=[];
            $http.get('api/folders/getSheet/'+obj.id).then(function(response) {

            $rootScope.items=$scope.stuffAray(response.data.results,20);
                         $scope.page.totalElements=response.data.totalElements;
                         $rootScope.orderEdit=false;
            });*/
        }

        $rootScope.getMainSheet = function(obj) {
            $rootScope.sheet.sheetId = 1;
            $rootScope.sheet.spreadSheetId = obj.spreadSheetId;
            $scope.loadURL();
        }

        $rootScope.getChildFolders = function(obj) {
            $rootScope.sheet.folderParentId = obj.id;
            $rootScope.getName(obj.id);
        }

        $rootScope.folder = [];
        $rootScope.addFolder = function() {
            var k = {
                parentId: $rootScope.sheet.folderParentId
            };
            $rootScope.folder.push(k);
        }
        $rootScope.saveFolders = function(obj) {
            $scope.params = $routeParams;
            $http.post("/api/folders", obj)
                .then(function successCallback(response) {
                    window.location.href ="#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id ;
                    console.log("Successfully POST-ed data");
                }, function errorCallback(response) {
                    window.location.href = "#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id ;
                    console.log("POST-ing of data failed");
                });
        }
        $rootScope.saveSheets = function(obj) {
            $scope.params = $routeParams;
            $http.post("/api/folders/saveSheet", obj)
                .then(function successCallback(response) {
                    window.location.href = "#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id ;
                    console.log("Successfully POST-ed data");
                }, function errorCallback(response) {
                    window.location.href = "#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id ;
                    console.log("POST-ing of data failed");
                });
        }


        $scope.page = {
            totalElements: 0,
            currentPage: 1,
            pageSize: 20
        };
        $scope.isEmptyOrderRow = function(obj) {

        }

        $scope.isObjectEmpty = function(card) {
            return Object.keys(card).length === 0;
        }

        $scope.getCookie = function(cname) {
            var name = cname + "=";
            var decodedCookie = decodeURIComponent(document.cookie);
            var ca = decodedCookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') {
                    c = c.substring(1);
                }
                if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        }

        $scope.setCookie = function(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toUTCString();
            document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
        }

        $scope.unStuff = function(obj) {
            var dest = [];
            if (obj.length == undefined)
                dest.push(obj);
            else
                for (var i = 0; i < obj.length; i++) {
                    if (!$scope.isObjectEmpty(obj[i])) dest.push(obj[i]);
                }
            return dest;
        }

        $scope.unStuffAutoSave = function(obj) {
            var dest = [];
            if (obj.length == undefined)
                dest.push(obj);
            else
                for (var i = 0; i < obj.length; i++) {
                    if (!$scope.isObjectEmpty(obj[i])) dest.push(obj[i]);
                }
            return dest;
        }
        var channelId = "";
        $rootScope.getSearchDetails = function(obj, sheet, selection) {
            if (selection == 'date'){
                obj.setDate(obj.getDate()+1);
                obj = obj.toISOString();
                }
            if ($routeParams.channelId == undefined)
                channelId = $routeParams.sheetNumber;
            else
                channelId = "1";
            $rootScope.items = [];
            $http.get("api/orders/getPurchaseData/" + selection + '/' + channelId + '/' + sheet.accountId + '/' + sheet.spreadSheetId + '/' + obj).then(function(response) {
                $rootScope.items = $scope.stuffAray(response.data.results, $rootScope.sheet.sheetSize);
                noty({
                    text: 'Data Fetched',
                    layout: 'topRight',
                    type: 'success',
                    killer: true,
                    timeout: 2000
                });
            });
        }

        $rootScope.getInvoiceOrdersData = function(obj){
                        channelId = "1";
                        obj.setDate(obj.getDate()+1);
                        obj = obj.toISOString();
         $http.get("api/orders/getInvoiceData/" + channelId + '/' + $routeParams.id +'/'+ $routeParams.spreadSheetId + '/' + obj).then(function(response) {
                        $rootScope.invoicesData = response.data.results;
                        noty({
                            text: 'Data Fetched',
                            layout: 'topRight',
                            type: 'success',
                            killer: true,
                            timeout: 2000
                        });
                    });

        }

        $rootScope.refreshPage=function(){
        var obj={};
        $scope.loadOrders(obj.duplicateId==null);
        }

        $rootScope.save = function(obj) {
            $scope.params = $routeParams;
            $rootScope.items = [];
            var sheetId = ($routeParams.spreadSheetId == null) ? 0 : $rootScope.sheet.spreadSheetId;
            $http.post("/api/orders/" + $rootScope.sheet.channelId + '/' + sheetId + '/' + $scope.params.id + '/' + $routeParams.sheetNumber, $scope.unStuff(obj))
                .then(function successCallback(response) {
                    console.log("Successfully POST-ed data");
                    $rootScope.items = $scope.stuffAray(response.data, $rootScope.sheet.sheetSize);
                    $scope.page.totalElements = response.data.totalElements;
                    noty({
                        text: 'Data Saved',
                        layout: 'topRight',
                        type: 'success',
                        killer: true,
                        timeout: 2000
                    });
                }, function errorCallback(response) {
                    window.location.href = "#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id ;
                    noty({
                        text: 'Unable to Save Data,try saving after refreshing the page',
                        layout: 'topRight',
                        type: 'error',
                        killer: true,
                        timeout: 2000
                    });
                    console.log("POST-ing of data failed");
                });
        }
        $scope.loadOrder = function(obj) {
            $http.get('api/orders/' + (obj)).
            then(function(response) {
                $scope.orders = response.data.results;
                // $scope.page.currentPage=response.data.totalPages;

            });
        }
        $scope.loadChannel = function(obj) {
            $http.get('api/channel').
            then(function(response) {
                $scope.channels = response.data.results;
                // $scope.page.currentPage=response.data.totalPages;

            });
        }
        $rootScope.newSheet = function(sheet) {
            $http.get("api/orders/newSheet/" + sheet.spreadSheetId + '/' + sheet.channelId + '/' + sheet.accountId).then(function(response) {
                $scope.loadSheetNumbers(sheet);
            });
        }
        $rootScope.refreshPurchaseOrders = function() {
            $scope.params = $routeParams;
            noty({
                text: 'Please Wait for a While....... ',
                layout: 'topRight',
                type: 'warning'
            });
            $http.post("api/orders/refreshPurchaseOrders?accountId=" + $routeParams.id).then(function successCallback(response) {
                if (response.data.errors) {
                    noty({
                        text: response.data.messages[0].messageText,
                        layout: 'topRight',
                        type: 'error'
                    });
                    window.location.href = "#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id;
                } else {
                    noty({
                        text: 'Fetched Orders Successfully, Please refresh the page',
                        layout: 'topRight',
                        type: 'success'
                    });
                    window.location.href = "#!/orders/" + $scope.params.spreadSheetId + "/" +$rootScope.sheet.channelId + "/" +$scope.params.id ;

                }
            });
        }

        $scope.loadSheetNumbers = function(sheet, obj) {
            if (obj != undefined && obj.duplicateId != null)
                sheet.spreadSheetId = obj.duplicateId;
            $http.get("api/orders/getSheet/" + sheet.spreadSheetId + '/' + sheet.channelId + '/' + sheet.accountId).then(function(response) {
                $rootScope.sheetNumbers = response.data.results;
            });
        }
        $scope.stuffAray = function(obj, maxLength) {
            if (obj == null) obj = [];
            var tot = obj.length;
            for (var i = tot; i < maxLength; i++) obj.push({});
            return obj;
        }
        $rootScope.updateSheetName = function(obj) {
            $http.post("/api/folders/sheet/" + $routeParams.id, obj)
                .then(function successCallback(response) {

                }, function errorCallback(response) {

                });
        }
        $rootScope.sheetMetadata = function(id) {
            $http.get('api/folders/sheet/' + id + '/' + $routeParams.id).then(function(response) {
                $rootScope.sSheet = response.data.results;
            });
        }
        $rootScope.content = function() {
            var name = [];
            $http.get('api/category/content').then(function(response) {
                for (var i = 0; i < response.data.results.length; i++)
                    name[i] = response.data.results[i].category;
                $rootScope.names = name;
            });
        }
        $rootScope.conditions = ['New', 'Used'];


        $scope.loadOrders = function(obj) {
            $rootScope.items = [];
            if (obj.duplicateId != null)
                var sheetId = obj.duplicateId;
            else
                var sheetId = ($routeParams.spreadSheetId != null ? $routeParams.spreadSheetId : 0);
            $rootScope.sheetMetadata(sheetId);
            $http.get('api/orders/load/' + sheetId + '?page=' + ($scope.page.currentPage - 1) + "&pageSize=" + $scope.page.pageSize + "&channelId=" + $rootScope.sheet.channelId + "&sheetNumber=" + $routeParams.sheetNumber + "&accountId=" + $rootScope.sheet.accountId).
            then(function(response) {
                $rootScope.orderEdit = false;
                /*$scope.loadSheetNumbers(sheetId,$rootScope.sheet.channelId);*/
                $rootScope.items = $scope.stuffAray(response.data.results, $rootScope.sheet.sheetSize);
                $scope.page.totalElements = response.data.totalElements;
            });
        }


    }])
    .controller('OrdersEditCtrl', ['$scope', '$http', '$routeParams', '$controller', function($scope, $http, $routeParams, $controller) {
        $scope.action = "Edit";
        angular.extend(this, $controller('OrdersCURDCtrl', {
            $scope: $scope
        }));
        $scope.loadOrder($routeParams.id);

        $scope.delete = function(obj) {
            $http.delete('api/orders/' + obj.id, {}).then(function(response) {

                if (response.data)

                    $scope.msg = "Data Deleted Successfully!";

            }, function(response) {

                $scope.msg = "Service not Exists";

                $scope.statusval = response.status;

                $scope.statustext = response.statusText;

                $scope.headers = response.headers();

            });

        }
    }])
    .controller('OrdersCtrl', ['$scope', '$http', '$controller', '$rootScope', '$routeParams', function($scope, $http, $controller, $rootScope, $routeParams) {
        angular.extend(this, $controller('OrdersCURDCtrl', {
            $scope: $scope
        }));
        $rootScope.content();
        $rootScope.orderEdit = false;
        $rootScope.getName(0);
        $rootScope.getSheetName();
        $scope.channelId = 1;
        $scope.params=$routeParams;
        if ($routeParams.channelId) $rootScope.sheet.channelId = $routeParams.channelId
        $scope.loadChannel();
        $scope.loadSheetNumbers($rootScope.sheet);
        $scope.loadOrders($routeParams);
        $rootScope.duplicateId = $routeParams.duplicateId;
        $scope.loadSheet($rootScope.sheet, $routeParams);
        /*$scope.$watch(function() {
          return $rootScope.items;
        }, function() {
        if($rootScope.orderEdit)
          $scope.save($rootScope.items);
        }, true);*/
        $rootScope.updateData = function() {
            $rootScope.orderEdit = false;
        }
    }]).controller('MainCtrl', MainCtrl);