//package factory.api;
//
//import driver.CustomWebDriver;
//import org.openqa.selenium.SearchContext;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//
//public class ElementFactory {
//
//    public static <T> T initElements (CustomWebDriver driver, Class<T> pageClassToProxy){
//        T page = instantiatePage (driver, pageClassToProxy);
//        return  initElements(driver, page);
//    }
//
//    private static <T> T initElements(SearchContext searchContext, T page) {
//        initElements(new ElementDecorator(new DefaultElementLocatorFactory(searchContext)), page);
//    }
//
//    public static <T> T instantiatePage (CustomWebDriver driver, Class<T> pageClassToProxy){
//        try {
//            try{
//                Constructor <T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
//                return constructor.newInstance(driver);
//            } catch (NoSuchMethodException e) {
//                return  pageClassToProxy.newInstance();
//            }
//        }
//        catch (InstantiationException | InvocationTargetException | IllegalAccessException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//}
