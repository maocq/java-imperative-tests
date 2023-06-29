package co.com.bancolombia.api;
import co.com.bancolombia.api.dto.request.RegisterAccountRequest;
import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.usecase.registeraccount.RegisterAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
//    private final MyUseCase useCase;
    private final RegisterAccountUseCase registerAccountUseCase;

    @GetMapping(path = "/path")
    public String commandName() {
        return "Hello World";
    }

    @PostMapping(path = "/usecase")
    public Account commandName(@RequestBody RegisterAccountRequest request) {
        return registerAccountUseCase.register(request.getId(), request.getName(), request.getStatusId());
    }
}
