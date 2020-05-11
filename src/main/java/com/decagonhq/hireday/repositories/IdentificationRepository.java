package com.decagonhq.hireday.repositories;

import com.decagonhq.hireday.entities.Identification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends CrudRepository<Identification, Long> {

    boolean existsIdentificationByDecaId(String decaId);
}
