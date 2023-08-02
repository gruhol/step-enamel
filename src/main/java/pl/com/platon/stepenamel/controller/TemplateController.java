package pl.com.platon.stepenamel.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.com.platon.stepenamel.dto.UserDataDto;

@Controller
public class TemplateController {

    @GetMapping("/form")
    public String generateForm(Model model) {
        model.addAttribute("UserDataDto", new UserDataDto());
        return "form";
    }

    // Only view html - not used
    public ModelAndView generateTemplate(@Valid UserDataDto userDataDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UserDataDto data = new UserDataDto();
        data.setEmail(userDataDto.getEmail());
        data.setPerson(userDataDto.getPerson());
        data.setPhone(userDataDto.getPhone());
        data.setMobilePhone(userDataDto.getMobilePhone());
        modelAndView.addObject("userDataDto", data);
        modelAndView.setViewName("theme");
        return modelAndView;
    }
}
