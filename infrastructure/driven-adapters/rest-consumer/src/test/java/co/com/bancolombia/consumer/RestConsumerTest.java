package co.com.bancolombia.consumer;

import co.com.bancolombia.model.statusaccount.StatusAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestConsumerTest {

    static RestConsumer restConsumer;
    static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = mockBackEnd.url("url").toString();
        //ReflectionTestUtils.setField(restConsumer, "url", url);

        restConsumer = new RestConsumer(client, new ObjectMapper(), url);
    }

    @Test
    void getStatusTest() {
        mockBackEnd.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody("{\"status\" : \"ok\"}"));

        StatusAccount result = restConsumer.getStatus("id");
        assertEquals("ok", result.getStatus());
    }
}
