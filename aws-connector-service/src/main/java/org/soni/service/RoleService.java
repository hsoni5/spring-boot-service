package org.soni.service;


import org.soni.dto.RoleDto;

import java.util.List;

public interface RoleService {

    public List<RoleDto> fetchAllRoles();

    public RoleDto findRoleByNameIgnoreCase(RoleDto roleDto);

    public RoleDto createRole(RoleDto roleDto);

}