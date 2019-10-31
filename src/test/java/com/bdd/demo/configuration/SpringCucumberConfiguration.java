package com.bdd.demo.configuration;

import com.bdd.demo.SpringBootDemoApplication;
import com.bdd.demo.util.TestContext;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.TimeZone;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {SpringBootDemoApplication.class},
    loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "classpath:application-bdd.properties")
public class SpringCucumberConfiguration {

  private static final Logger LOG = LoggerFactory.getLogger(SpringCucumberConfiguration.class);

  static {
    LOG.info("setting h2 db to America/New_York timezone in order to pass the date validations");
    TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
  }

  @Before
  public void init() {
    LOG.info("------------- TEST CONTEXT INITIALIZED -------------");
    TestContext.CONTEXT.reset();
  }
}
