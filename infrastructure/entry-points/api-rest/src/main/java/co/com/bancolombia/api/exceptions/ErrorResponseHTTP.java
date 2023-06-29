package co.com.bancolombia.api.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorResponseHTTP {

    private String code;
    private String mensaje;
}