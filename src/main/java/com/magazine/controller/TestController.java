package com.magazine.controller;

import com.magazine.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 测试类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-05
 */
@RestController
public class TestController {


    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @RequestMapping("/test")
    public String saveFile(@RequestParam("file") MultipartFile file) {
        String uploadUrl = "";
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {
            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }

}
