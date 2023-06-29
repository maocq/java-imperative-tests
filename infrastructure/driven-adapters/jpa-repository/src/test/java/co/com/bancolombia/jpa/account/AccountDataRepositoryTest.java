package co.com.bancolombia.jpa.account;

import co.com.bancolombia.model.account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapperImp;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AccountDataRepositoryTest {

    @Mock
    AccountDataDAO repository;
    AccountDataRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var mapper = new ObjectMapperImp();
        accountRepository = new AccountDataRepository(repository, mapper);
    }

    @Test
    void findByIdTest() {
        var data = new AccountData(0L, "name", "status");
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(data));

        Optional<Account> result = accountRepository.findById(0L);
        assertTrue(result.isPresent());

        Account account = result.get();
        assertEquals("name", account.getName());
    }
}
