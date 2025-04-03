package data.controller.medicalmap;

import com.fasterxml.jackson.databind.JsonNode;
import data.service.MedicalApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MedicalMapController {

    private final MedicalApiService medicalApiServicee;

    public MedicalMapController(MedicalApiService medicalApiServicee) {
        this.medicalApiServicee = medicalApiServicee;
    }

    // 페이지 이동 (맵 보여주는 화면)
    @GetMapping("/medicalmap")
    public String medicalmap() {
        return "page2/medicalmap";  // templates/page2/medicalmap.html 또는 medicalmap.jsp 있어야 함
    }

}