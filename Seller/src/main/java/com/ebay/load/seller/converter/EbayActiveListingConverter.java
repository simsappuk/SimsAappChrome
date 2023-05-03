package com.ebay.load.seller.converter;

import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.VariantItemDetails;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.util.*;

@Component
public class EbayActiveListingConverter {
    
    public EbayListing converTo(ItemType it) {
       /* id++;
        if(it.getPictureDetails()!=null && it.getPictureDetails().getAny(0)!=null)
        System.out.println("INSERT INTO public.item_url VALUES("+"'"+id+"'"+","+"'"+it.getItemID()+"'"+","+"'"+((TextImpl)((ElementNSImpl) it.getPictureDetails().getAny(0)).getFirstChild()).getData()+"'"+")"+";");
                */

        EbayListing st = new EbayListing();

        if (it.isAutoPay() != null)
            st.setAutoPay(it.isAutoPay());
        if (it.getBuyerProtection() != null) {
            st.setBuyerProtection(it.getBuyerProtection().value());
        }
        if (it.getBuyItNowPrice() != null) {
            st.setBuyItNowPriceValue(String.valueOf(it.getBuyItNowPrice().getValue()));
            st.setBuyItNowPriceCurrencyID(String.valueOf(it.getBuyItNowPrice().getCurrencyID()));
        }
        if (it.getCountry() != null) {
            st.setCountry(it.getCountry().value());
        }
        if (it.getCurrency() != null) {
            st.setCurrency(it.getCurrency().value());
        }
        st.setDescription(it.getDescription());
        if (it.getHitCounter() != null) {
            st.setHitCounter(it.getHitCounter().value());
        }
        st.setItemId(it.getItemID());
        if ((it.getListingDesigner() != null) && (it.getListingDesigner().getLayoutID() != null)) {
            st.setListingDesignerLayoutID(String.valueOf(it.getListingDesigner().getLayoutID().doubleValue()));
        }
        if ((it.getListingDesigner() != null) && (it.getListingDesigner().getThemeID() != null)) {
            st.setListingDesignerThemeID(String.valueOf(it.getListingDesigner().getThemeID().doubleValue()));
        }

        st.setListingDuration(it.getListingDuration());
        if (it.getListingType() != null) {
            st.setListingType(it.getListingType().value());
        }
        st.setLocation(it.getLocation());
        st.setPayPalEmailAddress(it.getPayPalEmailAddress());
        if (it.getPrimaryCategory() != null) {
            st.setPrimaryCategoryID(it.getPrimaryCategory().getCategoryID());
            st.setPrimaryCategoryName(it.getPrimaryCategory().getCategoryName());
        }
        st.setQuantity(String.valueOf(it.getQuantity()));
        if (it.getReservePrice() != null) {
            st.setReservePriceValue(String.valueOf(it.getReservePrice().getValue()));
            st.setReservePriceCurrencyID(String.valueOf(it.getReservePrice().getCurrencyID()));
            st.setReviseStatus_ItemRevised(String.valueOf(it.getReviseStatus().isItemRevised()));
        }
        if (it.getSite() != null) {
            st.setSite(it.getSite().value());
        }
        if (it.getStartPrice() != null) {
            st.setStartPriceValue(String.valueOf(it.getStartPrice().getValue()));
            st.setStartPriceCurrencyID(String.valueOf(it.getStartPrice().getCurrencyID()));
        }
        if (it.getStorefront() != null) {
            st.setStoreFrontCategoryID(String.valueOf(it.getStorefront().getStoreCategoryID()));
            st.setStoreFrontCategory2ID(String.valueOf(it.getStorefront().getStoreCategory2ID()));
            st.setStoreURL(it.getStorefront().getStoreURL());
        }
        st.setTitle(it.getTitle());
        if (it.getHitCount() != null) {
            st.setHitCount(String.valueOf(it.getHitCount().longValue()));
        }
        if(it.getWatchCount()!=null){
            st.setWatchCount(it.getWatchCount());
        }
        st.setBuyerResponsibleforShipping(it.isBuyerResponsibleForShipping());
        st.setSku(it.getSKU());
        if(it.getQuantityAvailable()!=null)
        st.setQuantityAvailable(it.getQuantityAvailable());
        else if(it.getSellingStatus().getQuantitySold()!=null){
            Integer s=it.getQuantity()-it.getSellingStatus().getQuantitySold();
            st.setQuantityAvailable(s);
        }
        st.setPostalCode(it.getPostalCode());
        if (it.getDispatchTimeMax() != null) {
            st.setDispatchTimeMax(String.valueOf(it.getDispatchTimeMax().doubleValue()));
        }
        st.setProxyItem(it.isProxyItem());
        if (it.getBuyerGuaranteePrice() != null) {
            st.setBuyerGuaranteePriceValue(String.valueOf(it.getBuyerGuaranteePrice().getValue()));
            st.setBuyerGuaranteePriceCurrencyID(String.valueOf(it.getBuyerGuaranteePrice().getCurrencyID()));
        }
        if (it.getConditionID() != null) {
            st.setConditionID(String.valueOf(it.getConditionID().intValue()));
        }
        st.setConditionDisplayName(it.getConditionDisplayName());
        if (it.getRelistParentID() != null) {
            st.setRelistParentID(String.valueOf(it.getRelistParentID().floatValue()));
        }
        st.setHideFromSearch(it.isHideFromSearch());
        st.setEbayPlus(it.isEBayPlus());
        st.setEbayPlusEligible(it.isEBayPlusEligible());
        st.setSecureDescription(it.isIsSecureDescription());
        st.setTimeLeft(it.getTimeLeft().toString());
        st.setStartTime(it.getListingDetails().getStartTime());
        st.setEndTime(it.getListingDetails().getEndTime());
        if (it.getProductListingDetails() != null && it.getProductListingDetails().getEAN() != null)
            st.setEan(it.getProductListingDetails().getEAN());
        if (it.getReturnPolicy() != null && it.getReturnPolicy().getReturnsWithin() != null)
            st.setReturns(it.getReturnPolicy().getReturnsWithin());
        if (it.getVariations() != null) {
            st.setVariantItemDetails(getItemDetails(it));
            st.setVariationsTypes(it.getVariations());
            //System.out.println("Details :" + getItemDetails(it));
        }
        if(it.getVariations()==null)
        st.setTotalQuantitySold(it.getSellingStatus().getQuantitySold());
        try {
            ElementNSImpl s = ((ElementNSImpl) it.getPictureDetails().getAny()[0]);
            Node tx = s.getFirstChild();
            st.setImageUrl(((TextImpl) tx).getWholeText());
        } catch (Exception e) {
            st.setImageError("Unable to Send Image from API");
        }
        return st;
    }

