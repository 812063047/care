package com.xzq.serviceBase.handler;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceBase.Exception.CareException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @Description: 全局异常
     * @Author xuzhiqiang
     * @Date 2021/1/20 17:43
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultMessage error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultMessage.error().message("执行全局异常处理。。");
    }

    /**
     * @Description:  特定异常
     * @Author xuzhiqiang
     * @Date 2021/1/20 17:43
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public  ResultMessage error(ArithmeticException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return  ResultMessage.error().message("执行了特定异常");
    }



    /**
     * @Description:  自定义异常
     * @Author xuzhiqiang
     * @Date 2021/1/20 17:43
     */
    @ExceptionHandler(CareException.class)
    @ResponseBody
    public  ResultMessage error(CareException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return  ResultMessage.error().message(e.getMsg()).code(e.getCode());
    }
}
