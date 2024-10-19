package com.example.ecommerce.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.example.pos"])
@EntityScan(basePackages = ["com.example.pos"])
class JpaConfig