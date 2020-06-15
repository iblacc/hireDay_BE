package com.decagonhq.hireday.repositories;

import com.decagonhq.hireday.entities.UserIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserIdentityRepository extends CrudRepository<UserIdentity, Long> {

    boolean existsByEmail(String email);
    Optional<UserIdentity> findByEmail(String email);
}
