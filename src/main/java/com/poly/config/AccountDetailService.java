package com.poly.config;


import java.util.Optional;

import com.poly.service.SessionService;
import com.poly.utils.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.poly.entity.Account;
import com.poly.impl.AccountServiceImpl;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailService implements UserDetailsService {


    private final  AccountServiceImpl dao;

    private final SessionService sessionService;
    BCryptPasswordEncoder p = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> acc = dao.findById(username);
        acc.ifPresent(account -> sessionService.setAttribute(Keyword.accountSession, account));
        return acc.map(AccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }

    public void loginFormOAuth2(@AuthenticationPrincipal OAuth2User oauth2 ) {
        String email = oauth2.getAttribute("name");
        String password = Long.toHexString(System.currentTimeMillis());
        UserDetails user = User.withUsername(email).password(p.encode(password)).roles("GUEST").build();
        sessionService.setAttribute(Keyword.accountSession,user);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
}
