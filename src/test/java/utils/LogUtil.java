package utils;
import io.restassured.response.Response;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

	public static void captureFailureLog(Response response, String testName) {
		try {
			// Step 1 — Create timestamp
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			// Step 2 — Create failure-logs folder
			String folderPath = System.getProperty("user.dir")
					+ File.separator + "failure-logs";
			new File(folderPath).mkdirs();

			// Step 3 — Define log file path
			String filePath = folderPath
					+ File.separator + testName + "_" + timestamp + ".txt";

			// Step 4 — Write response details to file
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write("====================================");
			writer.newLine();
			writer.write("TEST NAME    : " + testName);
			writer.newLine();
			writer.write("TIMESTAMP    : " + timestamp);
			writer.newLine();
			writer.write("====================================");
			writer.newLine();
			writer.write("STATUS CODE  : " + response.getStatusCode());
			writer.newLine();
			writer.write("STATUS LINE  : " + response.getStatusLine());
			writer.newLine();
			writer.write("====================================");
			writer.newLine();
			writer.write("RESPONSE HEADERS : ");
			writer.newLine();
			writer.write(response.getHeaders().toString());
			writer.newLine();
			writer.write("====================================");
			writer.newLine();
			writer.write("RESPONSE BODY : ");
			writer.newLine();
			writer.write(response.asPrettyString());   // ✅ full response body
			writer.newLine();
			writer.write("====================================");
			writer.close();

			System.out.println("Failure log saved : " + filePath);

		} catch (Exception e) {
			System.out.println("Log capture failed : " + e.getMessage());
		}
	}
}
