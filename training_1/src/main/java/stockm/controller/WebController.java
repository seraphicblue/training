package stockm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value = "/**/{path:[^\\.]*}")
    public String redirect() {
        // React 빌드의 index.html로 포워드
        return "forward:/";
    }
}