package com.decagonhq.hireday.repositories;

import com.decagonhq.hireday.entities.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Long> {
}
