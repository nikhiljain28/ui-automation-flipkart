package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
public class ExtentReportManager {
	private static ExtentReports extent;
	public static String reportPath;

	public static ExtentReports getInstance() {
		if (extent == null) {

			//Step 1 — Define report path
			reportPath = System.getProperty("user.dir")
					+ File.separator + "reports"
					+ File.separator + "ExtentReport.html";

			//Step 2 — Create SparkReporter
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

			// Step 3 — Configure report
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("API Automation Report");
			sparkReporter.config().setReportName("User Module Test Results");
			sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

			//Step 4 — Attach reporter to ExtentReports
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Step 5 — System info
			extent.setSystemInfo("Project", "API Automation");
			extent.setSystemInfo("Tester", "Nikhil");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Framework", "RestAssured + TestNG");
		}
		return extent;
	}
}
