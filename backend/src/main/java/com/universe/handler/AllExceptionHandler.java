package com.universe.handler;


import com.universe.marketing.product.pojo.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//对加了@Controller 注解的方法进行拦截处理 AOP实现
@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理，处理Exception。class异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail("系统内部异常！");
    }
}
