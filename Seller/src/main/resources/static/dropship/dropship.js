'use strict';

var controller1 = angular.module("myApp.dropship", ['ngRoute'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/dropship/:appId', {
      templateUrl: 'dropship/list.html',
      controller: 'dropshipCtrl'
    });

    $routeProvider.when('/dropship-edit/:appId/:id', {
      templateUrl: 'dropship/update.html',
      controller: 'dropshipEditCtrl'
    });

    $routeProvider.when('/dropship/form/:appId', {
      templateUrl: 'dropship/form.html',
      controller: 'dropshipNewCtrl'
    });
  }])

  .controller('dropshipCurdCtrl', ['$scope', '$http', '$rootScope', '$routeParams','$uibModal', function($scope, $http, $rootScope, $routeParams,$uibModal) {
    $rootScope.showCalculator = function() {
                      $rootScope.modalInstance = $uibModal.open({
                          controller: 'dropshipCurdCtrl',
                          ariaLabelledBy: 'modal-title',
                          ariaDescribedBy: 'modal-body',
                          templateUrl: '/view1/calculator.html',
                          size: 'lg'
                      });
                  }
    $scope.amazonValue = function(obj) {
     var url="";
     if(obj.source!='ebay')
     url = obj.link;
     else
     url='https://www.ebay.co.uk/itm/'+obj.link;

      $http.get('api/dropship/getAmazonData?url=' + escape(url)+"&source="+obj.source).then(function(response) {
        obj.sellerPrice = response.data.results;
        $scope.dropship = obj;
      });
    }

    $scope.calculate = function(obj) {
      $scope.content = obj;
      $http.get('api/dropship/newCondition/getSellingPrice?condition=' + obj.condition + "&epp=" + obj.ebayPaypalPercent + "&transFee=" + obj.paypalTransactionCharge + "&profit=" + obj.profitPercent + "&bp=" + obj.sellerPrice + "&vat=" + obj.vatPercent).then(function(response) {
        obj.ebayPrice = response.data.results;
        $scope.dropship = obj;
      });
    }

    $scope.calculateVat = function(obj) {
      $scope.content = obj;
      $http.get('api/dropship/calculateVat?condition=' + obj.condition + "&bp=" + obj.sellerPrice + "&sp=" + obj.ebayPrice).then(function(response) {
        obj.vatPercent = response.data.results;
        $scope.dropship = obj;
      });
    }

    $scope.getDropshipData = function() {
      $http.get('api/dropship/getData?accountId=' + $routeParams.appId).then(function(response) {
        $scope.dropship = response.data.results;
      });
    }

    $scope.getItemRelatedDropshipData = function(obj) {
      $http.get('api/dropship/' + obj).then(function(response) {
        $scope.dropship = response.data.results;
      });
    }

  }])

  .controller('dropshipCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
    $scope.params = $routeParams;
    angular.extend(this, $controller('dropshipCurdCtrl', {
      $scope: $scope
    }));
    $scope.getDropshipData();
  }])

  .controller('dropshipEditCtrl', ['$scope', '$http', '$controller', '$routeParams', '$rootScope', function($scope, $http, $controller, $routeParams, $rootScope) {
    $scope.dropship = {};
    $scope.params = $routeParams;
    $scope.deleteItem = function() {
      $http.post("api/dropship/delete", $scope.dropship).then(function successCallback(response) {
        window.location.href = "#!/dropship/" + $scope.params.appId;
        console.log("Successfully POST-ed data");
      }, function errorCallback(response) {
        window.location.href = "#!/dropship/" + $scope.params.appId;
        console.log("POST-ing of data failed");
      });
    }

    $scope.updateItem = function() {
      $.mpb("show", {
        value: [0, 500],
        speed: 0
      });
      $http.post("api/dropship/update?accountId=" + $scope.params.appId, $scope.dropship).then(function successCallback(response) {
        if (response.data.errors) $rootScope.displayError(response.data.messages);
        window.location.href = "#!/dropship/" + $scope.params.appId;
        console.log("Successfully POST-ed data");
      }, function errorCallback(response) {
        if (response.data.errors) $rootScope.displayError(response.data.messages);
        window.location.href = "#!/dropship/" + $scope.params.appId;
        console.log("POST-ing of data failed");
      });
    }
    angular.extend(this, $controller('dropshipCurdCtrl', {
      $scope: $scope
    }));
    $scope.getItemRelatedDropshipData($routeParams.id);
  }])

  .controller('dropshipNewCtrl', ['$scope', '$http', '$controller', '$routeParams', '$rootScope', function($scope, $http, $controller, $routeParams, $rootScope) {
    $scope.dropship = {};
    $scope.params = $routeParams;
    $scope.save = function() {
      $.mpb("show", {
        value: [0, 500],
        speed: 0
      });
      $http.post("api/dropship/save?accountId=" + $scope.params.appId, $scope.dropship).then(function successCallback(response) {
        if (response.data.errors) $rootScope.displayError(response.data.messages);
        window.location.href = "#!/dropship/" + $scope.params.appId;
        console.log("Successfully POST-ed data");
      }, function errorCallback(response) {
        if (response.data.errors) $rootScope.displayError(response.data.messages);
        window.location.href = "#!/dropship/" + $scope.params.appId;
        console.log("POST-ing of data failed");
      });
    }
    angular.extend(this, $controller('dropshipCurdCtrl', {
      $scope: $scope
    }));
  }])