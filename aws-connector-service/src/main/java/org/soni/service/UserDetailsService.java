package org.soni.service;


import org.soni.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    public UserDetails loadUserByUsername(UserDto gfUserDto) throws UsernameNotFoundException;
}
