

import java.net.URL;
import java.util.*;

import net.thucydides.core.environment.SystemEnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;

public class LambdaTestSerenityDriver implements DriverSource {

    public WebDriver newDriver() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String br = System.getProperty("profile");
        String environment = System.getProperty("environment");




        System.out.println("-------environment"+environment+"---br"+br);

        String username = System.getenv("LT_USERNAME");
        if (username == null) {
            username = (String) environmentVariables.getProperty("lt.user");
        }

        String accessKey = System.getenv("LT_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) environmentVariables.getProperty("lt.key");
        }



//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("plugin","Serenity LambdaTest Plugin");

//        ChromeOptions capabilities = new ChromeOptions();
//        //browserOptions.setPlatformName("Windows 10");
//        capabilities.setAcceptInsecureCerts(true);
//        HashMap<String, Object> lambdaTestOptions = new HashMap<>();
//        lambdaTestOptions.put("build", "SCT Regression test");
//        lambdaTestOptions.put("name", "SCT-UI");
//        lambdaTestOptions.put("project", "SCT");
//        lambdaTestOptions.put("selenium_version", "4.0.0");
//        lambdaTestOptions.put("network", false);
//        lambdaTestOptions.put("network.har", false);
//        lambdaTestOptions.put("video", true);
//        lambdaTestOptions.put("visual", false);
//        lambdaTestOptions.put("idleTimeout", "300");
//        lambdaTestOptions.put("lambdaStrict", false);
//        lambdaTestOptions.put("w3c", true);
////        lambdaTestOptions.put("tunnelName", "Serenity");
//        lambdaTestOptions.put("tunnel", false);
//        capabilities.setCapability("LT:Options", lambdaTestOptions);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("plugin", "Serenity LambdaTest Plugin");
        List<String> arg = new ArrayList<String>();

        Iterator it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();

            if (key.equals("lt.user") || key.equals("lt.key") || key.equals("lt.grid")) {
                continue;
            } else if (key.startsWith("lt_")) {
                capabilities.setCapability(key.replace("lt_", ""), environmentVariables.getProperty(key));

            } else if (environment != null && key.startsWith("environment." + environment)) {


                if (environment.equals("single")){


                 System.out.println("I AM THE IRON MAN");

                    capabilities.setCapability(key.replace("environment." + environment + "." + br + ".", ""),
                            environmentVariables.getProperty(key));
                   // System.out.println(capabilities+"--------->"+environmentVariables.getProperty(key));

                    if(key.startsWith("environment.single.chrome.chromeOptions"))
                    {
                        arg.add(environmentVariables.getProperty(key));
                    }

                }
                else {


                    System.out.println("I AM ENEVITABLE");


                    capabilities.setCapability(key.replace("environment." + environment + ".", ""),
                            environmentVariables.getProperty(key));
                    	System.out.println(capabilities+"-----parallel---->"+environmentVariables.getProperty(key));


                }
            }
        }
        Map<String, Object> options = new HashMap<>();
        options.put("args", arg);
        capabilities.setCapability("goog:chromeOptions", options);


        //System.out.println("CAPABILITY-------->"+capabilities);



        try {
            String url = "https://" + username + ":" + accessKey + "@" + environmentVariables.getProperty("lt.grid")
                    + "/wd/hub";
            return new RemoteWebDriver(new URL(url), capabilities);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean takesScreenshots() {
        return false;
    }
}
