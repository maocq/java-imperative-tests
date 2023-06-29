package co.com.bancolombia.usecase.registeraccount;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.exceptions.BusinessException;
import co.com.bancolombia.model.exceptions.TechnicalException;
import co.com.bancolombia.model.statusaccount.StatusAccount;
import co.com.bancolombia.model.statusaccount.gateways.StatusAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static co.com.bancolombia.model.exceptions.message.BusinessErrorMessage.ACCOUNT_VALIDATION_ERROR;
import static co.com.bancolombia.model.exceptions.message.TechnicalErrorMessage.TECHNICAL_RESTCLIENT_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class RegisterAccountUseCaseTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private StatusAccountService statusAccountService;
    private RegisterAccountUseCase registerAccountUseCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //accountRepository = mock(AccountRepository.class);
        //statusAccountService = mock(StatusAccountService.class);

        registerAccountUseCase = new RegisterAccountUseCase(statusAccountService, accountRepository);
    }

    @Test
    void registerValidAccountTest() {
        var status = StatusAccount.builder().status("ok").build();
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        when(statusAccountService.getStatus(anyString()))
                .thenReturn(status);

        var result = registerAccountUseCase.register(99L, "Foo", "1");
        assertEquals("Foo", result.getName(), "xxxx yyy zzz");
    }

    @Test
    void registerAccountAlreadyExistTest() {
        var account = Account.builder().id(99L).name("Foo").build();
        var status = StatusAccount.builder().status("ok").build();

        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(account));
        when(statusAccountService.getStatus(anyString()))
                .thenReturn(status);

        BusinessException error = assertThrows(BusinessException.class, () ->
                registerAccountUseCase.register(99L, "Foo", "1"));

        assertEquals(error.getErrorMessage(), ACCOUNT_VALIDATION_ERROR);
    }

    @Test
    void registerStatusServiceErrorTest() {
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        when(statusAccountService.getStatus(anyString()))
                .thenThrow(new TechnicalException(TECHNICAL_RESTCLIENT_ERROR));

        TechnicalException error = assertThrows(TechnicalException.class, () ->
                registerAccountUseCase.register(99L, "Foo", "1"));

        assertEquals(error.getErrorMessage(), TECHNICAL_RESTCLIENT_ERROR);
    }
}
