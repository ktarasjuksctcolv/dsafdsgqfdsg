package api_tests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import com.microsoft.playwright.options.RequestOptions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SamplePlaywrightApiPocTest {
    private static Playwright playwright;
    private static APIRequestContext request;
    private static long startTime;

    @AfterAll
    public static void tearDown() {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time of tests " + totalTime);;
    }

    @BeforeAll
    public static void setup() {
        startTime = System.currentTimeMillis();

        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                // All requests we send go to this API endpoint.
                .setBaseURL("https://petstore.swagger.io/"));
    }

    @Test
    public void addPet() {
        String requestBody = "{ \"id\": 10, \"name\": \"myPet\", \"status\": \"available\" }";

        Map<String, Object> data = new HashMap<>();
        data.put("id", 10);
        data.put("name", "doggie");
        data.put("status", "available");
        APIResponse pets = request.post("v2/pet",
                RequestOptions.create().setData(data));
        assertTrue(pets.ok());
    }

    @Test
    public void getPet() throws JsonProcessingException {
        String petID = "10";
        APIResponse pets = request.get("v2/pet/" + petID);
        String body = pets.text();
        assertTrue(pets.ok());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);
        assertThat(jsonNode.get("id").asInt()).isEqualTo(10);
        assertThat(jsonNode.get("name").asText()).isEqualTo("doggie");
        assertThat(jsonNode.get("status").asText()).isEqualTo("available");
    }
}