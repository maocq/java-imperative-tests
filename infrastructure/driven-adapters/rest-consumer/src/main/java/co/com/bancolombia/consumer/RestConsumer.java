package co.com.bancolombia.consumer;

import co.com.bancolombia.model.exceptions.TechnicalException;
import co.com.bancolombia.model.statusaccount.StatusAccount;
import co.com.bancolombia.model.statusaccount.gateways.StatusAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static co.com.bancolombia.model.exceptions.message.TechnicalErrorMessage.TECHNICAL_RESTCLIENT_ERROR;

@Service
public class RestConsumer implements StatusAccountService {

    //@Value("${adapter.restconsumer.url}")
    private final String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public RestConsumer(OkHttpClient client, ObjectMapper mapper, @Value("${adapter.restconsumer.url}") String url) {
        this.client = client;
        this.mapper = mapper;
        this.url = url;
    }


    @Override
    public StatusAccount getStatus(String idInfo) {
        var urlService = String.format("%s/v3/%s", url, idInfo);

        Request request = new Request.Builder()
            .url(urlService)
            .get()
            .addHeader("Content-Type","application/json")
            .build();

        var statusAccount = getStatusAccountDto(request);
        return StatusAccount.builder().status(statusAccount.getStatus()).build();
    }

    @SneakyThrows
    private StatusAccountDto getStatusAccountDto(Request request) {
        try {
            return mapper.readValue(client.newCall(request).execute().body().string(), StatusAccountDto.class);
        } catch (Exception exception) {
            throw new TechnicalException(exception, TECHNICAL_RESTCLIENT_ERROR);
        }
    }


    /*
    public ObjectResponse testPost() throws IOException {
        String json = mapper.writeValueAsString(ObjectRequest.builder()
            .val1("exampleval1")
            .val2("exampleval1")
            .build()
        );

        RequestBody requestBody = RequestBody
            .create(json, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type","application/json")
            .build();

        return mapper.readValue(client.newCall(request).execute().body().string(), ObjectResponse.class);
    }    
     */

}
