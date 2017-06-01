package com.greenfox.seed0forever.caloriecounter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class MealType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String type;

}
