package org.joinfaces.security.config;

import jakarta.faces.context.FacesContext;
import jakarta.transaction.Transactional;
import org.joinfaces.security.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

@Repository
public class SecurityLogin {

    @Autowired
    private AuthenticationManagerBuilder auth;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SecurityLogin(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void update(UserCredentialsDto userCredentialsDto) throws IOException {
        String sql = "INSERT INTO users (username, password, enabled) values (?,?,?);";
        jdbcTemplate.update(sql, userCredentialsDto.getUsername(), passwordEncoder.encode(userCredentialsDto.getPassword()), true);
        String sql2 = "INSERT INTO authorities (username, authority) values (?, ?);";
        if (userCredentialsDto.getAuthorities() != null) userCredentialsDto.getAuthorities().forEach(s ->
                jdbcTemplate.update(sql2, userCredentialsDto.getUsername(), "ROLE_" + s)
        );
        else
            jdbcTemplate.update(sql2, userCredentialsDto.getUsername(), "ROLE_" + "USER");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf");
    }
}
