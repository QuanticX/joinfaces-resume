package org.joinfaces.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConverter {

    @Autowired
    private ModelMapper modelMapper;

    public <T,S> T  convert(S s) {
        return modelMapper.map(s, (Class<T>) Object.class);
    }

    public <T,S> T  convert(S s,T t) {
        return modelMapper.map(s, (Class<T>) t.getClass());
    }
}
