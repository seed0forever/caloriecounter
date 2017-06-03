package com.greenfox.seed0forever.caloriecounter.model.rest.dto;

import com.greenfox.seed0forever.caloriecounter.model.rest.RestMessage;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Data
public class MealAddDto implements RestMessage {

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
