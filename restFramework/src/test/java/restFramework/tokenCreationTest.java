package restFramework;

import java.io.File;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import APIOperations.APIFunctionalities;


public class tokenCreationTest {
    
    //String configurationFile = System.getProperty("user.dir")+"/src/main/resources/log4j.xml";
    
    
    @Test
    public void tokenCreationTestMethod() {
        APIFunctionalities apiFunctionalities = new APIFunctionalities();
        JSONObject postApiResponse = apiFunctionalities.postTokenMgmtPublicAPIV3("augorg1","Cigital@123", "MS-Portal","SCRIPT", "PT1H","1.2.3.4");
        if (Integer.parseInt(postApiResponse.getString("responseCode")) == 200) {
            Assert.assertNotNull(postApiResponse.getString("id"));
            Assert.assertTrue(postApiResponse.getString("id").startsWith("TOKEN_"));
            Assert.assertEquals(Integer.parseInt(postApiResponse.getString("responseCode")), 200, "Response Code did not match");
            
            JSONObject getApiResponse = apiFunctionalities.getTokenMgmtPublicAPIV3(postApiResponse.getString("id"), postApiResponse.getString("token"));
            Assert.assertEquals(Integer.parseInt(getApiResponse.getString("responseCode")), 200, "Response Code did not match");
            Assert.assertEquals(getApiResponse.getString("id"), postApiResponse.getString("id"), "Response Code did not match");
        } else {
            Assert.assertTrue(postApiResponse.getString("errorResponse").contains("Cannot generate/assign tokens, only 5 access-tokens can be active at any time. Retry the request."), "Get Response Code did not match");
        }
       
        }

}
