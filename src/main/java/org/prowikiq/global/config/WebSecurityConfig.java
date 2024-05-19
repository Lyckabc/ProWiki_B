package org.prowikiq.global.config;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Class: WebSecurityConfig Project: prowikiQ Package: org.prowikiq.global.config
 * <p>
 * Description: WebSecurityConfig
 *
 * @author dong-hoshin
 * @date 4/27/24 17:51 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 *
 */

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger logger = LogManager.getLogger(WebSecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()  // Disable basic authentication
            .csrf().disable()  // Disable CSRF protection
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // No session will be created
            .and()
            .authorizeRequests()  // Authorize requests
            .antMatchers("/account/user/**").permitAll() // todo seperate Authority by user,admin,root
            .antMatchers("/h2-console/**").permitAll() // Allow all access to H2 console
            .anyRequest().permitAll()  // Allow all other requests
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class); // Disable frame options for H2 console
    }
}