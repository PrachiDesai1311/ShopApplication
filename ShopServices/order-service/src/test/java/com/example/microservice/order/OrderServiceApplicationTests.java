package com.example.microservice.order;

import com.example.microservice.order.model.Order;
import com.example.microservice.order.repository.OrderRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import com.example.microservice.order.stubs.InventoryClientStub;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
			.withDatabaseName("order_service")
			.withUsername("root")
			.withPassword("mysql");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	public void placeOrder() {
		String requestBody = """
				{
					"stuCode": "samsung",
				    "price" : 29000,
				    "quantity": 1
				}
				""";
		InventoryClientStub.stubInventoryClient("samsung", 1);
		var response = RestAssured.given().contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201).extract().body().asString();
		assertThat(response, Matchers.is("Order placed successfully"));
	}

}
