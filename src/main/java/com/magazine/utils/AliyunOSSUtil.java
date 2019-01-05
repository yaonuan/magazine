package com.magazine.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.magazine.config.OssConstantConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云 oss 文件类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-05
 */
@Component
public class AliyunOSSUtil {

    @Autowired
    OssConstantConfig constantConfig;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String upLoad(File file) {
        String endpoint = constantConfig.getEndpoint();
        String accessKeyId = constantConfig.getAccessKeyId();
        String accessKeySecret = constantConfig.getAccessKeySecret();
        String bucketName = constantConfig.getBucketName();
        String fileHost = constantConfig.getFileHost();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 设置日期
        String dataStr = format.format(new Date());
        if (file == null) {
            return null;
        }
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String fileUrl = fileHost + "/" + (dataStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            System.out.println(fileUrl);
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));

            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                logger.info("------OSS文件上传成功------" + fileUrl);
                return "https://"+ bucketName + "."+endpoint +"/"+fileUrl;
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return null;
    }

}
