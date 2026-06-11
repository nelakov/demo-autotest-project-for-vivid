package vivid.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Test configuration: values come from a classpath properties file
 * (config/${properties}.properties) and can be overridden by JVM system
 * properties (-Dkey=value). Replaces the abandoned org.aeonbits.owner library.
 * A missing key resolves to "" (never null), so callers stay NPE-free.
 */
public class ProjectConfig {
    private final Properties fileProperties = new Properties();

    public ProjectConfig() {
        String profile = System.getProperty("properties", "");
        String resource = "config/" + profile + ".properties";
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resource)) {
            if (input != null) {
                fileProperties.load(input);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read test config: " + resource, e);
        }
    }

    public String remoteDriverUrl() {
        return get("remoteDriverUrl");
    }

    public String videoStorage() {
        return get("videoStorage");
    }

    public String webUrl() {
        String url = get("baseUrl");
        if (url.isEmpty()) {
            throw new IllegalStateException(
                    "baseUrl is not configured. Pass -DbaseUrl=<url> or set baseUrl in config/<profile>.properties via -Dproperties=<profile>.");
        }
        return url;
    }

    public String browser() {
        return get("browser");
    }

    public String browserSize() {
        return get("browserSize");
    }

    public String browserVersion() {
        return get("browserVersion");
    }

    private String get(String key) {
        return System.getProperty(key, fileProperties.getProperty(key, ""));
    }
}
