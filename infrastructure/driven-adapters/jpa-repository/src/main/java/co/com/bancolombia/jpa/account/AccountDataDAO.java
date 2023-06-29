package co.com.bancolombia.jpa.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface AccountDataDAO extends CrudRepository<AccountData, Long>, QueryByExampleExecutor<AccountData> {
}
