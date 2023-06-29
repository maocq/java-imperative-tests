package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.request.RegisterAccountRequest;
import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.usecase.registeraccount.RegisterAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ApiRestTest {

    @Mock
    RegisterAccountUseCase registerAccountUseCase;
    ApiRest apiRest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiRest = new ApiRest(registerAccountUseCase);
    }

    @Test
    void useCaseTest() {
        when(registerAccountUseCase.register(anyLong(), anyString(), anyString()))
                .thenReturn(Account.builder().id(99L).name("Foo").build());

        apiRest = new ApiRest(registerAccountUseCase);
        RegisterAccountRequest request = RegisterAccountRequest.builder().id(99L).name("Foo").statusId("id").build();
        Account result = apiRest.commandName(request);

        assertEquals("Foo", result.getName());
    }
}
