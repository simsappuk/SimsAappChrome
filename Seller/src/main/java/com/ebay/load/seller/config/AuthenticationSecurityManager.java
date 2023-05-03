package com.ebay.load.seller.config;

import com.ebay.load.seller.model.EbayUserDetails;
//import com.ebay.load.seller.model.Roles;
import com.ebay.load.seller.model.Users;
import com.ebay.load.seller.repository.UsersRepository;
import com.ebay.load.seller.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
@Component
@Qualifier(value = "authenticationSecurityManager")
public class AuthenticationSecurityManager implements AuthenticationProvider
{
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        private final String SAAS_TENANT = "SAAS";

        @Autowired
        private UserService userService;

        @Autowired
        private UsersRepository usersReository;


        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            boolean authenticated = false;
            String pass = null;
            Users u = null;
            String emailname=null;

            // email validate workflow
            logger.info("trying to load user [{}] for authentication from database", username);

            String tokens[] = StringUtils.split(username, "\\\\");

            String tenant = null;
            logger.info("split username [{}]", new Object[] { tokens });

            String _username = null;

            u =  userService.checkExistingEmail(username);
            // - If found, check source column
            // - If source column is empty - validate using DB pass.
            if (u != null ) {
                validatePass(username, password, u.getPassword());
                checkActive(u);
                // audit
                // audit(username, Boolean.TRUE);
                return authenticatedUser(username, u);
            }


            throw new UsernameNotFoundException(username);

        }


        private Authentication authenticatedUser(String username, Users u) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("USER"));
           /* for (Roles auth : u.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(auth.getRole()));
            }
*/
            UserDetails details = new EbayUserDetails(u, username, "", true, true, true, true, authorities);

            return new UsernamePasswordAuthenticationToken(details, null, authorities);
        }

        private void validatePass(String username, String password, String encryptedPass) {
            // if (StringUtils.isEmpty(u.getSource())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (!encoder.matches(password, encryptedPass)) {
                // audit
                //  audit(username, Boolean.FALSE);
                throw new BadCredentialsException("1000");
            }
        }
        private void checkActive(Users u) {
            // if (StringUtils.isEmpty(u.getSource())) {
            //  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (u.isInactive()) {
                throw new InsufficientAuthenticationException("User is not active, Contact admin.");
            }
        }
        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }

    }



