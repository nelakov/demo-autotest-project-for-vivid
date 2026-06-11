package vivid.config;

public class Project {
    public static final ProjectConfig config = new ProjectConfig();

    public static boolean isRemoteWebDriver() {
        return !config.remoteDriverUrl().isEmpty();
    }

    public static boolean isVideoOn() {
        return !config.videoStorage().isEmpty();
    }
}
