package org.soni.service;


import org.soni.dto.RoleDto;
import org.soni.dto.UserDto;
import org.soni.entity.Role;
import org.soni.entity.User;
import org.soni.exception.UserNotExistException;
import org.soni.repository.RoleRepository;
import org.soni.repository.UserRepository;
import org.soni.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * This clas is used user management
 * @author VC60363
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     *  This method is used fetch all users
     * @return
     */
    @Override
    public List<UserDto> fetchAllUsers() {
        return ObjectMapperUtils.mapAll(userRepository.findAll(), UserDto.class);
    }

    /**
     * This method is used find user by userid
     * @param userId
     * @return
     */
    @Override
    public UserDto findByUserIdIgnoreCase(String userId) {
        Optional<User> userOption = userRepository.findByUserIdIgnoreCase(userId);
        if (userOption.isPresent()) {
            return ObjectMapperUtils.map(userOption.get(), UserDto.class);
        } else {
            throw new UserNotExistException(CommonConstant.USER_NOT_EXIST);
        }
    }

    /**
     * This method is used find user by digital id
     * @param digitalId
     * @return
     */
    @Override
    public UserDto findByDigitalIdIgnoreCase(String digitalId) {
        Optional<User> userOptional = userRepository.findByDigitalIdIgnoreCase(digitalId);
        if (userOptional.isPresent()) {
            return ObjectMapperUtils.map(userOptional.get(), UserDto.class);
        } else {
            throw new UserNotExistException(CommonConstant.USER_NOT_EXIST);
        }
    }

    /**
     * This method is used find user by email
     * @param email
     * @return
     */
    @Override
    public UserDto findByUserEmailIgnoreCase(String email) {
        Optional<User> userOption = userRepository.findByEmailIgnoreCase(email);
        if (userOption.isEmpty()) {
            throw new UserNotExistException(CommonConstant.USER_NOT_EXIST);
        }
        return ObjectMapperUtils.map(userRepository.findByUserIdIgnoreCase(email), UserDto.class);

    }

    /**
     * This method is used find user by username
     * @param username
     * @return
     */
    @Override
    public UserDto findByUsernameIgnoreCase(String username) {
        Optional<User> userOption = userRepository.findByUsernameIgnoreCase(username);
        if (userOption.isEmpty()) {
            throw new UserNotExistException(CommonConstant.USER_NOT_EXIST);
        }
        return ObjectMapperUtils.map(userRepository.findByUserIdIgnoreCase(username), UserDto.class);

    }

    /**
     * This method is used find user from spring context
     * @return
     */
    @Override
    public UserDto getUserDtoFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object object = authentication.getPrincipal();
        if (object instanceof String && ((String) object).equalsIgnoreCase("anonymousUser")) {
            return null;
        }
        return ((UserDetailsImpl) object).getUserDto();
    }

    /**
     * This method is used to new create
     * @param userDto
     * @return
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = ObjectMapperUtils.map(userDto, User.class);
        return ObjectMapperUtils.map(userRepository.save(user), UserDto.class);
    }

    /**
     * This method is used to update user
     * @param digitalId
     * @return
     */
    @Override
    public UserDto updateUserByDigitalId(String digitalId, UserDto userDto) {
        Optional<User> userOption = userRepository.findByDigitalIdIgnoreCase(digitalId);
        User user = null;
        if (userOption.isPresent()) {
            user = userOption.get();
            user.setUserId(userDto.getUserId().toUpperCase());
            userRepository.save(user);
            return ObjectMapperUtils.map(user, UserDto.class);
        } else {
            throw new UserNotExistException(CommonConstant.USER_NOT_EXIST);
        }
    }

    /**
     * This method is used to find all active user
     * @param digitalId
     * @return
     */
    @Override
    public List<RoleDto> findAllEnabledRoleForSelectedUser(String digitalId) {
        List<Role> results = new ArrayList<>();
        Optional<User> user = userRepository.findByDigitalIdIgnoreCase(digitalId);
        List<Role> allRoles = roleRepository.findAll();
        Set<Role> allRoleSets = new HashSet<>(allRoles);
        Set<Role> selectedRoleSets = new HashSet<>();
        if (user.isPresent()) {
            selectedRoleSets = user.get().getRoles();
        }
        for (Role role : allRoleSets) {
            boolean matched = selectedRoleSets.add(role);
            if (!matched) {
                role.setEnabled(true);
                results.add(role);
            } else {
                role.setEnabled(false);
                results.add(role);
            }
        }
        return ObjectMapperUtils.mapAll(results, RoleDto.class);
    }

}