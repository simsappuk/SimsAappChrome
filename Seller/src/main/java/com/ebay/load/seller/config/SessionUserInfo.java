package com.ebay.load.seller.config;

import com.ebay.load.seller.model.EbayUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUserInfo {
    public static EbayUserDetails getLoggedInUser() {
        EbayUserDetails userDetails = (EbayUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof EbayUserDetails) {
            return (EbayUserDetails) userDetails;
        }
        return null;
    }
}
