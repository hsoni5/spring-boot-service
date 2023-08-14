package org.soni.service;


import org.soni.dto.RoleDto;
import org.soni.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserDto> fetchAllUsers();

    public UserDto findByUserIdIgnoreCase(String userId);

    public UserDto findByDigitalIdIgnoreCase(String digitalId);

    public UserDto findByUserEmailIgnoreCase(String email);

    public UserDto findByUsernameIgnoreCase(String username);

    public UserDto getUserDtoFromSecurityContext();

    public UserDto createUser(UserDto userDto);

    public UserDto updateUserByDigitalId(String digitalId, UserDto userDto);

    public List<RoleDto> findAllEnabledRoleForSelectedUser(String digitalId);

}
