package com.ebay.load.seller.converter;

/*import com.ebay.load.seller.model.Stock;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

@Component
*/
public class EbayItemConverter {
    /*
    * private Double buyItNowPrice;
    private String currency;
    private String itemId;
    private String listingDuration;
    private String listingType;
    private Long onlineQuantity;
    private String title;
    private String sku;
    private Integer quantityAvailable;
    private String imageUrl;
private String shippingProfileName;
private String returnProfileName;
private String paymentProfileName;
    * */
   /* public Stock converTo(ItemType it){
        Stock st = new Stock();*/
       /* st.setBuyItNowPrice(it.getBuyItNowPrice().getValue());
        st.setCurrency(it.getBuyItNowPrice().getCurrencyID().value());
        st.setItemId(it.getItemID());
        st.setListingType(it.getListingType().value());
        st.setListingDuration(it.getListingDuration());
        st.setListingType(it.getListingType().value());
        st.setOnlineQuantity(it.getQuantity().longValue());
        st.setSku(it.getSKU());
        st.setTitle(it.getTitle());
        st.setQuantityAvailable(it.getQuantityAvailable());
        try {
            ElementNSImpl s = ((ElementNSImpl) it.getPictureDetails().getAny()[0]);
            Node tx = s.getFirstChild();
            st.setImageUrl(((TextImpl) tx).getWholeText());
        }catch(Exception e){st.setDescription("Unable to Send Image from API");}
        return st;
    }*/
}
