package org.joinfaces.cv.resume.service;

import org.joinfaces.common.config.ModelMapperConverter;
import org.joinfaces.common.repository.AbstractRepository;
import org.joinfaces.common.service.CoreService;
import org.joinfaces.cv.resume.dto.ResumeDto;
import org.joinfaces.cv.resume.entity.ResumeEntity;
import org.joinfaces.cv.resume.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResumeService extends CoreService<ResumeEntity, ResumeDto> implements Serializable {

    @Autowired
    private ResumeRepository abstractRepository;

    @Autowired
    private ModelMapperConverter modelMapperConverter;

    @Override
    public ResumeDto add(ResumeDto x) {
        return modelMapperConverter.convert(abstractRepository.save(modelMapperConverter.convert(x)));
    }

    @Override
    public List<ResumeDto> readAll() {
        List<ResumeEntity> tList = abstractRepository.findAll();
        List<ResumeDto> xList = new ArrayList<>(tList.size());
        for (ResumeEntity t : tList) {
            xList.add(modelMapperConverter.convert(t));
        }
        return xList;
    }

    @Override
    public ResumeDto read(Long id) {
        return modelMapperConverter.convert(abstractRepository.findById(id));
    }

    @Override
    public List<ResumeDto> readAllIn(List<Long> ids) {
        List<ResumeEntity> tList = abstractRepository.findAllByIdIn(ids);
        List<ResumeDto> xList = new ArrayList<>(tList.size());
        for (ResumeEntity t : tList) {
            xList.add(modelMapperConverter.convert(t));
        }
        return xList;
    }

    @Override
    public ResumeDto update(ResumeDto x) {
        return add(x);
    }

    @Override
    public void delete(Long id) {
        abstractRepository.deleteById(id);
    }

    @Override
    public void delete(ResumeDto x) {
        abstractRepository.delete(modelMapperConverter.convert(x));
    }
}
