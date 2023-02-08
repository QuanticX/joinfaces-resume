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

package org.joinfaces.security.config;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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
    private DataSource dataSource;


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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder, ApplicationUsers applicationUsers) throws Exception {
        var jdbcAuthentication = auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password,enabled "
                + "from users "
                + "where username = ?")
                .authoritiesByUsernameQuery("select username,authority "
                        + "from authorities "
                        + "where username = ?");

        for ( var applicationUser : applicationUsers.getUsersCredentials()) {
            jdbcAuthentication
                    .withUser(User.withUsername(applicationUser.getUsername())
                            .password(passwordEncoder.encode(applicationUser.getPassword()))
                            .roles(applicationUser.getAuthorities()
                                    .toArray(new String[0])));
        }

    }


}
