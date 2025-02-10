**Local Setup for irder-service**

**Pre-Requisites:**
1. IDE(Eclipse, IntelliJ)
2. Java
3. Postman
4. Docker Desktop

**Setting up order-service locally:**
1. Clone the order-service into the IDE
2. Run the command mvn clean verify
3. Open IDE terminal and run the command docker-compose up -d to install the MySQL Database
4. Start the application and test the endpoint through the postman (check API Endpoint section for endpoint URL)

**API Endpoint Testing through Postman and Swagger**

**A) Postman:**
1. To place an order, select "POST" method in the postman and use below endpoint and JSON payload,
   Endpoint URL : http://localhost:8081/api/order
   JSON Payload: {
    "stuCode": "samsung",
    "price" : 29000,
    "quantity": 1
}

**B) Swagger-ui:** Once application started successfully, hit the "http://localhost:8081/swagger-ui/index.html and test the endpoint.
