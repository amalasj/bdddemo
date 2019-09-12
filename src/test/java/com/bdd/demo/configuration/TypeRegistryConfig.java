package com.bdd.demo.configuration;

import com.bdd.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import java.util.Locale;
import static java.util.Locale.ENGLISH;

@Component
@Configurable
public class TypeRegistryConfig implements TypeRegistryConfigurer {

  private static ObjectMapper mapper;

  @Autowired
  public void init(ObjectMapper objectMapper) {
    TypeRegistryConfig.mapper = objectMapper;
  }

  @Override
  public Locale locale() {
    return ENGLISH;
  }

  @Override
  public void configureTypeRegistry(TypeRegistry typeRegistry) {
    typeRegistry.defineDataTableType(DataTableType.entry(Employee.class));
  }

}
