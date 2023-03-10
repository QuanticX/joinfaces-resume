package org.joinfaces.common.service;

import org.joinfaces.common.entity.AbstractEntity;
import org.joinfaces.common.dto.AbstractDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractService<T extends AbstractEntity, X extends AbstractDto> {


    public abstract X add(X x);

    public abstract List<X> readAll();

    public abstract X read(Long id);

    public abstract List<X> readAllIn(List<Long> id);

    public abstract X update(X x);

    public abstract void delete(Long id);

    public abstract void delete(X x);



}
