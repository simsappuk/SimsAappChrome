function MainCtrl($http,$scope,$rootScope,$cookies) {
//angular.extend(this, $controller('OrdersCtrl', {$scope: $scope}));

 // You can pass options by attributes..
 this.rowHeaders = true;
 this.colHeaders = true;
 this.changeIt=function(obj,obj1,obj2,obj3){
 //console.log(position);
     if(obj!=null){
$rootScope.orderEdit=true;
     $rootScope.items =this.getSourceData();
   //  $rootScope.save($rootScope.items);

 }
 }
this.Resize=function(event){
console.log(event);
var colArray=[];
for(var i=0;i<11;i++){
var cols={width:this.getColWidth(i)}
colArray.push(cols);}
$cookies.put("cols",JSON.stringify(colArray));
}
 this.destroy=function(obj,obj1,obj2){
   console.log("destroy called");
 console.log(obj);
   console.log(obj1);
   console.log(this.getDataAtRow(obj));
 }
 this.afterCreate=function(){
 console.log("New Row Added");
 }

 this.db = {
   items:  $rootScope.items
 };
 this.cols=[{width:50}, {width:91}, {width: 109},{width:358},{width:108},{width: 106},{width:61},{width: 46},{width: 100},{width:109},{width: 146}];
console.log(this.cols);
 // ..or as one objectF
 this.settings = {afterColumnResize:this.Resize,afterChange:this.changeIt,afterRemoveRow: this.destroy,afterCreateRow:this.afterCreate,search:this.search,
     stretchH: 'all',
     autoWrapRow: true,
     rowHeaders: true,
     columnSorting: true,
     colHeaders:true,
     search: true,
     allowInsertColumn: true,
     //fixedRowsTop: 1,
     formulas: true,
     //fixedColumnsLeft: 1,
     columnSorting: {
       indicator: true
     },
     autoColumnSize: {
       samplingRatio: 23
     },
     afterChange: function(changes, source) {
     if(changes==null && source=="loadData"){
          let index = 0;
          console.log(this.isEmptyRow(index));
          while (!this.isEmptyRow(index)) {
            index++
          }
        this.selectCell(index, 0);
      }
              	  if(!changes) {
                        $rootScope.orderEdit=true;
                        return;
                    }
              /*    if(changes && changes[0][0]==0){
                           this.getPlugin('search').query(changes[0][3]);
                           this.render();
                    }*/
                  else if(changes.length==1 && source=="edit")
                  $.each(changes, function(index, element) {
                        if(!window.location.href.includes("log")){
                            var row=element[0];
                            var value=element[3];
                            value=value.replace("[","'");
                            value=value.replace("]","/");
                            $rootScope.saveRowOrder(element,row,value);
                       }
                  });
                  },
     mergeCells: true,
     afterPaste:function(changes,source){
         var startRow=source[0].startRow;
         for(var l=0;l<changes.length;l++){
                $rootScope.saveRowData($rootScope.items[startRow]);
                   startRow++;
           }
         },
     contextMenu: true,
     manualRowResize: true,
     manualColumnResize: true,
     manualRowMove: true,
     manualColumnMove: true,
 };

 $rootScope.saveRowData=function(obj){
         $http.post("api/orders/saveCopyPaste?rowId="+obj.id+"&channelId="+window.location.href.split("/")[6]+"&accountId="+$rootScope.sheet.accountId+"&spreadSheetId="+$rootScope.sheet.spreadSheetId,obj).then(function successCallback(response){
                          noty({text:'Data Saved', layout: 'topRight', type: 'success',killer : true,timeout: 1000});
                     }, function errorCallback(response){
                        noty({text: 'There was an error', layout: 'topRight', type: 'error',killer : true,timeout:5000});
                  });
 }

      $rootScope.search=function(value){
      this.getPlugin('search').query(changes[0][3]);
                                 this.render();
       }
}

MainCtrl.$inject = ['$http','$scope','$rootScope','$cookies'];
