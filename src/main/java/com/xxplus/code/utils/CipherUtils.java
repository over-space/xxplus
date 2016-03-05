package com.xxplus.code.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 密码工具类
 * Created by Li.fang on 2014/11/13.
 */
public class CipherUtils {

    private static Logger logger = LoggerFactory.getLogger(CipherUtils.class);

    //指定DES加密解密所用密钥
    private static Key key;

    private static String STR_SALT = "APLUS OFFICE SYSTEM";

    private static final String GENERATOR_DES = "DES";

    private static final String ENCODING_UTF8 = "UTF-8";

    private static final int CIPHER_COUNT = 5;

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(GENERATOR_DES);
            generator.init(new SecureRandom(STR_SALT.getBytes()));
            key = generator.generateKey();
            generator = null;
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Cryptographic failure");
            throw new RuntimeException(e);
        }
    }

    /**
     * 对明文进行加密
     * 加密规则为对应明文多次MD5加密 + 时间戳MD5,形成64位
     *
     * @param plaintext
     * @return
     */
    public static String getTime64MD5(@NotBlank String plaintext) {
        String password = new Md5Hash(plaintext, STR_SALT, CIPHER_COUNT).toString();
        String timestamp = new Md5Hash(String.valueOf(System.currentTimeMillis())).toString();
        return new StringBuffer(timestamp).append(password).toString();
    }


    /**
     * 比较两个MD5是否相等(只需要比较后32未)
     *
     * @param sourcesMD5 数据库中密码
     * @param targetMD5  输入的密码
     * @return
     */
    public synchronized static boolean isMD5Equal(@NotBlank String sourcesMD5, @NotBlank String targetMD5) {
        if (sourcesMD5 == null || targetMD5 == null) {
            return false;
        } else if (targetMD5.length() != 64) {
            return false;
        }
        String password = String.valueOf(targetMD5).substring(33);
        if (String.valueOf(sourcesMD5).endsWith(password)) {
            return true;
        }
        return false;
    }

    /**
     * 对字符串进行DES加密,返回BASE64编码的加密字符串
     *
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String getEncryptBase64String(String str) {
//        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] strBytes = str.getBytes(ENCODING_UTF8);
            Cipher cipher = Cipher.getInstance(GENERATOR_DES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return Base64.encodeBase64String(encryptStrBytes);
        } catch (Exception e) {
            logger.warn("Cryptographic failure");
            throw new RuntimeException(e);
        }
    }

    /**
     * 对BASE64编码的加密字符串进行解密,返回解密后的字符串
     *
     * @param str BASE64加密字符串
     * @return 解密后的字符串
     */
    public static String getDecryptString(String str) {
//        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] strBytes = Base64.decodeBase64(str);
            Cipher cipher = Cipher.getInstance(GENERATOR_DES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes, ENCODING_UTF8);
        } catch (Exception e) {
            logger.warn("Decryption failure");
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试加密
     *
     * @param args
     */
    public static void main(String[] args) {

        //DES加密
        String[] decryptStr = {"root", "123456", "jdbc:mysql://127.0.0.1/db_office?useUnicode=true&characterEncoding=utf-8"};
        String[] encryptStr = new String[3];
        for (int i = 0; i < decryptStr.length; i++) {
            encryptStr[i] = getEncryptBase64String(decryptStr[i]);
            logger.info("{} : {}", decryptStr[i], getEncryptBase64String(decryptStr[i]));
        }
        for (int i = 0; i < encryptStr.length; i++) {
            logger.info("{} : {}", getDecryptString(encryptStr[i]), encryptStr[i]);
        }
        logger.info("time millis : {}", System.currentTimeMillis());

        //MD5加密
        String strPassword = "admin";
        String strPassword2 = "123456";
        String strPassword3 = "123456";
        String p1 = getTime64MD5(strPassword);
        String p2 = getTime64MD5(strPassword2);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            //ignore
        }
        String p3 = getTime64MD5(strPassword3);
        logger.info("password1: {}", String.valueOf(p1));
        logger.info("password2: {}", String.valueOf(p2));
        logger.info("password3: {}", String.valueOf(p3));

        logger.info("p1 == p2 : {}", isMD5Equal(p1, p2));
        logger.info("p2 == p3 : {}", isMD5Equal(p2, p3));
    }

}
