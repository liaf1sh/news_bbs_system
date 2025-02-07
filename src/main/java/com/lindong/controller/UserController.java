package com.lindong.controller;

import com.lindong.domain.Grade;
import com.lindong.domain.Integral;
import com.lindong.domain.User;
import com.lindong.exception.ApiResult;
import com.lindong.exception.CustomException;
import com.lindong.exception.ResultCode;
import com.lindong.service.IPostBrowseCommentService;
import com.lindong.service.IUserService;
import com.lindong.service.IUserSignService;
import com.lindong.utils.EmailUtil;
import com.lindong.utils.IPUtils;
import com.lindong.utils.VerifyCode;
import com.lindong.utils.shiro.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("bbs/user")
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private IUserSignService userSignService;
    @Resource
    private IPostBrowseCommentService postService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String imgCode = request.getParameter("imgCode");
        String vCode = (String) request.getSession().getAttribute("vImgCode");
        String last_login_ip = IPUtils.getIpAddr(request);
        System.out.println("===="+username+"==="+password+"==="+vCode+"======"+last_login_ip);
        //CodeMsg codeMsg = null;
        //验证码忽略大小写进行判断
        if (imgCode.equalsIgnoreCase(vCode)){
            Map<String,String> param = new HashMap<>();
            param.put("username",username);
            param.put("password",password);
            param.put("last_login_ip",last_login_ip);
            userService.login(param);
            //request.getSession().setAttribute("username",username);
        }else {
            throw new CustomException(ResultCode.VERIFY_ERROR);
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult register(@RequestBody(required = true) Map map, HttpServletRequest request){
        String imgCode = (String)map.get("imgCode");
        String vCode = (String) request.getSession().getAttribute("vImgCode");
        if (imgCode.equalsIgnoreCase(vCode)){
            User byNameUser = userService.findByName((String)map.get("username"));
            if (byNameUser != null){
                throw new CustomException(ResultCode.USER_EXIST);
            }
            String ip = IPUtils.getIpAddr(request);
            System.out.println(ip);
            String salt = MD5.getSalt();
            String passwordDB = MD5.getMd5String((String)map.get("password"),salt);
            map.put("password",passwordDB);
            map.put("salt",salt);
            map.put("register_ip",ip);
            userService.register(map);
        }else {
            throw new CustomException(ResultCode.VERIFY_ERROR);
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map getUserInfo(String username){
        User userInfo = userService.findByName(username);
        //获取用户积分数据
        Integral integral = userSignService.selectIntegral(userInfo.getId());
        //获取用户经验
        int experience = postService.getExperienceById(userInfo.getId());
        //获取用户相应等级
        Grade grade = postService.getGrade(experience);
        Map map = new HashMap();
        map.put("userInfo",userInfo);
        map.put("total_integral",integral.getTotal_integral());
        map.put("grade",grade);
        return map;
    }

    /*@RequestMapping(value = "/changeOnline",method = RequestMethod.POST)
    @ResponseBody
    public String changeOnline(@RequestBody User user){
        *//*System.out.println("打印======"+user.getId()+"--------"+user.getOnline_status());
        int count = userService.updateUser(user);
        if (count > 0) {
            System.out.println("用户在线状态 : " + user.getOnline_status());
        }*//*
        return "success";
    }*/


    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response){

        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        request.getSession().setAttribute("vImgCode", vc.getText());
        try {
            //System.out.println("======前台验证码......");
            VerifyCode.output(image, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @RequestMapping("/getGrade")
    @ResponseBody
    public Grade getGrade(@RequestParam("totalExperience") Integer totalExperience){
        Grade grade = postService.getGrade(totalExperience);
        return grade;
    }

    @RequestMapping(value = "/alterUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult alterUserInfo(@RequestBody Map map,HttpServletRequest request){
        String eCode = (String)map.get("emailCode");
        if (eCode != null){     //修改邮箱或忘记密码
            String emailCode = (String) request.getSession().getAttribute("emailCode");
            if (emailCode.equalsIgnoreCase(eCode)){  //验证邮箱
                String password = (String) map.get("password");
                if (password != null){
                    String salt = MD5.getSalt();
                    map.put("id",request.getSession().getAttribute("uid"));
                    map.put("salt",salt);
                    map.put("password",MD5.getMd5String(password,salt));
                }
                userService.updateUser(map);
            }else {
                throw new CustomException(ResultCode.VERIFY_ERROR);
            }
        }else {     //修改其他信息
            String oldPwd = (String)map.get("oldPwd");    //获取旧密码
            if (oldPwd != null){   //修改密码
                User user = userService.getPwdSalt((String)map.get("username"));
                String Pwd = MD5.getMd5String(oldPwd,user.getSalt());
                if (Pwd.equals(user.getPassword())){
                    map.put("id",user.getId());
                    map.put("password",MD5.getMd5String((String)map.get("password"),user.getSalt()));
                    userService.updateUser(map);
                    request.getSession().removeAttribute("emailCode");
                }else {
                    throw new CustomException(ResultCode.PASSWORD_FAILED);
                }
            }else {     //修改其他
                userService.updateUser(map);
            }
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public ApiResult sendEmail(HttpServletRequest request){
        String username = request.getParameter("username");
        String emailCode;
        if(username != null){
            User byName = userService.findByName(username);
            if (byName == null){
                throw new CustomException(ResultCode.USERNAME_ERROR);
            }
            emailCode = EmailUtil.sendEmail(byName.getEmail());
            request.getSession().setAttribute("uid",byName.getId());
        }else {
            emailCode = EmailUtil.sendEmail(request.getParameter("email"));  //发送邮箱验证码
        }
        request.getSession().setAttribute("emailCode",emailCode);
        return ApiResult.of(ResultCode.SUCCESS);
    }


}
