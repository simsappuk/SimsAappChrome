package com.ebay.load.seller.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com/ebay/load/seller/converter","com/ebay/load/seller/service","com/ebay/load/seller/converter","ma/glasnost/orika","com/ebay/load/seller/controller","com/ebay/load/seller/rest"})
@EntityScan("com/ebay/load/seller/model")
@EnableJpaRepositories("com/ebay/load/seller/repository")

public class AppConfig {

}
