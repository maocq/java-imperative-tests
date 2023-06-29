package co.com.bancolombia.model.account.gateways;

import co.com.bancolombia.model.account.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(long id);
}
