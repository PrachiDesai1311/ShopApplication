**Local Setup for api-gateway**

**<u>Pre-Requisites:</u>**

1. IDE(Eclipse, IntelliJ)
2. Java
3. Postman
4. Docker Desktop

**<u>Setting up api-gateway locally:</u>**

1. Clone the api-gateway into the IDE
2. Setup order-service, product-service and inventory-service, each in separate IDE window.
3. Run the command mvn clean verify
4. Open IDE terminal and run the command docker-compose up -d to install the Keycloak and Keycloak-mysql
5. Start the application and test the endpoint through the postman (check API Endpoint section for testing steps and endpoint URL)
6. Make sure order-service, product-service and inventory-service are up and running in local. (Follow the individual service's readme file to setup and run services locally.)


**<u>API Endpoint Testing through Postman and Swagger<u>**

**<i>A) Postman:</i>**

  Step 1: Hit the "http://localhost:8181/" in the preferred browser
  
  Step 2: Provide Username as "admin" and Password as "admin" to log into the Keycloak console
  
  Step 3: At left side, click on create realm -> add Realm Name as "spring-microservice-security-realm" -> Click "Create"
  
  Step 4: Go to Client from left side options -> Select "Create Client" -> Select Client Type as " OpenID connect ->       
          Provide Client ID as "spring-client-credentials-id" -> Click "Next"-> Enable "Client Authentication" and select 
          "Service account roles" -> Click "Next -> Select "Save".
          
          **Note:** Check the credentials tab of created client and note down the Client Secret.
          
  Step 5: Open "Realm settings" available at left side of page under "Configure tab" -> Scroll to the down and Click on     
          "OpenID Endpoint Configuration". It will open configuration urls in new tab. Note down the "token_endpoint" url.
          
  Step 6: Re-run the application and open the Postman
  
          a. To create product, select "POST" method in the postman. Go to Authorization tab, Select Type as "OAuth 2.0" -> 
             Configure New Token and select Grant Type as "Client Credentials" from dropdown and enter the Access Token 
             URL(Paste the token_endpoint url noted down in Step 5) -> Add Client Id as "spring-client-credentials-id" and 
             Client Secret(Paste Client Secret noted in Step 4) -> Click "Get new access token" -> Once Token generated, Click on "Use Token". Now, use below endpoint and Json Payload to test it.
              Endpoint URL : http://localhost:9000/api/product 
              
              JSON Payload: { 
                "name": "Apple Iphone", 
                "description" : "5 Mega Pixel Camera", 
                "price": 10000 
              }
              
          b. To view all available product, select "Get" method. Go to the authorization tab, scroll down and click on 
             "Get new access token" and Hit the endpoint "http://localhost:9000/api/product"
             
          c. To place an order, select "POST" method in the postman. Go to the authorization tab, scroll down and click on 
             "Get new access token". Use below endpoint and JSON payload, 
                Endpoint URL : http://localhost:9000/api/order 
                
                JSON Payload: { 
                  "stuCode": "samsung", 
                  "price" : 29000, 
                  "quantity": 1 
                }

                
**<i>B) Swagger-ui:</i>** Once application started successfully, hit the "http://localhost:9000/swagger-ui/index.html, select service from dropdown at the top right side of the page and test the endpoint.
