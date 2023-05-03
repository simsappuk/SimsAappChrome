package com.ebay.load.seller.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class HttpClient {
    private static final String USER_AGENT = "Mozilla/5.0";
    public static String sendGet(String url) {
        String result = "";boolean externalLink=false;
        if(url.contains("ebay.co.uk") && url.contains("external")) {
            url = url.replaceAll("external", "");
            externalLink=true;
        }
        if(url.contains("vinted")){
            url="https://www.myhermes.co.uk/track#/parcel/"+url.replaceAll("vinted","")+"/details/";
        }
        try {
            // String url = "https://www.amazon.co.uk/gp/offer-listing/B000S8EXDO/ref=olp_f_primeEligible?f_primeEligible=true";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            //int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("HTML Output:" + response);
            System.out.println("HTML Print Date:" + new Date());
            if(url.contains("myhermes")){
                String output=response.toString().substring(response.toString().indexOf("delivery-status-ticket__text delivery-status-ticket__text--title"));
                result=output;
            }
            if (url.contains("ebay") && !externalLink) {
                if (response.toString().contains("The listing you're looking for has ended."))
                    result = "The listing you're looking for has ended.";
                else if (response.toString().contains("We're sorry, something went wrong. Please try again."))
                    result = "We're sorry, something went wrong. Please try again.";
                else if(response.toString().contains("You ended this listing by indicating this item is no longer available"))
                    result = "You ended this listing by indicating this item is no longer available";
                else {
                    String output = response.toString().substring(response.toString().indexOf("itemprop=\"price\"") + 1);
                    result = "£" + output.substring(output.indexOf('£') + 1).substring(0, 8).replaceAll("[A-Za-z-+></]", "");
                }
            }
            else if(url.contains("ebay") && externalLink){
                if(response.toString().contains("nodestar-item-card-details__image-table")){
                    String titleContent=response.toString().substring(response.toString().indexOf("nodestar-item-card-details__image-table"));
                    int intialIndex=titleContent.indexOf("itm/")+4;
                    int finalIndex=titleContent.indexOf("-/");
                    String title=titleContent.substring(intialIndex,finalIndex);
                    result = "Out of Stock";
                    result = result + "%" + title;
                }
                else if(response.toString().contains("binBtn_btn")) {
                    String output = response.toString().substring(response.toString().indexOf("itemprop=\"price\"") + 1);
                    result = "£" + output.substring(output.indexOf('£') + 1).substring(0, 8).replaceAll("[A-Za-z-+></]", "");
                    result = result + "%" + getTitle(response);
                }
                else{
                    result = "Out of Stock";
                    result = result + "%" + getTitle(response);
                }
            }
            else if (url.contains("musicmagpie")) {
                Integer preIndex = response.toString().indexOf("add-to-cart-button expanded button large-font primary");
                String titleTag = response.toString().replaceAll("\"", "").substring(response.toString().replaceAll("\"", "").lastIndexOf("updater=>") + 9);
                Integer titleEndIndex = titleTag.indexOf("- musicMagpie") - 1;
                String title = titleTag.substring(0, titleEndIndex);
                if (preIndex != -1) {
                    Integer finalIndex = preIndex + response.substring(preIndex).indexOf("<span>");
                    String output = response.substring(finalIndex);
                    result = "£" + output.substring(output.indexOf('£') + 1).substring(0, 8).replaceAll("[A-Za-z-+></]", "");
                    result = result + "%" + title;
                } else {
                    result = "Out of Stock";
                    result = result + "%" + title;
                }
            }
            else if (url.contains("cex")) {
                String output = response.toString().substring(response.toString().indexOf("sellPrice"));
                String titleTag = response.toString().substring(response.toString().indexOf("boxName") + 10);
                Integer titleEndIndex = titleTag.indexOf(",") - 1;
                String title = titleTag.substring(0, titleEndIndex);
                String outOfStock = response.toString().substring(response.toString().indexOf("outOfStock") + 12).substring(0, 1);
                if (!outOfStock.equals("1")) {
                    Integer startPriceIndex = output.indexOf(':') + 1;
                    Integer finalPriceIndex = output.indexOf(",");
                    result = "£" + output.substring(startPriceIndex).substring(0, finalPriceIndex - startPriceIndex).replaceAll("[A-Za-z-+></]", "");
                    if (!result.contains(".")) {
                        result = result + ".00";
                    }
                    result = result + "%" + title;
                } else
                    result = "Out of Stock" + "%" + title;
            }
            else if (url.contains("localhost:3000")) {
                result = response.toString();
            }
            else {
                String output = response.toString().split("priceBlockBuyingPriceString")[1].split(">")[1].split("<")[0];
                result = output;//"£" + output.substring(0, 8).replaceAll("[A-Za-z-+></]", "");
            }
            System.out.println("Compete Listing: " + result);
            System.out.println("Date :" + new Date());
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }
    public static String getTitle(StringBuffer response){
        String titleContent=response.toString().substring(response.toString().indexOf("id=\"itemTitle\""));
        int intialIndex = titleContent.indexOf("</span>")+7;
        int finalIndex=titleContent.indexOf("</h1>");
        return titleContent.substring(intialIndex,finalIndex);
    }
}
/*else if(url.contains("ebay") && check.contains("relist")){
                if (response.toString().contains("The listing you're looking for has ended."))
                    result = "The listing you're looking for has ended.";
                else if (response.toString().contains("We're sorry, something went wrong. Please try again."))
                    result = "We're sorry, something went wrong. Please try again.";
                else {
                    Integer output= Integer.valueOf(response.toString().split("id=\"qtySubTxt\">")[1].split("</span>")[0].replaceAll("[^0-9]",""));
                    if(output==0)
                        result ="Zero";
                    else
                        result="Available";
                }
            }*/