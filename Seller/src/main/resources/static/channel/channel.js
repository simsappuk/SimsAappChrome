'use strict';

angular.module('myApp.channel', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/channel', {
    templateUrl: 'channel/list.html',
    controller: 'ChannelCtrl'
  });
  $routeProvider.when('/channel-new', {
      templateUrl: 'channel/add.html',
      controller: 'ChannelNewCtrl'
    });
  $routeProvider.when('/channel-edit/:id', {
          templateUrl: 'channel/add.html',
          controller: 'ChannelEditCtrl'
        });


}])
.controller('ChannelNewCtrl', ['$scope','$http','$controller',function($scope,$http,$controller) {

 $scope.action="New"
 angular.extend(this, $controller('ChannelCURDCtrl', {$scope: $scope}));


}]).controller('ChannelCURDCtrl', ['$scope','$http','$routeParams',function($scope,$http,$routeParams) {
  $scope.channel={};
   $scope.page={totalElements:0,currentPage:1,pageSize:10};
   $scope.tableVal=[ ];
 $scope.save=function(obj){
 $http.post("/api/channel", obj)
            .then(function successCallback(response){
            window.location.href="#!/channel";
                console.log("Successfully POST-ed data");
            }, function errorCallback(response){
            window.location.href="#!/channel";
                console.log("POST-ing of data failed");
            });
 }
 $scope.channel=function(obj){
   $http.get('api/channel/'+(obj)).
         then(function(response) {
             $scope.channel = response.data.results;
            // $scope.page.currentPage=response.data.totalPages;

         });
 }
 $scope.channels=function(){
   $http.get('api/channel?page='+($scope.page.currentPage-1)+"&pageSize="+$scope.page.pageSize).
         then(function(response) {
             $scope.tableVal = response.data.results;
             $scope.page.totalElements=response.data.totalElements;
            // $scope.page.currentPage=response.data.totalPages;

         });

}
}])
.controller('ChannelEditCtrl', ['$scope','$http','$routeParams','$controller',function($scope,$http,$routeParams,$controller) {
 $scope.action="Edit";
 angular.extend(this, $controller('ChannelCURDCtrl', {$scope: $scope}));
$scope.loadChannel($routeParams.id);

$scope.delete=function(obj){
$http.delete('api/channel/'+obj.id, {}).then(function (response) {

if (response.data)

$scope.msg = "Data Deleted Successfully!";

}, function (response) {

$scope.msg = "Service not Exists";

$scope.statusval = response.status;

$scope.statustext = response.statusText;

$scope.headers = response.headers();

});
}
}])
.controller('ChannelCtrl', ['$scope','$http','$controller', function($scope,$http,$controller) {
angular.extend(this, $controller('ChannelCURDCtrl', {$scope: $scope}));
$scope.loadChannel();
$scope.load=function(){$scope.loadChannel();
}
}]);
