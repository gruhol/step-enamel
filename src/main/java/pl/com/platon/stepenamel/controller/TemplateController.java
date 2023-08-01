package pl.com.platon.stepenamel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/form")
    public String generateTemplate(Model model) {
        return "form";
    }
}
