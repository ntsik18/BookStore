package com.nini.bookstore.catalog;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "catalog")
public record ApplicationProperties(@DefaultValue("10") @Min(1) int pageSize) {}

/*
When to Use @ConfigurationProperties:
@ConfigurationProperties is designed for binding a group of related properties into a structured object (like a record or class). In your case, you're binding the catalog prefix to the ApplicationProperties record, which contains the pageSize property.

Advantages of @ConfigurationProperties:

Grouped Configuration: When you have several related properties (e.g., catalog.pageSize, catalog.maxItems, catalog.someOtherProperty), @ConfigurationProperties allows you to group them logically into a single class or record. This makes the configuration more structured and easier to manage.
Type Safety: Spring automatically binds the properties and ensures that the data types match (e.g., int, String, etc.).
Automatic Validation: With @ConfigurationProperties, you can easily apply validation annotations like @Min, @Max, @NotNull, etc. Spring will validate the values at application startup, and if they don’t meet the validation constraints, the application will fail to start.
Default Values: You can set default values directly in the class (though not with the @DefaultValue annotation—typically you use the class constructor for defaults).
Easy Testing and Maintenance: Having all configuration properties in one class or record makes testing and maintenance easier. You can mock the properties or inject different configurations without touching the service code.
More Scalable: If you anticipate adding more properties under the catalog prefix in the future, @ConfigurationProperties scales better than @Value.
 */
