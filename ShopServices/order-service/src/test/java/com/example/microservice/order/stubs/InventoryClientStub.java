package com.example.microservice.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {
    public static void stubInventoryClient(String stuCode, Integer quantity) {
        stubFor(get(urlEqualTo("/api/inventory?stuCode=" + stuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
