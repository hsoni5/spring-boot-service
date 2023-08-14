package org.soni.service;


import org.apache.commons.lang3.ObjectUtils;
import org.soni.dto.RoleDto;
import org.soni.entity.Role;
import org.soni.exception.RoleAlreadyExistException;
import org.soni.repository.RoleRepository;
import org.soni.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This clas is used role management
 *
 * @author VC60363
 */

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * This method is used to find all roles
     *
     * @return
     */
    @Override
    public List<RoleDto> fetchAllRoles() {
        return ObjectMapperUtils.mapAll(roleRepository.findAll(), RoleDto.class);
    }

    /**
     * This method is used to find role by role name
     *
     * @param roleDto
     * @return
     */
    @Override
    public RoleDto findRoleByNameIgnoreCase(RoleDto roleDto) {
        return ObjectMapperUtils.map(roleRepository.findByNameIgnoreCase(roleDto.getName()), RoleDto.class);
    }

    /**
     * This method is used to create role
     *
     * @param roleDto
     * @return
     */
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = (Role) roleRepository.findByNameIgnoreCase(roleDto.getName());
        if (ObjectUtils.isNotEmpty(role))
            throw new RoleAlreadyExistException("Role already exist");
        return ObjectMapperUtils.map(roleRepository.save(ObjectMapperUtils.map(roleDto, Role.class)), RoleDto.class);
    }

}
