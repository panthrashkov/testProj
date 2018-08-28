package ru.rencredit.test;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;


@Profile("test")
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "ru.rencredit.test",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class)})
public class TestConfig {
}
