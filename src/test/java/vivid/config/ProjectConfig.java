package vivid.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/${properties}.properties")
public interface ProjectConfig extends Config {
    @Key("remoteDriverUrl")
    String remoteDriverUrl();

    @Key("videoStorage")
    String videoStorage();

    @Key("baseUrl")
    String webUrl();

    @Key("browser")
    String browser();

    @Key("browserSize")
    String browserSize();

    @Key("browserVersion")
    String browserVersion();
}
