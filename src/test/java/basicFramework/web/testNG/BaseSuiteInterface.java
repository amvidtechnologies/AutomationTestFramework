package basicFramework.web.testNG;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface BaseSuiteInterface {

//    public String getEnvironmentVariable(String variable);
//    public String getEnvURL();
    final static long implicitlyWeightForSeconds = 30;

    default String reportName(){
        return "report"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
