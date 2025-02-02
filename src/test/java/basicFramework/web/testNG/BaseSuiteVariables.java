package basicFramework.web.testNG;
public class BaseSuiteVariables implements BaseSuiteInterface {
    private String envURL;

    public String getEnvironmentVariable(String variable) {
        return System.getenv(variable);
    }
    public void setEnvURL() {
        envURL = getEnvironmentVariable("envURL");
    }
    public String getEnvURL() {
        return envURL;
    }

}
