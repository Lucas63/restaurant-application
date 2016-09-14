package me.billsays.restaurant.client.service;

import me.billsays.restaurant.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * DAOUserDetailsService.java 9/1/16, 2016
 * <p/>
 * DIRECTV PROPRIETARY
 * Copyright� 2016 DIRECTV, INC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 * <p/>
 * This software is the confidential and proprietary information of
 * DIRECTV, Inc. ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with
 * DIRECTV providing access to this software.
 *
 * @author mkvitko
 */
@Service
public class DAOUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!StringUtils.isEmpty(email)) {
            return userService.findUserByEmail(email).map(user -> new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getRoleName().toUpperCase()))
                            .collect(Collectors.toSet());
                }
                @Override
                public String getPassword() {
                    return user.getPassword();
                }
                @Override
                public String getUsername() {
                    return user.getName();
                }
                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }
                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }
                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }
                @Override
                public boolean isEnabled() {
                    return true;
                }
            } ).orElseThrow(() -> new UsernameNotFoundException("There is no such user!"));
        }
        return null;
    }
}
