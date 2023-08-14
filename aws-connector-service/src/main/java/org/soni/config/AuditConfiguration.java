package org.soni.config;


import org.soni.service.AuditAwareImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "customAuditProvider")
public class AuditConfiguration {
    @Value("${application.time.zone}")
    public String timeZone;
    @Bean
    public AuditorAware<String> customAuditProvider() {
        return new AuditAwareImpl();
    }

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }
}