package com.counter.spring.test.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.counter.spring.configuration.SecurityConfiguration;
import com.counter.spring.configuration.SpringAppConfiguration;
import com.counter.spring.configuration.SpringWebConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfiguration.class,SpringWebConfiguration.class,SecurityConfiguration.class})
public abstract class AbstractSecurityJunitConfiguration {

}
