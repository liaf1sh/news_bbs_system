package com.lindong.controller;


import com.lindong.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("bbs/file")
public class FileUploadController {

    @Resource
    private IUserService userService;

    //单文件上传
    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    @ResponseBody
    public Map upload(MultipartFile file, HttpServletRequest request) throws IOException {

        //初始化时间并格式化,用于做上传文件的名字
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());*/

        // uploads文件夹位置
        String rootPath = request.getSession().getServletContext().getRealPath("uploadfiles/");
        // 原始名称
        String originalFileName = file.getOriginalFilename();
        //uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        // 文件名后缀(.*)
        String newFileName = uuid + originalFileName.substring(originalFileName.lastIndexOf("."));
        // 创建年月文件夹
        Calendar date = Calendar.getInstance();
        // File.separator 保证了在任何系统下不会出错,尽量加上,也考虑跨平台抛异常
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
        System.out.println("datedirs:" + dateDirs);
        // 新文件
        File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
        System.out.println("newFile:" + newFile);
        // 判断目标文件所在目录是否存在
        if (!newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        // 将内存中的数据写入磁盘
        file.transferTo(newFile);
        // 完整的url
        String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
        System.out.println("==============fileUrl:" + fileUrl);
        Map map = new HashMap();
        String id = request.getParameter("id");
        map.put("picture","uploadfiles/"+fileUrl);
        if (id != null){
            map.put("id",id);
            userService.updateUser(map);
        }
        map.put("code",0);
        map.put("msg","");
        return map;
    }


    /**
     * 多图片上传
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam(value = "files", required = false) List<MultipartFile> files,
                                           HttpServletRequest request) {
        System.out.println(">>>>>" + files);
        Map<String, Object> result = new HashMap<>();
        String imgUrls[] = new String[files.size()];
        // 文件存放的路径
        String filePath = request.getSession().getServletContext().getRealPath("uploadfiles/");
        System.out.println(filePath);
        if (files != null && files.size() > 0) {
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                // 原始名称
                String originalFileName = file.getOriginalFilename();
                //uuid
                String uuid = UUID.randomUUID().toString().replace("-","");
                // 文件名后缀(.*)
                String newFileName = uuid + originalFileName.substring(originalFileName.lastIndexOf("."));
                // 创建年月文件夹
                Calendar date = Calendar.getInstance();
                // File.separator 保证了在任何系统下不会出错,尽量加上,也考虑跨平台抛异常
                File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
                System.out.println("datedirs:" + dateDirs);
                // 新文件
                File newFile = new File(filePath + File.separator + dateDirs + File.separator + newFileName);
                System.out.println("newFile:" + newFile);
                // 判断目标文件所在目录是否存在
                if (!newFile.getParentFile().exists()) {
                    // 如果目标文件所在的目录不存在，则创建父目录
                    newFile.getParentFile().mkdirs();
                }
                System.out.println(newFile);
                // 将内存中的数据写入磁盘
                try {
                    file.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 完整的url
                String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
                System.out.println("==============fileUrl:" + fileUrl);
                imgUrls[i] = fileUrl;
            }

        }
        result.put("errno", 0);
        result.put("data", imgUrls);
        return result;
    }


}
