package com.decagonhq.hireday.repositories;

import com.decagonhq.hireday.entities.Decadev;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecadevRepository extends CrudRepository<Decadev, Long> {

    Optional<Decadev> findByDecaId(String decaId);

    Iterable<Decadev> findAllByStack(String stack);

    void deleteByDecaId(String decaId);
}
