package data.controller.dashboard;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        
        String query = "코로나";
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
        String encode = StandardCharsets.UTF_8.decode(buffer).toString();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/news.json")
                .queryParam("query", encode)
                .queryParam("display", 10) // 결과 10개 제한
                .queryParam("start", 1)    // 1번부터 시작
                .queryParam("sort", "date") // 결과 정렬 방식
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "zs_Ia_nWaMfm98XeX2oX")
                .header("X-Naver-Client-Secret", "Y3h8tQGf_u")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        // JSON 문자열을 JSONObject로 변환
        JSONObject jsonResponse = new JSONObject(result.getBody());

        // "items" 배열을 가져와서 모델에 추가
        JSONArray newsItems = jsonResponse.getJSONArray("items");
        List<Map<String, Object>> list = new ArrayList<>();
        
        for(int i=0; i<newsItems.length();i++) {
        	JSONObject item = newsItems.getJSONObject(i);
        	 Map<String, Object> newsMap = new HashMap<>();
             newsMap.put("title", item.getString("title"));
             newsMap.put("originallink", item.getString("originallink"));
             newsMap.put("link", item.getString("link"));
             newsMap.put("description", item.getString("description"));
             newsMap.put("pubDate", item.getString("pubDate"));
             list.add(newsMap);
        }
        model.addAttribute("newsData", list);

        
        return "page1/dashboard";
    }
    
    
    
    // [ 뉴스 데이터 출력을 위한 테스트 코드 ]
//    @GetMapping("/testjson")
//    @ResponseBody
//    public String test() {
//    	String query = "코로나";
//        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
//        String encode = StandardCharsets.UTF_8.decode(buffer).toString();
//
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://openapi.naver.com")
//                .path("/v1/search/news.json")
//                .queryParam("query", encode)
//                .queryParam("display", 10) // 결과 10개 제한
//                .queryParam("start", 1)    // 1번부터 시작
//                .queryParam("sort", "date") // 결과 정렬 방식
//                .encode()
//                .build()
//                .toUri();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        RequestEntity<Void> req = RequestEntity
//                .get(uri)
//                .header("X-Naver-Client-Id", "zs_Ia_nWaMfm98XeX2oX")
//                .header("X-Naver-Client-Secret", "Y3h8tQGf_u")
//                .build();
//
//        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
//
//        // JSON 문자열을 JSONObject로 변환
//        JSONObject jsonResponse = new JSONObject(result.getBody());
//
//        // "items" 배열을 가져와서 모델에 추가
//        JSONArray newsItems = jsonResponse.getJSONArray("items");
//        
//        return result.getBody();
//    }
}
