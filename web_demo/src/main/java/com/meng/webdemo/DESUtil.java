package com.meng.webdemo;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES类:加密
 */
public class DESUtil {

    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    public static final String DES = "DES";
    /**
     * 使用DES加密，返回加密后的结果
     */
    public static String enCryptSql(String originalText, String salt){
        String afterCryptSql;
        try {
            DESKeySpec desKeySpec = new DESKeySpec(salt.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] enCryptSqlStr = cipher.doFinal(originalText.getBytes());
            afterCryptSql = Base64.encodeBase64String(enCryptSqlStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return afterCryptSql;
    }

    /**
     * 前端CryptoJS 使用DES加密过得字符串，拿到这里进行解密
     */
    public static String desCryptSql(String cryptSql, String salt){
        String afterCryptSql;
        try {

            DESKeySpec desKeySpec = new DESKeySpec(salt.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            afterCryptSql = new String(cipher.doFinal(Base64.decodeBase64(cryptSql)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return afterCryptSql;
    }
}