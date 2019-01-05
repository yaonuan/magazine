package com.magazine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss常量
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-05
 */
@Component
@Configuration
public class OssConstantConfig {

    /**
     * oss地域节点
     */
    @Value("${ossclient.endpoint}")
    private String endpoint;

    /**
     * 阿里云 云账号id
     */
    @Value("${ossclient.accessKeyId}")
    private String accessKeyId;

    /**
     * 阿里云 云账号访问密钥
     */
    @Value("${ossclient.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 对象存储名称
     */
    @Value("${ossclient.bucketName}")
    private String bucketName;

    @Value("${ossclient.fileHost}")
    private String fileHost;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileHost() {
        return fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }
}
