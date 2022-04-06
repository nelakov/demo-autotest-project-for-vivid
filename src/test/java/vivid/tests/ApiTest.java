package vivid.tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vivid.config.ApiConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {

    @Test
    @DisplayName("Check token/url test")
    void apiTokenTest() {
        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
        assertThat(apiConfig.apiToken()).isEqualTo("");
        assertThat(apiConfig.apiBaseUrl()).isEqualTo("https://vivid.money/en-eu/");
    }
}