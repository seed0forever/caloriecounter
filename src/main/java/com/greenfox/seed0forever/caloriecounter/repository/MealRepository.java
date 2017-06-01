package com.greenfox.seed0forever.caloriecounter.repository;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

}
