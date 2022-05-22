package vivid.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/${properties}.properties")
public interface ProjectConfig extends Config {
    @Key("remoteDriverUrl")
    String remoteDriverUrl();

    @Key("videoStorage")
    String videoStorage();

    @Key("baseurl")
    String webUrl();

    @Key("browser")
    String browser();

    @Key("browser.size")
    String browserSize();

    @Key("browser.version")
    String browserVersion();
}
