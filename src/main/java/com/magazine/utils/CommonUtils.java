package com.magazine.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 常用工具类的封装
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018/11/29 0029
 */
public class CommonUtils {

    /**
     * 生成uuid
     * @return
     */
    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString()
                .replaceAll("-", "").substring(0,32 );
        return uuid;
    }

    /**
     * MD5 常用工具类
     * @param data
     * @return
     */
    public static String MD5(String data){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 保存图片
     * @param request
     * @param pictureFile
     * @return
     * @throws IOException
     */
    public static String savePic(HttpServletRequest request, MultipartFile pictureFile) throws IOException {

        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件的扩展名
        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        //设置图片上传路径
        String url = request.getSession().getServletContext().getRealPath("/upload");
        //以绝对路径保存重名命后的图片
        pictureFile.transferTo(new File(url+"/"+name+"."+ext));

        //返回图片存储路径
        return "upload/"+name+"."+ext;
    }

}
