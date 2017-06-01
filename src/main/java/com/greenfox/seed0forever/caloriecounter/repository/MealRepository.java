package com.greenfox.seed0forever.caloriecounter.repository;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

  @Override
  List<Meal> findAll();

  @Override
  List<Meal> findAll(Iterable<Long> idList);

  @Override
  void delete(Meal meal);

  @Override
  boolean exists(Long id);

  @Override
  void delete(Long id);

  @Override
  Meal findOne(Long id);

}
