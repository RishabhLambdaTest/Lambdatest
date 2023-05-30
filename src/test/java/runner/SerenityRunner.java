package runner;

import com.lambdatest.tunnel.Tunnel;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs"},
        dryRun = false,
        monochrome = true,
        tags = "@test",
        plugin = {"pretty"}
)
public class SerenityRunner {

   private static Tunnel t;

    public Scenario scenario;
    private static final Logger LOGGER = LogManager.getLogger(SerenityRunner.class);
    static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    static {
        try {
           t = new Tunnel();
        } catch (Exception e) {
            LOGGER.info("Failed to create tunnel", e);
        }
    }


    @BeforeClass()
    public static void setupTunneling() throws InterruptedException, IOException {
        HashMap<String, String> args = new HashMap<>();
        args.put("user", environmentVariables.getProperty("lt.user"));
        args.put("key", environmentVariables.getProperty("lt.key"));
        args.put("tunnelName", "Serenity");
        t.start(args);
//       String accessKey = environmentVariables.getProperty("lt.key");
//       String command = "LT  --user s.m5@elsevier.com --key " +accessKey;
//       Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + command);
       Thread.sleep(5000);
        LOGGER.info("LambdaTest tunnel with name {} has started", "Serenity");
        System.out.println("tunnel started");
    }



}
