package com.greenfox.seed0forever.caloriecounter.repository;

import com.greenfox.seed0forever.caloriecounter.model.MealType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTypeRepository extends CrudRepository<MealType, Long> {

}
