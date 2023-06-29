package co.com.bancolombia.model.statusaccount.gateways;

import co.com.bancolombia.model.statusaccount.StatusAccount;

public interface StatusAccountService {

    StatusAccount getStatus(String idInfo);
}
