package co.com.bancolombia.model.account;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {

    private final long id;
    private final String name;
    private final String status;
}
