package org.soni.service;


import org.soni.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This clas is used load user
 * @author VC60363
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsImpl userDetails;

    /**
     * this method is used to load user by username
     * @param userDto
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(UserDto userDto) throws UsernameNotFoundException {
        return userDetails.build(userDto);
    }
}