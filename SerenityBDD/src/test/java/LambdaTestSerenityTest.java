
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class LambdaTestSerenityTest {

    @BeforeClass
    public static void setUp() throws Exception {
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

//	private static Tunnel t;
//
//    public Scenario scenario;
//    private static final Logger LOGGER = LogManager.getLogger(SerenityRunner.class);
//    static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
//
//    static {
//        try {
//           t = new Tunnel();
//        } catch (Exception e) {
//            LOGGER.info("Failed to create tunnel", e);
//        }
//    }
//
//	@BeforeClass
//	public static void setUp() throws Exception {
//		HashMap<String, String> args = new HashMap<>();
//		args.put("user", environmentVariables.getProperty("lt.user"));
//		args.put("key", environmentVariables.getProperty("lt.key"));
//		args.put("tunnelName", "Serenity");
//		t.start(args);
//		Thread.sleep(5000);
//		LOGGER.info("LambdaTest tunnel with name {} has started", "Serenity");
//		System.out.println("tunnel started");
//	}
//
//	@AfterClass
//	public static void tearDown() throws Exception {
//		t.stop();
//	}
}