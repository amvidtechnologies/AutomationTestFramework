package webAutomation.wait;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import webAutomation.exception.TestException;
import webAutomation.logger.LoggerManager;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class CustomWebDriverWait {
    protected Logger logger = LoggerManager.getLogger(CustomWebDriverWait.class);
    WebDriverWait webDriverWait;
    private String expectedCondition;

    public CustomWebDriverWait(WebDriver driver, long timeOutInSeconds, long sleepInMillis){
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis));
        logger.debug("custom web driver wait for seconds: "+timeOutInSeconds+" AND sleep in millis "+sleepInMillis);
    }

    public <V> V until(Function<? super WebDriver, V> isTrue, Optional<Boolean> bPrintException) throws TestException {
        try{
            expectedCondition = isTrue.toString();
            return webDriverWait.until(isTrue);
        }
        catch (WebDriverException e){
            throw new TestException(e.getCause(), "Fail -> wait.until: "
                    + expectedCondition.replace("Proxy element for: DefaultElementLocator","")
                    + "/r"+ pullStackTraceMethods(e.getStackTrace(),4,5)
                    + "/r" + e.getMessage(), bPrintException.get());
        }

    }

    private String pullStackTraceMethods(StackTraceElement[] stackTraceElements, int from, int to){
        List<String> results = new ArrayList<>();
        AtomicReference<AtomicInteger> cnt = new AtomicReference<>(new AtomicInteger());
        List<StackTraceElement> stackTraceList = new ArrayList<>(Arrays.asList(stackTraceElements));
        stackTraceList.forEach(element ->{
            if (cnt.get().get() >= from && cnt.get().get() <= to) {
                results.add(element.getClass().getSimpleName()+":"+ element.getMethodName()+">");
            }
            cnt.get().getAndIncrement();
        });
        Collections.reverse(results);
        return results.toString();
    }

    public <V> V until(Function<? super WebDriver, V> isTrue) throws TestException {
        return until(isTrue, Optional.of(true));
    }

//    public void until(Function<? super WebDriver, V> isTrue){
//        super.until(isTrue);
//    }
}
