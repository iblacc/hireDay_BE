package com.decagonhq.hireday.repositories;

import com.decagonhq.hireday.entities.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Long> {

    Optional<Employer> findByEmail(String email);
}
