package com.greenfox.seed0forever.caloriecounter.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.greenfox.seed0forever.caloriecounter.CaloriecounterApplication;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaloriecounterApplication.class)
@WebAppConfiguration
public class RestApiControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  private MockMvc mockMvc;
  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Mock
  private MealService mealService;

  @InjectMocks
  private RestApiController restApiController;

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

    assertNotNull("the JSON message converter must not be null",
            this.mappingJackson2HttpMessageConverter);
  }

  @Before
  public void setUp() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void respondWithAllMeals() throws Exception {
    mockMvc.perform(get("/getmeals"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$", hasSize(36)));
  }

  @Test
  public void respondWithMealStats() throws Exception {
    mockMvc.perform(get("/getstats"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.['number of meals']", is(36)))
            .andExpect(jsonPath("$.['total calories']", is(465766)));
  }

  @Test
  public void receiveNewMeal() throws Exception {
  }

  @Test
  public void updateExistingMeal() throws Exception {
  }

}