    public List<EbayListing> converTo(ItemType[] it) {
        List<EbayListing> list = new <EbayListing>ArrayList();
        if (it != null)
            for (int i = 0; i < it.length; i++)
                list.add(converTo(it[i]));

        return list;
    }


    public ArrayList<VariantItemDetails> getItemDetails(ItemType it) {
        HashMap<String, VariantItemDetails> map = new HashMap();
        VariantItemDetails v = new VariantItemDetails();
        if (it.getVariations().getVariation() != null) {
            for(int i=0;i<it.getVariations().getVariation().length;i++){
                v=new VariantItemDetails();
                v.setName(it.getVariations().getVariation(i).getVariationSpecifics().getNameValueList()[0].getValue(0));
                try {
                    if(it.getVariations().getVariation(i).getQuantity()!=null && it.getVariations().getVariation(i).getSellingStatus().getQuantitySold()!=null)
                        v.setQuantityAvailable(it.getVariations().getVariation(i).getQuantity() - it.getVariations().getVariation(i).getSellingStatus().getQuantitySold());
                    else
                        v.setQuantityAvailable(it.getVariations().getVariation(i).getQuantity());
                }catch(Exception e)
                {e.printStackTrace();}
                v.setPrice(String.valueOf(it.getVariations().getVariation(i).getStartPrice().getValue()));
                v.setCurrency(String.valueOf(it.getVariations().getVariation(i).getStartPrice().getCurrencyID()));
                if(it.getVariations().getVariation(i).getSKU()!=null) {
                    v.setSku(it.getVariations().getVariation(i).getSKU());
                }
                if(it.getVariations().getVariation(i).getVariationProductListingDetails()!=null) {
                    v.setEan(it.getVariations().getVariation(i).getVariationProductListingDetails().getEAN());
                }
                if(it.getVariations().getVariation(i).getVariationSpecifics()!=null) {
                    v.setItemType(it.getVariations().getVariation(i).getVariationSpecifics().getNameValueList(0).getName());
                }

                for(int j=0;j<it.getVariations().getPicturesLength();j++) {
                        if(it.getVariations().getPictures(0).getVariationSpecificPictureSet(j).getVariationSpecificValue().equals(v.getName())){
                            v.setUrl(it.getVariations().getPictures(0).getVariationSpecificPictureSet(j).getPictureURL(0));
                            break;
                        }
                }
                map.put(v.getName(), v);
            }
        }

        Collection<VariantItemDetails> values = map.values();
        ArrayList<VariantItemDetails> listOfValues = new ArrayList<VariantItemDetails>(values);

        return listOfValues;
    }

   }

 /*  SellingStatus ss =new SellingStatus();
        SellingStatus.HighBidder hb= new SellingStatus.HighBidder();
        ProductListing pl=new ProductListing();
        Seller sr=new Seller();
        ListingDetails ld=new ListingDetails();
        Seller.SellerInfo si=new Seller.SellerInfo();
        ShippingDetails sd =new ShippingDetails();
        PictureDetails pd=new PictureDetails();
        BusinessSellerDetails bsd=new BusinessSellerDetails();
        ReturnPolicy rp=new ReturnPolicy();
        BuyerRequirementDetails brd=new BuyerRequirementDetails();
        SellerProfiles sp=new SellerProfiles();
        ShippingPackageDetails spd=new ShippingPackageDetails();


        if(it.getSellingStatus()!=null) {
            if (it.getSellingStatus().getBidCount() != null) {
                ss.setBidCount(String.valueOf(it.getSellingStatus().getBidCount().floatValue()));
            }
            if(it.getSellingStatus().getBidIncrement()!=null) {
                ss.setBidIncrementValue(String.valueOf(it.getSellingStatus().getBidIncrement().getValue()));
                ss.setBidIncrementCurrencyID(String.valueOf(it.getSellingStatus().getBidIncrement().getCurrencyID()));
            }
            if(it.getSellingStatus().getConvertedCurrentPrice()!=null) {
                ss.setConvertedCurrentPriceValue(String.valueOf(it.getSellingStatus().getConvertedCurrentPrice().getValue()));
                ss.setConvertedCurrentPriceCurrencyID(String.valueOf(it.getSellingStatus().getConvertedCurrentPrice().getCurrencyID()));
            }
            if(it.getSellingStatus().getCurrentPrice()!=null) {
                ss.setCurrentPriceValue(String.valueOf(it.getSellingStatus().getCurrentPrice().getValue()));
                ss.setCurrentPriceCurrencyID(String.valueOf(it.getSellingStatus().getCurrentPrice().getCurrencyID()));
            }

            if (it.getSellingStatus().getLeadCount() != null) {
                ss.setLeadCount(String.valueOf(it.getSellingStatus().getLeadCount().doubleValue()));
            }
            if(it.getSellingStatus().getMinimumToBid()!=null) {
                ss.setMinimumToBidValue(String.valueOf(it.getSellingStatus().getMinimumToBid().getValue()));
                ss.setMinimumToBidCurrencyID(String.valueOf(it.getSellingStatus().getMinimumToBid().getCurrencyID()));
            }
            if ((it.getSellingStatus() != null) && (it.getSellingStatus().getQuantitySold() != null)) {
                ss.setQuantitySold(String.valueOf(it.getSellingStatus().getQuantitySold().intValue()));
            }
            ss.setReserveMet(it.getSellingStatus().isReserveMet());
            ss.setSecondChanceEligible(it.getSellingStatus().isSecondChanceEligible());
            if (it.getSellingStatus().getListingStatus() != null) {
                ss.setListingStatusValue(it.getSellingStatus().getListingStatus().value());
            }
            if (it.getSellingStatus().getQuantitySoldByPickupInStore() != null) {
                ss.setQuantitySoldByPickUpInStore(String.valueOf(it.getSellingStatus().getQuantitySoldByPickupInStore().doubleValue()));
            }
            if(it.getSellingStatus().getHighBidder()!=null) {
                hb.setAboutMePage(it.getSellingStatus().getHighBidder().isAboutMePage());
                hb.setEiasToken(it.getSellingStatus().getHighBidder().getEIASToken());
                hb.setEmail(it.getSellingStatus().getHighBidder().getEmail());
                if ((it.getSellingStatus() != null) && (it.getSellingStatus().getHighBidder() != null) && (it.getSellingStatus().getHighBidder().getFeedbackScore() != null)) {
                    hb.setFeedBackScore(String.valueOf(it.getSellingStatus().getHighBidder().getFeedbackScore().intValue()));
                }
                if ((it.getSellingStatus() != null) && (it.getSellingStatus().getHighBidder() != null) && (it.getSellingStatus().getHighBidder().getPositiveFeedbackPercent() != null)) {
                    hb.setPositiveFeedBackPercent(String.valueOf(it.getSellingStatus().getHighBidder().getPositiveFeedbackPercent().floatValue()));
                }
                hb.setFeedBackPrivate(it.getSellingStatus().getHighBidder().isFeedbackPrivate());
                if ((it.getSellingStatus() != null) && (it.getSellingStatus().getHighBidder() != null) && (it.getSellingStatus().getHighBidder().getFeedbackRatingStar() != null)) {
                    hb.setFeedBackRatingStar(it.getSellingStatus().getHighBidder().getFeedbackRatingStar().value());
                }
                hb.setIdVerified(it.getSellingStatus().getHighBidder().isIDVerified());
                hb.setEbayGoodStanding(it.getSellingStatus().getHighBidder().isEBayGoodStanding());
                hb.setNewUser(it.getSellingStatus().getHighBidder().isNewUser());
                if ((it.getSellingStatus() != null) && (it.getSellingStatus().getHighBidder() != null) && (it.getSellingStatus().getHighBidder().getSite() != null)) {
                    hb.setSite(it.getSellingStatus().getHighBidder().getSite().value());
                }
                if ((it.getSellingStatus() != null) && (it.getSellingStatus().getHighBidder() != null) && (it.getSellingStatus().getHighBidder().getStatus() != null)) {
                    hb.setStatus(it.getSellingStatus().getHighBidder().getStatus().value());
                }
                hb.setUserId(it.getSellingStatus().getHighBidder().getUserID());
                hb.setUserIdChanged(it.getSellingStatus().getHighBidder().isUserIDChanged());
                if ((it.getSellingStatus() != null) && (it.getSellingStatus().getHighBidder() != null) && (it.getSellingStatus().getHighBidder().getVATStatus() != null)) {
                    hb.setVatStatus(it.getSellingStatus().getHighBidder().getVATStatus().value());
                }
                hb.setUserAnonymized(it.getSellingStatus().getHighBidder().isUserAnonymized());
            }
        }

        if(it.getProductListingDetails()!=null) {
            pl.setIncudeStockPhotoUrl(it.getProductListingDetails().isIncludeStockPhotoURL());
            pl.setUseStockPhotoUrlAsGallery(it.getProductListingDetails().isUseStockPhotoURLAsGallery());
            pl.setProductReferenceId(it.getProductListingDetails().getProductReferenceID());
            pl.setEan(it.getProductListingDetails().getEAN());
            if((it.getProductListingDetails().getBrandMPN()!=null)&&(it.getProductListingDetails().getBrandMPN().getMPN()!=null)) {
                pl.setMpn(it.getProductListingDetails().getBrandMPN().getMPN());
            }
            pl.setIncludeEbayProductDetails(it.getProductListingDetails().isIncludeeBayProductDetails());
        }

        if(it.getSeller()!=null) {
            sr.setAboutMePage(it.getSeller().isAboutMePage());
            sr.setEmail(it.getSeller().getEmail());
            if (it.getSeller().getFeedbackScore() != null) {
                sr.setFeedBackScore(String.valueOf(it.getSeller().getFeedbackScore().doubleValue()));
            }
            if (it.getSeller().getPositiveFeedbackPercent() != null) {
                sr.setPositiveFeedBackPercent(String.valueOf(it.getSeller().getPositiveFeedbackPercent().floatValue()));
            }
            sr.setFeedBackPrivate(it.getSeller().isFeedbackPrivate());
            if (it.getSeller().getFeedbackRatingStar() != null) {
                sr.setFeedBackRatingStar(it.getSeller().getFeedbackRatingStar().value());
            }
            sr.setIdVerified(it.getSeller().isIDVerified());
            sr.setEbayGoodStanding(it.getSeller().isEBayGoodStanding());
            sr.setNewUser(it.getSeller().isNewUser());
            if (it.getSeller().getSite() != null) {
                sr.setSite(it.getSeller().getSite().value());
            }
            if (it.getSeller().getStatus() != null) {
                sr.setStatus(it.getSeller().getStatus().value());
            }
            sr.setUserId(it.getSeller().getUserID());
            sr.setUserIdChanged(it.getSeller().isUserIDChanged());
            if (it.getSeller().getVATStatus() != null) {
                sr.setVatStatus(it.getSeller().getVATStatus().value());
            }
            sr.setMotorsDealer(it.getSeller().isMotorsDealer());
            if ((it.getSeller().getSellerInfo() != null) && (it.getSeller().getSellerInfo().getMerchandizingPref() != null)) {
                si.setMerchandizingPref(it.getSeller().getSellerInfo().getMerchandizingPref().value());
            }
            if (it.getSeller().getSellerInfo() != null) {
                si.setStoreUrl(it.getSeller().getSellerInfo().getStoreURL());
                si.setSafePaymentExempt(it.getSeller().getSellerInfo().isSafePaymentExempt());
                si.setAllowPaymentEdit(it.getSeller().getSellerInfo().isAllowPaymentEdit());
                si.setCipBankAccountStored(it.getSeller().getSellerInfo().isCIPBankAccountStored());
                si.setCheckOutEnabled(it.getSeller().getSellerInfo().isCheckoutEnabled());
                si.setGoodStanding(it.getSeller().getSellerInfo().isGoodStanding());
                si.setQualifiesForB2BVat(it.getSeller().getSellerInfo().isQualifiesForB2BVAT());
                si.setStoreOwner(it.getSeller().getSellerInfo().isStoreOwner());
            }
        }
        if (it.getListingDetails() != null) {

            ld.setAdult(it.getListingDetails().isAdult());
            ld.setBindingAuction(it.getListingDetails().isBindingAuction());
            ld.setCheckOutEnabled(it.getListingDetails().isCheckoutEnabled());
            ld.setConvertedBuyItNowPriceValue(String.valueOf(it.getListingDetails().getConvertedBuyItNowPrice().getValue()));
            if ((it.getListingDetails().getConvertedBuyItNowPrice() != null) && (it.getListingDetails().getConvertedBuyItNowPrice().getCurrencyID() != null)) {
                ld.setConvertedBuyItNowPriceCurrencyId(it.getListingDetails().getConvertedBuyItNowPrice().getCurrencyID().value());
            }
            if(it.getListingDetails().getConvertedStartPrice()!=null) {
                ld.setConvertedStartPriceValue(String.valueOf(it.getListingDetails().getConvertedStartPrice().getValue()));
                if (it.getListingDetails().getConvertedStartPrice().getCurrencyID() != null) {
                    ld.setConvertedStartPriceCurrencyId(it.getListingDetails().getConvertedStartPrice().getCurrencyID().value());
                }
            }
            if(it.getListingDetails().getConvertedReservePrice()!=null) {
                ld.setConvertedReservePriceValue(String.valueOf(it.getListingDetails().getConvertedReservePrice().getValue()));
                if(it.getListingDetails().getConvertedReservePrice().getCurrencyID()!=null) {
                    ld.setConvertedReservePriceCurrencyId(it.getListingDetails().getConvertedReservePrice().getCurrencyID().value());
                }
            }
            ld.setHasReservePrice(it.getListingDetails().isHasReservePrice());
            ld.setHasPublicMessages(it.getListingDetails().isHasPublicMessages());
            ld.setViewItemUrl(it.getListingDetails().getViewItemURL());
            ld.setHasUnansweredQuestions(it.getListingDetails().isHasUnansweredQuestions());
            ld.setViewItemUrlForNaturalSearch(it.getListingDetails().getViewItemURLForNaturalSearch());

        }

        if(it.getShippingDetails()!=null) {
            sd.setApplyShippingDiscount(it.getShippingDetails().isApplyShippingDiscount());
            if ((it.getShippingDetails().getSalesTax()!=null)&&(it.getShippingDetails().getSalesTax().getSalesTaxPercent() != null)) {
                sd.setSalesTaxPercent(String.valueOf(it.getShippingDetails().getSalesTax().getSalesTaxPercent().floatValue()));
                sd.setShippingIncludedInTax(it.getShippingDetails().getSalesTax().isShippingIncludedInTax());
            }
            if (it.getShippingDetails().getShippingType() != null) {
                sd.setShippingType(it.getShippingDetails().getShippingType().value());
            }
            sd.setThirdPartyCheckOut(it.getShippingDetails().isThirdPartyCheckout());
            sd.setShippingDiscountProfileId(it.getShippingDetails().getShippingDiscountProfileID());
            sd.setInternationalShippingDiscountProfileId(it.getShippingDetails().getInternationalShippingDiscountProfileID());
            sd.setSellerExcludeShipToLocationsPreference(it.getShippingDetails().isSellerExcludeShipToLocationsPreference());
        }

        if(it.getPictureDetails()!=null) {
            if (it.getPictureDetails().getGalleryType() != null) {
                pd.setGalleryType(it.getPictureDetails().getGalleryType().value());
            }
            if (it.getPictureDetails().getPhotoDisplay() != null) {
                pd.setPhotoDisplay(it.getPictureDetails().getPhotoDisplay().value());
            }
        }
        if(it.getBusinessSellerDetails()!=null) {
            bsd.setStreet1(it.getBusinessSellerDetails().getAddress().getStreet1());
            bsd.setStreet2(it.getBusinessSellerDetails().getAddress().getStreet2());
            bsd.setCityName(it.getBusinessSellerDetails().getAddress().getCityName());
            bsd.setStateOrProvince(it.getBusinessSellerDetails().getAddress().getStateOrProvince());
            bsd.setCountryName(it.getBusinessSellerDetails().getAddress().getCountryName());
            bsd.setPhone(it.getBusinessSellerDetails().getAddress().getPhone());
            bsd.setPostalCode(it.getBusinessSellerDetails().getAddress().getPostalCode());
            bsd.setCompanyName(it.getBusinessSellerDetails().getAddress().getCompanyName());
            bsd.setFirstName(it.getBusinessSellerDetails().getAddress().getFirstName());
            bsd.setLastName(it.getBusinessSellerDetails().getAddress().getLastName());
            bsd.setEmail(it.getBusinessSellerDetails().getEmail());
            bsd.setLegalInvoice(it.getBusinessSellerDetails().isLegalInvoice());
        }
        if(it.getReturnPolicy()!=null) {
            rp.setReturnsWithInOption(it.getReturnPolicy().getReturnsWithinOption());
            rp.setReturnsWithIn(it.getReturnPolicy().getReturnsWithin());
            rp.setReturnsAcceptedOption(it.getReturnPolicy().getReturnsAcceptedOption());
            rp.setReturnsAccepted(it.getReturnPolicy().getReturnsAccepted());
            rp.setShippingCostPaidByOption(it.getReturnPolicy().getShippingCostPaidByOption());
            rp.setShippingCostPaidBy(it.getReturnPolicy().getShippingCostPaidBy());
        }
        if(it.getBuyerRequirementDetails()!=null) {
            brd.setShipToRegistrationCountry(it.getBuyerRequirementDetails().isShipToRegistrationCountry());
            if ((it.getBuyerRequirementDetails().getMaximumItemRequirements()!=null)&&(it.getBuyerRequirementDetails().getMaximumItemRequirements().getMaximumItemCount() != null)){
                brd.setMaximumItemCount(String.valueOf(it.getBuyerRequirementDetails().getMaximumItemRequirements().getMaximumItemCount().doubleValue()));
            }
            if ((it.getBuyerRequirementDetails().getMaximumUnpaidItemStrikesInfo()!=null)&&(it.getBuyerRequirementDetails().getMaximumUnpaidItemStrikesInfo().getCount() != null)) {
                brd.setMaximumUnpaidItemStrikesInfoCount(String.valueOf(it.getBuyerRequirementDetails().getMaximumUnpaidItemStrikesInfo().getCount().doubleValue()));
            }
            if ((it.getBuyerRequirementDetails().getMaximumUnpaidItemStrikesInfo()!=null)&&(it.getBuyerRequirementDetails().getMaximumUnpaidItemStrikesInfo().getPeriod() != null)) {
                brd.setMaximumUnpaidItemStrikesInfoPeriod(String.valueOf(it.getBuyerRequirementDetails().getMaximumUnpaidItemStrikesInfo().getPeriod().value()));
            }
        }

        if(it.getSellerProfiles()!=null) {
            if ((it.getSellerProfiles().getSellerShippingProfile()!=null)&&(it.getSellerProfiles().getSellerShippingProfile().getShippingProfileID() != null)) {
                sp.setShippingProfileId(String.valueOf(it.getSellerProfiles().getSellerShippingProfile().getShippingProfileID().doubleValue()));
                sp.setShippingProfileName(it.getSellerProfiles().getSellerShippingProfile().getShippingProfileName());
            }
            if ((it.getSellerProfiles().getSellerReturnProfile()!=null)&&(it.getSellerProfiles().getSellerReturnProfile().getReturnProfileID() != null)) {
                sp.setReturnProfileId(String.valueOf(it.getSellerProfiles().getSellerReturnProfile().getReturnProfileID().doubleValue()));
                sp.setReturnProfileName(it.getSellerProfiles().getSellerReturnProfile().getReturnProfileName());
            }
            if ((it.getSellerProfiles().getSellerPaymentProfile()!=null)&&(it.getSellerProfiles().getSellerPaymentProfile().getPaymentProfileID() != null)) {
                sp.setPaymentProfileId(String.valueOf(it.getSellerProfiles().getSellerPaymentProfile().getPaymentProfileID().doubleValue()));
                sp.setPaymentProfileName(it.getSellerProfiles().getSellerPaymentProfile().getPaymentProfileName());
            }
        }
        if(it.getShippingPackageDetails()!=null) {
            spd.setShippingIrregular(it.getShippingPackageDetails().isShippingIrregular());
            if (it.getShippingPackageDetails().getShippingPackage() != null) {
                spd.setShippingPackage(it.getShippingPackageDetails().getShippingPackage().value());
            }
            if (it.getShippingPackageDetails().getWeightMajor() != null) {
                if (it.getShippingPackageDetails().getWeightMajor().getValue() != null) {
                    spd.setWeightMajorValue(String.valueOf(it.getShippingPackageDetails().getWeightMajor().getValue().floatValue()));
                }
                spd.setWeightMajorUnit(it.getShippingPackageDetails().getWeightMajor().getUnit());
                if (it.getShippingPackageDetails().getWeightMajor().getMeasurementSystem() != null) {
                    spd.setWeightMajorMeasurementSystem(it.getShippingPackageDetails().getWeightMajor().getMeasurementSystem().value());
                }
            }
            if (it.getShippingPackageDetails().getWeightMinor() != null) {
                if (it.getShippingPackageDetails().getWeightMinor().getValue() != null) {
                    spd.setWeightMinorValue(String.valueOf(it.getShippingPackageDetails().getWeightMinor().getValue().floatValue()));
                }

                spd.setWeightMinorUnit(it.getShippingPackageDetails().getWeightMinor().getUnit());
                if (it.getShippingPackageDetails().getWeightMinor().getMeasurementSystem() != null) {
                    spd.setWeightMinorMeasurementSystem(it.getShippingPackageDetails().getWeightMinor().getMeasurementSystem().value());
                }
            }
        }*/