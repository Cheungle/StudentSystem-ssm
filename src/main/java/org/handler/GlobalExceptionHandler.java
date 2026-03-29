package org.handler;

import lombok.extern.slf4j.Slf4j;
import org.common.Result;
import org.handler.exception.LuaException;
import org.handler.exception.PageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(RejectedExecutionException.class)
    public ResponseEntity<String> handleRejectedExecution(RejectedExecutionException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE) // 503
                .body("系统繁忙，请稍后重试");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("系统异常", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body("服务器内部错误");
    }
    @ExceptionHandler(PageException.class)
    public Result handlePageException(PageException e) {
        return Result.fail(e.getMessage());
    }
    @ExceptionHandler(LuaException.class)
    public Result handleLuaException(PageException e) {
        return Result.fail(e.getMessage());
    }
}
