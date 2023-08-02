package pl.com.platon.stepenamel.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.com.platon.stepenamel.dto.UserDataDto;

@RestController
public class FileController {

    //@PostMapping("/generate")
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
