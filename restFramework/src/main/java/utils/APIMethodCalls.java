package utils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;




public class APIMethodCalls {
    
    private final static String acceptType_json = "application/json";
    private static Logger log = LoggerFactory.getLogger(APIMethodCalls.class);
    
    public static JSONObject postRequestJSON(String apiCall, String payload) {
        RestAssured.useRelaxedHTTPSValidation();
        apiCall = "https://cedar-qa01.internal.synopsys.com" + apiCall;
        JSONObject APIresponse = new JSONObject();
        try {
            Response response = RestAssured.given().body(payload).header("Content-Type", acceptType_json).when()
                    .post(apiCall);
            log.info("Status Code: ", response.getStatusCode());
            log.info("JSON Body: ", response.getBody());
            if (response.getStatusCode() == 200) {
                APIresponse = new JSONObject(response.getBody().asString());
                APIresponse.put("responseCode", String.valueOf(response.getStatusCode()));
            } else {
                String resp = response.getBody().asString();
                APIresponse.put("responseCode", String.valueOf(response.getStatusCode()));
                APIresponse.put("errorResponse", resp);
            }
        } catch (Exception e) {
            log.error("Exception occured at post request method (Public API)...");
            log.error("Trace : ", e);
        }
        return APIresponse;
    }
    
    public static JSONObject getRequestForSpecificToken(String apiCall, String tokenID, String tokenValue) {
        RestAssured.useRelaxedHTTPSValidation();
        apiCall = "https://cedar-qa01.internal.synopsys.com" + apiCall + "/" + tokenID; 
        JSONObject APIresponse = null;
        try{
             Response response = RestAssured.given().
                     header("token", tokenValue).and().
                     header("Accept", acceptType_json).
                     when().get(apiCall);
             log.info("Status Code: ", response.getStatusCode());
             log.info("JSON Body: ", response.getBody());
             APIresponse = new JSONObject(response.getBody().asString());
             APIresponse.put("responseCode", response.getStatusCode());
         }catch(Exception e){
             log.error("Exception occured at post request method (Public API)...");
             log.error("Trace : ",e);
         }
        return APIresponse;
     }

}
