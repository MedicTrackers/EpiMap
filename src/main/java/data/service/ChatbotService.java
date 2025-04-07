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
	
	// [사용자의 메세지를 JSON 형태로 반환] 
	public String sendMessageToClova(String messageContent) {
		try {
			// 사용자가 보낸 messageContent를 클로바 API 형식의 JSON 문자열로 변환 (내부에서 userId, timestamp, bubbles 등을 포함하는 구조로 만듦.)
			String requestJson = createRequestBody(messageContent); 
			String signature = makeSignature(requestJson, SECRET_KEY); // 암호화(서명) - 이게 없으면 API 호출 거부
			
			URL url = new URL(API_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST"); //데이터 전송이 목적이니까 POST
			con.setRequestProperty("Content-Type", "application/json;UTF-8");
			con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", signature); // 앞서 만든 서명을 API 요청 헤더에 추가
			con.setDoOutput(true); // 데이터 전송하는 OutputStream을 열겠다는 의미 (POST니까 당연히 있어야 함.)
			
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())){
				wr.write(requestJson.getBytes("UTF-8"));
			}
			
			int responseCode = con.getResponseCode(); // HTTP 응답 상태 코드 - 200이면 성공
			BufferedReader br = new BufferedReader(
				new InputStreamReader(responseCode == 200 ? con.getInputStream() : con.getErrorStream(), "UTF-8")
			);
			
			StringBuilder response = new StringBuilder(); // 응답 본문 한 줄씩 읽어서 StringBuilder에 저장
			String line;
			while((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();
			
			return response.toString(); // 응답 JSON을 문자열 그대로 반환
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}
	
	// [메세지를 서명하기 위한 함수] - 클로바 API에서 보안을 위해 요구
	private String makeSignature(String message, String secretKey) throws Exception {
		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256"); 
		Mac mac = Mac.getInstance("HmacSHA256"); // HMAC-SHA256 방식으로 서명 생성 (헤더에 사용)
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(rawHmac);
	}
	
	// [챗봇에 전송할 JSON 메세지 구성]
	private String createRequestBody(String messageContent) {
		JSONObject obj = new JSONObject();
		long timestamp = new Date().getTime();
		
		obj.put("version", "v2"); // v2
		obj.put("userId", UUID.randomUUID().toString()); // 랜덤 UUID
		obj.put("timestamp", timestamp); // 현재 시간
		
		JSONObject bubbleObj = new JSONObject();
		bubbleObj.put("type", "text");
		
		JSONObject dataObj = new JSONObject();
		dataObj.put("description", messageContent); // 사용자가 입력한 메세지
		bubbleObj.put("data", dataObj);
		
		JSONArray bubbles = new JSONArray();
		bubbles.put(bubbleObj);
		
		obj.put("bubbles", bubbles);
		obj.put("event", "send"); // send 이벤트
		
		return obj.toString();
	}
}
