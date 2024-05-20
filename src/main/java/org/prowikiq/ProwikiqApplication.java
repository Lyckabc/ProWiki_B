package org.prowikiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Class: prowikiqApplication Project: prowikiQ Package: org.prowikiq
 * <p>
 * Description: prowikiqApplication
 *
 * @author dong-hoshin
 * @date 4/19/24 14:52 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

@SpringBootApplication
@EnableJpaAuditing
public class ProwikiqApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProwikiqApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProwikiqApplication.class);
    }
}