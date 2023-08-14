package org.soni.dto;


import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class UserDto extends AuditDto implements Serializable {
    private static final long serialVersionUID = 7874493593515141603L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private String fullName;
    private String digitalId;
    private Set<RoleDto> roles = new HashSet<>();
}