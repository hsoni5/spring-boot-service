package org.soni.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends AuditDto implements Serializable {
    private static final long serialVersionUID = 7874493593505341603L;
    private Integer id;
    private String name;
    private String role;
    private String description;
    private boolean enabled;
}