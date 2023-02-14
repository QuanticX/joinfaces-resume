package org.joinfaces.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.security.config.SecurityLogin;
import org.joinfaces.security.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@SessionScoped
public class UserView implements Serializable {
    @Getter
    @Setter
    private UserCredentialsDto userCredentialsDto;

    @Autowired
    private SecurityLogin securityLogin;

    public void addUser() throws Exception {
        securityLogin.update(userCredentialsDto);
    }

    @PostConstruct
    public void init(){
        userCredentialsDto = new UserCredentialsDto();
    }

}
