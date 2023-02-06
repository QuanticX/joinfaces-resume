package org.joinfaces.resume.common.service;

import org.joinfaces.resume.common.config.ModelMapperConverter;
import org.joinfaces.resume.common.dto.AbstractDto;
import org.joinfaces.resume.common.entity.AbstractEntity;
import org.joinfaces.resume.common.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CoreService<T extends AbstractEntity, X extends AbstractDto> extends AbstractService<T, X> {

    @Autowired
    private AbstractRepository<T> abstractRepository;

    @Autowired
    private ModelMapperConverter modelMapperConverter;

    @Override
    public X add(X x) {
        return modelMapperConverter.convert(abstractRepository.save(modelMapperConverter.convert(x)));
    }

    @Override
    public List<X> readAll() {
        List<T> tList = abstractRepository.findAll();
        List<X> xList = new ArrayList<>(tList.size());
        for (T t : tList) {
            xList.add(modelMapperConverter.convert(t));
        }
        return xList;
    }

    @Override
    public X read(Long id) {
        return modelMapperConverter.convert(abstractRepository.findById(id));
    }

    @Override
    public List<X> readAllIn(List<Long> ids) {
        List<T> tList = abstractRepository.findAllByIdIn(ids);
        List<X> xList = new ArrayList<>(tList.size());
        for (T t : tList) {
            xList.add(modelMapperConverter.convert(t));
        }
        return xList;
    }

    @Override
    public X update(X x) {
        return add(x);
    }

    @Override
    public void delete(Long id) {
        abstractRepository.deleteById(id);
    }

    @Override
    public void delete(X x) {
        abstractRepository.delete(modelMapperConverter.convert(x));
    }
}
