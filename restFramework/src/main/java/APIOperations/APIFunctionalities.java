package APIOperations;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import constants.PublicAPICalls;
import utils.APIMethodCalls;

public class APIFunctionalities {
    
    private Logger log = LoggerFactory.getLogger(APIFunctionalities.class);
    
    /**
     * Method to create Token
     * 
     * @param userName
     * @param password
     * @param applicationName
     * @param tokenType
     * @param duration
     * @param allowedIPRange
     * @return JSONObject
     */
    public JSONObject postTokenMgmtPublicAPIV3(String userName, String password, String applicationName,
            String tokenType, String duration, String allowedIPRange) {
        JSONObject response = new JSONObject();
        JSONObject payload = new JSONObject();
        // Creating payload for token creation
        try {
            payload.put("userName", userName);
            payload.put("password", password);
            payload.put("applicationName", applicationName);
            payload.put("duration", duration);
            payload.put("type", tokenType);
            payload.put("allowedIpRange", allowedIPRange);
        } catch (JSONException e) {
            log.error("Exception occured while creating the payload for Token Creation", e);
            throw new AssertionError("Exception occured while creating the payload for Token Creation");
        }
        try {
            response = APIMethodCalls.postRequestJSON(PublicAPICalls.CREATE_TOKEN, payload.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create Token");
        }
        return response;
    }
    
    public JSONObject getTokenMgmtPublicAPIV3(String tokenID, String tokenValue) {
        JSONObject response = new JSONObject();
        try {
            response = APIMethodCalls.getRequestForSpecificToken(PublicAPICalls.GET_TOKEN, tokenID, tokenValue);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Failed to create Token");
        }
        return response;
    }
}