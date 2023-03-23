package com.bsk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 */
public class MD5Utils {

    /**
     * 加密
     * @param msg 原始字符串
     * @return 加密的字符串
     */
    public static String encrypt(String msg){
        try {
            //创建Md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            //将原始字符串转换为字节数组
            byte[] bytes = msg.getBytes();
            //将字节数组进行加密
            md.update(bytes);
            //获得加密后的结果
            byte[] newBytes = md.digest();
            //将每个字节转换为16进制的文字，拼接起来
            StringBuilder strb = new StringBuilder();
            for(byte b : newBytes){
                strb.append(Integer.toHexString(b >> 4 & 0xf));
                strb.append(Integer.toHexString(b & 0xf));
            }
            return strb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
