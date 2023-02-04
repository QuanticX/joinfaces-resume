package org.joinfaces.resume.common.repository;

import org.joinfaces.resume.common.entity.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbstractRepository<T extends AbstractEntity> extends CrudRepository<T, Long> {

    List<T> findAll();

    List<T> findAllByIdIn(List<Long> ids);
}
