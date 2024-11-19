package com.nini.bookstore.orderservice.Web;

import com.nini.bookstore.orderservice.AbstractIt;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class OrderControllerTest extends AbstractIt {

    @Nested
    class CreateOrderTests {
        @Test
        void shouldCreateOrderSuccessfully () {
            var payLoad =
                    """
                       {
                        "customer" : {
                            "name" : "nini",
                            "email" : "nini@gmail.com",
                            "phone":"123456789"
                        },
                        "deliveryAddress" : {
                                              "line1": "HNO 123",
                                               "line2": "Kukatpally",
                                               "city": "Hyderabad",
                                               "state": "Telangana",
                                               "zip": "500072",
                                               "country": "India"
                                           },
                        "items":[
                            {
                                "code": "P100",
                                "name": "Product 1",
                                "price": 34.0,
                                "quantity": 1
                            }
                         ]
                        }
                    """;
            RestAssured.given().contentType(ContentType.JSON)
                    .body(payLoad)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", CoreMatchers.notNullValue());






        }
    }
}
