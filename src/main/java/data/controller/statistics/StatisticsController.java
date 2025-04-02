package data.controller.statistics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class StatisticsController {
	
	private final String clientId = "mBM2BJScjmKEXDQXz9e4"; // 애플리케이션 클라이언트 아이디
    private final String clientSecret = "rzazEQye00"; // 애플리케이션 클라이언트 시크릿
    private final String query = "독감";
	
    @AllArgsConstructor
    @Data
    private static class BlogPost
    {
        private String title;
        private String link;
    }
    
	@GetMapping("/statistics")
	public String getBlogSearch(Model model) {
	    try {
	    	String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
	    	String apiURL = "https://openapi.naver.com/v1/search/blog?query="+encodedQuery+"&display=3&sort=date";
	    	Map<String, String> headers = new HashMap<>();
	    	headers.put("X-Naver-Client-Id", clientId);
	    	headers.put("X-Naver-Client-Secret", clientSecret);
	    	
	    	String apiResponse = get(apiURL, headers);
	    	List<BlogPost> posts = parseBlogPosts(apiResponse);
	    	
	    	model.addAttribute("blogPosts", posts);
	    }
	    catch (Exception e) {
	        model.addAttribute("error", "data load failed");
	    }
	    
	    return "page0/statistics";
	}
	
	//JSON parsing method
	private List<BlogPost> parseBlogPosts(String json) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(json);
		List<BlogPost> posts = new ArrayList<>();
		
		for (JsonNode item : rootNode.path("items")) {
            String title = item.path("title").asText()
                             .replaceAll("<b>|</b>", "") // 하이라이트 태그 제거
                             .replaceAll("&quot;", "\"")
                             .replaceAll("<.*?>", "")
                             .replaceAll("&amp;", "&");
            String link = item.path("link").asText();
            posts.add(new BlogPost(title, link));
        }
		
		System.out.println("Parsed Posts: " + posts); // 파싱 결과 출력
		
		return posts;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (Exception e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (Exception e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
	
}
