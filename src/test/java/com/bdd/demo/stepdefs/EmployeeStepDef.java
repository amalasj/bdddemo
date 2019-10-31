package com.bdd.demo.stepdefs;

import com.bdd.demo.IntegrationTest;
import com.bdd.demo.model.Employee;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeStepDef extends IntegrationTest implements En {

    public EmployeeStepDef()
    {
        // Common steps
        Then("the HTTP response status code should be {int}", (Integer statusCode) -> {
            Response response = getTestContext().getResponse();
            Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
        });


        Given("I have employee details in table", () -> {
            String getUrl = baseUrl() + "/employees/";
            executeGet(getUrl);
            Response response = getTestContext().getResponse();
            List<Employee> employees = Arrays.asList(response.as(Employee[].class));
            employees.forEach(employee -> System.out.println(employee.getFirstName()));
            Assert.assertEquals(3, employees.size());
        });

        //*******************************************************************************

        When("I make a call to get employee with id {int}",
                (Integer id) -> {
                    String getUrl = baseUrl() + "/employees/"+id;
                    executeGet(getUrl);
                });

        And("employee first name should be {string} and last name should be {string}",
           (String firstName, String lastName) -> {
              Response response = getTestContext().getResponse();
              Employee employee = response.as(Employee.class);
              Assert.assertEquals(firstName, employee.getFirstName());
              Assert.assertEquals(lastName, employee.getLastName());
        });
        //*******************************************************************************

        When("I make a call to get all employees",
                () -> {
                    String getUrl = baseUrl() + "/employees/";
                    executeGet(getUrl);
                });

        And("employee ids should include the following:",
                (io.cucumber.datatable.DataTable dataTable) -> {
                    List<String> ids = dataTable.asList();
                    Response response = getTestContext().getResponse();
                    List<Employee> employees = Arrays.asList(response.as(Employee[].class));
                    for(String id : ids)
                    {
                        assertThat(employees.stream()
                                .filter(e -> e.getId().equals(Integer.valueOf(id))).findFirst()).isPresent();
                    }
                });

        //******************************************************************************************************

        And("employee details should include the following:",
                (io.cucumber.datatable.DataTable dataTable) -> {
                    List<Employee> employees = dataTable.asList(Employee.class);
                    Response response = getTestContext().getResponse();
                    List<Employee> employeesResponse = Arrays.asList(response.as(Employee[].class));
                    for(Employee employee : employees)
                    {
                        assertThat(employeesResponse.stream()
                                .filter(e -> e.getId().equals(employee.getId())).findFirst()).isPresent();
                    }
                });

        //************************************************************************************************************

        When("I make a call to add employee with below details:",
                (io.cucumber.datatable.DataTable dataTable) -> {
                    List<Employee> employees = dataTable.asList(Employee.class);
                    String postUrl = baseUrl() + "/employees/";
                    getTestContext().setPayload(employees.get(0));
                    executePost(postUrl);
                });
    }

}
