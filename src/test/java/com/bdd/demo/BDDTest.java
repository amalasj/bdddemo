package com.bdd.demo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
    glue = {"com.bdd.demo.configuration", "com.bdd.demo.stepdefs"})
public class BDDTest{


}
