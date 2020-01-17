package com.github.hygoni.dormitory.advice;

import com.github.hygoni.dormitory.advice.exception.DuplicateObjectException;
import com.github.hygoni.dormitory.advice.exception.LoginException;
import com.github.hygoni.dormitory.advice.exception.UserNotFoundException;
import com.github.hygoni.dormitory.advice.exception.WasherNotFoundException;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.service.MessageService;
import com.github.hygoni.dormitory.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionManager {
    private final ResponseService responseService;
    private final MessageService messageService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(Integer.parseInt(getMessage("unknown.code")), getMessage("unknown.msg") + " : " + e.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult loginException(HttpServletRequest request) {
        return responseService.getFailResult(Integer.parseInt(getMessage("loginException.code")), getMessage("loginException.msg"));
    }

    @ExceptionHandler(WasherNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult washerNotFoundException(HttpServletRequest request) {
        return responseService.getFailResult(Integer.parseInt(getMessage("washerNotFoundException.code")), getMessage("washerNotFoundException.msg"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userNotFoundException(HttpServletRequest request) {
        return responseService.getFailResult(Integer.parseInt(getMessage("userNotFoundException.code")), getMessage("userNotFoundException.msg"));
    }

    @ExceptionHandler(DuplicateObjectException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult duplicateObjectException(HttpServletRequest request) {
        return responseService.getFailResult(Integer.parseInt(getMessage("duplicateObjectException.code")), getMessage("duplicateObjectException.msg"));
    }

    private String getMessage(String code){
        return messageService.getMessage(code);
    }

}
