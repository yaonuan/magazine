package com.magazine.schedule;

import com.magazine.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-05-05
 */
@Service
public class LockNxExJob {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisTemplate redisTemplate;

    private final RedisService redisService;

    private static String LOCK_PREFIX = "prefix_";

    private static int LOCK_EXPIRE = 100;

    @Autowired
    public LockNxExJob(RedisTemplate redisTemplate, RedisService redisService) {
        this.redisTemplate = redisTemplate;
        this.redisService = redisService;
    }

    @Scheduled(cron = "1/10 * * * * *")
    public void lock(){
        String lock  = LOCK_PREFIX + "LockNxExJob";
        boolean nxRet = false;
        try {
            // redisTemplate setnx 操作
            nxRet = redisTemplate.opsForValue().setIfAbsent(lock,getHostIp() );
            Object lockValue = redisService.genValue(lock);
            // 获取锁失败
            if (!nxRet){
                String value = (String)redisService.get(lock);
                logger.info("get lock fail,lock belong to :{}",value);
                return;
            }else {
                redisTemplate.opsForValue().set(lock,getHostIp() , 8000);
                // 获取锁成功
                logger.info("start lock lockNxExJob success");
                Thread.sleep(5000);
            }
        }catch (Exception e){
            logger.error("lock error",e);
        }finally {
            if (nxRet){
                logger.info("release lock success");
                redisService.remove(lock);
            }
        }
//        System.out.println("enter job "+ System.currentTimeMillis());
    }

    /**
     * 获取本机内网IP地址方法
     * @return
     */
    private static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String localIP = "";
        try {
            localIP = getHostIp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取本机IP
        System.out.println(localIP);
    }


}
