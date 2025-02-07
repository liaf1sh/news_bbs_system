package com.lindong.utils;

import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.awt.image.BufferedImage;
import java.util.Random;

public class EmailUtil {

    private static HtmlEmail email;

    private static String text="abcdefghijklmnopqrstuvwxyz";

    static{
        //创建一个HtmlEmail实例对象
        email=new HtmlEmail();
        //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setHostName("smtp.qq.com");
        //设置发送的字符类型
        email.setCharset("utf-8");
    }

    public static String sendEmail(String emailUser){
        String code = verifyCode();
        try {
            //设置收件人
            email.addTo(emailUser);
            //发送人的邮箱为自己的，用户名可以随便填
            email.setFrom("2217963603@qq.com","MiShiZhe");
            //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setAuthentication("2217963603@qq.com","aumxqixfqbsueabf");
            //设置发送主题
            email.setSubject("邮箱验证码");
            //设置发送内容
            email.setMsg("<h2>修改信息的验证码为:"+"<span style='font-size:22px;color:blue;letter-spacing: 4px;'><u>"+code+"</u></span></h2>");
            //进行发送
            email.send();
        } catch (EmailException e) {
            throw new CustomException(ResultCode.SEND_FAIL);
        }
        //String str = "验证码已发送到您的邮箱,请登录邮箱查看!";
        return code;
    }

    public static String verifyCode(){

        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(text.charAt(random.nextInt(text.length())));
        }
        return sb.toString();

    }

    public static void main(String[] args) {

        //String message = sendEmail("1653816237@qq.com");
        //System.out.println(sb.toString());
    }


}
