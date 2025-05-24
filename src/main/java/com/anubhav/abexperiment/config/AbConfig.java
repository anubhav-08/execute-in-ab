package com.anubhav.abexperiment.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ConditionalOnProperty(prefix = "execute.in.ab", name = "enable", havingValue = "true")
@ComponentScan(basePackages = {"com.anubhav.abexperiment"})
public class AbConfig {
}
