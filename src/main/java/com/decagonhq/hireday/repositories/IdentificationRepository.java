package com.decagonhq.hireday.repositories;

import com.decagonhq.hireday.entities.Identification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentificationRepository extends CrudRepository<Identification, Long> {

    boolean existsIdentificationByDecaId(String decaId);

    Optional<Identification> findByDecaId(String decaId);
}
