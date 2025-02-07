package com.lindong.utils.shiro;


import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Random;

public class MD5 {

    //加密盐
    private static final String salt = "ahJieHfGrlVwEf==";
    //加盐次数
    private static final int hashIteration = 24;


    /**
     * 根据输入的密码以及最终盐加密
     * @param password
     * @return
     */
    public static String getMd5String(String password,String saltDB){
        //调用shiro提供的MD5加密算法
        String md5String= new SimpleHash("md5",password,saltDB,hashIteration).toString();
        return md5String;
    }

    /**
     * 获取最终加密盐
     * @return
     */
    public static String getSalt(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(salt.charAt(random.nextInt(salt.length())));
        }
        sb.append("==");
        String saltDB = sb.toString();
        return saltDB;
    }

    public static void main(String[] args) {
        System.out.println(getMd5String("12345","HlfliarEhJ=="));
    }
}
