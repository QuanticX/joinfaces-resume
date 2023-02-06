package org.joinfaces.resume.cv.event.repository;

import org.joinfaces.resume.common.repository.AbstractRepository;
import org.joinfaces.resume.cv.event.entity.EventEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends AbstractRepository<EventEntity> {
}
