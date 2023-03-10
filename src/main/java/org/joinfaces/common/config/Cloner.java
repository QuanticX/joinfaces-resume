package org.joinfaces.common.config;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

@Component
public class Cloner {


    public <T extends Serializable> T clone(T t){
        return SerializationUtils.clone(t);
    }

}
