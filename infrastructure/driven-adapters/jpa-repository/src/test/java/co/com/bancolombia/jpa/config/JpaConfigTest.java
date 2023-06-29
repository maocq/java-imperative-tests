package co.com.bancolombia.jpa.config;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaConfigTest {

    @Test
    void name() {
        JpaConfig jpaConfig = new JpaConfig();
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.setProperty("spring.datasource.url", "url");
        mockEnvironment.setProperty("spring.datasource.username", "username");
        mockEnvironment.setProperty("spring.datasource.password", "password");

        DBSecret result = jpaConfig.dbSecret(mockEnvironment);

        assertEquals("username", result.getUsername());
    }
}
