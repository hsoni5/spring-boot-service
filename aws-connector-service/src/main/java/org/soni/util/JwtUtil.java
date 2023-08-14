package org.soni.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.soni.dto.RoleDto;
import org.soni.dto.UserDto;
import org.soni.exception.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Log4j2
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String secretKey;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User digital id  with first name,last name and roles prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply throw TokenNotFoundException.
     *
     * @param token the JWT token to parse
     * @throw token not found exception.
     */
    public UserDto parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            UserDto userDto = new UserDto();
            userDto.setDigitalId((String) body.get("CommonConstant.DIGITAL_ID"));
            userDto.setUserId((String) body.get("CommonConstant.USER_ID"));
            String firstName = (String) body.get("CommonConstant.GIVEN_NAME");
            String lastName = (String) body.get("CommonConstant.SURNAME");
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            if (ObjectUtils.isNotEmpty(userDto.getFirstName()) && ObjectUtils.isNotEmpty(userDto.getLastName())) {
                userDto.setFullName(firstName.concat("CommonConstant.BLANK").concat(lastName));
            }
            String roles = (String) body.get("CommonConstant.ROLES");
            Set<RoleDto> roleDtoList = new HashSet<>();
            if (ObjectUtils.isNotEmpty(roles)) {
                Stream.of(roles.split(","))
                        .map(String::trim)
                        .collect(Collectors.toSet()).forEach(role -> {
                            RoleDto roleDto = new RoleDto();
                            roleDto.setName(role);
                            roleDtoList.add(roleDto);
                        });
            }
            userDto.setRoles(roleDtoList);
            return userDto;
        } catch (JwtException | ClassCastException exception) {
            log.error("Exception occurred during in token verification: {}", exception);
            throw new JWTVerificationException("Jwt header token verification failed" + exception);
        }
    }
}