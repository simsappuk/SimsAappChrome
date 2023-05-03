'use strict';

var controller1 = angular.module("myApp.reset", ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/reset/:appId', {
            templateUrl: 'reset/list.html',
            controller: 'resetCtrl'
        });

        $routeProvider.when('/reset/form/:appId', {
            templateUrl: 'reset/form.html',
            controller: 'resetNewCtrl'
        });

        $routeProvider.when('/reset/history/:appId', {
            templateUrl: 'reset/history.html',
            controller: 'resetHistoryCtrl'
        });

        $routeProvider.when('/reset-edit/:appId/:id',{
        templateUrl:'reset/update.html',
        controller:'resetEditCtrl'
        });
    }])

    .controller('resetCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
        $scope.params = $routeParams;
        angular.extend(this, $controller('resetCurdCtrl', {
            $scope: $scope
        }));
        $scope.getResetData();
    }])

    .controller('resetHistoryCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
            $scope.params = $routeParams;
            angular.extend(this, $controller('resetCurdCtrl', {
                $scope: $scope
            }));
            $scope.getCurrentDateHistory();
        }])

    .controller('resetEditCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
            $scope.params = $routeParams;
            $scope.deleteItem=function(){
            $http.post("api/reset/delete",$scope.reset).then(function successCallback(response){
                            window.location.href="#!/reset/"+$scope.params.appId;
                noty({text: 'Deleted Successfully',layout: 'topRight',type: 'success',killer: true,timeout: 5000});
                         }, function errorCallback(response){
                                  window.location.href="#!/reset/"+$scope.params.appId;
                noty({text: 'There was an error',layout: 'topRight',type: 'error',killer: true,timeout: 5000});
                         });
            }

           $scope.updateItem=function(){
                       $http.post("api/reset/update?accountId="+$scope.params.appId,$scope.reset).then(function successCallback(response){
                                       window.location.href="#!/reset/"+$scope.params.appId;
                           noty({text: 'Updated Successfully',layout: 'topRight',type: 'success',killer: true,timeout: 5000});
                                    }, function errorCallback(response){
                                             window.location.href="#!/reset/"+$scope.params.appId;
                           noty({text: 'There was an error',layout: 'topRight',type: 'error',killer: true,timeout: 5000});
                                    });
                       }
            angular.extend(this, $controller('resetCurdCtrl', {
                $scope: $scope
            }));
            $scope.getItemRelatedResetData($routeParams.id);
        }])

    .controller('resetNewCtrl', ['$scope', '$http', '$controller', '$routeParams', '$rootScope', function($scope, $http, $controller, $routeParams, $rootScope) {
        $scope.reset = {};
        $scope.params = $routeParams;
        $scope.save = function() {
            $.mpb("show", {
                value: [0, 500],
                speed: 0
            });
            $http.post("api/reset/save?accountId=" + $scope.params.appId, $scope.reset).then(function successCallback(response) {
                if (response.data.errors) $rootScope.displayError(response.data.messages);
                window.location.href = "#!/reset/" + $scope.params.appId;
            }, function errorCallback(response) {
                if (response.data.errors) $rootScope.displayError(response.data.messages);
                window.location.href = "#!/reset/" + $scope.params.appId;
            });
        }
        angular.extend(this, $controller('resetCurdCtrl', {
            $scope: $scope
        }));
    }])

    .controller('resetCurdCtrl', ['$scope', '$http', '$rootScope', '$routeParams','$uibModal', function($scope, $http, $rootScope, $routeParams,$uibModal) {
      $scope.page={totalElements:0,currentPage:1,pageSize:200,loading:false};
        $rootScope.showCalculator = function() {
                                      $rootScope.modalInstance = $uibModal.open({
                                          controller: 'resetCurdCtrl',
                                          ariaLabelledBy: 'modal-title',
                                          ariaDescribedBy: 'modal-body',
                                          templateUrl: '/view1/calculator.html',
                                          size: 'lg'
                                      });
                                  }
        $scope.getListingDetails = function(obj) {
            if (obj.ebayItemId != "" && obj.ebayItemId != undefined)
                $http.get('api/reset/getItemDetails?itemId=' + obj.ebayItemId + "&accountId=" + $scope.params.appId).then(function(response) {
                    $scope.reset = response.data.results

                });
            else
                noty({text: 'Please Enter ItemID',layout: 'topRight',type: 'warning',killer: true,timeout: 5000});
        }

        $scope.getResetData = function() {
            $http.get('api/reset/getData?accountId=' + $routeParams.appId+"&page="+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).then(function(response) {
                $scope.reset = response.data.results;
            });
        }

        $scope.reviseListing=function(obj,accountId){
           noty({text: 'Please wait the quantity is yet to revise',layout: 'topRight',type: 'warning',killer: true,timeout: 4000});
           $scope.params=$routeParams;
            $http.get("api/ActiveListing/revise/"+accountId+'?itemId='+(obj.ebayItemId)+"&amount="+(obj.ebayPrice)+"&quantity="+(obj.existedQuantity)+"&listingType="+(obj.listingType)+"&firstVariationName="+(obj.firstVariationName)+"&firstVariationValue="+(obj.firstVariationValue)+"&variantSku="+(obj.variantSku)+"&secondVariationName="+(obj.secondVariationName)+"&secondVariationValue="+(obj.secondVariationValue))
                      .then(function (response){
                      if(response.data.errors)
                        $rootScope.displayError(response.data.messages);
                      else
                        noty({text: 'Updated Successfully',layout: 'topRight',type: 'success',killer: true,timeout: 5000});
            });
        }

        $scope.updateAdRate=function(obj){
            noty({text: 'Please wait for a while',layout: 'topRight',type: 'warning',killer: true,timeout: 4000});
            $http.post("api/reset/update/adRate?accountId="+$routeParams.appId,obj).then(function successCallback(response){
                noty({text: 'adRate Updated Successfully',layout: 'topRight',type: 'success',killer: true,timeout: 5000});
            }, function errorCallback(response){
                noty({text: 'There was an error',layout: 'topRight',type: 'error',killer: true,timeout: 5000});
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


        $scope.loadResetHistoryByDate = function() {
                    $http.get('api/reset/getHistory/byDate?accountId=' + $routeParams.appId+"&page="+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize+"&date="+$scope.formatDate($scope.params.date)).then(function(response) {
                        $scope.reset = response.data.results;
                    });
                }

        $scope.getCurrentDateHistory = function() {
                    $http.get('api/reset/getCurrentDayhistory/data?accountId=' + $routeParams.appId).then(function(response) {
                        $scope.reset = response.data.results;
                    });
                }

        $scope.getItemRelatedResetData=function(obj){
        noty({text: 'Please Wait Data is Loading',layout: 'topRight',type: 'warning',killer: true,timeout: 8000});
        $http.get('api/reset/'+obj).then(function(response){
        $scope.reset=response.data.results;
        });
        }

        $scope.getSkuQuantity=function(obj){
        if(obj.variationsTypes==null || obj.listingType=='Standard')
                        noty({text: 'You have entered a Standard Listing ID',layout: 'topRight',type: 'warning',killer: true,timeout: 5000});
        else
        for(var i=0;i<obj.variationsTypes.variation.length;i++){
        var variation=obj.variationsTypes.variation[i];
        if(variation.sku==obj.variantSku){
          obj.totalQuantitySold=variation.sellingStatus.quantitySold;
          if(variation.quantity>variation.sellingStatus.quantitySold)
          obj.existedQuantity=variation.quantity-variation.sellingStatus.quantitySold;
          else
          obj.existedQuantity=variation.sellingStatus.quantitySold-variation.quantity;
          if(variation.variationSpecifics.nameValueListLength==1){
          obj.listingType=='UniqueVariation';
          obj.firstVariationName=variation.variationSpecifics.nameValueList[0].name;
          obj.firstVariationValue=variation.variationSpecifics.nameValueList[0].value[0];
          }
          else if(variation.variationSpecifics.nameValueListLength>1){
          obj.listingType=='MultiVariation';
          obj.firstVariationName=variation.variationSpecifics.nameValueList[0].name;
          obj.firstVariationValue=variation.variationSpecifics.nameValueList[0].value[0];
          obj.firstVariationName=variation.variationSpecifics.nameValueList[1].name;
          obj.firstVariationValue=variation.variationSpecifics.nameValueList[1].value[0];
          }
          noty({text: 'Maximum Quantity Updated',layout: 'topRight',type: 'warning',killer: true,timeout: 5000});
          }
        }


        }

    }])