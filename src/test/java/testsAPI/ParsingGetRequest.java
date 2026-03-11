package testsAPI;

//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

//import io.restassured.response.Response;

public class ParsingGetRequest extends BaseClass{


	@Test
	public void getRequest() throws IOException {

		String baseURI = "https://jsonplaceholder.typicode.com";
		String endPoint = "/posts";
		URL ur = new URL(baseURI+endPoint);
		HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		int responseCode = conn.getResponseCode();
		System.out.println("Response Code: " + responseCode);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream())
				);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		String response = sb.toString().trim();
        System.out.println("RAW: " + response); // debug print
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            System.out.println("Title : " + jo.getString("title"));            
        }
	};

}