package com.ls.eventmanager.security.config;

import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private XUserRepository xUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        XUser userEntity = xUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        String[] roles = userEntity.getRoles().stream()
                .map(Enum::name)
                .toArray(String[]::new);

        return new User(userEntity.getUsername(), userEntity.getPassword(), AuthorityUtils.createAuthorityList(roles));
    }

}
