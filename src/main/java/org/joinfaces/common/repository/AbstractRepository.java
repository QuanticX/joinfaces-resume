package org.joinfaces.common.repository;

import org.joinfaces.common.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {

    List<T> findAll();

    List<T> findAllByIdIn(List<Long> ids);
}
