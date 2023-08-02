package pl.com.platon.stepenamel.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.platon.stepenamel.dto.UserDataDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static pl.com.platon.stepenamel.util.TemplateData.beforeEmail;
import static pl.com.platon.stepenamel.util.TemplateData.beforeMobile;
import static pl.com.platon.stepenamel.util.TemplateData.beforePhone;
import static pl.com.platon.stepenamel.util.TemplateData.end;
import static pl.com.platon.stepenamel.util.TemplateData.header;

@RestController
public class ResultController {

    @PostMapping("/generate")
    public ResponseEntity generateFile(@Valid UserDataDto userDataDto) throws IOException {
        String result = header + userDataDto.getPerson()
                + beforePhone + userDataDto.getPhone()
                + beforeMobile + userDataDto.getMobilePhone()
                + beforeEmail + userDataDto.getEmail()
                + end;


        File tmpFile = File.createTempFile("test", ".tmp");
        FileWriter writer = new FileWriter(tmpFile);
        writer.write(result);
        writer.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "text/txt")
                .body(tmpFile);
    }
}
