package com.emailsender.app.controllers;

import com.emailsender.app.helper.CustomResponse;
import com.emailsender.app.requestdto.EmailRequest;
import com.emailsender.app.services.EmailService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService=emailService;
    }

    @PostMapping(path="/send",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse> sendEmail(@RequestBody EmailRequest request)
    {
        emailService.sendEmailWithHtml(request.getTo(),request.getSubject(),request.getMessage());
        CustomResponse response = CustomResponse.builder()
                .message("Email Sent Successfully !!")
                .httpStatus(HttpStatus.OK)
                .success(true)
                .build();
     return ResponseEntity.ok(response);

    }
    @PostMapping("/sendFile")
    public ResponseEntity<?> sendWithFile(@RequestPart EmailRequest request,@RequestPart MultipartFile file) throws IOException
    {
        emailService.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(),file.getInputStream());
        CustomResponse response = CustomResponse.builder()
                .message("Email Sent Successfully !!")
                .httpStatus(HttpStatus.OK)
                .success(true)
                .build();
        return ResponseEntity.ok(response);
    }

}
