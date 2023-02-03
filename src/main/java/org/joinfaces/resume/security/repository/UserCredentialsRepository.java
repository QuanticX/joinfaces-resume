package org.joinfaces.resume.security.repository;

import org.joinfaces.resume.common.repository.AbstractRepository;
import org.joinfaces.resume.security.entity.UserCredentialsEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserCredentialsRepository extends AbstractRepository<UserCredentialsEntity> {

    Optional<UserCredentialsEntity> findByUsername(String username);
}
