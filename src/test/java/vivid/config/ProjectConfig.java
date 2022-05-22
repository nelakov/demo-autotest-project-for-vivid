package vivid.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/${properties}.properties")
public interface ProjectConfig extends Config {
    @Key("remoteDriverUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub/")
    String remoteDriverUrl();

    @Key("videoStorage")
    @DefaultValue("")
    String videoStorage();

    @Key("baseurl")
    @DefaultValue("https://vivid.money/en-eu/")
    String webUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browser.size")
    @DefaultValue("1920*1080")
    String browserSize();

    @Key("browser.version")
    String browserVersion();
}
