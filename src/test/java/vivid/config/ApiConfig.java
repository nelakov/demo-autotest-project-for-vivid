package vivid.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:/tmp/api.properties",
        "classpath:config/api.properties"})
public interface ApiConfig extends Config{

    @Key("apibaseurl")
    String apiBaseUrl();

    @Key("apitoken")
    String apiToken();

}
