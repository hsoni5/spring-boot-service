package org.soni.service;



import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * This clas is used get current login user
 * @author VC60363
 */

public class AuditAwareImpl implements AuditorAware<String>{
    /**
     * This method is used get current login user
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor(){
        Optional<String> userId = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UserDetailsImpl.class::cast)
                .map(UserDetailsImpl::getUsername);
        return userId.isPresent() ? userId : Optional.of("SYSTEM");
    }
}
