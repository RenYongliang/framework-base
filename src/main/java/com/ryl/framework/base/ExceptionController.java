package com.ryl.framework.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultModel handleException(HttpServletResponse resp, Exception e) {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        log.error(String.format("handleException(HttpServletRequest, HttpServletResponse, Exception) - %s", e.getMessage()), e);
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            return ResultModel.fail(se.getErrorCode(),se.getErrorMessage());
        } else {
            //未捕捉的内部服务异常
            String name = Thread.currentThread().getName();
            log.debug(String.format("当前线程: %s,异常具体类容为：", name, e.getMessage()));
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                String threadName = stackTraceElement.getClass().getName();
                log.debug(String.format("具体异常跟踪:%s", threadName));
            }
            log.warn(String.format("uncaught internal server exception, msg: %s", e.getMessage()), e);
            return ResultModel.fail(ResultStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

