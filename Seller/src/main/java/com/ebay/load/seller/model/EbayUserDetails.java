package com.ebay.load.seller.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;



    public class EbayUserDetails extends User {

        private final Users user;
      //  private  Tenant tenant;

        public EbayUserDetails(Users user, String username, String password, Collection<? extends GrantedAuthority> authorities)
        {
            super(username, password, authorities);
            this.user = user;

        }

        public EbayUserDetails(Users user, String username, String password, Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.user = user;
        }

        public Users getUser() {
            return user;
        }

    }

