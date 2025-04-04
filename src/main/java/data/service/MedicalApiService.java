package data.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class MedicalApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public JsonNode getMedicalData() {
        String encodedKey = URLEncoder.encode("17Ds6cxIqo9+znsM81nIgrGHeWGzUlZBHQ9s9GSWEFqc1McvZaH2ovzWYKxVdi8AopGjXaYCZWcLCX6VcqWd/Q==", StandardCharsets.UTF_8);
        System.out.println(">>>>>"+encodedKey);
        String url = "http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire"
                + "?serviceKey=" + encodedKey  // 여기에 본인의 API 키 입력
                + "&STAGE1=서울특별시&STAGE2=강남구"
                + "&pageNo=1&numOfRows=10";


        // API에서 XML 데이터 받아오기
        String xmlResponse = restTemplate.getForObject(url, String.class);

        try {
            // XML을 JSON으로 변환
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readTree(xmlResponse);
        } catch (Exception e) {
            throw new RuntimeException("XML을 JSON으로 변환하는 중 오류 발생", e);
        }
    }

}