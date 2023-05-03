package com.ebay.load.seller.service;


import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.converter.EbayActiveListingConverter;
import com.ebay.load.seller.converter.EbayOrdersConverter;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.dto.EbayOrders;
import com.ebay.load.seller.model.*;
import com.ebay.load.seller.repository.*;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.PdfEntity;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.call.*;
import com.ebay.soap.eBLBaseComponents.*;
import com.itextpdf.text.DocumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EbayService {

    @Value("${ebay.api.key}")
    String token;

    @Value("${ebay.api.wsdl}")
    String wsdlURL;

    @Autowired
    EbayActiveListingConverter ebayActiveListingConverter;

    @Autowired
    EbayOrdersConverter ebayOrdersConverter;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    ActiveListingRepository activeListingRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    UpdateOrdersRepository updateOrdersRepository;

    @Autowired
    DropshipRepository dropshipRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    RelistRepository relistRepository;


    @Autowired
    ResetRepository resetRepository;

    @Autowired
    RelistService relistService;

    @Autowired
    StockService stockService;

    @Autowired
    ActivityLogService activityLogService;

    @Autowired
    HTMLToPDFService htmlToPDFService;

    @Autowired
    MagpiePdfService magpiePdfService;

    @Autowired
    PdfRepository pdfRepository;


//ActiveListing

    public ResponseEntity < ItemType > postNewListing(String accountId, EbayListing listingDetails) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, SessionUserInfo.getLoggedInUser().getUser().getId());
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        apiContext.setApiLogging(new ApiLogging());
        try {
            AddItemCall call = new AddItemCall(apiContext);
            AddFixedPriceItemCall call2= new AddFixedPriceItemCall(apiContext);
            ItemType itemType=geItem("112601619064", s);
            itemType.setItemID(null);
            AmountType amount = new AmountType();
            amount.setValue(itemType.getStartPrice().getValue());
            itemType.setBuyItNowPrice(amount);
            itemType.setCountry(CountryCodeType.GB);
            itemType.setListingDuration(String.valueOf(ListingDurationCodeType.GTC));
            itemType.setCurrency(CurrencyCodeType.GBP);
            //itemType.setReservePrice(null);
            call.setItem(itemType);
            call.setAutoSetItemUUID(true);
            FeesType fees;
            /*VerifyAddItemCall verify = new VerifyAddItemCall(apiContext);
            verify.setItem(getItem(listingDetails));*/
            VerifyAddFixedPriceItemCall verifyAddFixedPriceItemCall=new VerifyAddFixedPriceItemCall(apiContext);
            fees=verifyAddFixedPriceItemCall.verifyAddFixedPriceItem();
            System.out.print(fees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ItemType getItem(EbayListing listingDetails) {
        ItemType item = new ItemType();
        item.setTitle(listingDetails.getTitle());
        AmountType amount = new AmountType();
        amount.setValue(Double.parseDouble(listingDetails.getBuyItNowPriceValue()));
        item.setStartPrice(amount);
        //item.setBuyItNowPrice(amount);
        item.setDescription(listingDetails.getDescription());
        item.setConditionID(Integer.valueOf(listingDetails.getConditionID()));
        CategoryType c = new CategoryType();
        c.setCategoryID(listingDetails.getPrimaryCategoryID());
        item.setPrimaryCategory(c);
        item.setCategoryMappingAllowed(true);
        if (listingDetails.getSku() != null)
            item.setSKU(listingDetails.getSku());
        //Standard
        item.setCurrency(CurrencyCodeType.GBP);
        item.setCountry(CountryCodeType.GB);
        item.setSite(SiteCodeType.UK);
        item.setListingDuration("GTC");
        item.setListingType(ListingTypeCodeType.FIXED_PRICE_ITEM);
        item.setLocation("Leicester, LE5 5DB, United Kingdom");
        /*BuyerPaymentMethodCodeType[] arrPaymentMethods =
                new BuyerPaymentMethodCodeType[] {
                        BuyerPaymentMethodCodeType.PAY_PAL
                };*/
        //item.setPayPalEmailAddress("paypal@poundmonkey.com");
        //item.setPaymentMethods(arrPaymentMethods);
        item.setPostalCode("LE5 5DB");
        //Pic
        item.setQuantity(listingDetails.getQuantityAvailable());
        PictureDetailsType pic = new PictureDetailsType();
        pic.setPictureURL(new String[] {
                listingDetails.getImageUrl()
        });
        item.setPictureDetails(pic);
        ReturnPolicyType returnPolicyType = new ReturnPolicyType();
        returnPolicyType.setReturnsAcceptedOption("ReturnsAccepted");
        returnPolicyType.setReturnsWithin("30 days");
        returnPolicyType.setReturnsWithinOption("Days_30");
        returnPolicyType.setShippingCostPaidByOption("Buyer");
        returnPolicyType.setShippingCostPaidBy("Buyer");
        item.setReturnPolicy(returnPolicyType);
        //item.setDispatchTimeMax(3);
        ShippingDetailsType shippingDetailsType = new ShippingDetailsType();
        shippingDetailsType.setShippingType(ShippingTypeCodeType.FLAT);
        ShippingServiceOptionsType shippingServiceOptionsType = new ShippingServiceOptionsType();
        shippingServiceOptionsType.setShippingService("UK_RoyalMailFirstClassStandard");
        AmountType amountType = new AmountType();
        amountType.setValue(1.50);
        shippingServiceOptionsType.setShippingServiceCost(amountType);
        shippingServiceOptionsType.setShippingServicePriority(1);
        shippingDetailsType.setShippingServiceOptions(new ShippingServiceOptionsType[] {
                shippingServiceOptionsType
        });
        item.setShippingDetails(shippingDetailsType);
  /*
        //BrandAndMPNAndEAN
        BrandMPNType brandMPNType=new BrandMPNType();
        if(listingDetails.getBrand()!=null)
        brandMPNType.setBrand(listingDetails.getBrand());
        if(listingDetails.getMpn()!=null)
        brandMPNType.setMPN(listingDetails.getMpn());
        ProductListingDetailsType productListingDetailsType= new ProductListingDetailsType();
        productListingDetailsType.setEAN(listingDetails.getEan());
        productListingDetailsType.setBrandMPN(brandMPNType);
        item.setProductListingDetails(productListingDetailsType);
        //SellerProfiles
        SellerProfilesType sellerProfilesType= new SellerProfilesType();
        SellerShippingProfileType sellerShippingProfileType=new SellerShippingProfileType();
        //sellerShippingProfileType.setShippingProfileID(123476327020L);
        sellerShippingProfileType.setShippingProfileName("Tracked");
        sellerProfilesType.setSellerShippingProfile(sellerShippingProfileType);
        SellerPaymentProfileType sellerPaymentProfileType=new SellerPaymentProfileType();
        //sellerPaymentProfileType.setPaymentProfileID(57297565020L);
        sellerPaymentProfileType.setPaymentProfileName("PayPal");
        sellerProfilesType.setSellerPaymentProfile(sellerPaymentProfileType);
        SellerReturnProfileType sellerReturnProfileType=new SellerReturnProfileType();
       // sellerReturnProfileType.setReturnProfileID(70990666020L);
        sellerReturnProfileType.setReturnProfileName("Return Accepted");
        sellerProfilesType.setSellerReturnProfile(sellerReturnProfileType);
        item.setSellerProfiles(sellerProfilesType);*/
        return item;
    }

    public ResponseEntity < EbayListing > reviseSetNewSKU(String accountId, String ownerId, String itemId, String sku) {
        try {
            Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
            ApiContext apiContext = new ApiContext();
            ApiCredential cred = apiContext.getApiCredential();
            cred.seteBayToken(s.getApiToken());
            apiContext.setApiServerUrl(s.getUrl());
            apiContext.setSite(SiteCodeType.UK);
            apiContext.setApiLogging(new ApiLogging());
            ReviseItemCall call = new ReviseItemCall(apiContext);
            ItemType item = new ItemType();
            item.setItemID(itemId);
            item.setSKU(sku);
            call.setItemToBeRevised(item);
            ActiveListings listing = activeListingRepository.findByOwnerIdAndAccountIdAndItemId(ownerId, accountId, itemId);
            listing.setSku(sku);
            activeListingRepository.save(listing);
            Stock stock = stockRepository.findByOwnerIdAndItemIdAndAccountId(ownerId,  itemId, accountId);
            if (stock != null) {
                stock.setSku(Collections.singletonList(sku));
                stockRepository.save(stock);
            }
            call.reviseItem();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        return null;
    }

    public ResponseEntity < EbayListing > reviseExistingListing(String accountId, String ownerId, String itemId, String amountValue, String quantity) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        apiContext.setApiLogging(new ApiLogging());
        try {
            ReviseItemCall call = new ReviseItemCall(apiContext);
            ItemType item = new ItemType();
            item.setItemID(itemId);
            AmountType amount = new AmountType();
            if (!amountValue.equals("null") && !amountValue.equals("undefined")) {
                amount.setValue(Double.parseDouble(amountValue));
                amount.setCurrencyID(CurrencyCodeType.GBP);
                item.setStartPrice(amount);
            }
            if (quantity != null && !quantity.equals("undefined"))
                item.setQuantity(Integer.valueOf(quantity));
            call.setItemToBeRevised(item);
            System.out.println(item.getItemID());
            call.reviseItem();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        EbayListing result = new EbayListing();
        result.setDescription("UPDATE SUCCESS!");
        return new ResponseEntity < EbayListing > ().withResults(result);
    }

    public ResponseEntity < EbayListing > reviseVariantListing(String accountId, String ownerId, String itemId, String amountValue, String quantity, String firstVariantName, String firstVariantValue, String secondVariantName, String secondVariantValue, String variantSku) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        apiContext.setApiLogging(new ApiLogging());
        try {
            ReviseFixedPriceItemCall call = new ReviseFixedPriceItemCall(apiContext);
            ItemType item = new ItemType();
            item.setItemID(itemId);
            VariationsType variations = new VariationsType();
            VariationType variation = new VariationType();
            if (variantSku != null && !variantSku.equals("undefined"))
                variation.setSKU(variantSku);
            NameValueListArrayType variationSpecifics = new NameValueListArrayType();
            NameValueListType firstVariationNameAndValue = new NameValueListType();
            NameValueListType secondVariationNameAndValue = new NameValueListType();
            AmountType amount = new AmountType();
            if (!amountValue.equals("null") && !amountValue.equals("undefined")) {
                amount.setValue(Double.parseDouble(amountValue));
                amount.setCurrencyID(CurrencyCodeType.GBP);
                variation.setStartPrice(amount);
            }
            if (quantity != null && !quantity.equals("undefined"))
                variation.setQuantity(Integer.valueOf(quantity));

            if (!firstVariantName.equals("null") && !firstVariantName.equals("undefined")) {
                firstVariationNameAndValue.setName(firstVariantName);
                firstVariationNameAndValue.setValue(new String[] {
                        String.valueOf(firstVariantValue)
                });
                if (!secondVariantName.equals("null") && !secondVariantName.equals("undefined")) {
                    secondVariationNameAndValue.setName(secondVariantName);
                    secondVariationNameAndValue.setValue(new String[] {
                            secondVariantValue
                    });
                    variationSpecifics.setNameValueList(new NameValueListType[] {
                            firstVariationNameAndValue,
                            secondVariationNameAndValue
                    });
                }
                else
                    variationSpecifics.setNameValueList(new NameValueListType[] {
                            firstVariationNameAndValue
                    });

                variation.setVariationSpecifics(variationSpecifics);
                variations.setVariation(new VariationType[] {
                        variation
                });
                item.setVariations(variations);
            }
            call.setItemToBeRevised(item);
            call.reviseFixedPriceItem();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        EbayListing result = new EbayListing();
        result.setDescription("UPDATE SUCCESS!");
        return new ResponseEntity < EbayListing > ().withResults(result);
    }

    public ResponseEntity < List < ActiveListings >> findAllByOwnerId(String ownerId, String accountId, Pageable p) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        return updateDatabase(s, p);
    }

    public ResponseEntity < List < ActiveListings >> updateDatabase(Accounts s, Pageable p) {
        boolean processing = true;
        int totLoaded = 0;
        int currentPage = 1;
        List < ActiveListings > data = activeListingRepository.findByOwnerIdAndAccountId(s.getOwnerIdPk(), s.getId());
        if (data.size() != 0)
            activeListingRepository.deleteByAccountId(s.getId());
        do {
            ResponseEntity < List < EbayListing >> ebay = displayItems(p, s);
            if (ebay.isErrors() == true)
                return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(ebay.getMessages().get(0).getMessageText()));
            saveListings(ebay, s);
            totLoaded += ebay.getResults().size();
            if (totLoaded >= ebay.getTotalElements()) processing = false;
            if (totLoaded < ebay.getTotalElements()) {
                currentPage++;
                p = new PageRequest(currentPage, p.getPageSize());
            }
        } while (processing);
        Page < ActiveListings > list = activeListingRepository.findByOwnerIdAndAccountId(s.getOwnerIdPk(), s.getId(), p);
        return new ResponseEntity < List < ActiveListings >> ().withResults(list.getContent()).withTotalPages(list.getTotalPages()).withTotalElements(list.getTotalElements());
    }

    private void saveListings(ResponseEntity < List < EbayListing >> ebay, Accounts s) {
        for (int i = 0; i < ebay.getResults().size(); i++) {
            ActiveListings a = new ActiveListings();
            a.setConditionDisplayName(ebay.getResults().get(i).getConditionDisplayName());
            a.setEan(ebay.getResults().get(i).getEan());
            a.setTitle(ebay.getResults().get(i).getTitle());
            a.setStartPriceValue(ebay.getResults().get(i).getBuyItNowPriceValue());
            a.setImageUrl(ebay.getResults().get(i).getImageUrl());
            a.setItemId(ebay.getResults().get(i).getItemId());
            a.setStartTime(ebay.getResults().get(i).getStartTime());
            a.setRoundOffPrice((int)(Float.parseFloat(a.getStartPriceValue()) * 100));
            a.setPrimaryCategoryName(ebay.getResults().get(i).getPrimaryCategoryName());
            a.setQuantityAvailable(ebay.getResults().get(i).getQuantityAvailable());
            a.setReturns(ebay.getResults().get(i).getReturns());
            if (ebay.getResults().get(i).getSku() != null) {
                a.setSku(ebay.getResults().get(i).getSku());
                String sku = a.getSku();
                try {
                    a.setSkuNumber(Integer.valueOf(sku.substring(sku.lastIndexOf("-") + 1).replaceAll("\\s", "").replaceAll("[A-Za-z-+></]", "")));
                } catch (Exception e) {}
            }
            a.setAccountId(s.getId());
            a.setStartPriceCurrencyId(ebay.getResults().get(i).getBuyItNowPriceCurrencyID());
            a.setOwnerId(s.getOwnerIdPk());
            activeListingRepository.saveAndFlush(a);
        }
    }

    public ResponseEntity < List < ActiveListings >> findAll(String ownerId, String accountId, Pageable p) {
        Page < ActiveListings > a = activeListingRepository.findByOwnerIdAndAccountId(ownerId, accountId, p);
        return new ResponseEntity < List < ActiveListings >> ().withResults(a.getContent()).withTotalPages(a.getTotalPages()).withTotalElements(a.getTotalElements());
    }

    public ResponseEntity < EbayListing > findStockById(String ownerId, String accountId, String itemId) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        ItemType item = geItem(itemId, s);
        if (item != null) {
            EbayListing k = ebayActiveListingConverter.converTo(item);
            //stockService.saveListingDetailsToStock(k,s);
            return new ResponseEntity < EbayListing > ().withResults(k);
        }
        else
            return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("IAF Token Supplied is Expired"));
    }

    public ResponseEntity < List < ActiveListings >> sortByOwnerId(String ownerId, String accountId, Pageable p) {
        List < ActiveListings > list = activeListingRepository.findByOwnerIdAndAccountId(ownerId, accountId, p.getSort());
        return paginateResult(list, p);
    }

    //Orders

    public ResponseEntity < List < Orders >> refreshAllOrdersByOwnerId(String ownerId, String accountId, Pageable p, Date d1, Date d2) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        return displayOrdersItems(p, s, d1, d2, ownerId);
    }

    ResponseEntity < List < Orders >> displayOrdersItems(Pageable pg, Accounts s, Date d1, Date d2, String ownerId) {
        OrderPackage op = new OrderPackage();
        op.setCurrentPage(pg.getPageNumber());
        do {
            try {
                op = getOrders(op, op.getCurrentPage(), pg.getPageSize(), s, d1, d2, ownerId);
            } catch (Exception e) {
                return new ResponseEntity < List < Orders >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
            }
            if (op == null)
                return new ResponseEntity < List < Orders >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("IAF Token is Invalid or Expired"));
        }
        while (op.getHasMoreOrders());
        updateOrders(op.getTotalOrders(), s.getId());
        return data(ownerId, s, pg, d1, d2);
    }

    public ResponseEntity < List < Orders >> ebayOrdersFromDB() {
        List < Orders > o = ordersRepository.findByLastEffectiveDateAndOrderType(null, "EBAY");
        return new ResponseEntity < List < Orders >> ().withResults(o).withTotalPages(1).withTotalElements(Long.valueOf(o.size()));
    }

    public ResponseEntity < EbayOrders > findStockThroughId(String ownerId, String accountId, String orderId, String itemId) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        EbayOrders e = ebayActiveListingConverter.converTo(this.geItem(itemId, s));
        return new ResponseEntity < EbayOrders > ().withResults(e);
    }

    public ResponseEntity < List < Orders >> findAllOrdersByOwnerId(String ownerId, String accountId, Pageable p) {
        return sortOrders(ownerId, accountId, new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public ResponseEntity < List < Orders >> getAllIndividualOrders(String ownerId, String accountId, Pageable p) {
        return sortAwaitingDispatchOrders(ownerId, accountId, new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public ResponseEntity < List < Orders >> getOrdersIncludingBundles(String ownerIdPk, String accountId, Integer fromRecordNo, Integer toRecordNo) {
        List < Orders > orders = ordersRepository.findByOwnerIdPkAndAccountIdAndRecordNumRangeAndOrderStatus(ownerIdPk, accountId, fromRecordNo, toRecordNo, "AwaitingDispatch");
        for (int i = 0; i < orders.size(); i++) {
            String remove = orders.get(i).getTitle();
            if (orders.get(i).getTransactionArrayLength() != 1) {
                int index = remove.indexOf("[");
                if (index > 0)
                    remove = remove.substring(0, index);
            }
            orders.get(i).setTitle(remove);
            Page < Orders > o = ordersRepository.findByAccountIdAndPOrderRefAndLastEffectiveDate(accountId, orders.get(i).getOrderRef(), null, new PageRequest(0, 200));
            for (int j = 0; j < o.getContent().size(); j++)
                if (orders.get(i).getItemId() != null && orders.get(i).getItemId().equals(o.getContent().get(j).getItemId()))
                    o.getContent().get(j).setPickingType("show");
            for (int k = 0; k < o.getContent().size(); k++)
                if (o.getContent().get(k).getPickingType() == null)
                    orders.get(i).setPickingType("Dont show");
                else
                    orders.get(i).setPickingType("show");
        }
        return new ResponseEntity < > ().withResults(orders);
    }

    private Sort orderBy(String sortBy) {
        String[] s = sortBy.split("_");
        if (s[1].equals("asc"))
            return new Sort(Sort.Direction.ASC, s[0]);
        else
            return new Sort(Sort.Direction.DESC, s[0]);
    }

    public ResponseEntity < List < Orders >> findAllAwaitingDispatchOrders(String ownerId, String accountId, Pageable p) {
        return sortAwaitingDispatchOrders(ownerId, accountId, new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public ResponseEntity < List < Orders >> findAllAwaitingPaymentOrders(String ownerId, String accountId, Pageable p) {
        return sortAwaitingPaymentOrders(ownerId, accountId, new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public ResponseEntity < List < Orders >> findAllPaidAndDispatchedOrders(String ownerId, String accountId, Pageable p) {
        return sortPaidAndDispatchedOrders(ownerId, accountId, new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public ResponseEntity < List < Orders >> findAllResetListingOrders(String ownerId, String accountId, Pageable p) {
        return sortResetListingOrders(ownerId, accountId, new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public ResponseEntity < List < Orders >> findAllResetListingOrdersByDate(String ownerId, String accountId,Date date,Date nextDate, Pageable p) {
        return sortResetListingOrdersByDate(ownerId, accountId,date,nextDate,new PageRequest(p.getPageNumber(), p.getPageSize(), orderBy("orderRef_desc")));
    }

    public OrderType[] getOrdersByOrderId(String ownerId, String accountId, String orderId) {
        Accounts s = accountsRepository.findByIdAndOwnerIdPk(accountId, ownerId);
        return geOrder(orderId, s);
    }

    public ResponseEntity<List<Orders>> orderContent(Integer orderRef,String accountId,Pageable p){
        Page<Orders> o=ordersRepository.findByAccountIdAndPOrderRefAndLastEffectiveDate(accountId,orderRef,null,p);
        for(int i=0;i<o.getContent().size();i++) {
            String title=o.getContent().get(i).getTitle().toLowerCase().replaceAll("\\s","");
            if(title.contains("["))
                title=title.split("\\[")[0];
            if (title.contains("xbox360/xboxone")||title.contains("xboxone/xbox360") )
                o.getContent().get(i).setCategory("XBox 360 / Xbox One");
            else if (title.contains("xboxone"))
                o.getContent().get(i).setCategory("Xbox One");
            else if (title.contains("xbox360"))
                o.getContent().get(i).setCategory("Xbox 360");
            else if (title.contains("xbox"))
                o.getContent().get(i).setCategory("Xbox");
            else if(title.contains("dvd"))
                o.getContent().get(i).setCategory("DVD");
            else if(title.contains("wii") && !title.contains("wiiu"))
                o.getContent().get(i).setCategory("WII");
            else if(title.contains("wiiu"))
                o.getContent().get(i).setCategory("WII-U");
            else if(title.contains("psvita"))
                o.getContent().get(i).setCategory("PS-VITA");
            else if(title.contains("nintendo") && title.contains("3ds"))
                o.getContent().get(i).setCategory("Nintendo 3DS");
            else if(title.contains("nintendo") && title.contains("ds"))
                o.getContent().get(i).setCategory("Nintendo DS");
            else if(title.contains("ps3")) {
                if(!title.contains("blackops3"))
                    o.getContent().get(i).setCategory("PS3");
                else if(title.replaceAll("blackops3","").contains("ps3"))
                    o.getContent().get(i).setCategory("PS3");
                else if(title.replaceAll("blackops3","").contains("ps4"))
                    o.getContent().get(i).setCategory("PS4");
            }
            else if(title.contains("ps4")) {
                if(!title.contains("blackops4"))
                    o.getContent().get(i).setCategory("PS4");
                else if(title.replaceAll("blackops4","").contains("ps4"))
                    o.getContent().get(i).setCategory("PS4");
                else if(title.replaceAll("blackops4","").contains("ps3"))
                    o.getContent().get(i).setCategory("PS3");
            }
        }
            return new ResponseEntity<List<Orders>>().withResults(o.getContent()).withTotalElements(o.getTotalElements()).withTotalPages(o.getTotalPages());
    }


    public String getOrderCateory(String title,Orders o){
        title=title.toLowerCase().replaceAll("\\s","");
        if (title.contains("xbox360/xboxone")||title.contains("xboxone/xbox360") )
            o.setCategory("Xbox 360 / Xbox One");
        else if (title.contains("xboxone"))
            o.setCategory("Xbox One");
        else if (title.contains("xbox360"))
            o.setCategory("Xbox 360");
        else if (title.contains("xbox"))
            o.setCategory("Xbox");
        else if(title.contains("wii") && !title.contains("wiiu"))
            o.setCategory("WII");
        else if(title.contains("wiiu"))
            o.setCategory("WII-U");
        else if(title.contains("dvd"))
            o.setCategory("DVD");
        else if(title.contains("psvita"))
            o.setCategory("PS-VITA");
        else if(title.contains("nintendo") && title.contains("3ds"))
            o.setCategory("Nintendo 3DS");
        else if(title.contains("nintendo") && title.contains("ds"))
            o.setCategory("Nintendo DS");
        else if(title.matches("ps3") || title.matches("playstation3") ) {
            if(!title.contains("blackops3"))
                o.setCategory("PS3");
            else if(title.replaceAll("blackops3","").contains("ps3") || title.replaceAll("blackops3","").contains("playstation3"))
                o.setCategory("PS3");
            else if(title.replaceAll("blackops3","").contains("ps4") || title.replaceAll("blackops3","").contains("playstation4"))
                o.setCategory("PS4");
        }
        else if(title.contains("ps4") || title.matches("playstation4")) {
            if(!title.contains("blackops4"))
                o.setCategory("PS4");
            else if(title.replaceAll("blackops4","").contains("ps4") || title.replaceAll("blackops4","").contains("playstation4") )
                o.setCategory("PS4");
            else if(title.replaceAll("blackops4","").contains("ps3") || title.replaceAll("blackops4","").contains("playstation3"))
                o.setCategory("PS3");
        }
        return o.getCategory();
    }

    public ResponseEntity<List<Orders>> extraLabel(String orderRef,String accountId,Pageable p){
        if(orderRef.contains("-")){
            Page<Orders> s1 = ordersRepository.findByOwnerIdPkAndAccountIdAndExtendedOrderIdAndChannelIdAndPOrderRef(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, orderRef,null,null,p);
            return new ResponseEntity<List<Orders>>().withResults(s1.getContent()).withTotalElements(s1.getTotalElements()).withTotalPages(s1.getTotalPages());
        }
        else {
            Page<Orders> o = ordersRepository.findByAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(accountId, Integer.valueOf(orderRef), null, null, p);
            return new ResponseEntity<List<Orders>>().withResults(o.getContent()).withTotalElements(o.getTotalElements()).withTotalPages(o.getTotalPages());
        }
    }

    public ResponseEntity<List<Orders>> findSearchText(String text,String accountId,Pageable p){
        if(text.matches("[0-9]{5,}")){
            Orders s=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,Integer.parseInt(text),null,null);
            return new ResponseEntity<List<Orders>>().withResults(Collections.singletonList(s)).withTotalPages(1);
        }
        else if(text.equals("") && p.getPageSize()!=200){
            return sortAwaitingDispatchOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(p.getPageNumber(),p.getPageSize(),orderBy("orderRef_desc")));
        }
        else if(text.equals("")){
            Page<Orders> s1 = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRef(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, "EBAY", null, null, p);
            return new ResponseEntity<List<Orders>>().withResults(s1.getContent()).withTotalElements(s1.getTotalElements()).withTotalPages(s1.getTotalPages());
        }
        else if(text.contains("-")){
            Page<Orders> s1 = ordersRepository.findByOwnerIdPkAndAccountIdAndExtendedOrderIdAndChannelIdAndPOrderRef(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, text,null,null,p);
            return new ResponseEntity<List<Orders>>().withResults(s1.getContent()).withTotalElements(s1.getTotalElements()).withTotalPages(s1.getTotalPages());
        }
        else {
            List<Orders> s =ordersRepository.findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndPOrderRefAndLastEffectiveDateAndOrderStatus(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,text,null,null,"AwaitingDispatch");
            return new ResponseEntity<List<Orders>>().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        }
    }

    public ResponseEntity<List<Orders>> findItemFromPaidAndDispatched(String text,String accountId,Pageable p){
        if(text.matches("[0-9]{5,}")){
            List<Orders> s=ordersRepository.findByOwnerIdPkAndAccountIdAndChannelIdAndPOrderRefAndShippedDateNotNullAndPaidDateNotNull(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,null,Integer.parseInt(text));
            return new ResponseEntity<List<Orders>>().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        }
        else if(text.equals("") && p.getPageSize()!=200){
            return sortPaidAndDispatchedOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(p.getPageNumber(),p.getPageSize(),orderBy("orderRef_desc")));
        }
        else if(text.equals("")){
            Page<Orders> s1 = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRef(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, "EBAY", null, null, p);
            return new ResponseEntity<List<Orders>>().withResults(s1.getContent()).withTotalElements(s1.getTotalElements()).withTotalPages(s1.getTotalPages());
        }
        else if(text.contains("-")){
            Page<Orders> s1 = ordersRepository.findByOwnerIdPkAndAccountIdAndExtendedOrderIdAndChannelIdAndPOrderRef(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, text,null,null,p);
            return new ResponseEntity<List<Orders>>().withResults(s1.getContent()).withTotalElements(s1.getTotalElements()).withTotalPages(s1.getTotalPages());
        }
        else {
            List<Orders> s =ordersRepository.findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndPOrderRefAndLastEffectiveDateAndOrderStatus(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,text,null,null,"Dispatched");
            return new ResponseEntity<List<Orders>>().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        }
    }

    public ResponseEntity < List < Orders >> sortOrders(String ownerId, String accountId, PageRequest p) {
        Accounts accounts = accountsRepository.findByAccountsId(accountId);
        if (accounts.getAccountType().equals("eBay")) {
            List < Orders > list = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRef(ownerId, accountId, "EBAY", null, null, p.getSort());
            return paginate(list, p);
        }
        else {
            List < Orders > list = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRef(ownerId, accountId, "Amazon", null, null, p.getSort());
            return paginate(list, p);
        }
    }

    public ResponseEntity<List<Orders>> sortAwaitingDispatchOrders(String ownerId, String accountId, PageRequest p) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndPaidDateNotNullAndShippedDateAndOrderDropshipStatusAndOrderStatusAndCheckResetOrderAndCheckDropshipOrder(ownerId,accountId,"EBAY",null,null,null,null,"AwaitingDispatch",null,null,p.getSort());
        return paginate(list,p);
    }

    public ResponseEntity<List<Orders>> sortAwaitingPaymentOrders(String ownerId, String accountId, PageRequest p) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndPaidDate(ownerId,accountId,"EBAY",null,null,null,p.getSort());
        return paginate(list,p);
    }

    public ResponseEntity<List<Orders>> sortPaidAndDispatchedOrders(String ownerId, String accountId, PageRequest p) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndShippedDateNotNullAndPaidDateNotNull(ownerId,accountId,"EBAY",null,null,p.getSort());
        return paginate(list,p);
    }

    public ResponseEntity<List<Orders>> sortResetListingOrders(String ownerId, String accountId, PageRequest p) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndAccountIdAndPOrderRefAndCheckResetOrderAndOrderStatus(ownerId,accountId,null,true,"AwaitingDispatch",p.getSort());
        list.addAll(ordersRepository.findByOwnerIdPkAndAccountIdAndPOrderRefAndCheckDropshipOrderAndCheckResetOrderAndOrderStatus(ownerId,accountId,null,true,null,"AwaitingDispatch",p.getSort()));
        return paginate(list,p);
    }

    public ResponseEntity<List<Orders>> sortResetListingOrdersByDate(String ownerId, String accountId,Date date,Date nextDate,PageRequest p) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndAccountIdAndCheckResetOrderAndOrderStatusAndPaidDate(ownerId,accountId,true,"DROPSHIPPED",date,nextDate);
        return paginate(list,p);
    }


    //Remaining

    public ItemType geItem(String itemIDStr, Accounts a) {
        // [1] Create ApiContext object.
        System.out.println("===== [1] Collect Account Information ====");

        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();

        // input = ConsoleUtil.readString("Enter your OAuth User Access Token");
        cred.seteBayToken(a.getApiToken());

        //input = ConsoleUtil.readString("Enter eBay SOAP server URL (e.g., https://api.ebay.com/wsapi): ");
        apiContext.setApiServerUrl(a.getUrl());
        // [2] Ask for itemID.
        System.out.println("===== [2] Call GetItemCall ====");
        //String itemIDStr = "112820220527";//ConsoleUtil.readString("Enter ID of the item that you want to get: ");

        GetItemCall gc = new GetItemCall(apiContext);
        // GetMyeBaySellingCall gmc = new GetMyeBaySellingCall(apiContext);
        DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
                DetailLevelCodeType.RETURN_ALL,
                DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
                DetailLevelCodeType.ITEM_RETURN_DESCRIPTION

        };

        gc.setIncludeItemSpecifics(true);
        //ItemListCustomizationType ss = gmc.getActiveList();
        //System.out.println("OUTPUT : "+ss.getAnyLength());
        gc.setDetailLevel(detailLevels);

        ItemType item = null;
        try {
            item = gc.getItem(itemIDStr);
        } catch (Exception e) {
            return item;
        }

        return item;
    }

    public OrderType[] geOrder(String orderID, Accounts a) {
        System.out.println("===== [1] Collect Account Information ====");
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(a.getApiToken());
        apiContext.setApiServerUrl(a.getUrl());
        apiContext.setWSDLVersion("1215");
        System.out.println("===== [2] Call GetOrdersCall ====");
        GetOrdersCall gc = new GetOrdersCall(apiContext);
        DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
                DetailLevelCodeType.RETURN_ALL,
                DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
                DetailLevelCodeType.ITEM_RETURN_DESCRIPTION,
                DetailLevelCodeType.RETURN_SUMMARY
        };
        gc.setOrderStatus(OrderStatusCodeType.ALL);
        gc.setDetailLevel(detailLevels);
        OrderIDArrayType ota = new OrderIDArrayType();
        ota.setOrderID(orderID.split(","));
        gc.setOrderIDArray(ota);
        OrderType[] order = null;
        try {
            order = gc.getOrders();
        } catch (Exception e) {}
        return order;
    }

    public String getVatIOSSNumber(String orderId, Accounts a){
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(a.getApiToken());
        apiContext.setApiServerUrl(a.getUrl());
        apiContext.setWSDLVersion("1215");
        System.out.println("===== [2] Call GetOrdersCall ====");
        GetOrdersCall gc = new GetOrdersCall(apiContext);
        DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
                DetailLevelCodeType.RETURN_ALL,
                DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
                DetailLevelCodeType.ITEM_RETURN_DESCRIPTION,
                DetailLevelCodeType.RETURN_SUMMARY
        };
        gc.setOrderStatus(OrderStatusCodeType.ALL);
        gc.setDetailLevel(detailLevels);
        OrderIDArrayType ota = new OrderIDArrayType();
        ota.setOrderID(orderId.split(","));
        gc.setOrderIDArray(ota);
        try {
            gc.getOrders();
        } catch (Exception e) {}
       return apiContext.getResponseXml().split("eBayReference")[1].split(">")[1].replaceAll("</","");
    }

    public OrderPackage getOrders(OrderPackage op, int pg, int pgSize, Accounts s, Date d1, Date d2, String ownerId) throws Exception {

        OrderType[] tempActiveItems = null;
        System.out.println("===== [1] Collect Account Information ====");
        ApiContext apiContext = new ApiContext();
        apiContext.setWSDLVersion("1215");
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        GetOrdersCall api = new GetOrdersCall(apiContext);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        api.setCreateTimeTo(cal2);
        api.setCreateTimeFrom(cal1);
        PaginationType p = new PaginationType();
        p.setEntriesPerPage(pgSize);
        p.setPageNumber(pg + 1);
        api.setPagination(p);
        api.setOrderStatus(OrderStatusCodeType.ALL);
        tempActiveItems = api.getOrders();
        if (api.getReturnedHasMoreOrders())
            op.setHasMoreOrders(true);
        else
            op.setHasMoreOrders(false);
        if (tempActiveItems != null)
            op.appendOrders(ebayOrdersConverter.convertToOrders(tempActiveItems, s.getId(), ownerId));
        return op;
    }

    public void checkDropshipItemOrNot(Orders order) {
        List < Dropship > list = dropshipRepository.findByOwnerIdAndAccountId(order.getOwnerIdPk(), order.getAccountId());
        for (int i = 0; i < list.size(); i++) {
            if (order.getItemId() != null && list.get(i).getItemId().equals(order.getItemId())) {
                order.setCheckDropshipOrder(true);
                ordersRepository.save(order);
                Orders o = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(order.getOwnerIdPk(), order.getAccountId(), order.getOrderRef(), null, null);
                if (o != null) {
                    o.setCheckDropshipOrder(true);
                    ordersRepository.save(o);
                }
                break;
            }

        }
    }

    public void checkResetListingOrNot(Orders order) {
        List < Reset > list = resetRepository.findByOwnerIdAndAccountIdAndListingHistoryNull(order.getOwnerIdPk(), order.getAccountId());
        for (int i = 0; i < list.size(); i++) {
            if (order.getItemId() != null && list.get(i).getEbayItemId().equals(order.getItemId()) && !list.get(i).getUpdated() && !order.getOrderStatus().equals("DROPSHIPPED") && (list.get(i).getMoveToAwaitingDispatch()==null || !list.get(i).getMoveToAwaitingDispatch())){
                order.setCheckResetOrder(true);
                ordersRepository.save(order);
                Orders o = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(order.getOwnerIdPk(), order.getAccountId(), order.getOrderRef(), null, null);
                if (o != null) {
                    o.setCheckResetOrder(true);
                    ordersRepository.save(o);
                }
                break;
            }

        }
    }

/*    ResponseEntity <List<Orders>> returnExceptionMessage(OrderPackage op){
        if(op==null || op.getTotalOrders()==null)
            return new ResponseEntity<List<Orders>>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("IAF Token Supplied is Expired"));
        else if(op.getCurrentPage()==0.1)
            return new ResponseEntity<List<Orders>>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("Time To should be equal to or greater than Time From"));
        else if(op.getCurrentPage()==0.2)
            return new ResponseEntity<List<Orders>>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("ApiCall.execute() - HTTP transport error: java.net.SocketException: Network is unreachable (connect failed)"));
        else if(op.getCurrentPage()==0.3)
            return new ResponseEntity<List<Orders>>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("ApiCall.execute() - HTTP transport error: java.net.UnknownHostException: api.ebay.com"));
        else if(op.getCurrentPage()==0.4)
            return new ResponseEntity<List<Orders>>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("Either OrderId or  Creation Time Range or Modified Time Range or Number of Days must be provided."));
        return null;
    }*/

    private OrderPackage exception(OrderPackage op,Exception e) {
        if(e.getMessage().equals("IAF token supplied is invalid. ")||e.getMessage().equals("IAF token supplied is expired. ")) {
            OrderPackage o=null;
            return o;
        }
        else if(e.getMessage().equals("Time To should be equal to or greater than Time From")){
            op.setCurrentPage((int) 0.1);
            return op;
        }
        else if(e.getMessage().equals("ApiCall.execute() - HTTP transport error: java.net.SocketException: Network is unreachable (connect failed)")){
            op.setCurrentPage((int) 0.2);
            return op;
        }
        else if(e.getMessage().equals("ApiCall.execute() - HTTP transport error: java.net.UnknownHostException: api.ebay.com")){
            op.setCurrentPage((int) 0.3);
            return op;
        }
        else if(e.getMessage().equals("Either OrderId or  Creation Time Range or Modified Time Range or Number of Days must be provided.")){
             op.setCurrentPage((int) 0.4);
            return op;
        }
        return op;
    }

    public ResponseEntity < List < Orders >> data(String ownerId, Accounts s, Pageable pg, Date d1, Date d2) {
        Date dn1 = removeTime(new Date());
        if (d1.equals(dn1) && d2 != null)
            return refreshData(ownerId, s.getId(), pg);
        else if (d1 != null && d2 != null)
            return refreshData(ownerId, s.getId(), pg);
        return null;
    }

    public ResponseEntity<List<Orders>> refreshData(String ownerId, String accountId, Pageable p){
        return findAllOrdersByOwnerId(ownerId,accountId,p);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public void updateOrders(List < Orders > olEbay, String accountId) {
        if (olEbay != null) {
            for (int i = 0; i < olEbay.size(); i++) {
                try {
                    if (checkOrderInDB(olEbay.get(i))) {
                        if (!isModify(olEbay.get(i))) {
                            try {
                                Orders res = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(olEbay.get(i).getOwnerIdPk(), olEbay.get(i).getAccountId(), olEbay.get(i).getOrderRef(), olEbay.get(i).getpOrderRef(), null);
                                if (!(res.getCheckResetOrder() != null && res.getCheckResetOrder()) || (res.getCheckDropshipOrder() != null && res.getCheckDropshipOrder())) {
                                    Orders modified = modifyOrder(olEbay.get(i));
                                    modified.setLastEffectiveDate(null);
                                    saveOrders(modified);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            saveOrders(olEbay.get(i));
                            checkDropshipItemOrNot(olEbay.get(i));
                            checkResetListingOrNot(olEbay.get(i));
                            if (olEbay.get(i).getSellerUserId() != null && olEbay.get(i).getOrderStatus().equals("AwaitingDispatch") && olEbay.get(i).getpOrderRef() == null && olEbay.get(i).getSentTextMessage() == null)
                                sendMessageToAndFromBuyer(accountId, olEbay.get(i).getBuyerUserId(), olEbay.get(i).getSellerUserId(), olEbay.get(i), null);
                            if ((olEbay.get(i).getpOrderRef() == null && olEbay.get(i).getTransactionArrayLength() == 1) || olEbay.get(i).getpOrderRef() != null && olEbay.get(i).getTransactionArrayLength() != 1)
                                updateStockQuantity(olEbay.get(i), accountId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }

    public void updateStockQuantity(Orders olEbay,String accountId){
        if(olEbay.getPaidDate()!=null) {
            try {
                if(olEbay.getSku()!=null) {
                    Stock s = stockRepository.findByOwnerIdAndAccountIdAndStockDeleteNullAndSkuAndItemIdAndStockCodeNull(olEbay.getOwnerIdPk(),accountId,olEbay.getSku(),olEbay.getItemId());
                    if (s != null && s.getLinkAwaitingDispatch()!=null && s.getLinkAwaitingDispatch()) {
                        if(olEbay.getOrderDropshipStatus()!=null) {
                            Integer r = s.getQuantityAvailable()+Integer.parseInt(olEbay.getQuantityPurchased());
                            s.setQuantityAvailable(r);
                        }
                        else {
                            Integer r = s.getQuantityAvailable() - Integer.parseInt(olEbay.getQuantityPurchased());
                            s.setQuantityAvailable(r);
                        }
                        stockRepository.saveAndFlush(s);
                    }
                }
            }catch(Exception e){e.printStackTrace();}
        }
    }

    private boolean checkOrderInDB(Orders orders) {
        Boolean exist=false;
        try {
            Orders res = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(orders.getOwnerIdPk(), orders.getAccountId(), orders.getOrderRef(), orders.getpOrderRef(), null);
            if(res!=null) exist=true;
        }catch (Exception e){System.out.println(orders.getOrderRef()+"-"+orders.getSellingRecordNumber());}
        return exist;
    }

    public void saveOrders(Orders o){
        if(o.getId()==null){
            try {
                if(SessionUserInfo.getLoggedInUser()!=null)
                    o.setCreatedBy(SessionUserInfo.getLoggedInUser().getUser().getEmail());
            }catch (Exception e){}
            o.setCreatedDate(removeTime(new Date()));
        }
        ordersRepository.save(o);
    }

    public Orders modifyOrder(Orders olEbay) {
        Orders res = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(olEbay.getOwnerIdPk(), olEbay.getAccountId(), olEbay.getOrderRef(), olEbay.getpOrderRef(), null);
        res.setLastEffectiveDate(new Date());
        Orders o = new Orders();
        if (olEbay.getShippedToCountry() != null) o.setShippedToCountry(olEbay.getShippedToCountry());
        if (olEbay.getOwnerIdPk() != null)
            o.setOwnerIdPk(olEbay.getOwnerIdPk());
        if (olEbay.getExtendedOrderId() != null)
            o.setExtendedOrderId(olEbay.getExtendedOrderId());
        if (olEbay.getQuantityPurchased() != null)
            o.setQuantityPurchased(olEbay.getQuantityPurchased());
        if (olEbay.getAmountPaid() != null)
            o.setAmountPaid(olEbay.getAmountPaid());
        if (olEbay.getStatus() != null)
            o.setStatus(olEbay.getStatus());
        if (olEbay.getOrderStatus() != null)
            o.setOrderStatus(olEbay.getOrderStatus());
        if (olEbay.getTitle() != null)
            o.setTitle(olEbay.getTitle());
        if (olEbay.getItemId() != null)
            o.setItemId(olEbay.getItemId());
        if (olEbay.getSellerEmail() != null)
            o.setSellerEmail(olEbay.getSellerEmail());
        if (olEbay.getBuyerUserId() != null)
            o.setBuyerUserId(olEbay.getBuyerUserId());
        if (olEbay.getBuyerName() != null)
            o.setBuyerName(olEbay.getBuyerName());
        if (olEbay.getBuyerEmailId() != null)
            o.setBuyerEmailId(olEbay.getBuyerEmailId());
        if (olEbay.getCancelStatus() != null)
            o.setCancelStatus(olEbay.getCancelStatus());
        if (olEbay.getOrderId() != null)
            o.setOrderId(olEbay.getOrderId());
        if (olEbay.getSku() != null)
            o.setSku(olEbay.getSku());
        if (olEbay.getAdjustmentAmount() != null)
            o.setAdjustmentAmount(olEbay.getAdjustmentAmount());
        if (olEbay.getSubTotal() != null)
            o.setSubTotal(olEbay.getSubTotal());
        if (olEbay.getTransactionId() != null)
            o.setTransactionId(olEbay.getTransactionId());
        if (olEbay.getOriginalItemPrice() != null)
            o.setOriginalItemPrice(olEbay.getOriginalItemPrice());
        if (olEbay.getOrderReferenceNumber() != null)
            o.setOrderReferenceNumber(olEbay.getOrderReferenceNumber());
        if (olEbay.getOrderType() != null)
            o.setOrderType(olEbay.getOrderType());
        if (olEbay.getSellingRecordNumber() != null)
            o.setSellingRecordNumber(olEbay.getSellingRecordNumber());
        if (olEbay.getSellerUserId() != null)
            o.setSellerUserId(olEbay.getSellerUserId());
        if (olEbay.getTotalAmount() != null)
            o.setTotalAmount(olEbay.getTotalAmount());
        if (olEbay.getTransactionDate() != null)
            o.setTransactionDate(olEbay.getTransactionDate());
        if (olEbay.getLastModifiedDate() != null)
            o.setLastModifiedDate(olEbay.getLastModifiedDate());
        if (olEbay.getpReferenceNumber() != null)
            o.setpReferenceNumber(olEbay.getpReferenceNumber());
        if (olEbay.getOrderRef() != null)
            o.setOrderRef(olEbay.getOrderRef());
        if (olEbay.getTotalAmountCurrencyId() != null)
            o.setTotalAmountCurrencyId(olEbay.getTotalAmountCurrencyId());
        if (olEbay.getSubTotalCurrencyId() != null)
            o.setSubTotalCurrencyId(olEbay.getSubTotalCurrencyId());
        if (olEbay.getAccountId() != null)
            o.setAccountId(olEbay.getAccountId());
        if (olEbay.getShippedDate() != null)
            o.setShippedDate(olEbay.getShippedDate());
        if (olEbay.getPrintDate() != null)
            o.setPrintDate(olEbay.getPrintDate());
        if (olEbay.getPaidDate() != null)
            o.setPaidDate(olEbay.getPaidDate());
        if (olEbay.getTransactionArrayLength() != null)
            o.setTransactionArrayLength(olEbay.getTransactionArrayLength());
        if (olEbay.getTransDateRef() != null)
            o.setTransDateRef(olEbay.getTransDateRef());
        if (olEbay.getpOrderRef() != null)
            o.setpOrderRef(olEbay.getpOrderRef());
        if (olEbay.getQuantity() != null)
            o.setQuantity(olEbay.getQuantity());
        if (olEbay.getBuyerStreet1() != null)
            o.setBuyerStreet1(olEbay.getBuyerStreet1());
        if (olEbay.getBuyerStreet2() != null)
            o.setBuyerStreet2(olEbay.getBuyerStreet2());
        if (olEbay.getBuyerCity() != null)
            o.setBuyerCity(olEbay.getBuyerCity());
        if (olEbay.getBuyerPhoneNumber() != null)
            o.setBuyerPhoneNumber(olEbay.getBuyerPhoneNumber());
        if (olEbay.getBuyerState() != null)
            o.setBuyerState(olEbay.getBuyerState());
        if (olEbay.getBuyerCountry() != null)
            o.setBuyerCountry(olEbay.getBuyerCountry());
        if (olEbay.getShippingServiceSelected() != null)
            o.setShippingServiceSelected(olEbay.getShippingServiceSelected());
        if (olEbay.getShippingServiceCost() != null)
            o.setShippingServiceCost(olEbay.getShippingServiceCost());
        if (olEbay.getShippingServiceCostCurrencyId() != null)
            o.setShippingServiceCostCurrencyId(olEbay.getShippingServiceCostCurrencyId());
        if (res.getCheckDropshipOrder() != null)
            o.setCheckDropshipOrder(res.getCheckDropshipOrder());
        if (res.getCheckResetOrder() != null)
            o.setCheckResetOrder(res.getCheckResetOrder());
        if (res.getOrderDropshipStatus() != null)
            o.setOrderDropshipStatus(res.getOrderDropshipStatus());
        if (olEbay.getBuyerPostalCode() != null)
            o.setBuyerPostalCode(olEbay.getBuyerPostalCode());
        if (res.getStatusId() != null)
            o.setStatusId(olEbay.getStatusId());
        if (olEbay.getOrderVariationName() != null)
            o.setOrderVariationName(olEbay.getOrderVariationName());
        if (olEbay.getOrderVariationValue() != null)
            o.setOrderVariationValue(olEbay.getOrderVariationValue());
        if (olEbay.getOrderTrackingId() != null)
            o.setOrderTrackingId(olEbay.getOrderTrackingId());
        ordersRepository.delete(res);
        return o;
    }

    public Boolean isModify(Orders olEbay){
        Orders res = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(olEbay.getOwnerIdPk(),olEbay.getAccountId(),olEbay.getOrderRef(),olEbay.getpOrderRef(),null);
        if(res.getShippedDate()==null || (olEbay.getCancelStatus()!=null && (olEbay.getCancelStatus().equals("CANCELLED") || olEbay.getCancelStatus().equals("CancelClosedWithRefund")))) {
            return res.equals(olEbay);
        } else return true;
    }

    public void convertImageUrl(){
        List<Stock> list=stockRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getImageUrl()!=null && list.get(i).getImageUrl().startsWith("http:")){
                String Url=list.get(i).getImageUrl().replace("http:","https:");
                list.get(i).setImageUrl(Url);
                stockRepository.saveAndFlush(list.get(i));
            }
        }
    }

    ResponseEntity < List < EbayListing >> displayItems(Pageable p, Accounts s) {
        ItemType[] tempActiveItems = null;
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        try {
            GetMyeBaySellingCall api = new GetMyeBaySellingCall(apiContext);
            ItemListCustomizationType itemListType = new ItemListCustomizationType();
            itemListType.setInclude(Boolean.TRUE);
            PaginationType page = new PaginationType();
            page.setPageNumber(p.getPageNumber());
            itemListType.setPagination(page);
            api.setActiveList(itemListType);
            api.setScheduledList(itemListType);
            api.setSoldList(itemListType);
            api.setUnsoldList(itemListType);
            try {
                api.getMyeBaySelling();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity < List < ActiveListings >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
            }

            if (api.getReturnedActiveList() != null)
                tempActiveItems = (api.getReturnedActiveList().getItemArray()).getItem();
            int totalNumberOfEntries = api.getReturnedActiveList().getPaginationResult().getTotalNumberOfEntries();
            int totalNumberOfPages = api.getReturnedActiveList().getPaginationResult().getTotalNumberOfPages();
            final ItemType[] activeItems = tempActiveItems;
            if (activeItems != null) {
                return new ResponseEntity < List < EbayListing >> ().withResults(ebayActiveListingConverter.converTo(activeItems)).withTotalPages(totalNumberOfPages).withTotalElements(Long.valueOf(totalNumberOfEntries));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }




    public List<EbayListing> findAllListings(Accounts s){
        ItemType[] tempActiveItems = null;
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        try {
            GetMyeBaySellingCall api = new GetMyeBaySellingCall(apiContext);
            ItemListCustomizationType itemListType = new ItemListCustomizationType();
            itemListType.setInclude(Boolean.TRUE);
            api.setActiveList(itemListType);
            api.setScheduledList(itemListType);
            api.setSoldList(itemListType);
            api.setUnsoldList(itemListType);
            try {
                api.getMyeBaySelling();
            }catch(Exception e) {}

            if (api.getReturnedActiveList() != null)
                tempActiveItems = (api.getReturnedActiveList().getItemArray()).getItem();
            final ItemType[] activeItems = tempActiveItems;
            if(activeItems!=null)
                return(ebayActiveListingConverter.converTo(activeItems));


        } catch (Exception ex) {}
        return null;
    }

    public List<Orders> findAllByDate(Accounts s,Date d1,Date d2) {

    OrderType[] tempActiveItems = null;
    System.out.println("===== [1] Collect Account Information ====");

    ApiContext apiContext = new ApiContext();
    ApiCredential cred = apiContext.getApiCredential();
    cred.seteBayToken(s.getApiToken());
    apiContext.setApiServerUrl(s.getUrl());
    try {
        GetOrdersCall api = new GetOrdersCall(apiContext);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        long diff = d2.getTime() - d1.getTime();
        int numberOfDays = Integer.parseInt("" + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        cal1.setTime(d1);
        cal2.setTime(d2);
        if (numberOfDays != 0) {
            api.setCreateTimeFrom(cal1);
            api.setCreateTimeTo(cal2);
        } else {
            api.setCreateTimeFrom(cal1);
            api.setCreateTimeTo(cal2);
        }
        tempActiveItems = api.getOrders();
       return ebayOrdersConverter.convertToOrders(tempActiveItems,s.getId(),SessionUserInfo.getLoggedInUser().getUser().getId());
    }
    catch (Exception e){System.out.println(e.getMessage());}

       return null;
}

    public ResponseEntity<List<ActiveListings>> paginateResult(List<ActiveListings> result,Pageable p)
    {
        int start=(p.getPageNumber()-1)*p.getPageSize();
        if (start<0) start=0;
        else if(start>=0)start+=p.getPageSize();
        int max=start+p.getPageSize();
        int totalPages=result.size()/p.getPageSize();
        if(result.size()%p.getPageSize()>0) totalPages++;
        if(max>result.size()) max=result.size();
        List<ActiveListings> subResult = result.subList(start,max);
        return new ResponseEntity<List<ActiveListings>>().withResults(subResult).withTotalPages(totalPages).withTotalElements(Long.parseLong(""+result.size()));
    }

    public ResponseEntity<List<Orders>> paginate(List<Orders> result,Pageable p)
    {
        int start=(p.getPageNumber()-1)*p.getPageSize();
        if (start<0) start=0;
        else if(start>=0)start+=p.getPageSize();
        int max=start+p.getPageSize();
        int totalPages=result.size()/p.getPageSize();
        if(result.size()%p.getPageSize()>0) totalPages++;
        if(max>result.size()) max=result.size();
        List<Orders> subResult = result.subList(start,max);
        return new ResponseEntity<List<Orders>>().withResults(subResult).withTotalPages(totalPages).withTotalElements(Long.parseLong(""+result.size()));
    }

    public ResponseEntity<ItemCategory> getItemCategoryContent(String selectedOn,String content,String console) {
        ItemCategory i=itemCategoryRepository.findByCategoryAndSelectionAndConsole(content,selectedOn,console);
        if(i==null){
            ItemCategory c=new ItemCategory();
            c.setCategory(content);
            c.setSelection(selectedOn);
            c.setConsole(console);
            itemCategoryRepository.save(c);
        }
        return returnCategoryResponse(selectedOn,console);
    }

    public ResponseEntity<ItemCategory> returnCategoryResponse(String selectedOn,String console){
        if(console.equals("undefined")){
            List<ItemCategory> s=itemCategoryRepository.findBySelection(selectedOn);
            return new ResponseEntity<List<ItemCategory>>().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        }
        else {
            List<ItemCategory> s = itemCategoryRepository.findBySelectionAndConsole(selectedOn, console);
            return new ResponseEntity<List<ItemCategory>>().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        }

    }

    public ResponseEntity<ItemCategory> displayItemCategoryContent(String selectedOn,String console) {
        return returnCategoryResponse(selectedOn,console);
    }

    public void updatePreviousOrders(String ownerId,String accountId,Pageable p){
        List<Orders> o=combineOrdersToBeUpdated(ownerId,accountId);
        if(o.size()!=0) {
            Date d = removeTime(o.get(0).getPrintDate());
            if(checkInDatabase(d)==null)
                saveUpdateOrders(d);
            for (int i = 1; i < o.size(); i++) {
                if (d.compareTo(removeTime(o.get(i).getPrintDate())) != 0) {
                    if(checkInDatabase(removeTime(o.get(i).getPrintDate()))==null){
                        saveUpdateOrders(removeTime(o.get(i).getPrintDate()));
                        d = removeTime(o.get(i).getPrintDate());
                    }
                }
            }

            List<UpdateOrders> uo = updateOrdersRepository.findAll();
            if (uo.size() != 0) {
                for (int k = 0; k < uo.size(); k++) {
                    try {

                        ResponseEntity<List<Orders>> res = refreshAllOrdersByOwnerId(ownerId, accountId, p, uo.get(k).getPassingDate(), getNextDate(uo.get(k).getPassingDate()));
                    }catch (Exception e){e.printStackTrace();updateOrdersRepository.deleteAll();}
                }
                updateOrdersRepository.deleteAll();
            }
        }
    }

    public Date getNextDate(Date passingDate) {
        if(removeTime(new Date()).equals(passingDate))
            return new Date();
        else {
            Calendar c = Calendar.getInstance();
            c.setTime(passingDate);
            c.add(Calendar.DAY_OF_YEAR, 1);
            return removeTime(c.getTime());
        }
    }

    public List<Orders> combineOrdersToBeUpdated(String ownerId,String accountId){
        List<Orders> add=new ArrayList<Orders>();
        List<Orders> awaitingDispatch=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndPOrderRefAndPaidDateNotNullAndShippedDateAndOrderStatus(ownerId,accountId,"EBAY",null,null,"AwaitingDispatch");
       /* List<Orders> awaitingPayment=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndPOrderRefAndPaidDateAndOrderStatus(ownerId,accountId,"EBAY",null,null,"AwaitingPayment");
        for(int i=0;i<awaitingPayment.size();i++){
            if(awaitingPayment.get(i).getStatus()!=null && awaitingPayment.get(i).getStatus().equals("CancelClosedWithRefund"))
                awaitingPayment.remove(i);
        }*/
        add.addAll(awaitingDispatch);//add.addAll(awaitingPayment);
        return add;
    }

    public void saveUpdateOrders(Date d){
        UpdateOrders up = new UpdateOrders();
        up.setPassingDate(d);
        updateOrdersRepository.save(up);
    }

    public UpdateOrders checkInDatabase(Date d){
        UpdateOrders check=updateOrdersRepository.findByPassingDate(d);
        return check;
    }

    public void updateShippedDate(String ownerId,String accountId,String token) throws ParseException {
        List<Orders> list = ordersRepository.findByOwnerIdPkAndAccountIdAndTransactionArrayLengthAndShippedDateNullAndOrderDropshipStatusNullAndPOrderRefNullAndOrderStatus(ownerId, accountId, 1,"AwaitingDispatch");
        for (int i = 0; i < list.size(); i++) {
            String url = "https://api.ebay.com/sell/fulfillment/v1/order/"+list.get(i).getExtendedOrderId()+"/shipping_fulfillment";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer "+token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            org.springframework.http.ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if(result.toString().contains("shippedDate")) {
                String shippedDate = result.toString().substring(result.toString().indexOf("shippedDate"), result.toString().indexOf(".000Z")).replace("shippedDate", "").substring(3);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = dateFormat.parse(shippedDate);
                    list.get(i).setShippedDate(date);
                    list.get(i).setOrderStatus("Dispatched");
                    ordersRepository.saveAndFlush(list.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<Orders> orders = ordersRepository.findByPOrderRef(list.get(i).getOrderRef());
                for (int j = 0; j < orders.size(); j++) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = dateFormat.parse(shippedDate);
                    orders.get(j).setShippedDate(date);
                    ordersRepository.saveAndFlush(orders.get(j));
                }
            }
        }
    }

    public void getGameData(String result, String channelId) throws JSONException, ParseException {
        try {
            Accounts s = accountsRepository.findByAccountNameAndAccountType("PoundMonkey", "eBay");
            JSONObject jsonObject = new JSONObject(result);
            ArrayList<String> titleList = new ArrayList<String>();
            ArrayList<String> priceList = new ArrayList<>();
            ArrayList<String> quantityList = new ArrayList<String>();
            ArrayList<String> statusList = new ArrayList<>();
            ArrayList<String> categoryList=new ArrayList<>();
            Orders orders =new Orders();
            List < Orders > orderExist = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderIdAndChannelId(s.getOwnerIdPk(), s.getId(), jsonObject.getString("orderNumber"), channelId);
            if (orderExist.size() == 0) {
                String date = (String) jsonObject.get("date");
                String orderNumber = (String) jsonObject.get("orderNumber");
                if (jsonObject.has("quantities")) {
                    JSONArray arrayOfQuantities = (JSONArray) jsonObject.get("quantities");
                    int len = arrayOfQuantities.length();
                    for (int j = 0; j < len; j++) {
                        quantityList.add(arrayOfQuantities.get(j).toString());
                    }
                }

                if (jsonObject.has("titles")) {
                    JSONArray arrayOfTitles = (JSONArray) jsonObject.get("titles");
                    int len = arrayOfTitles.length();
                    for (int k = 0; k < len; k++) {
                        titleList.add(arrayOfTitles.get(k).toString());
                        categoryList.add(getOrderCateory(arrayOfTitles.get(k).toString(),orders));

                    }
                }

                if (jsonObject.has("prices")) {
                    JSONArray arrayOfPrices = (JSONArray) jsonObject.get("prices");
                    int len = arrayOfPrices.length();
                    for (int l = 0; l < len; l++) {
                        priceList.add(arrayOfPrices.get(l).toString());
                    }
                }

                if (jsonObject.has("statuses")) {
                    JSONArray arrayOfStatuses = (JSONArray) jsonObject.get("statuses");
                    int len = arrayOfStatuses.length();
                    for (int l = 0; l < len; l++) {
                        statusList.add(arrayOfStatuses.get(l).toString());
                    }
                }

                for (int m = 0; m < quantityList.size(); m++) {
                    if (quantityList.size() == titleList.size()) {
                        setOrders(new Orders(), "Game", s, s.getSpreadSheetId(), orderNumber, date, priceList.get(m), quantityList.get(m), channelId, titleList.get(m), "Used", statusList.get(m), null, null,categoryList.get(m));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMagpieJsonData(String result, String channelId) throws JSONException, ParseException {
        try {
            Accounts s = accountsRepository.findByAccountNameAndAccountType("Poundmonkey", "eBay");
            JSONObject jsonObject = new JSONObject(result);
            ArrayList<String > titleList = new ArrayList<String>();
            ArrayList<String> priceList = new ArrayList< >();
            ArrayList<String> quantityList = new ArrayList<String>();
            ArrayList<String> categoryList=new ArrayList<>();
            Orders orders =new Orders();
            ArrayList<String> conditionsList = new ArrayList<String>();
            List < Orders > orderExist = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderIdAndChannelId(s.getOwnerIdPk(), s.getId(), jsonObject.getString("orderNumber"), channelId);
            if (orderExist.size() == 0) {
                String date = (String) jsonObject.get("date");
                String orderNumber = (String) jsonObject.get("orderNumber");
                //String pricePaid = (String) jsonObject.get("pricePaid");
                //String quantity = (String) jsonObject.get("quantity");
                if (jsonObject.has("quantities")) {
                    JSONArray arrayOfQuantities = (JSONArray) jsonObject.get("quantities");
                    int len = arrayOfQuantities.length();
                    for (int j = 0; j < len; j++) {
                        quantityList.add(arrayOfQuantities.get(j).toString());
                    }
                }
                if (jsonObject.has("titles")) {
                    JSONArray arrayOfTitles = (JSONArray) jsonObject.get("titles");
                    int len = arrayOfTitles.length();
                    for (int k = 0; k < len; k++) {
                        titleList.add(arrayOfTitles.get(k).toString());
                        categoryList.add(getOrderCateory(arrayOfTitles.get(k).toString(),orders));
                    }
                }
                if (jsonObject.has("prices")) {
                    JSONArray arrayOfPrices = (JSONArray) jsonObject.get("prices");
                    int len = arrayOfPrices.length();
                    for (int l = 0; l < len; l++) {
                        priceList.add(arrayOfPrices.get(l).toString());
                    }
                }

                if (jsonObject.has("conditions")) {
                    JSONArray arrayOfQuantities = (JSONArray) jsonObject.get("conditions");
                    int len = arrayOfQuantities.length();
                    for (int l = 0; l < len; l++) {
                        conditionsList.add(arrayOfQuantities.get(l).toString());
                    }
                }
                for (int m = 0; m < quantityList.size(); m++) {
                    if (quantityList.size() == titleList.size()) {
                        setOrders(new Orders(), "MusicMagpie", s, s.getSpreadSheetId(), orderNumber, date, priceList.get(m), quantityList.get(m), channelId, titleList.get(m), conditionsList.get(m), null, null, null,categoryList.get(m));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getCexData(String result,String channelId) throws JSONException, ParseException {
        try{
            Accounts s=accountsRepository.findByAccountNameAndAccountType("PoundMonkey","eBay");
            JSONObject jsonObject=new JSONObject(result);
            ArrayList<String> titleList = new ArrayList<>();
            ArrayList<String> priceList = new ArrayList<>();
            ArrayList<String> quantityList = new ArrayList<>();
            ArrayList<String> itemIds=new ArrayList<>();
            List<Orders> orderExist = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderIdAndChannelId(s.getOwnerIdPk(), s.getId(), jsonObject.getString("orderNumber"), channelId);
            if (orderExist.size() == 0) {
                String date = (String) jsonObject.get("date");
                String orderNumber = (String) jsonObject.get("orderNumber");
                String pricePaid = (String) jsonObject.get("total");
                String trackingNumber=(String) jsonObject.get("trackingNumber");

                if (jsonObject.has("itemIds")) {
                    JSONArray arrayOfItemIds = (JSONArray) jsonObject.get("itemIds");
                    int len = arrayOfItemIds.length();
                    for (int k = 0; k < len; k++) {
                        itemIds.add(arrayOfItemIds.get(k).toString());
                    }
                }

                if (jsonObject.has("quantities")) {
                    JSONArray arrayOfQuantities = (JSONArray) jsonObject.get("quantities");
                    int len = arrayOfQuantities.length();
                    for (int j = 0; j < len; j++) {
                        quantityList.add(arrayOfQuantities.get(j).toString());
                    }
                }
                if (jsonObject.has("titles")) {
                    JSONArray arrayOfTitles = (JSONArray) jsonObject.get("titles");
                    int len = arrayOfTitles.length();
                    for (int k = 0; k < len; k++) {
                        titleList.add(arrayOfTitles.get(k).toString());
                    }
                }
                if (jsonObject.has("prices")) {
                    JSONArray arrayOfPrices = (JSONArray) jsonObject.get("prices");
                    int len = arrayOfPrices.length();
                    for (int l = 0; l < len; l++) {
                        priceList.add(arrayOfPrices.get(l).toString());
                    }
                }


                for (int m = 0; m < quantityList.size(); m++) {
                    if (quantityList.size() == titleList.size()) {
                        setOrders(new Orders(), "Cex", s, s.getSpreadSheetId(), orderNumber, date, priceList.get(m), quantityList.get(m), channelId, titleList.get(m), "Used", null,itemIds.get(m),trackingNumber,null);
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public Double roundOff(Double value){
        return Math.round(value * 100D) / 100D;
    }

    public void setOrders(Orders orders,String seller,Accounts s,String spreadsheetId,String orderNumber,String date,String pricePaid,String quantity,String channelId,String title,String condition,String status,String itemId,String trackingNumber,String category) throws ParseException {
        try {
            if (seller.equals("Game"))
                orders.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse(parseValidDate(date)));
            else
                orders.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
            Orders order = ordersRepository.findBySellerUserIdAndExtendedOrderIdAndItemId(seller, orderNumber, itemId);
            if (seller.equals("Cex") && order != null) {
                Double amount;
                if (order.getQuantityPurchased().equals("1")) {
                    amount = roundOff(Double.parseDouble(order.getTotalAmount().replace("£", "")) * 2);
                    order.setQuantityPurchased(String.valueOf(Integer.parseInt(order.getQuantityPurchased()) + 1));
                    order.setTotalAmount("£" + amount);
                } else {
                    amount = roundOff(Double.parseDouble(order.getTotalAmount().replace("£", "")) / Integer.parseInt(order.getQuantityPurchased()));
                    order.setQuantityPurchased(String.valueOf(Integer.parseInt(order.getQuantityPurchased()) + 1));
                    order.setTotalAmount("£" + roundOff(amount * Integer.parseInt(order.getQuantityPurchased())).toString());
                }
                ordersRepository.save(order);
            }
            else {
                orders.setQuantityPurchased(quantity);
                orders.setExtendedOrderId(orderNumber);
                orders.setTitle(title);
                orders.setConditionDisplayName(condition);
                orders.setTotalAmount(pricePaid);
                if (seller.equals("MusicMagpie"))
                    orders.setSellerUserId("Music_Magpie");
                else if (seller.equals("Game"))
                    orders.setSellerUserId("GAME");
                else
                    orders.setSellerUserId("Cex");
                orders.setSpreadsheetId(spreadsheetId);
                if (itemId != null)
                    orders.setItemId(itemId);
                if(category!=null)
                    orders.setCategory(category);
                if (trackingNumber != null)
                    orders.setOrderTrackingId(trackingNumber);
                orders.setOrderId(orderNumber);
                if (status == null && !seller.equals("MusicMagpie"))
                    orders.setStatus("AWAITING_SHIPMENT");
                else
                    orders.setStatus("DISPATCHED");
                orders.setOwnerIdPk(s.getOwnerIdPk());
                orders.setAccountId(s.getId());
                orders.setDuplicateId(spreadsheetId);
                orders.setChannel(getChannel(channelId));
                try {
                    List<Orders> orders1 = ordersRepository.findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndChannelIdAndCategory(s.getOwnerIdPk(), s.getId(), orders.getTitle().toLowerCase().split(orders.getCategory().toLowerCase())[0], orders.getChannel().getId(), orders.getCategory());
                    for (int i = 0; i < orders1.size(); i++) {
                        if (orders1.get(i).getSku() != null) {
                            orders.setSku(orders1.get(i).getSku());
                            break;
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
                ordersRepository.save(orders);
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public Channel getChannel(String id){
        Channel c = new Channel();
        c.setId(id);
        return c;
    }

    public String parseValidDate(String passDate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("MMM").parse(passDate.split(" ")[1]));
        int monthInt = cal.get(Calendar.MONTH) + 1;
        if(String.valueOf(monthInt).length()!=1)
            monthInt= Integer.parseInt(String.format("%04d", monthInt));
        return passDate.split(" ")[0]+"/"+monthInt+"/"+passDate.split(" ")[2];
    }

    public ResponseEntity < List < Orders >> purchaseOrderContent(String orderId) {
        Orders order = ordersRepository.findOneById(orderId);
        List < Orders > orders = ordersRepository.findByBuyerUserIdAndOrderStatus(order.getBuyerUserId(), "AwaitingDispatch");
        ArrayList < Orders > contentList = new ArrayList < > ();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderRef() != null) {
                List < Orders > list = ordersRepository.findByPOrderRef(orders.get(i).getOrderRef());
                for (int j = 0; j < list.size(); j++) {
                    contentList.add(list.get(j));
                }
            }
        }
        return new ResponseEntity < List < Orders >> ().withResults(contentList);
    }


    public ResponseEntity<List<Orders>> getOrderDetails(String orderId) {
        List<Orders> list=new ArrayList<>();
        List<Orders> order=ordersRepository.findByExtendedOrderIdAndPOrderRef(orderId,null);
        for(int i=0;i<order.size();i++){
            if(order.get(i).getTransactionArrayLength()==1)
                list.add(order.get(i));
            else{
                List<Orders> bundleOrders=ordersRepository.findByExtendedOrderId(order.get(i).getExtendedOrderId());
                for(int j=0;j<bundleOrders.size();j++)
                list.add(bundleOrders.get(j));
            }
        }
        return new ResponseEntity<List<Orders>>().withResults(list);
    }

    public ResponseEntity<List<String>> purchaseOrderContentByUserId(String userId){
        List<Orders> order=ordersRepository.findByBuyerUserIdAndPaidDate(userId,getStartingDate(15),new Date());
        ArrayList<String> contentList=new ArrayList<>();
        for(int i=0;i<order.size();i++){
            contentList.add(order.get(i).getExtendedOrderId());
        }
        return new ResponseEntity<List<String>>().withResults(contentList);
    }

    public Date getStartingDate(int days){
       Date date=new Date();
       long DAY_IN_MS = 1000 * 60 * 60 * 24;
       return new Date(date.getTime() - (days * DAY_IN_MS));

    }



    public static void send(String from,String password,String to,String sub,String msg){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(javax.mail.Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

    public  void sendMessageToAndFromBuyer(String accountId,String buyerId,String sellerId,Orders order,Integer days){
        Accounts s=accountsRepository.findByAccountsId(accountId);
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        AddMemberMessageAAQToPartnerCall api=new AddMemberMessageAAQToPartnerCall(apiContext);
        api.setItemID(order.getItemId());
        MemberMessageType memberMessageType=new MemberMessageType();
        if(!buyerId.matches("poundmonkey|excelstuff|goodley"))
        memberMessageType.setRecipientID(new String[]{buyerId});
        else
            memberMessageType.setRecipientID(new String[]{sellerId});
        if(!buyerId.matches("poundmonkey|excelstuff|goodley"))
        memberMessageType.setSenderID(sellerId);
        else
            memberMessageType.setSenderID(buyerId);
        memberMessageType.setSubject("");
        memberMessageType.setQuestionType(QuestionTypeCodeType.fromValue("General"));
        if(!buyerId.matches("poundmonkey|excelstuff|goodley") && !order.getItemId().matches("363011102296|362912470219"))
        memberMessageType.setBody("Dear Customer\n" +
                "Many thanks for your order. Due to the current health crisis Royal Mail may delay in delivering orders (shortage of staff) and that deliveries may take more time than usual. This would not be more than a day or two, but we are making sure to send all our orders via 1st Class so it may reach our customers as soon as possible. I request you to please be patient and please do not leave a negative feedback due to delay in receiving the order.\n" +
                "Please also go through the below link to read through the preventative guidance from Royal Mail.  \n" +
                "https://www.royalmail.com/coronavirus\n" +
                "Your patience and understanding is highly appreciated.\n" +
                "Many thanks\n");
        else if(!buyerId.matches("poundmonkey|excelstuff|goodley") && order.getItemId().matches("363011102296|363106283135"))
            memberMessageType.setBody("\n" +
                    "Dear Customer\n" +
                    "\n" +
                    "Many thanks for your order. Could you please reply to this message and confirm the size you have selected at checkout. We have had many returns due to customers chosing the wrong size and end up returning the item. If we do not receive a reply in the next few hours we may have to send the size that was selected at checkout and if it was not the size you intended, any return postage charges and replacement postage charges will be incurred on you.\n" +
                    "\n" +
                    "Many thanks for your understanding\n" +
                    "\n" +
                    "Best Regards");
        else if(!buyerId.matches("poundmonkey|excelstuff|goodley") && order.getItemId().equals("362912470219"))
            memberMessageType.setBody("Dear Customer\n" +
                    "\n" +
                    "Please be advised that what you have purchased is not a box quantity but canisters quantity so please do not expect to receive multiple boxes in post. We have listed it as 2 canisters and if you increase the quantity from one then it will be a multiple of 2 canisters. £4.99 is the price for 2 cream canisters and NOT the price of 2 boxes of cream canisters. If you have purchased this mistakenly, please contact us to cancel the order.  \n" +
                    "\n" +
                    "Many thanks");
        else if(buyerId.matches("poundmonkey|goodley") && !sellerId.matches("musicmagpie|chawla-industries|muzed|garkell-99") && days==null)
            memberMessageType.setBody("Hi\n" +
                    "Please do not send an invoice with the order. Please try to post it as soon as possible and update tracking number or postage receipt if available.\n" +
                    "Thanks\n");
        else if(buyerId.matches("poundmonkey|goodley") && !sellerId.matches("musicmagpie|chawla-industries|muzed") && order.getSentTextMessage()==null && days!=null)
            memberMessageType.setBody("Hi\n" +
                    "\n" +
                    "Could you please post the item soon and if you already did please provide tracking details.\n" +
                    "\n" +
                    "Thanks");
            api.setMemberMessage(memberMessageType);
        try {
            if(api.getMemberMessage().getBody()!=null)
            api.addMemberMessageAAQToPartner();
            order.setSentTextMessage("Yes");
            ordersRepository.saveAndFlush(order);
        }catch (Exception e){e.printStackTrace();}


    }

    public ResponseEntity<String> getTitle(String recordNum) {
        Orders order=ordersRepository.findByOwnerIdPkAndSellingRecordNumber(SessionUserInfo.getLoggedInUser().getUser().getId(),recordNum);
        if(order!=null)
            return new ResponseEntity<String>().withResults(order.getTitle());
        else
            return new ResponseEntity<String>().withResults("Title Not Found");
    }

    @Transactional
    public ResponseEntity<String> pushToRelist(List<String> listingId, String ownerId, String accountId) {
        for(int i=0;i<listingId.size();i++){
            try {
                ActiveListings activeListings = activeListingRepository.findByIdAndOwnerIdAndAccountId(listingId.get(i), ownerId, accountId);
                if(activeListings.getQuantityAvailable()!=0) {
                    Relist relist = new Relist();
                    relist.setItemId(activeListings.getItemId());
                    relist.setTitle(activeListings.getTitle());
                    relist.setLink("https://www.ebay.co.uk/itm/" + activeListings.getItemId());
                    relist.setNewItemId(activeListings.getItemId());
                    if (activeListings.getSku()!= null)
                        relist.setSku(activeListings.getSku());
                    if (activeListings.getQuantityAvailable() > 3)
                        relist.setReListQuantity(3);
                    else
                        relist.setReListQuantity(activeListings.getQuantityAvailable());
                    relist.setRemainingQuantity(activeListings.getQuantityAvailable());
                    relist.setPrice(Double.valueOf(activeListings.getStartPriceValue()));
                    relist.setLastEffectiveDate(new Date());
                    relist.setAccountId(accountId);
                    relist.setOwnerId(ownerId);
                    relist.setCurrency(activeListings.getStartPriceCurrencyId());
                    relistRepository.save(relist);
                }
                else
                    return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText("Quantity UnAvailable"));
            }catch (Exception e){
                return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText(e.getMessage()));
            }
        }
        return new ResponseEntity<String>().withResults("success");
    }

    public ResponseEntity<List<ActiveListings>> searchListing(String text,String ownerId, String accountId, Pageable p) {
        if(text.matches("[0-9]+")){
            ActiveListings activeListings=activeListingRepository.findByOwnerIdAndAccountIdAndItemId(ownerId,accountId,text);
            return new ResponseEntity<List<ActiveListings>>().withResults(Collections.singletonList(activeListings)).withTotalPages(1);
        }
        else if(text.equals("")){
           return findAll(ownerId,accountId,p);
        }
        else {
            List<ActiveListings> title = activeListingRepository.findByOwnerIdAndAccountIdAndTitleContainingIgnoreCase(ownerId, accountId, text);
            return new ResponseEntity<List<ActiveListings>>().withResults(title).withTotalPages(1).withTotalElements(Long.valueOf(title.size()));
        }
    }


    public ResponseEntity<ItemCategory> deleteContent(String modelName,String console) {
        itemCategoryRepository.deleteByCategoryAndSelectionAndConsole(modelName,"stockLog",console);
        List<ItemCategory> s=itemCategoryRepository.findBySelectionAndConsole("stockLog",console);
        return new ResponseEntity<List<ItemCategory>>().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
    }

    public ResponseEntity<List<Orders>> MarkOrderAsAwaitingDispath(List<String> id, PageRequest pageRequest) {
        for(int i=0;i<id.size();i++) {
            Orders order = ordersRepository.findOneById(id.get(i));
            order.setShippedDate(null);
            order.setOrderStatus("AwaitingDispatch");
            ordersRepository.save(order);
        }
        return null;
    }

    public ResponseEntity<List<Orders>> MarkResetOrderAsAwaitingDispatch(List<String> id, PageRequest pageRequest) {
        for(int i=0;i<id.size();i++) {
            Orders order = ordersRepository.findOneById(id.get(i));
            order.setShippedDate(null);
            order.setOrderStatus("AwaitingDispatch");
            order.setCheckResetOrder(null);
            List<Orders> list=ordersRepository.findByPOrderRef(order.getOrderRef());
            for(int j=0;j<list.size();j++){
                list.get(j).setCheckResetOrder(null);
            }
            ordersRepository.save(order);
        }
        return null;
    }

    @Transactional
    public PdfEntity saveOrderDetailsFromGmail(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        Pdf pdf=new Pdf();
        ArrayList<String> arrayList= new ArrayList<>();
        ArrayList<GmailOrderDetails> gmailOrderDetailsArrayList= new ArrayList<>();
        pdf.setBuyerUserId(jsonObject.getString("Buyer"));
        pdf.setBuyerName(jsonObject.getString("BuyerName"));
        pdf.setBuyerStreet1(jsonObject.getString("BuyerStreet1"));
        pdf.setBuyerStreet2(jsonObject.getString("BuyerStreet2"));
        pdf.setBuyerCountry(jsonObject.getString("BuyerCountry"));
        try {
            if (jsonObject.get("Item discount") != null)
                pdf.setItemDiscount(jsonObject.getString("Item discount"));
        }catch (Exception e){}
        pdf.setTotalAmount(jsonObject.getString("Order total"));
        pdf.setPostageFee(jsonObject.getString("Postage"));
        pdf.setPaymentMethod(jsonObject.getString("Payment method"));
        pdf.setPaidDate(jsonObject.getString("Paid on"));
        pdf.setPlacedDate(jsonObject.getString("Placed on"));
        try {
            pdf.setSubTotal(String.valueOf(pdf.getTotalAmount()));
            Double subTotal=Double.parseDouble(pdf.getTotalAmount().replace("£", "")) - Double.parseDouble(pdf.getItemDiscount().replace("£", ""));
            pdf.setSubTotal("£"+String.valueOf(Math.round(subTotal * 100.0) / 100.0));
        }catch (Exception e){}
        JSONArray jArray = jsonObject.getJSONArray("orderDetails");
        int len = jArray.length();
        for (int k = 0; k < len; k++) {
            arrayList.add(jArray.get(k).toString());
        }
        for(int i=0;i<arrayList.size();i++){
            JSONObject orderDetailObject = new JSONObject(arrayList.get(i));
            String title= (String) orderDetailObject.get("Item name");
            String quantity=orderDetailObject.getString("Qty");
            String deliveryService=orderDetailObject.getString("Delivery service");
            String itemPrice=orderDetailObject.getString("Item price");
            String sellerUserId=orderDetailObject.getString("sellerUserId");
            String extendedOrderId=orderDetailObject.getString("extendedOrderId");
            GmailOrderDetails gmailOrderDetails=new GmailOrderDetails(title,quantity,deliveryService,itemPrice,sellerUserId,extendedOrderId);
            gmailOrderDetailsArrayList.add(gmailOrderDetails);
        }
        pdf.setOrderDetails(gmailOrderDetailsArrayList);
        pdfRepository.save(pdf);
        ByteArrayInputStream bis = null;
        try{
            bis =htmlToPDFService.HTMlToPdfConversion(pdf);
        }catch(DocumentException e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename="+pdf.getPlacedDate()+ pdf.getTotalAmount()+".pdf");
        return PdfEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @Transactional
    public PdfEntity<InputStreamResource> generateMagpiePdf(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        Pdf pdf=new Pdf();Double total=0.00;
        ArrayList<String> arrayList= new ArrayList<>();
        ArrayList<Double> priceList= new ArrayList<>();
        ArrayList<GmailOrderDetails> gmailOrderDetailsArrayList= new ArrayList<>();
        JSONArray jArray = jsonObject.getJSONArray("orderDetails");
        int len = jArray.length();
        for (int k = 0; k < len; k++) {
            arrayList.add(jArray.get(k).toString());
        }
        for(int i=0;i<arrayList.size();i++){
            JSONObject orderDetailObject = new JSONObject(arrayList.get(i));
            String title= (String) orderDetailObject.get("title");
            String quantity=orderDetailObject.getString("quantity");
            String itemPrice=orderDetailObject.getString("price");
            priceList.add(Double.parseDouble(itemPrice.replace("£","")));
            String extendedOrderId=jsonObject.getString("orderNumber");
            GmailOrderDetails gmailOrderDetails=new GmailOrderDetails(title,quantity,null,itemPrice,"MusicMagpie",extendedOrderId);
            gmailOrderDetailsArrayList.add(gmailOrderDetails);
        }
        pdf.setOrderDetails(gmailOrderDetailsArrayList);
        for( int j=0;j<priceList.size();j++){
            total=total+priceList.get(j);
            total=Math.round(total * 100.0) / 100.0;
        }
        pdf.setTotalAmount("£"+total.toString());
        pdf.setPaidDate(jsonObject.getString("orderDate"));
        String promo=jsonObject.getString("promo");
        if(promo.toLowerCase().equals("promo"))
            pdf.setPostageFee("None");
        else
            pdf.setPostageFee(promo);
        ByteArrayInputStream bis = null;
        try{
            bis =magpiePdfService.HTMlToPdfConversion(pdf);
        }catch(DocumentException e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename="+pdf.getPaidDate()+pdf.getTotalAmount()+".pdf");
        return PdfEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }

    /*public static void generatePDFFromHTML(String data) throws IOException, DocumentException {
        String orderNumber=data.split("Order number:")[1].split("</span>")[0];
        File f = new File(orderNumber+".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(toXHTML("<html><head><link rel=\"stylesheet\" href=\"https://ir.ebaystatic.com/rs/v/ars1p3qydy4ihcuhc1ypeiwjbq3.css\"><link rel=\"stylesheet\" href=\"https://ir.ebaystatic.com/rs/v/ftmbvcqzuu3f5il0zns5y1v1v2j.css\"></head><body>"+data+"</body></html>"));
        bw.close();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(orderNumber+".pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(f));
        document.close();
    }

    private static String toXHTML(String html) {
        final org.jsoup.nodes.Document document = Jsoup.parse(html);
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        return document.html();
    }*/



}