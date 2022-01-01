package com.cailei.mail.portal.advice;

import com.cailei.base.result.ResultWarpper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.ResponseWrapper;

/**
 * 统一异常处理
 */


@ControllerAdvice
public class ValidateHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : ex.getFieldErrors()) {

           sb.append(fieldError.getDefaultMessage());
        }


        return new ResponseEntity(ResultWarpper.builder().code(110).msg(sb.toString()).build(),HttpStatus.OK);
    }
}
