package data.controller.dashboard;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import groovy.util.logging.Slf4j;

// [ 뉴스 정보 출력 ]
@Slf4j
@RestController
public class DashboardController {

	@GetMapping("/dashboard")
	public String dashboard() {
		
		String query = "코로나";
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
        String encode = StandardCharsets.UTF_8.decode(buffer).toString();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/news.json")
                .queryParam("query",encode)
                .queryParam("display",10) // 결과 10개 제한
                .queryParam("start",1) 	// 1번부터 시작
                .queryParam("sort", "date") // 결과 정렬 방식
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        // 아래는 헤더를 넣기 위함
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "zs_Ia_nWaMfm98XeX2oX")
                .header("X-Naver-Client-Secret","Y3h8tQGf_u")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }
}
