package com.schoolyard.common.util;

/** 
* @author yang123he123
*  描述：3DES加密
*/ 


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;



/**
 * @author yang123he123
 *
 */
public class EncryptUtil {
	// 密钥
    private final static String secretKey = "w3sflink@0123x100$#365#$";
    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";
    
    /**
     * 3DES加密
     * 
     * @param plainText 普通文本
     * @return
     * @throws Exception 
     */
    public static String encode(String plainText) throws Exception {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
            return Base64.encode(encryptData);
    }

    /**
     * 3DES解密
     * 
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

            return new String(decryptData, encoding);
    }
    
    public static void main(String[] args){
    	try {
			System.out.println("***************");
			String testPwd = "jinganoa";
			System.out.println("明文是:" + testPwd);
			String encryptedPwd = EncryptUtil.encode(testPwd);
			System.out.println("密文是:" + encryptedPwd);
			String result = EncryptUtil.decode(encryptedPwd);
			System.out.println("解密后:" + result);
			System.out.println("***************");
		} catch (Exception e) {
			System.out.println("***3DES加密ERROR！！***");
			e.printStackTrace();
		}
    }
}
