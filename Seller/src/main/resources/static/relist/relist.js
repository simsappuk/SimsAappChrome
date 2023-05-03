'use strict';

var controller1 = angular.module("myApp.relist", ['ngRoute'])

  .config(['$routeProvider', function($routeProvider) {

    $routeProvider.when('/reListing/:appId', {
      templateUrl: 'relist/list.html',
      controller: 'relistCtrl'
    });

    $routeProvider.when('/relist/add/:appId', {
      templateUrl: 'relist/add.html',
      controller: 'relistAddCtrl'
    });

    $routeProvider.when('/relist/update/:appId/:id', {
      templateUrl: 'relist/updateForm.html',
      controller: 'relistUpdateCtrl'
    });

  }])

  .controller('relistCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
    $scope.params = $routeParams;
    angular.extend(this, $controller('relistCurdCtrl', {
      $scope: $scope
    }));
    $scope.getData();
  }])

  .controller('relistAddCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
    $scope.params = $routeParams;
    angular.extend(this, $controller('relistCurdCtrl', {
      $scope: $scope
    }));
  }])

  .controller('relistUpdateCtrl', ['$scope', '$http', '$controller', '$routeParams', function($scope, $http, $controller, $routeParams) {
    $scope.params = $routeParams;
    angular.extend(this, $controller('relistCurdCtrl', {
      $scope: $scope
    }));
    $scope.updateForm();
  }])

  .controller('relistCurdCtrl', ['$scope', '$http', '$rootScope', '$routeParams','$uibModal', function($scope, $http, $rootScope, $routeParams,$uibModal) {
    $rootScope.showCalculator = function() {
                                          $rootScope.modalInstance = $uibModal.open({
                                              controller: 'relistCurdCtrl',
                                              ariaLabelledBy: 'modal-title',
                                              ariaDescribedBy: 'modal-body',
                                              templateUrl: '/view1/calculator.html',
                                              size: 'lg'
                                          });
                                      }

    $scope.getData = function() {
      $http.get('api/relist/getData?accountId=' + $routeParams.appId).then(function(response) {
        $scope.relist = response.data.results;
      });
    }

    $scope.searchText = '';
    $scope.search = function() {
      $http.get('api/relist/search?q=' + $scope.searchText + "&id=" + ($routeParams.appId)).
      then(function(response) {
        $scope.relist = response.data.results;
      })
    }

    $scope.update = function(obj) {
      $http.post('api/relist/update', obj).then(function successCallback(response) {
        noty({
          text: 'Updated Successfully',
          layout: 'topRight',
          type: 'success'
        });
      });
      window.location.href = "#!/reListing/" + $routeParams.appId;
    }

    $scope.updateForm = function() {
      $http.get('api/relist/form/update?accountId=' + $routeParams.appId + "&id=" + $routeParams.id).then(function(response) {
        $scope.relist = response.data.results;
      });
    }

    $scope.checkItem = function(obj) {
      $scope.Relist = [];
      $http.get('api/relist/check/itemId?accountId=' + $routeParams.appId + "&itemId=" + obj).then(function(response) {
        var exist = response.data.results;
        $scope.Relist = {
          itemId: obj,
          itemExist: exist
        };
      });
    }

    $scope.check = [];
    $scope.selected = function(obj1, obj) {
      if (obj1 == true && (obj != null && obj.length != 0))
        $scope.check.push(obj);
      else if (obj1 == false)
        $scope.check.pop(obj);
    }

    $scope.deleteItem = function(obj) {
      if (obj.length == 0 || obj == undefined)
        noty({
          text: 'Please Select an Item To Delete',
          layout: 'topRight',
          type: 'warning'
        });
      else {
        $scope.check = [];
        $http.post('api/relist/delete/item/' + obj + '/' + $routeParams.appId).then(function successCallback(response) {
          noty({
            text: 'Deleted Successfully',
            layout: 'topRight',
            type: 'success'
          });
          $scope.getData();
        });
      }
    }

    $scope.save = function(obj, accountId) {
      $.mpb("show", {
        value: [0, 500],
        speed: 0
      });
      $http.post("api/relist/save?accountId=" + accountId, obj).then(function successCallback(response) {
        if (response.data.errors) $rootScope.displayError(response.data.messages);
        else
          noty({
            text: 'Item Added Successfully',
            layout: 'topRight',
            type: 'success'
          });
        window.location.href = "#!/reListing/" + $routeParams.appId;
        console.log("Successfully POST-ed data");
      }, function errorCallback(response) {
        if (response.data.errors)
          $rootScope.displayError(response.data.messages);
        else if (response.data.error != null)
          noty({
            text: response.data.message,
            layout: 'topRight',
            type: 'error'
          });
        window.location.href = "#!/reListing/" + $routeParams.appId;
        console.log("POST-ing of data failed");
      });
    }

  }])