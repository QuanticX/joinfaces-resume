/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.resume.security.config;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.joinfaces.resume.security.config.ApplicationUsers;
import org.joinfaces.resume.security.dto.UserCredentialsDto;
import org.joinfaces.resume.security.module.JpaUserDetailsManager;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;

/**
 * Spring Security Configuration.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationUsers.class)
public class SecurityConfig {

    @Autowired
    private JpaUserDetailsManager jpaUserDetailsManager;

    /**
     * Configure security.
     **/
    @SuppressFBWarnings("SPRING_CSRF_PROTECTION_DISABLED")
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) {
        try {
            http.csrf().disable();
            http
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/**.jsf").permitAll()
                            .requestMatchers("/jakarta.faces.resource/**").permitAll()
                            .anyRequest().authenticated())
                    .formLogin()
                    .loginPage("/login.jsf")
                    .permitAll()
                    .failureUrl("/login.jsf?error=true")
                    .defaultSuccessUrl("/starter.jsf")
                    .and()
                    .logout()
                    .logoutSuccessUrl("/login.jsf")
                    .deleteCookies("JSESSIONID");
            return http.build();
        } catch (Exception ex) {
            throw new BeanCreationException("Wrong spring security configuration", ex);
        }
    }

    /**
     * UserDetailsService that configures an in-memory users store.
     *
     * @param applicationUsers - autowired users from the application.yml file
     * @return InMemoryUserDetailsManager - a manager that keeps all the users' info in the memory
     */
    @Bean
    public JpaUserDetailsManager userDetailsService(ApplicationUsers applicationUsers) {
        for (UserCredentialsDto userCredentialsDto : applicationUsers.getUsersCredentials()) {
            jpaUserDetailsManager.createUser(User.builder()
                    .username(userCredentialsDto.getUsername())
                    .password(userCredentialsDto.getPassword())
                    .authorities(userCredentialsDto.getAuthorities().stream()
                            .map(authority -> authority.getAuthority())
                            .collect(Collectors.toList())
                            .toArray(new String[0])).build());
        }
        return jpaUserDetailsManager;
    }
}
