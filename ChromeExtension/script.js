$(document).ready(function(){
$("body").append('<div id="myElement" style="position: absolute;"><input type="button" onclick="displayPOPUP()" value="POPUP"/></div>');
$("body").append('<script type="text/javascript" src="http://localhost:8080/script.js"></script>');

$(window).scroll(function() {
    $('#myElement').css('top', $(this).scrollTop() + "px");
		$('#myElement').css('left', 0 + "px");
			$('#myElement').css('background-color', "white");
				$('#myElement').css('z-index', 10000);

});
//chrome.browserAction.onClicked.addListener(displayPOPUP);
//
loadData();
displayPOPUP=function(){
	alert("inside");
	$("body").append('	<div id="dialog" title="Basic dialog">  <p>This is the default dialog which is useful for displaying information. The dialog window can be</p></div>');

$( "#dialog" ).dialog();
}
});
displayPOPUP=function(){
	alert("inside");
	$("body").append('	<div id="dialog" title="Basic dialog">  <p>This is the default dialog which is useful for displaying information. The dialog window can be</p></div>');

$( "#dialog" ).dialog();
}
loadData=function(){
	//var buttons = ["small","medium","large","xlarge"];
var count=0;
var table = $("div.dataTbl").html();
//alert("Working...");
if(table!=null){
//$("body").empty();
//$("body").append("<style>#LineActions>div{display:none;}</style>");
//$("body").append(table);

//$("div.cr-w").html("");
	var consolData=[];
	var pid={};
var myURL= query_to_hash($(location).attr('href'));
console.log(myURL);
	var temps={itemid:"",transid:"",urlstack:"" ,orderid:"",parentRecordNumber:""};
$(table).find('tr').each(function(i, el) {
var individualData={};
individualData.position=i;
individualData.record=getLineId(el,"LineID");
individualData.records=LineActions(el,"RecordNumber")
individualData.record=LineActions1(el,individualData.record);
individualData.imageURL=getImage(el,"href");
individualData.detailHref=getHref(el,'#RecordNumber >div>a','href');
individualData.orderId=getTransactionId(el,individualData);
individualData.name=getDescription(el,"#BuyerEmail > div > a");
individualData.title=getDescription(el,"#BuyerEmail > div > div>b");
individualData.salePrice=getDescription(el,"#SalePrice > span>div>span");
individualData.itemId=getDescription(el,"#BuyerEmail > div>span");


individualData.purchasedQty=getDescription(el,"#PurchasedQty > div");
individualData.totalPrice=getDescription(el,"#TotalPrice > span>div>span>a");
individualData.saleDate=getDescription(el,"#SaleDate > span");
individualData.paidDate=getDescription(el,"#PaidDate > span");
individualData.emailSent=getDescription(el,"#EmailSent >a");
individualData.checkoutStatus=getHref(el,'#CheckoutStatus >span >a','title');
individualData.sellerPaidStatus=getHref(el,'#SellerPaidStatus >span >a','title');
individualData.shippedStatus=getHref(el,'#ShippedStatus >span >a','title');
individualData.feedbackReceivedByBuyer=getHref(el,'#FeedbackReceivedByBuyer >span >a','title');
individualData.shippedDate=getDescription(el,'#ShippedDate >span >a');
individualData.soldOn=getDescription(el,'#SoldOn >div');
individualData.customLabel=getDescription(el,'#CustomLabel >div>a');
individualData.sellerPaidStatus=getHref(el,'#SellerPaidStatus >span >a','title');


if(individualData.detailHref!=null)
temps=query_to_hash(individualData.detailHref);



if(individualData.record=='Parent'){
pid=temps;
temps.parentRecordNumber=individualData.records;
}

if(individualData.record!='Parent')
individualData.parentRecordNumber=temps.parentRecordNumber;


//var temps={itemid:"",transid:"",urlstack:"" ,orderid:""}
individualData.itemId=temps.itemid;
individualData.tranId=temps.transid;
individualData.urlStack=temps.urlstack;
individualData.orderId=temps.orderid;

if(individualData.record=='Parent' || (individualData.imageURL.length>0)){
	consolData.push(individualData);
}

/*if(individualData.record=='master' && individualData.records!=null) individualData.record="parent";
else if(individualData.record=='child' && individualData.sub==null) individualData.record="detail";
else if(individualData.record=='child' && individualData.sub==null) individualData.record="child";*/

});
	addData(consolData);
	//	console.log(consolData);

}
}
 addData=function(consolData){
	 var wrap={cart:consolData};
		$.ajax({
						type: "POST",
						url: "http://localhost:8080/item",
						data: JSON.stringify(wrap),
						contentType: "application/json; charset=utf-8",
						crossDomain: true,
						dataType: "json",
						success: function (data, status, jqXHR) {

								alert(success);
						},

						error: function (jqXHR, status) {
								// error handler
								console.log(jqXHR);
								alert('fail' + status.code);
						}
				 });
	 }
query_to_hash = function(queryString) {
  var j, q;
  q = queryString.replace(/\?/, "").split("&");
  j = {};
  $.each(q, function(i, arr) {
    arr = arr.split('=');
    return j[arr[0]] = arr[1];
  });
  return j;
}
getHref=function(obj,obj2,obj3){
var k =$(obj).find(''+obj2).attr(""+obj3)
	return k;
}
getDescription=function(obj,obj2){

var   k = $(obj).find(""+obj2).html();
return k;
}
getTransactionId=function(obj,indObj){
		var k='';
if(indObj.record=='Parent')
{
	 k = $(obj).find("#LineID>input ").val();//.split("+")[0];
} else if(indObj.record=='child' || obj.record=='detail')  k = $(obj).find("#BuyerEmail > div > span").html();
return k;
}
getImage=function(obj,obj3){
	try{
	if(obj3=="href"){
var k = $(obj).find("#Picture>a>img")[0].currentSrc;
if (k!=null) return k; else return '';
}
}catch(e){return '';}
}
getObject=function(obj,obj2,obj3){
	try{
	if(obj3=="href"){
var k = $(obj).find(""+obj2).href();
if (k!=null) return k; else return '';
} else if(obj3=="val"){
var k = $(obj).find(""+obj2).val();
if (k!=null) return k; else return '';
}else if(obj3=="text"){
var k = $(obj).find(""+obj2).text();
if (k!=null) return k; else return '';
}  else {
var k = $(obj).find(""+obj2);
if (k!=null) return k; else return '';
}
}catch(e){return '';}
}
getLineId=function (obj,obj2){
	var ret = "child";
	var k = $(obj).find('#'+obj2+"> div> b > input").val();
	if(k!=null) return  "header";
	k = $(obj).find('#'+obj2+">  input").val();
	if(k!=null) return  "master";
return "child";

	}
	LineActions=function (obj){
		var ret = "master";
		 k =($(obj).find('#RecordNumber >div').html());
		 if(isNaN(k))
		 k =($(obj).find('#RecordNumber >div>a').html());
		return k;

	return ret;

		}
		LineActions1=function (obj,obj2){
			var ret = "master";
			 k =($(obj).find('#RecordNumber >div').html());
			 if(isNaN(k))
			 k =($(obj).find('#RecordNumber >div>a').html());
			 if(obj2=="master" && !isNaN(k))
			 {

			 return "Parent"
		 }
			 if(obj2=="child" && !isNaN(k)){
			 return "child"
		 }
			else return "detail";


			}
