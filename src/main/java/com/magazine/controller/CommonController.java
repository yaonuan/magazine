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
 * 公共类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-08
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @RequestMapping("/save_file")
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
