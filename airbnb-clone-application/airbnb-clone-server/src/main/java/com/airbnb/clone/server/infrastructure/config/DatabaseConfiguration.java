package com.airbnb.clone.server.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Created 5/17/2024 - 4:03 PM on (Friday)
 * @Package com.airbnb.clone.server.infrastructure.config
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Configuration
@EnableJpaRepositories({"com.airbnb.clone.server.user.repository",
        "com.airbnb.clone.server.listing.repository",
        "com.airbnb.clone.server.booking.repository"})
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration {
}