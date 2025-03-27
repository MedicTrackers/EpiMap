package data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * test 파일입니다.
 * 기본 data.service로 시작하게 패키지를 구성하면 됩니다.
 * 그리고 안에 패키지 및 파일명 구성
 */
@Controller
public class TestController {
	@RequestMapping("/")
	public String hello() {
		return "test";
	}
}
