package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.repository.OrdersRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class AmazonService
{

    @Autowired
    OrdersRepository ordersRepository;


    public ResponseEntity<List<Orders>> getAmazonOrders(String accountId, Pageable p) {
        List<Orders> orders=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderType(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,"Amazon");
        return new ResponseEntity<List<Orders>>().withResults(orders).withTotalPages(p.getPageNumber()).withTotalElements((long) orders.size());
    }

    public ResponseEntity<List<Orders>> getOrderNumbers(String ownerId,String accountId,Pageable p) throws JSONException, NoSuchAlgorithmException, InvalidKeyException, IOException {

        String timeStamp = getTimeStamp();
        String stringToSign = "POST\n" +
                "mws.amazonservices.co.uk\n" +
                "/Orders/2013-09-01\n" +
                "AWSAccessKeyId=AKIAIMPA2Z6BLBLNNNWA&Action=ListOrders&CreatedAfter="+dateToString()+"T18%3A30%3A00Z&MWSAuthToken=amzn.mws.20b88e47-32ed-4e6e-a346-01c712557166&MarketplaceId.Id.1=A1F83G8C2ARO7P&SellerId=A1NDKQE04GWFJX&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=" + URLEncoder.encode(timeStamp, "UTF-8") + "&Version=2013-09-01";

        String xmlResponse =returnXmlResponse("https://mws.amazonservices.co.uk/Orders/2013-09-01?AWSAccessKeyId=AKIAIMPA2Z6BLBLNNNWA&Action=ListOrders&CreatedAfter="+dateToString()+"T18%3A30%3A00Z&MWSAuthToken=amzn.mws.20b88e47-32ed-4e6e-a346-01c712557166&MarketplaceId.Id.1=A1F83G8C2ARO7P&SellerId=A1NDKQE04GWFJX&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=" + URLEncoder.encode(timeStamp, "UTF-8") + "&Version=2013-09-01&Signature=" + URLEncoder.encode(returnSignature(stringToSign), "UTF-8"));

        ArrayList<String> orderNumbers=new ArrayList<String>();
        for(int i=0;i<=xmlResponse.split("</Order>").length-2;i++) {
            String orderId=xmlResponse.split("</Order>")[i].split("<AmazonOrderId>")[1].split("</AmazonOrderId>")[0];
            orderNumbers.add(orderId);
            getOrderInformation(orderNumbers,accountId,ownerId);
        }

        List<Orders> orders=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderType(ownerId,accountId,"Amazon");
        return new ResponseEntity<List<Orders>>().withResults(orders).withTotalPages(p.getPageNumber()).withTotalElements((long) orders.size());

    }

    public  void getOrderInformation(List<String> orderNumbers,String accountId,String ownerId ) throws IOException, InvalidKeyException, NoSuchAlgorithmException, JSONException {
        String timeStamp = getTimeStamp();
        for(int i=0;i<orderNumbers.size();i++) {
            String stringToSignOrderStatusInfo = "POST\n" +
                    "mws.amazonservices.co.uk\n" +
                    "/Orders/2013-09-01\n" +
                    "AWSAccessKeyId=AKIAIMPA2Z6BLBLNNNWA&Action=GetOrder&AmazonOrderId.Id.1="+orderNumbers.get(i)+"&MWSAuthToken=amzn.mws.20b88e47-32ed-4e6e-a346-01c712557166&SellerId=A1NDKQE04GWFJX&SignatureMethod=HmacSHA256&SignatureVersion=2"+"&Timestamp=" + URLEncoder.encode(timeStamp, "UTF-8")+"&Version=2013-09-01";
            String xmlResponse =returnXmlResponse("https://mws.amazonservices.co.uk/Orders/2013-09-01?AWSAccessKeyId=AKIAIMPA2Z6BLBLNNNWA&Action=GetOrder&AmazonOrderId.Id.1="+orderNumbers.get(i)+"&MWSAuthToken=amzn.mws.20b88e47-32ed-4e6e-a346-01c712557166&SellerId=A1NDKQE04GWFJX&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=" + URLEncoder.encode(timeStamp, "UTF-8") + "&Version=2013-09-01&Signature=" + URLEncoder.encode(returnSignature(stringToSignOrderStatusInfo), "UTF-8"));
            JSONObject orderStatusObject = new JSONObject(returnJSONString(xmlResponse));
            String stringToSignOrderTitleInfo="POST\n" +
                    "mws.amazonservices.co.uk\n" +
                    "/Orders/2013-09-01\n" +
                    "AWSAccessKeyId=AKIAIMPA2Z6BLBLNNNWA&Action=ListOrderItems&AmazonOrderId="+orderNumbers.get(i)+"&MWSAuthToken=amzn.mws.20b88e47-32ed-4e6e-a346-01c712557166&SellerId=A1NDKQE04GWFJX&SignatureMethod=HmacSHA256&SignatureVersion=2"+"&Timestamp=" + URLEncoder.encode(timeStamp, "UTF-8")+"&Version=2013-09-01";
            String xmlResponseOrderTitleInfo =returnXmlResponse("https://mws.amazonservices.co.uk/Orders/2013-09-01?AWSAccessKeyId=AKIAIMPA2Z6BLBLNNNWA&Action=ListOrderItems&AmazonOrderId="+orderNumbers.get(i)+"&MWSAuthToken=amzn.mws.20b88e47-32ed-4e6e-a346-01c712557166&SellerId=A1NDKQE04GWFJX&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=" + URLEncoder.encode(timeStamp, "UTF-8") + "&Version=2013-09-01&Signature=" + URLEncoder.encode(returnSignature(stringToSignOrderTitleInfo), "UTF-8"));
            JSONObject orderTitleObject = new JSONObject(returnJSONString(xmlResponseOrderTitleInfo));
            saveOrders(orderStatusObject.getJSONObject("GetOrderResult").getJSONObject("Orders").getJSONObject("Order"),orderTitleObject.getJSONObject("ListOrderItemsResult").getJSONObject("OrderItems").getJSONObject("OrderItem"),accountId,ownerId);
        }
    }

    public  String returnSignature(String stringToSign) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
       return sign(stringToSign, "NbJNzRD326EiiA2AqHgDt01ds3bjs5OZ7FsimtRF");

    }
    public  String returnXmlResponse(String signatureUrl) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(signatureUrl);
        CloseableHttpResponse response = client.execute(httpPost);
        return IOUtils.toString(response.getEntity().getContent(), "UTF-8");
    }

    private  String returnJSONString(String xmlResponse) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(xmlResponse.getBytes());
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(node);
    }


    public  void saveOrders(JSONObject orderInfo, JSONObject orderTitleInfo,String accountId,String ownerId) throws JSONException {
        List<Orders> list=ordersRepository.findByExtendedOrderId(orderInfo.getString("AmazonOrderId"));
        if(list.size()==0) {
            Orders orders = new Orders();
            orders.setAccountId(accountId);
            orders.setOwnerIdPk(ownerId);
            orders.setExtendedOrderId(orderInfo.getString("AmazonOrderId"));
            if (orderInfo.has("OrderStatus") && orderInfo.getString("OrderStatus").equals("Shipped"))
                orders.setOrderStatus("Dispatched");
            if(orderInfo.has("BuyerName"))
            orders.setBuyerName(orderInfo.getString("BuyerName"));
            if(orderTitleInfo.has("Title"))
                orders.setTitle(orderTitleInfo.getString("Title"));
            if(orderTitleInfo.has("QuantityOrdered"))
                orders.setQuantityPurchased(orderTitleInfo.getString("QuantityOrdered"));
            if(orderInfo.has("BuyerEmail"))
                orders.setBuyerEmailId(orderInfo.getString("BuyerEmail"));
            if(orderTitleInfo.has("ShippingPrice"))
                orders.setShippingServiceCost(orderTitleInfo.getJSONObject("ShippingPrice").getString("Amount"));
            if(orderTitleInfo.has("ItemPrice"))
                orders.setSubTotal(orderTitleInfo.getJSONObject("ItemPrice").getString("Amount"));
            if(orderInfo.has("OrderTotal"))
            orders.setTotalAmount(orderInfo.getJSONObject("OrderTotal").getString("Amount"));
            if(orderInfo.has("ShippingAddress") && orderInfo.getJSONObject("ShippingAddress").has("AddressLine1"))
                orders.setBuyerStreet1(orderInfo.getJSONObject("ShippingAddress").getString("AddressLine1"));
            if(orderInfo.has("ShippingAddress") && orderInfo.getJSONObject("ShippingAddress").has("AddressLine2"))
                orders.setBuyerStreet2(orderInfo.getJSONObject("ShippingAddress").getString("AddressLine2"));
            if(orderInfo.has("OrderTotal") && orderInfo.getJSONObject("ShippingAddress").has("PostalCode"))
                orders.setBuyerPostalCode(orderInfo.getJSONObject("ShippingAddress").getString("PostalCode"));
            if(orderInfo.has("OrderTotal")&& orderInfo.getJSONObject("ShippingAddress").has("City"))
                orders.setBuyerCity(orderInfo.getJSONObject("ShippingAddress").getString("City"));
            orders.setOrderType("Amazon");
            ordersRepository.save(orders);
        }

    }

    private  String sign(String data, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IllegalStateException, UnsupportedEncodingException
    {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes("UTF-8"),
                "HmacSHA256"));
        byte[] signature = mac.doFinal(data.getBytes("UTF-8"));
        String signatureBase64 = new String(Base64.encodeBase64(signature),
                "UTF-8");
        return signatureBase64;
    }


    private  String getTimeStamp()
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }

    public String dateToString(){
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return  dateFormat.format(date);

    }

}