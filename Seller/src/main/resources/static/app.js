'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.awd',
  'myApp.orders',
  'myApp.channel',
  'myApp.listing',
  'myApp.scheduler',
  'myApp.user',
  'myApp.unavailable',
  'myApp.sales',
  'myApp.dropship',
  'myApp.compete',
  'myApp.upload',
  'myApp.itemCategory',
  'myApp.usergroup',
  'myApp.log',
  'myApp.accounts',
  'myApp.relist',
  'myApp.reset',
  'myApp.externalStockLinks',
  'myApp.version',
  'myApp.vinted',
  'ui.bootstrap','ngHandsontable','ngCookies'
]).
run(
        ['$rootScope', '$http' ,   function ($rootScope,$http) {
        $rootScope.linkCss="dashboard";
         $rootScope.$on("$locationChangeStart", function(event, next, current) {

                       event.currentScope.linkCss='dashboard';
                        if(next.indexOf("#!/dashboard")>-1) $rootScope.linkCss="dashboard";
                        else if(next.indexOf("#!/stock")>-1) $rootScope.linkCss="stock";
                        else if(next.indexOf("#!/orders")>-1) $rootScope.linkCss="orders";
                        else if(next.indexOf("#!/channel")>-1) $rootScope.linkCss="channel";
                        else if(next.indexOf("#!/purchaseOrders")>-1) $rootScope.linkCss="purchaseOrders";
                        else if(next.indexOf("#!/users")>-1) $rootScope.linkCss="users";
                        else if(next.indexOf("#!/Activelisting")>-1) $rootScope.linkCss="activeListing";
                        else if(next.indexOf("#!/accounts")>-1) $rootScope.linkCss="accounts";
                        else if(next.indexOf("#!/upload")>-1) $rootScope.linkCss="upload";
                        else if(next.indexOf("#!/log")>-1) $rootScope.linkCss="log";
                        else if(next.indexOf("#!/vinted")>-1) $rootScope.linkCss="vinted";
                        else if(next.indexOf("#!/vintedData")>-1) $rootScope.linkCss="vintedData";

    });
     $rootScope.loadAccounts=function(){
       $http.get('api/accounts?page=0&pageSize=100').
             then(function(response) {
                 $rootScope.tableVal = response.data.results;
                 //$scope.page.totalElements=response.data.totalElements;
                // $scope.page.currentPage=response.data.totalPages;

             });

    }
    $rootScope.loadAccounts();
    $rootScope.displayError=function(obj){
      var box = $("#message-box-sound-2");
           // if(box.length > 0){
                box.toggleClass("open");
                $rootScope.errorMsg="";
                for(var i=0;i<obj.length;i++)
$rootScope.errorMsg+=obj[i].messageText+"\n";
                var sound = box.data("sound");

                if(sound === 'alert')
                    playAudio('alert');

                if(sound === 'fail')
                    playAudio('fail');

          //  }
            return false;
    }
        }
   ]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.otherwise({redirectTo: '/dashboard'});
$(document).ready(function(){
        $(function(){
        $('#ideal_form').submit(function(e){
                e.preventDefault();
                var form = $(this);
                var post_url = form.attr('action');
                var post_data = form.serialize();
                $('#loader3', form).html('<img src="../../images/ajax-loader.gif" />       Please wait...');
                $.ajax({
                    type: 'POST',
                    url: post_url,
                    data: post_data,
                    success: function(msg) {
                        $(form).fadeOut(800, function(){
                            form.html(msg).fadeIn().delay(2000);

                        });
                    }
                });
            });
        });
         });



}]);

