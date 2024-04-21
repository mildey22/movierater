package com.harjoitustyo.movierater.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movierater.model.MovieUser;
import com.harjoitustyo.movierater.model.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository uRepository;

    public UserDetailService(UserRepository userRepo) {
        this.uRepository = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MovieUser currUser = uRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(currUser.getRole()));
        return user;
    }
}
