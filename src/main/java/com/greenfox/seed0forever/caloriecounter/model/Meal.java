package com.greenfox.seed0forever.caloriecounter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private Date date;

  private String type;

  @Size(min = 5, message = "length must be at least 5")
  private String description;

  @NotNull(message = "must not be empty")
  @Range(min = 1, message = "minimum: 1")
  private Integer calories;

}
