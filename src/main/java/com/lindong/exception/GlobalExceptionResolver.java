package com.lindong.exception;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局Controller层异常处理类
 * 通过 @ControllerAdvice 指定该类为 Controller 增强类。
 * 通过 @ExceptionHandler 自定捕获的异常类型。
 * 通过 @ResponseBody 返回 json 到前端。
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    //private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult handleException(Exception e) {
        //打印异常
        e.printStackTrace();
        // 打印异常堆栈信息
        //LOG.error(e.getMessage(), e);
        return ApiResult.of(ResultCode.UNKNOWN_ERROR);
    }

    /**
     * 处理所有业务异常
     *
     * @param e 业务异常
     * @return json结果
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ApiResult handleOpdRuntimeException(CustomException e) {
        // 不打印异常堆栈信息
        //LOG.error(e.getMsg());
        return ApiResult.of(e.getResultCode());
    }

    /**
     * 角色权限异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResult processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        /*ModelAndView mv = new ModelAndView();
        mv.addObject("ex", e);
        mv.setViewName("unauthorized");
        return "forward:/WEB-INF/admin/403.html";*/
        return ApiResult.of(ResultCode.AUTHORITY_ERROR);
    }


}

