package com.lindong.controller;

import com.lindong.exception.ApiResult;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.utils.VerifyCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping(value="/loginAction", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult login(HttpServletRequest request){
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String imgCode = request.getParameter("captcha");
        String vCode = (String) request.getSession().getAttribute("vCode");
        if (imgCode.equalsIgnoreCase(vCode)){

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            try {
                subject.login(token);
                Session session=subject.getSession();
                session.setAttribute("subject", subject);
                if (!subject.hasRole("cuser") ){
                    throw new CustomException(ResultCode.USER_NO_POWER);
                }
                request.getSession().setAttribute("username",name);
            } catch (AuthenticationException e) {
                throw new CustomException(ResultCode.PASSWORD_ERROR);
            }
        }else {
            throw new CustomException(ResultCode.VERIFY_ERROR);
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response){

        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        request.getSession().setAttribute("vCode", vc.getText());
        try {
            VerifyCode.output(image, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }


}
