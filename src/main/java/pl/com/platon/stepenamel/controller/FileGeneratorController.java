package pl.com.platon.stepenamel.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.platon.stepenamel.dto.UserDataDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static pl.com.platon.stepenamel.util.TemplateData.beforeEmail;
import static pl.com.platon.stepenamel.util.TemplateData.beforeMobile;
import static pl.com.platon.stepenamel.util.TemplateData.beforePerson;
import static pl.com.platon.stepenamel.util.TemplateData.beforePhone;
import static pl.com.platon.stepenamel.util.TemplateData.end;
import static pl.com.platon.stepenamel.util.TemplateData.header;

@RestController
public class FileGeneratorController {

    @RequestMapping(method = RequestMethod.POST, value = "/generate")
    public ResponseEntity<byte[]> getHTMLFile(@Valid UserDataDto userDataDto) throws IOException {
        String str = header + userDataDto.getPerson() +
                beforePerson + userDataDto.getPerson() +
                beforePhone + userDataDto.getPhone() +
                beforeMobile + userDataDto.getMobilePhone() +
                beforeEmail + userDataDto.getEmail() + end;

        OutputStream out = new ByteArrayOutputStream();
        out.write(str.getBytes());

        final byte[] file = str.getBytes(StandardCharsets.UTF_8);
        out.close();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/html"));
        headers.setContentDispositionFormData("attachment", null);
        headers.setCacheControl("no-cache");

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }
}
