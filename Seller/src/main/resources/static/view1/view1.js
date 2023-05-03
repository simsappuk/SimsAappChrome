'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/dashboard', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl',['$rootScope','$uibModal','$scope',function($rootScope,$uibModal,$scope) {
$rootScope.showCalculator = function() {
    $rootScope.modalInstance = $uibModal.open({
        controller: 'View1Ctrl',
        ariaLabelledBy: 'modal-title',
        ariaDescribedBy: 'modal-body',
        templateUrl: '/view1/calculator.html',
        size: 'lg'
    });
}

$rootScope.close = function() {
    $rootScope.modalInstance.close();
}

$rootScope.calculateChange = function(obj){
if(obj.sponsor==null)
obj.sponsor=0;
if(obj.postage==null)
obj.postage=0;
if(obj.sp==null)
obj.sp==0;
obj.profit =parseFloat((obj.sp - obj.bp - obj.fee - obj.vat - obj.sponsor - obj.postage).toFixed(2));
obj.profitPercent=parseFloat(((obj.profit/obj.sp)*100).toFixed(2));
}

$rootScope.profit = function(obj) {
    var vat = 0;var sponsor = 0;var postage = 0;var numerator=0;var denominator=0; var split=[];var fixedFee=0; var fixedProfit=0;
    if (obj.selected=='profit' && obj.sp!=null && obj.sp!=undefined && obj.bp != null && obj.bp != undefined && obj.condition != undefined && obj.condition != null) {
        var fee = obj.sp * 13.5 / 100 + 0.35;
        obj.fee =parseFloat(fee.toFixed(2));
        var difference = obj.sp - obj.bp;
        if (obj.condition == 'used')
            vat = difference * 16.66 / 100;
        else
            vat = obj.sp / 6;
        obj.vat =parseFloat(vat.toFixed(2));
        if (obj.sponsor != null && obj.sponsor != undefined)
            sponsor = obj.sp * (obj.sponsor / 100);
        if (obj.postage != null && obj.postage != undefined)
            postage = obj.postage;
        obj.profit =parseFloat((obj.sp - obj.bp - fee - vat - sponsor - postage).toFixed(2));
        obj.profitPercent=parseFloat(((obj.profit/obj.sp)*100).toFixed(2));
    }

    else if(obj.selected!='profit' && obj.bp != null && obj.bp != undefined && obj.condition != undefined && obj.condition != null){
    obj.vat='0.83';
    if(obj.sponsor==null || obj.sponsor==undefined)
    obj.sponsor=0;
    obj.fee=(13.5/100);
    if(obj.profit==null || obj.profit==undefined)
    obj.profit=10;
    if(obj.postage==null || obj.postage==undefined)
    obj.postage=0;
    obj.sp=parseFloat((((obj.bp*(0.83)+0.35)/(0.83-(obj.fee+(obj.sponsor/100))-(obj.profit/100)))+parseFloat(obj.postage)).toFixed(2));
    }
}
$rootScope.refreshCal = function(obj) {
    if(Object.keys(obj).length==1)
    $scope.cal =obj;
    else
    $scope.cal={selected:obj.selected};
}



 /* reportrange */
    if($("#reportrange").length > 0){
        $("#reportrange").daterangepicker({
            ranges: {
               'Today': [moment(), moment()],
               'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
               'Last 7 Days': [moment().subtract(6, 'days'), moment()],
               'Last 30 Days': [moment().subtract(29, 'days'), moment()],
               'This Month': [moment().startOf('month'), moment().endOf('month')],
               'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            opens: 'left',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary',
            cancelClass: 'btn-small',
            format: 'MM.DD.YYYY',
            separator: ' to ',
            startDate: moment().subtract('days', 29),
            endDate: moment()
          },function(start, end) {
              $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        });

        $("#reportrange span").html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
    }

    Morris.Donut({
        element: 'dashboard-donut-1',
        data: [
            {label: "Returned", value: 2513},
            {label: "New", value: 764},
            {label: "Registred", value: 311}
        ],
        colors: ['#33414E', '#1caf9a', '#FEA223'],
        resize: true
    });
    /* END Donut dashboard chart */


    /* Bar dashboard chart */
    Morris.Bar({
        element: 'dashboard-bar-1',
        data: [
            { y: 'Oct 10', a: 75, b: 35 },
            { y: 'Oct 11', a: 64, b: 26 },
            { y: 'Oct 12', a: 78, b: 39 },
            { y: 'Oct 13', a: 82, b: 34 },
            { y: 'Oct 14', a: 86, b: 39 },
            { y: 'Oct 15', a: 94, b: 40 },
            { y: 'Oct 16', a: 96, b: 41 }
        ],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['New Users', 'Returned'],
        barColors: ['#33414E', '#1caf9a'],
        gridTextSize: '10px',
        hideHover: true,
        resize: true,
        gridLineColor: '#E5E5E5'
    });
    /* END Bar dashboard chart */

    /* Line dashboard chart */
    Morris.Line({
      element: 'dashboard-line-1',
      data: [
        { y: '2014-10-10', a: 2,b: 4},
        { y: '2014-10-11', a: 4,b: 6},
        { y: '2014-10-12', a: 7,b: 10},
        { y: '2014-10-13', a: 5,b: 7},
        { y: '2014-10-14', a: 6,b: 9},
        { y: '2014-10-15', a: 9,b: 12},
        { y: '2014-10-16', a: 18,b: 20}
      ],
      xkey: 'y',
      ykeys: ['a','b'],
      labels: ['Sales','Event'],
      resize: true,
      hideHover: true,
      xLabels: 'day',
      gridTextSize: '10px',
      lineColors: ['#1caf9a','#33414E'],
      gridLineColor: '#E5E5E5'
    });
    /* EMD Line dashboard chart */
    /* Moris Area Chart */
      Morris.Area({
      element: 'dashboard-area-1',
      data: [
        { y: '2014-10-10', a: 17,b: 19},
        { y: '2014-10-11', a: 19,b: 21},
        { y: '2014-10-12', a: 22,b: 25},
        { y: '2014-10-13', a: 20,b: 22},
        { y: '2014-10-14', a: 21,b: 24},
        { y: '2014-10-15', a: 34,b: 37},
        { y: '2014-10-16', a: 43,b: 45}
      ],
      xkey: 'y',
      ykeys: ['a','b'],
      labels: ['Sales','Event'],
      resize: true,
      hideHover: true,
      xLabels: 'day',
      gridTextSize: '10px',
      lineColors: ['#1caf9a','#33414E'],
      gridLineColor: '#E5E5E5'
    });
    /* End Moris Area Chart */
    /* Vector Map */
    var jvm_wm = new jvm.WorldMap({container: $('#dashboard-map-seles'),
                                    map: 'world_mill_en',
                                    backgroundColor: '#FFFFFF',
                                    regionsSelectable: true,
                                    regionStyle: {selected: {fill: '#B64645'},
                                                    initial: {fill: '#33414E'}},
                                    markerStyle: {initial: {fill: '#1caf9a',
                                                   stroke: '#1caf9a'}},
                                    markers: [{latLng: [50.27, 30.31], name: 'Kyiv - 1'},
                                              {latLng: [52.52, 13.40], name: 'Berlin - 2'},
                                              {latLng: [48.85, 2.35], name: 'Paris - 1'},
                                              {latLng: [51.51, -0.13], name: 'London - 3'},
                                              {latLng: [40.71, -74.00], name: 'New York - 5'},
                                              {latLng: [35.38, 139.69], name: 'Tokyo - 12'},
                                              {latLng: [37.78, -122.41], name: 'San Francisco - 8'},
                                              {latLng: [28.61, 77.20], name: 'New Delhi - 4'},
                                              {latLng: [39.91, 116.39], name: 'Beijing - 3'}]
                                });
    /* END Vector Map */


$rootScope.log=function(){
                 setTimeout(function(){
                    $rootScope.log();
                 },2000);
}

$rootScope.log();
  /*  $(".x-navigation-minimize").on("click",function(){
        setTimeout(function(){
            rdc_resize();
        },200);
    });*/

}]);