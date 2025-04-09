package data.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {
   private static final String API_URL = "https://263s6itmj4.apigw.ntruss.com/custom/v1/17219/c2e207b6d0b7e18cc72ed99276200bd5eb54d7bf7636de92a3852788c609bdc9";
   private static final String SECRET_KEY = "TW1LTElFb1NLYWhiWktQVmVsQWtGeHB4WG9OY0JXTlY=";
   
   public String sendMessageToClova(String messageContent) {
      try {
         String requestJson = createRequestBody(messageContent);
         String signature = makeSignature(requestJson, SECRET_KEY);
         
         URL url = new URL(API_URL);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", "application/json;UTF-8");
         con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", signature);
         con.setDoOutput(true);
         
         try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())){
            wr.write(requestJson.getBytes("UTF-8"));
         }
         
         int responseCode = con.getResponseCode();
         BufferedReader br = new BufferedReader(
            new InputStreamReader(responseCode == 200 ? con.getInputStream() : con.getErrorStream(), "UTF-8")
         );
         
         StringBuilder response = new StringBuilder();
         String line;
         while((line = br.readLine()) != null) {
            response.append(line);
         }
         br.close();
         
         // DB 문항을 위해 테스트 중 ================================================================
           
         
         
         
         // ===================================================================================
         
         return response.toString(); // JSON 응답 받기
         
      } catch (Exception e) {
         e.printStackTrace();
         return "Error occurred: " + e.getMessage();
      }
   }
   
   private String makeSignature(String message, String secretKey) throws Exception {
      SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(signingKey);
      byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
      return Base64.getEncoder().encodeToString(rawHmac);
   }
   
   private String createRequestBody(String messageContent) {
      JSONObject obj = new JSONObject();
      long timestamp = new Date().getTime();
      
      obj.put("version", "v2");
      /* obj.put("userId", UUID.randomUUID().toString()); */
      obj.put("userId", "abcd");
      obj.put("timestamp", timestamp);
      
      JSONArray bubbles = new JSONArray();
      /* System.out.println(messageContent); */
      if(messageContent == null || messageContent.trim().isEmpty()) {
         obj.put("event", "open");
      } else {
    	  
         JSONObject bubbleObj = new JSONObject();
         bubbleObj.put("type", "text");
         
         JSONObject dataObj = new JSONObject();
         dataObj.put("description", messageContent);
         bubbleObj.put("data", dataObj);
         
         bubbles.put(bubbleObj);
         
         obj.put("event", "send");
         obj.put("bubbles", bubbles);
      }
      
      return obj.toString();
   }
}
