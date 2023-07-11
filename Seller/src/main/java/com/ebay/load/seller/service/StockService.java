package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.converter.EbayActiveListingConverter;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.ItemDetails;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.StockRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.call.GetMyeBaySellingCall;
import com.ebay.sdk.call.ReviseItemCall;
import com.ebay.soap.eBLBaseComponents.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;

    @Autowired
    EbayService ebayService;

    @Autowired
    EbayActiveListingConverter ebayActiveListingConverter;

    @Autowired
    AccountsRepository accountsRepository;

    @Transactional
    public ResponseEntity < Stock > save(Stock s, String accountId) {
        if (s.getId() == null) {
            s.setCreatedDate(new Date());
            s.setAccountId(accountId);
            s.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
            s.setCreatedBy(SessionUserInfo.getLoggedInUser().getUser().getName());
            Stock s1 = stockRepository.save(s);
            return new ResponseEntity < Stock > ().withResults(s1);
        } else if (s.getId() != null) {
            Stock s1 = updateStock(s);
            stockRepository.save(s1);
        }
        return new ResponseEntity < Stock > ().withResults(s);
    }

    public List < String > cloneList(List < String > list) {
        List < String > clone = new ArrayList < String > ();
        clone.addAll(list);
        return clone;
    }

    public ResponseEntity < List < Stock >> findAll(Pageable p, String accountId) {
        Page < Stock > s = stockRepository.findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, null, null, p);
        return new ResponseEntity < List < Stock >> ().withResults(s.getContent()).withTotalPages(s.getTotalPages()).withTotalElements(s.getTotalElements());
    }

    public Stock updateStock(Stock s) {
        s.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
        s.setModifiedDate(new Date());
        Stock s2 = stockRepository.findOneById(s.getId());

        if (s2.getQuantityAvailable() != s.getQuantityAvailable()) {
            s2.setLastEffectiveDate(new Date());
            s2.setStockDelete("DELETED");
            s2.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
            s2.setModifiedDate(new Date());
            s.setId(null);
            s.setSku(cloneList(s2.getSku()));
            /*
                        if(s2.getSku().size()!=0){
                            String sku=String.valueOf(s2.getSku()).replaceAll("[A-Za-z-+></]", "");
                            s.setSkuNumber(Integer.valueOf(sku.substring(1,sku.length()-1)));
                        }*/
            stockRepository.save(s);

        }
        return s;
    }

    public ResponseEntity < Stock > saveSku(String id, String sku, String accountId) {
        Stock s = stockRepository.findOneById(id);
        List < Stock > check = stockRepository.findBySkuList(sku, accountId);
        if (check.size() != 0) {
            return new ResponseEntity < List < Stock >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("Sku Already Exist"));
        }
        if (s != null) {
            s.setSku(cloneList(Collections.singletonList(sku)));
            try {
                s.setSkuNumber(Integer.valueOf(sku.replaceAll("[A-Za-z-+></]", "")));
            } catch (Exception e) {}
            save(s, accountId);
            return new ResponseEntity < Stock > ().withResults(s);
        }

        return new ResponseEntity < List < Stock >> ().withErrors(true);
    }

    public ResponseEntity < List < Stock >> findSearchText(String text, String accountId) {
        if (text.contains("-")) {
            List < Stock > s = stockRepository.findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndSkuContainingIgnoreCase(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, text);
            return new ResponseEntity < List < Stock >> ().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        } else {
            List < Stock > s = stockRepository.findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndTitleContainingIgnoreCaseAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, null, null, text);
            return new ResponseEntity < List < Stock >> ().withResults(s).withTotalPages(1).withTotalElements(Long.valueOf(s.size()));
        }
    }

    public ResponseEntity < Stock > findStockById(String id) {
        Stock s2 = stockRepository.findOneById(id);
        if (s2 != null)
            return new ResponseEntity < Stock > ().withResults(s2);
        else
            return null;
    }


    public ResponseEntity < List < Stock >> findStockHistoryById(String id, Pageable p) {
        Page < Stock > s2 = stockRepository.findHistoryByStockId(id, p);
        return new ResponseEntity < List < Stock >> ().withResults(s2.getContent()).withTotalPages(s2.getTotalPages()).withTotalElements(s2.getTotalElements());
    }

    public Stock getStock(String sku, String accountId) {
        try {
            Stock s = stockRepository.findBySKU(sku, accountId);
            return s;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResponseEntity < List < Stock >> checkSkuAvailability(String sku, String accountId) {
        List < Stock > s = stockRepository.findBySkuList(sku, accountId);
        if (s.size() != 0)
            return new ResponseEntity < List < Stock >> ().withResults(s);
        else
            return null;
    }

    public ResponseEntity < Stock > checkItemIdExistence(String itemId, String accountId) {
        Stock s = stockRepository.findByOwnerIdAndItemIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(), itemId, accountId);
        if (s != null)
            return new ResponseEntity < Stock > ().withResults(s);
        else
            return null;
    }

    public ResponseEntity < Stock > checkEanExistence(String ean, String accountId) {
        Stock s = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, ean);
        if (s != null)
            return new ResponseEntity < Stock > ().withResults(s);
        else
            return null;
    }

    public ResponseEntity < List < Stock >> getListingsAndCheckWithStock(String accountId, Pageable p) {
        Accounts a = accountsRepository.findByAccountsId(accountId);
        boolean processing = true;
        int totLoaded = 0;

        if (a != null) {
            int currentPage = 1;
            Pageable pb = new PageRequest(currentPage, p.getPageSize());
            do {
                ResponseEntity < List < EbayListing >> l = getListings(a, pb);
                if (l != null && !l.isErrors()) {
                    for (int j = 0; j < l.getResults().size(); j++) {
                        Stock sk = stockRepository.findByOwnerIdAndItemIdAndAccountId(a.getOwnerIdPk(), a.getId(), l.getResults().get(j).getItemId());
                        if (sk == null) {
                            saveListingDetailsToStock(l.getResults().get(j), a);
                        } else if (l.getResults().get(j).getVariantItemDetails() != null) {
                            updateListingDetailsInStock(sk, l.getResults().get(j));
                        }
                    }
                } else if (l.isErrors() == true)
                    return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(l.getMessages().get(0).getMessageText()));
                totLoaded += l.getResults().size();
                if (totLoaded >= l.getTotalElements()) processing = false;
                if (totLoaded < l.getTotalElements()) {
                    currentPage++;
                    pb = new PageRequest(currentPage, p.getPageSize());
                }
            } while (processing);
        } else {
            return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("Account Not Found"));
        }
        Page < Stock > s = stockRepository.findAll(p);
        setItemIdToStock(accountId);
        return new ResponseEntity < List < Stock >> ().withResults(s.getContent()).withTotalPages(s.getTotalPages()).withTotalElements(s.getTotalElements());
    }

    public ResponseEntity < List < EbayListing >> getListings(Accounts s, Pageable p) {
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
                return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
            }

            if (api.getReturnedActiveList() != null)
                tempActiveItems = (api.getReturnedActiveList().getItemArray()).getItem();
            int totalNumberOfEntries = api.getReturnedActiveList().getPaginationResult().getTotalNumberOfEntries();
            int totalNumberOfPages = api.getReturnedActiveList().getPaginationResult().getTotalNumberOfPages();
            final ItemType[] activeItems = tempActiveItems;
            if (activeItems != null) {
                return new ResponseEntity < List < EbayListing >> ().withResults(ebayActiveListingConverter.converTo(activeItems)).withTotalElements(Long.parseLong("" + totalNumberOfEntries)).withTotalPages(totalNumberOfPages);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void saveListingDetailsToStock(EbayListing l, Accounts a) {
        try {
            ItemType item = ebayService.geItem(l.getItemId(), a);
            if (item.getVariations() != null) {
                for (int i = 0; i < item.getVariations().getVariationLength(); i++) {
                    Stock s = new Stock();
                    s.setTitle(item.getTitle() + "[" + item.getVariations().getVariation(i).getVariationSpecifics().getNameValueList(0).getValue(0) + "]");
                    if (item.getVariations().getPictures() != null && item.getVariations().getPicturesLength() != 0)
                        s.setImageUrl(item.getVariations().getPictures(0).getVariationSpecificPictureSet(i).getPictureURL(0));
                    if (item.getVariations().getVariation(i).getSKU() != null) {
                        s.setSku(Collections.singletonList(item.getVariations().getVariation(i).getSKU()));
                        try {
                            String sku = item.getVariations().getVariation(i).getSKU();
                            s.setSkuNumber(Integer.valueOf(sku.substring(sku.lastIndexOf("-") + 1).replaceAll("\\s", "").replaceAll("[A-Za-z-+></]", "")));

                        } catch (Exception e) {}
                    }
                    s.setQuantityAvailable(item.getVariations().getVariation(i).getQuantity());
                    s.setAccountId(a.getId());
                    s.setName(a.getAccountName());
                    s.setItemId(Collections.singletonList(item.getItemID()));
                    s.setAccountId(a.getId());
                    s.setCurrency(item.getVariations().getVariation(i).getStartPrice().getCurrencyID().value());
                    s.setBuyItNowPrice(item.getVariations().getVariation(i).getStartPrice().getValue());
                    s.setAvgPrice(item.getVariations().getVariation(i).getStartPrice().getValue());
                    save(s, a.getId());
                }
            } else {
                Stock s = new Stock();
                s.setItemId(Collections.singletonList(item.getItemID()));
                try {
                    if (l.getImageUrl() != null)
                        s.setImageUrl(l.getImageUrl());
                    else if (item.getPictureDetails() != null && item.getPictureDetails().getPictureURL().length != 0)
                        s.setImageUrl(item.getPictureDetails().getPictureURL(0));
                } catch (Exception e) {}
                s.setTitle(item.getTitle());
                if (item.getConditionDisplayName() != null)
                    s.setItemCondition(item.getConditionDisplayName());
                s.setQuantityAvailable(l.getQuantityAvailable());
                s.setAccountId(a.getId());
                if (item.getSKU() != null) {
                    s.setSku(Arrays.asList(item.getSKU()));
                    try {
                        s.setSkuNumber(Integer.valueOf(item.getSKU().substring(item.getSKU().lastIndexOf("-") + 1).replaceAll("\\s", "").replaceAll("[A-Za-z-+></]", "")));
                    } catch (Exception e) {
                        System.out.println(item.getItemID() + item.getSKU());
                    }
                }
                if (item.getProductListingDetails().getEAN() != null)
                    s.setEan(Collections.singletonList(item.getProductListingDetails().getEAN()));
                s.setCurrency(l.getBuyItNowPriceCurrencyID());
                s.setBuyItNowPrice(Double.valueOf(l.getBuyItNowPriceValue()));
                s.setName(a.getAccountName());
                s.setAvgPrice(Double.valueOf(l.getBuyItNowPriceValue()));
                save(s, a.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateListingDetailsInStock(Stock sk, EbayListing ebayListing) {

        try {
            if (ebayListing.getVariantItemDetails().size() != 0) {
                ResponseEntity < EbayListing > listing = ebayService.findStockById(SessionUserInfo.getLoggedInUser().getUser().getId(), sk.getAccountId(), ebayListing.getItemId());
                for (int i = 0; i < listing.getResults().getVariantItemDetails().size(); i++) {
                    String title = listing.getResults().getTitle() + "[" + listing.getResults().getVariantItemDetails().get(i).getName() + "]";
                    List < Stock > stock = stockRepository.findByOwnerIdAndAccountIdAndTitleAndStockDeleteAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), sk.getAccountId(), title, null);
                    if (listing.getResults().getVariantItemDetails().get(i).getEan() != null && stock != null) {
                        for (int j = 0; j < stock.size(); j++) {
                            stock.get(j).setEan(Collections.singletonList(listing.getResults().getVariantItemDetails().get(i).getEan()));
                            save(stock.get(j), sk.getAccountId());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List < Stock > getStockAndOrderId(String sku, String ordId) {
        try {
            List < Stock > s = stockRepository.findBySKUAndOrder(sku, ordId);
            return s;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public ResponseEntity < List < Stock >> deleteFromStock(List < String > id, String accountId, Pageable p) {

        for (String anId : id) {
            Stock sk = stockRepository.findOneById(anId);
            if (sk != null) {
                sk.setStockDelete("DELETED");
                stockRepository.save(sk);
            }
        }
        return findAll(p, accountId);
    }

    public ResponseEntity < List < Stock >> attachToAwaitingDispatch(List < String > id, String accountId, Pageable p) {
        for (String anId : id) {
            Stock sk = stockRepository.findOneById(anId);
            if (sk != null) {
                sk.setLinkAwaitingDispatch(true);
                stockRepository.save(sk);
            }
        }
        return findAll(p, accountId);
    }

    public ResponseEntity < List < Stock >> getSortedStockData(Pageable p, String accountId) {
        List < Stock > result = stockRepository.findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, null, null, p.getSort());
        return paginateResult(result, p);
    }

    public ResponseEntity < List < Stock >> deleteEntireStockDataByAccount(Pageable p, String accountId) {
        List < Stock > result = stockRepository.findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, null, null, p.getSort());
        try {
            for (int i = 0; i < result.size(); i++)
                stockRepository.delete(result.get(i));
        } catch (Exception e) {}
        return null;
    }

    public ResponseEntity < List < Stock >> paginateResult(List < Stock > result, Pageable p) {
        int start = (p.getPageNumber() - 1) * p.getPageSize();
        if (start < 0) start = 0;
        else if (start >= 0) start += p.getPageSize();
        int max = start + p.getPageSize();
        int totalPages = result.size() / p.getPageSize();
        if (result.size() % p.getPageSize() > 0) totalPages++;
        if (max > result.size()) max = result.size();
        List < Stock > subResult = result.subList(start, max);
        return new ResponseEntity < List < Stock >> ().withResults(subResult).withTotalPages(totalPages).withTotalElements(Long.parseLong("" + result.size()));
    }

    public ResponseEntity < List < Stock >> getStockWithInLimits(String ownerId, String accountId, Integer quantity, Pageable p) {
        Page < Stock > limitedStock = stockRepository.findByOwnerIdAndAccountIdAndStockDeleteAndStockLimitAndQuantityAvailableAfterAndQuantityAvailableBeforeAndStockCodeNull(ownerId, accountId, null, quantity, 0, quantity, p);
        return new ResponseEntity < > ().withResults(limitedStock).withTotalElements(limitedStock.getTotalElements()).withTotalPages(limitedStock.getTotalPages());
    }

    public ResponseEntity < List < Stock >> getLimitedStock(String ownerId, String accountId) {
        List < Stock > limitedStock = stockRepository.findByOwnerIdAndAccountIdAndStockDeleteAndStockLimitNotNullAndStockCodeNull(ownerId, accountId, null);
        ArrayList < Stock > s = new ArrayList < Stock > ();
        for (int i = 0; i < limitedStock.size(); i++) {
            if (limitedStock.get(i).getQuantityAvailable() <= limitedStock.get(i).getStockLimit()) {
                s.add(limitedStock.get(i));
            }
        }
        return new ResponseEntity < > ().withResults(s).withTotalElements(Long.valueOf(s.size())).withTotalPages(1);
    }

    public ResponseEntity < List < Stock >> setStockLimit(List < String > stockId, Integer limitValue, String accountId) {
        for (int i = 0; i < stockId.size(); i++) {
            Stock s2 = stockRepository.findOneById(stockId.get(i));
            s2.setStockLimit(limitValue);
            stockRepository.saveAndFlush(s2);
        }
        Page < Stock > s = stockRepository.findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId, null, null, new PageRequest(0, 200));
        return new ResponseEntity < List < Stock >> ().withResults(s.getContent()).withTotalPages(s.getTotalPages()).withTotalElements(s.getTotalElements());
    }

    public ResponseEntity < Stock > updateStockWithEan(String ean, String accountId, Integer quantity, String condition) {
        if (!ean.equals("undefined")) {
            Stock s = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, ean);
            if (s != null) {
                try {
                    Integer q = s.getQuantityAvailable() + quantity;
                    s.setQuantityAvailable(q);
                    s.setModifiedDate(new Date());
                    stockRepository.saveAndFlush(s);
                    s.setDescription(s.getTitle().substring(0, 17) + "....'s Quantity Updated to " + s.getQuantityAvailable());
                    return new ResponseEntity < Stock > ().withResults(s);
                } catch (Exception e) {
                    new ResponseEntity < Stock > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
                }
            } else if (s == null) {
                Stock result = new Stock();
                result.setDescription("Oops! EAN Number " + ean + " Doesn't Exist In Stock");
                return new ResponseEntity < > ().withResults(result);
            }
        }
        Stock result = new Stock();
        result.setDescription("Invalid EAN number, Please Scan or Paste valid EAN Number");
        return new ResponseEntity < > ().withResults(result);
    }

    public ResponseEntity < Stock > savePurchaseOrdersToStock(String accountId, List<Stock> purchasedStock) {
        Stock eanStock = new Stock();Stock skuStock = new Stock();
        try {
            for (Stock aStock : purchasedStock) {
                if (aStock.getEan() != null && aStock.getEan().size() != 0)
                    eanStock = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, aStock.getEan().get(0));
                else
                    eanStock = null;
                if (aStock.getSku() != null && aStock.getSku().size() != 0)
                    skuStock = stockRepository.findBySKU(aStock.getSku().get(0), accountId);
                else
                    skuStock = null;
                if (eanStock != null && skuStock == null) {
                    updatePurchasedStock(eanStock, aStock);
                } else if (skuStock != null) {
                    updatePurchasedStock(skuStock, aStock);
                } else {
                    save(aStock, accountId);
                    modifyStock(aStock);
                }
            }
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    public void modifyStock(Stock aStock){
        Stock duplicate=new Stock();
        duplicate.setTitle(aStock.getTitle());
        duplicate.setSku(cloneList(aStock.getSku()));
        if(aStock.getEan()!=null && aStock.getEan().size()!=0)
        duplicate.setEan(cloneList(aStock.getEan()));
        duplicate.setQuantityAvailable(aStock.getQuantityAvailable());
        duplicate.setOwnerId(aStock.getOwnerId());
        duplicate.setAccountId(aStock.getAccountId());
        if(aStock.getBuyItNowPrice()!=null) {
            duplicate.setBuyItNowPrice(aStock.getBuyItNowPrice());
            duplicate.setAvgPrice(aStock.getBuyItNowPrice());
        }
        if(aStock.getItemId()!=null && aStock.getItemId().size()!=0)
        duplicate.setItemId(cloneList(aStock.getItemId()));
        if(aStock.getImageUrl()!=null)
        duplicate.setImageUrl(aStock.getImageUrl());
        if(aStock.getItemCondition()!=null)
        duplicate.setItemCondition(aStock.getItemCondition());
        if(aStock.getItemCategory()!=null)
        duplicate.setItemCategory(aStock.getItemCategory());
        if(aStock.getItemSubCategory()!=null)
        duplicate.setItemSubCategory(aStock.getItemSubCategory());
        stockRepository.save(duplicate);
    }

    public void updatePurchasedStock(Stock existingStock, Stock purchasedStock) {
        existingStock.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
        existingStock.setModifiedDate(new Date());
        if(existingStock.getBuyItNowPrice()!=null && purchasedStock.getBuyItNowPrice()!=null)
        existingStock.setAvgPrice(averagePrice(existingStock, purchasedStock));
        Stock s = stockRepository.save(existingStock);
        stockRepository.save(purchasedStock);
    }

    public Double averagePrice(Stock existing, Stock purchased) {
        int origQuantity = existing.getQuantityAvailable();
        if(purchased.getQuantityAvailable()!=null)
        existing.setQuantityAvailable(existing.getQuantityAvailable() + purchased.getQuantityAvailable());
        double origAvgPRice = existing.getAvgPrice() != null ? existing.getAvgPrice() : existing.getBuyItNowPrice();
        return  ((origQuantity * origAvgPRice) + (purchased.getQuantityAvailable() * purchased.getBuyItNowPrice())) / (origQuantity + purchased.getQuantityAvailable());
    }

    public ResponseEntity < Stock > showAddedSourceData(String accountId, Pageable p) {
        Page < Stock > list = stockRepository.findByAccountIdAndStockCodeNotNullAndListingTypeNullAndStockDeleteNull(accountId, p);
        return new ResponseEntity < List < Stock >> ().withResults(list.getContent()).withTotalPages(list.getTotalPages()).withTotalElements(list.getTotalElements());

    }

    public ResponseEntity <Stock> getAddedSourceData(String accountId, String orderRefId, String stockCode) {
        List < Stock > list = stockRepository.findByAccountIdAndOrderRefIdAndStockCodeAndListingTypeNullAndStockDeleteNull(accountId, orderRefId, stockCode);
        return new ResponseEntity < List < Stock >> ().withResults(list);

    }

    public ResponseEntity < Stock > getIndividualThirdPartyStock(String accountId, String orderId) {
        List < Stock > list = stockRepository.findByAccountIdAndOrderRefIdAndStockCodeAndListingTypeNullAndStockDeleteNull(accountId, orderId, "Facebook");
        return new ResponseEntity < List < Stock >> ().withResults(list);

    }

    public ResponseEntity < Stock > checkData(String accountId, String orderId, PageRequest pageRequest) {
        List < Stock > list = new ArrayList < > ();
        if (orderId != null && !orderId.equals("")) {
            list = stockRepository.findByAccountIdAndOrderRefIdAndStockCodeAndListingTypeNullAndStockDeleteNull(accountId, orderId, "Facebook");
            return new ResponseEntity < List < Stock >> ().withResults(list);
        } else
            return new ResponseEntity < List < Stock >> ().withResults(list);
    }

    public ResponseEntity < Stock > removeData(String accountId, List < String > id) {
        Stock ebayStock = new Stock();
        for (int i = 0; i < id.size(); i++) {
            Stock thirdPartyStockData = stockRepository.findOneById(id.get(i));
            if (thirdPartyStockData.getEan().size() != 0)
                ebayStock = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, thirdPartyStockData.getEan().get(0));
            else if (thirdPartyStockData.getSku().size() != 0)
                ebayStock = stockRepository.findBySKU(thirdPartyStockData.getSku().get(0), accountId);
            if (ebayStock != null && ebayStock.getId() != null) {
                try {
                    if (ebayStock.getQuantityAvailable() - thirdPartyStockData.getQuantityAvailable() >= 0)
                        ebayStock.setQuantityAvailable(ebayStock.getQuantityAvailable() - thirdPartyStockData.getQuantityAvailable());
                    else
                        ebayStock.setQuantityAvailable(thirdPartyStockData.getQuantityAvailable() - ebayStock.getQuantityAvailable());
                    stockRepository.save(ebayStock);
                } catch (Exception e) {};
            }
            stockRepository.delete(thirdPartyStockData);
        }
        return showAddedSourceData(accountId, new PageRequest(0, 200));

    }

    public ResponseEntity < Stock > updateThirdPartyStock(Stock modifiedStock, String accountId, String position) {
        Stock stockOrigin = new Stock();
        if (modifiedStock.getId() != null) {
            Stock unModifiedStock = stockRepository.findOneById(modifiedStock.getId());
            try {
                if (position.equals("quantity") || position.equals("price")) {
                    if (unModifiedStock.getEan().size() != 0)
                        stockOrigin = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, unModifiedStock.getEan().get(0));
                    else if (unModifiedStock.getSku().size() != 0)
                        stockOrigin = stockRepository.findBySKU(unModifiedStock.getSku().get(0), accountId);
                    if (position.equals("quantity") && stockOrigin != null)
                        stockOrigin.setQuantityAvailable(stockOrigin.getQuantityAvailable() - unModifiedStock.getQuantityAvailable() + modifiedStock.getQuantityAvailable());
                    if (position.equals("price") && stockOrigin != null)
                        stockOrigin.setBuyItNowPrice(averagePrice(stockOrigin, modifiedStock));
                }
                if (position.equals("sku")) {
                    stockOrigin = stockRepository.findBySKU(unModifiedStock.getSku().get(0), accountId);
                    if (stockOrigin != null)
                        stockOrigin.setQuantityAvailable(stockOrigin.getQuantityAvailable() - unModifiedStock.getQuantityAvailable());
                    Stock newStockOrigin = stockRepository.findBySKU(modifiedStock.getSku().get(0), accountId);
                    if (newStockOrigin != null) {
                        newStockOrigin.setQuantityAvailable(newStockOrigin.getQuantityAvailable() + modifiedStock.getQuantityAvailable());
                        stockRepository.save(newStockOrigin);
                    }
                }
                if (position.equals("ean")) {
                    stockOrigin = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, unModifiedStock.getEan().get(0));
                    if (stockOrigin != null)
                        stockOrigin.setQuantityAvailable(stockOrigin.getQuantityAvailable() - unModifiedStock.getQuantityAvailable());
                    Stock newStockOrigin = stockRepository.findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(accountId, modifiedStock.getEan().get(0));
                    if (newStockOrigin != null) {
                        newStockOrigin.setQuantityAvailable(newStockOrigin.getQuantityAvailable() + modifiedStock.getQuantityAvailable());
                        stockRepository.save(newStockOrigin);
                    }
                }
                stockRepository.save(modifiedStock);
                if (stockOrigin != null)
                    stockRepository.save(stockOrigin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            modifiedStock.setAccountId(accountId);
            stockRepository.save(modifiedStock);
        }
        return null;
    }

    public ResponseEntity < Stock > getListingsAndAddToStock(String accountId, List < String > itemList, String sku) {
        Accounts a = accountsRepository.findByAccountsId(accountId);
        ArrayList < String > itemIds = new ArrayList < String > ();
        Stock s = null;
        if (!sku.equals(""))
            s = stockRepository.findBySKU(sku, accountId);
        for (int i = 0; i < itemList.size(); i++) {
            Stock sk = stockRepository.findByOwnerIdAndItemIdAndAccountId(a.getOwnerIdPk(), a.getId(), itemList.get(i));
            if (sk == null) {
                try {
                    ResponseEntity < EbayListing > ebayListing = ebayService.findStockById(a.getOwnerIdPk(), accountId, itemList.get(i));
                    if (s == null)
                        saveListingDetailsToStock(ebayListing.getResults(), a);
                    else {
                        s.setQuantityAvailable(s.getQuantityAvailable() + ebayListing.getResults().getQuantityAvailable());
                        itemIds.addAll(s.getItemId());
                        itemIds.add(ebayListing.getResults().getItemId());
                        s.setItemId(itemIds);
                        s.setModifiedBy(a.getOwnerIdPk());
                        s.setModifiedDate(new Date());
                        s.setAvgPrice(averagePriceWithItemId(s, ebayListing.getResults()));
                        stockRepository.save(s);
                    }
                } catch (Exception e) {
                    return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
                }
            }
        }
        return null;
    }

    public Double averagePriceWithItemId(Stock existing, EbayListing purchased) {
        int origQuantity = existing.getQuantityAvailable();
        existing.setQuantityAvailable(existing.getQuantityAvailable() + purchased.getQuantityAvailable());
        double origAvgPRice = existing.getAvgPrice() != null ? existing.getAvgPrice() : existing.getBuyItNowPrice();
        return ((origQuantity * origAvgPRice) + (purchased.getQuantityAvailable() * Integer.parseInt(purchased.getBuyItNowPriceValue()))) / (origQuantity + purchased.getQuantityAvailable());
    }

    public ResponseEntity < Stock > listFacebookItem(String accountId, List < String > stockId) {
        try {
            for (int i = 0; i < stockId.size(); i++) {
                Stock s = stockRepository.findByStockId(stockId.get(i));
                s.setListingType("YetToList");
                stockRepository.save(s);
            }
        } catch (Exception e) {
            return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        return null;
    }

    public Integer getStockWaitingToListNumber() {
        List < Stock > s = stockRepository.findByListingTypeAndStockCodeAndStockDeleteNull("YetToList","Facebook");
        return s.size();
    }

    public Integer getTotalStockListItNumber() {
        List < Stock > s = stockRepository.findByStockCodeAndListingTypeNotNullAndStockDeleteNull("Facebook");
        return s.size();
    }

    public Integer getTotalEbayStockListItNumber() {
        List < Stock > s = stockRepository.findByStockCodeAndListingTypeNotNullAndStockDeleteNull("eBay");
        return s.size();
    }

    public String ListItFacebookItem(String itemId) {
        ArrayList<String> itemArray= new ArrayList<>();
        itemArray.add(itemId);
        List < Stock > s = stockRepository.findByListingTypeAndStockCodeAndStockDeleteNull("YetToList","Facebook");
        s.get(0).setCreatedDate(new Date());
        s.get(0).setItemId(itemArray);
        stockRepository.saveAndFlush(s.get(0));
        if (s.size() != 0)
            return reviseNewListItem(s.get(0),itemId);
        return null;
    }

    public String ListItemToEbay(String itemId) {
        ArrayList<String> itemArray= new ArrayList<>();
        itemArray.add(itemId);
        List < Stock > s = stockRepository.findByListingTypeAndStockCodeAndStockDeleteNull("YetToList","eBay");
        s.get(0).setCreatedDate(new Date());
        s.get(0).setItemId(itemArray);
        stockRepository.saveAndFlush(s.get(0));
        if (s.size() != 0)
            return reviseNewListItem(s.get(0),itemId);
        return null;
    }

    public String reviseNewListItem(Stock s,String itemId){
        Accounts accounts = accountsRepository.findByIdAndOwnerIdPk(s.getAccountId(), s.getOwnerId());
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(accounts.getApiToken());
        apiContext.setApiServerUrl(accounts.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        apiContext.setApiLogging(new ApiLogging());
        try {
            ReviseItemCall call = new ReviseItemCall(apiContext);
            ItemType item = new ItemType();
            item.setItemID(itemId);
            item.setTitle(s.getTitle());
            AmountType amount = new AmountType();
            amount.setValue(s.getBuyItNowPrice());
            amount.setCurrencyID(CurrencyCodeType.GBP);
            item.setStartPrice(amount);
            if(s.getEan().size()!=0 && s.getEan().get(0)!=null) {
                ProductListingDetailsType productListingDetailsType=new ProductListingDetailsType();
                productListingDetailsType.setEAN(s.getEan().get(0));
                item.setProductListingDetails(productListingDetailsType);
            }
            item.setDescription(s.getTitle());
            item.setQuantity(s.getQuantityAvailable());
            if (s.getSku().size() != 0)
                item.setSKU(s.getSku().get(0));
            if(s.getImageUrl()!=null) {
                PictureDetailsType pictureDetailsType = new PictureDetailsType();
                pictureDetailsType.setPhotoDisplay(PhotoDisplayCodeType.PICTURE_PACK);
                pictureDetailsType.setPictureURL(new String[]{s.getImageUrl()});
                item.setPictureDetails(pictureDetailsType);
            }
            if(s.getItemSubCategory()!=null) {
                NameValueListArrayType nameValueListArrayType = new NameValueListArrayType();
                NameValueListType nameValueListType = new NameValueListType();
                nameValueListType.setName("Platform");
                nameValueListType.setValue(new String[]{s.getItemSubCategory()});
                NameValueListType nameValueListType1 = new NameValueListType();
                nameValueListType1.setName("Game Name");
                if( s.getTitle().length()>=60)
                    nameValueListType1.setValue(new String[]{s.getTitle().substring(0, 55)});
                else
                    nameValueListType1.setValue(new String[]{s.getTitle()});
                nameValueListArrayType.setNameValueList(new NameValueListType[]{nameValueListType, nameValueListType1});
                item.setItemSpecifics(nameValueListArrayType);
            }
            call.setItemToBeRevised(item);
            call.reviseItem();
            if(s.getEan().size()!=0 && s.getEan().get(0)!=null && s.getImageUrl()!=null)
                s.setListingType("Listed");
            else if((s.getEan().size()!=0 && s.getEan().get(0)==null) || s.getImageUrl()==null )
                s.setListingType("Full_Details_Not_Listed");
            s.setCreatedDate(new Date());
            stockRepository.saveAndFlush(s);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "UPDATE SUCCESS!";
    }

    public ResponseEntity < Stock > showThirdPartyListedStock(String accountId, Pageable p) {
        Page < Stock > list = stockRepository.findByAccountIdAndStockCodeNotNullAndListingTypeNotNullAndStockDeleteNull(accountId, p);
        return new ResponseEntity < List < Stock >> ().withResults(list.getContent()).withTotalPages(list.getTotalPages()).withTotalElements(list.getTotalElements());
    }

    @Transactional
    public ResponseEntity<String> setItemIdToStock(String accountId) {
        List < Stock > stock = stockRepository.findByOwnerIdAndAccountIdAndStockDeleteNullAndStockCodeNull(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId);
        try {
            for (Stock aStock: stock) {
                if (aStock.getSku()!=null && aStock.getSku().size()!=0) {
                    List < Stock > skuListedStocks = stockRepository.findBySkuList(aStock.getSku().get(0), accountId);
                    if (skuListedStocks.size() > 1) {
                        ArrayList<ItemDetails> list=new ArrayList<>();
                        ArrayList<String> itemId= new ArrayList<String>();
                        for (Stock stock1: skuListedStocks) {
                            ItemDetails itemDetails=new ItemDetails(stock1.getItemId().get(0),stock1.getBuyItNowPrice(),stock1.getQuantityAvailable());
                            list.add(itemDetails);
                            itemId.addAll(stock1.getItemId());
                        }
                        for(Stock stock2:skuListedStocks){
                            stock2.setItemDetails(list);
                            stock2.setItemId(itemId);
                            save(stock2, accountId);
                        }

                        for (Stock stock3: skuListedStocks) {
                            if(stock3.getImageUrl()!=null) {
                                for (Stock stock4 : skuListedStocks) {
                                    if (!stock4.getId().equals(stock3.getId()))
                                        stockRepository.delete(stock4);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String> ().withResults("Success");
    }

    public ResponseEntity < Stock > saveThirdPartyItemToRevise(Stock stock) {
        ArrayList<String> arrayList=new ArrayList<>();
        try {
            stock.setListingType("YetToList");
            stock.setItemId(arrayList);
            stockRepository.save(stock);
        } catch (Exception e) {
            return new ResponseEntity < EbayListing > ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        return null;
    }

    public ResponseEntity<Stock> deleteEntirelyFromStock(List<String> stockId) {
        for (String aStockId : stockId) {
            Stock stock=stockRepository.findOneById(aStockId);
            stockRepository.delete(stock);
        }
        return null;
    }
}









/*
      public ResponseEntity<List<Stock>> findAllByOwnerId(String ownerId, Pageable p){
      Page<Stock> s = stockRepository.findByOwnerId( ownerId,p);
      return new ResponseEntity<List<Stock>>().withResults(s.getContent()).withTotalPages(s.getTotalPages()).withTotalElements(s.getTotalElements());
      }
      public ResponseEntity<Stock> findStockById(String ownerId,String itemId){
          Stock s = stockRepository.findByIdAndOwnerId(Long.parseLong(itemId),ownerId);
          return new ResponseEntity<Stock>().withResults(s);
      }
  */