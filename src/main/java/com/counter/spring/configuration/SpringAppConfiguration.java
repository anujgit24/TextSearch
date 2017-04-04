package com.counter.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ="com.counter.spring.rest.*")
public class SpringAppConfiguration {

}
