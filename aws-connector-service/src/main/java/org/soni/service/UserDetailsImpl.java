package org.soni.service;


import org.apache.commons.lang3.ObjectUtils;
import org.soni.dto.RoleDto;
import org.soni.dto.UserDto;
import org.soni.entity.Role;
import org.soni.entity.User;
import org.soni.repository.RoleRepository;
import org.soni.repository.UserRepository;
import org.soni.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * This clas is used to validate user
 *
 * @author VC60363
 */
@Service
public class UserDetailsImpl implements UserDetails, Serializable {
    private static final long serialVersionUID = 5880056631025482610L;
    @Autowired
    private UserDto userDto;

    private UserRepository userRepository;

    private RoleRepository roleRepository;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(UserDto userDto, Collection<? extends GrantedAuthority> authorities, UserRepository userRepository, RoleRepository roleRepository) {
        this.userDto = userDto;
        this.authorities = authorities;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * this method is used to load user by username
     *
     * @param gfUserDto
     * @return
     */
    public UserDetailsImpl build(UserDto gfUserDto) {
        Optional<User> userOptional = userRepository.findByDigitalIdIgnoreCase(gfUserDto.getDigitalId());
        User user = userOptional.isEmpty() ? createNewUser(gfUserDto) : userOptional.get();
        validateRoles(gfUserDto, user);
        userDto = ObjectMapperUtils.map(user, UserDto.class);
        if (ObjectUtils.isNotEmpty(user.getRoles())) {
            authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
        }
        return new UserDetailsImpl(userDto, authorities, userRepository, roleRepository);
    }

    /**
     * this method is used to create new user
     *
     * @param userDto
     * @return
     */
    private User createNewUser(UserDto userDto) {
        User user = new User();
        user.setDigitalId(userDto.getDigitalId());
        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        if (ObjectUtils.isNotEmpty(userDto.getRoles())) {
            userDto.getRoles().forEach(roleDto -> {
                Role role = roleRepository.findByNameIgnoreCase(roleDto.getName());
                if (null != role) {
                    user.getRoles().add(role);
                }
            });
        }
        return userRepository.save(user);
    }

    /**
     * this method is used to validate user
     *
     * @param userDto
     * @param user
     */
    private void validateRoles(UserDto userDto, User user) {
        AtomicBoolean isNewRoleFound = new AtomicBoolean(false);
        List<RoleDto> roleDtoList = userDto.getRoles().stream().filter(roleDto ->
                        user.getRoles().stream().anyMatch(role ->
                                !roleDto.getName().equalsIgnoreCase(role.getRole()))).
                collect(Collectors.toList());
        roleDtoList.forEach(role -> {
            Role newRole = roleRepository.findByNameIgnoreCase(role.getName());
            if (ObjectUtils.isNotEmpty(newRole)) {
                user.getRoles().add(newRole);
                isNewRoleFound.set(true);
            }
        });
        if (isNewRoleFound.get()) {
            userRepository.save(user);
        }
    }

    /**
     * this method is used to find user dto
     *
     * @return
     */
    public UserDto getUserDto() {
        return userDto;
    }

    /**
     * this method is used to find all authorities
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * this method is used to get password
     *
     * @return
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * this method is used to load username
     *
     * @return
     */
    @Override
    public String getUsername() {
        return ObjectUtils.isNotEmpty(userDto) ? userDto.getUserId() : null;
    }

    /**
     * this method is used to get user digital id
     *
     * @return
     */
    public String getDigitalId() {
        return ObjectUtils.isNotEmpty(userDto) ? userDto.getDigitalId() : null;
    }

    /**
     * this method is used to check is user account locked
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * this method is used to check is user account not locked
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * this method is used to check is user CredentialsNonExpired
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * this method is used to check is user enabled
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}