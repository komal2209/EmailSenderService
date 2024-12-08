package com.emailsender.app.helper;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomResponse {
    private String message;
    private HttpStatus httpStatus;
    private boolean success=true;


}
