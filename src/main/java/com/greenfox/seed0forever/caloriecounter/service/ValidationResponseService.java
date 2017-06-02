package com.greenfox.seed0forever.caloriecounter.service;

import com.greenfox.seed0forever.caloriecounter.model.rest.ErrorRestMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationResponseService {

  public static final String FIELD_ERRORS_MESSAGE_BEGINNING = "validation error with field(s): ";

  private final ErrorRestMessage errorMessage;

  @Autowired
  public ValidationResponseService(
          ErrorRestMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String createFieldErrorsMessage(BindingResult bindingResult) {
    String errorMessage = FIELD_ERRORS_MESSAGE_BEGINNING;

    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    for (FieldError fieldError : fieldErrors) {
      errorMessage += fieldError.getField();
      errorMessage += ", ";
    }
    errorMessage = errorMessage.substring(0, errorMessage.length() - 2);

    return errorMessage;
  }

}
