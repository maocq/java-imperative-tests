package co.com.bancolombia.model.account.gateways;

import co.com.bancolombia.model.account.Account;

public interface AccountRepository {

    Account findById(long id);
}
