package ru.rencredit.test.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ExceptionController {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionView systemExceptionHandler(Exception exception){
        log.error("Не удалось выполнить действие. Обратитесь к разработчикам.", exception);
        return new ExceptionView(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    protected ExceptionView userExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new ExceptionView(e.getMessage());
    }


}
