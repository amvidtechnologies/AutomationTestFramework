package webAutomation.driver;

public class DriverInfo {

    private  String type;
    private  String version;
    private  String resolution;

    DriverInfo(String type, String version, String resolution){
        this.type = type;
        this.version = version;
        this.resolution = resolution;
    }

    public String getType() {
        return type;
    }
    public String getVersion() {
        return version;
    }
    public String getResolution() {
        return resolution;
    }

}
