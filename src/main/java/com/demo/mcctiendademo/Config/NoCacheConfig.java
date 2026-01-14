package com.demo.mcctiendademo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class NoCacheConfig {
}
