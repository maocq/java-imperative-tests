package co.com.bancolombia.usecase.registeraccount;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.exceptions.BusinessException;
import co.com.bancolombia.model.statusaccount.StatusAccount;
import co.com.bancolombia.model.statusaccount.gateways.StatusAccountService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Random;

import static co.com.bancolombia.model.exceptions.message.BusinessErrorMessage.ACCOUNT_VALIDATION_ERROR;

@RequiredArgsConstructor
public class RegisterAccountUseCase {

    private final StatusAccountService statusAccountService;
    private final AccountRepository accountRepository;

    public Account register(long id, String name, String statusId) {

        Optional<Account> account = accountRepository.findById(id);
                //Account account = account.orElseThrow(() -> new BusinessException(ACCOUNT_FIND_ERROR));
        if (account.isPresent())
            throw new BusinessException(ACCOUNT_VALIDATION_ERROR);

        StatusAccount status = statusAccountService.getStatus(statusId);
        return Account.newAccount(new Random().nextLong(), name, status.getStatus());
    }
}
