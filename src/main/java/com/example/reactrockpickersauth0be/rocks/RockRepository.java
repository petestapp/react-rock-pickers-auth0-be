package com.example.reactrockpickersauth0be.rocks;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RockRepository extends CrudRepository<Rock, Long> {
}
