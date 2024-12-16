package fisAPIExcercise;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class FISAPIExcercise {
	String URI = "https://api.coindesk.com/v1/bpi/currentprice.json";

	@Test
	public void JSONValidation() {

		RequestSpecification request = RestAssured.given();
		Response response = request.when().get(URI);

		JSONObject responseJson = new JSONObject(response.asString());
		JSONObject bpi = responseJson.getJSONObject("bpi");

		Assert.assertEquals(bpi.length(), 3);

        // Verify the presence of USD, GBP, and EUR
        String[] expectedCurrencies = {"USD", "GBP", "EUR"};
        for (String currency : expectedCurrencies) {
            if (!bpi.has(currency)) {
                throw new RuntimeException("Missing expected currency: " + currency);
            }
        }
        
        // Verify the GBP Description
        String gbpExpectedDescription = "British Pound Sterling";
        
        JSONObject gbp = bpi.getJSONObject("GBP");
        String gbpActualDescription = gbp.getString("description");
        Assert.assertEquals(gbpActualDescription, gbpExpectedDescription);
        
	}

}
