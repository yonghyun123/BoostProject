package kr.or.connect.project3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.project3.dao", "kr.or.connect.project3.service"})
@Import({DBConfig.class})

public class ApplicationConfig {

}
