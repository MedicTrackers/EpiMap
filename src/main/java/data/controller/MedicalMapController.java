package data.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.service.MedicalApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import reactor.core.publisher.Mono;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringJoiner;

@Controller
public class MedicalMapController {

    private final MedicalApiService medicalApiServicee;

    public MedicalMapController(MedicalApiService medicalApiServicee) {
        this.medicalApiServicee = medicalApiServicee;
    }

    // JSON 응답 (의료기관 데이터)
    @GetMapping("/data")
    @ResponseBody
    public JsonNode getMedicalData() {
        return medicalApiServicee.getMedicalData();
    }

    // 페이지 이동 (맵 보여주는 화면)
    @GetMapping("/medicalmap")
    public String medicalmap() {
        return "page2/medicalmap";  // templates/page2/medicalmap.html 또는 medicalmap.jsp 있어야 함
    }

    // 테스트-
    @GetMapping("/test")
    public String test() {
        return "page2/test";

    }


//    /*@GetMapping("/hospital/nearby")
//    @ResponseBody
//    public String getNearbyHospitals() {
//        StringBuilder result = new StringBuilder();
//
//        try {
//            String serviceKey = "17Ds6cxIqo9%2BznsM81nIgrGHeWGzUlZBHQ9s9GSWEFqc1McvZaH2ovzWYKxVdi8AopGjXaYCZWcLCX6VcqWd%2FQ%3D%3D";
//            String lon = "126.96704997103927";  // 경도
//            String lat = "37.56740267694248";  // 위도
//
//            String apiUrl = "https://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytLcinfoInqire"
//                    + "?serviceKey=" + serviceKey
//                    + "&WGS84_LON=" + lon
//                    + "&WGS84_LAT=" + lat
//                    + "&pageNo=1"
//                    + "&numOfRows=10";
//
//            URL url = new URL(apiUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(conn.getInputStream());
//            doc.getDocumentElement().normalize();
//
//            NodeList itemList = doc.getElementsByTagName("item");
//
//            if (itemList.getLength() == 0) {
//                return "❌ 주변 응급의료기관이 없습니다.";
//            }
//
//            result.append("📍 주변 응급의료기관 목록\n\n");
//
//            for (int i = 0; i < itemList.getLength(); i++) {
//                Element e = (Element) itemList.item(i);
//
//                result.append("🏥 병원명: ").append(getTagValue("dutyName", e)).append("\n");
//                result.append("📞 전화: ").append(getTagValue("dutyTel3", e)).append("\n");
//                result.append("📌 주소: ").append(getTagValue("dutyAddr", e)).append("\n");
//                result.append("📌 위도: ").append(getTagValue("wgs84Lat", e)).append("\n");
//                result.append("📌 경도: ").append(getTagValue("wgs84Lon", e)).append("\n\n");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "API 호출 중 오류 발생: " + e.getMessage();
//        }
//
//        return result.toString();
//    }
//
//    private String getTagValue(String tag, Element e) {
//        NodeList nl = e.getElementsByTagName(tag);
//        if (nl.getLength() > 0 && nl.item(0).getFirstChild() != null) {
//            return nl.item(0).getFirstChild().getNodeValue();
//        }
//        return "";
//    }*/




}
