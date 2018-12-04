package com.app.microyang.utils;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by chenyu on 2017/11/26.
 */

public class RSAUtil {

    private static String publicKeyString =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDmtHtb9+mkanNjspKcmekvSh3fX9D+6wVKbnyz" +
                    "ntYba5deVcPnhJDwpnuIOfBlHwLKriR4Td8zelEqZGNyDL/Ez8fkQlq9NOfB+wzYQ3AZoZo7iEHeU" +
                    "nkg+PU+9seAl7oVsqIJWw8WHmedJhHbnrlBefYqgbhP70izL7TnTTr3QehQIDAQAB";

    private static String privateKeyString =
            "";

    private void refreshKey() {

    }

    public static String base64Encrypted(String data) {
        byte[] encryptedBytes = new byte[0];
        try {
            PublicKey publicKey = getPublicKey(publicKeyString);
            encryptedBytes = encrypted(data.getBytes(), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(encryptedBytes, Base64.NO_WRAP);
    }

    private static PublicKey getPublicKey(String publicKeyString) throws Exception {
        byte[] keyBytes = Base64.decode(publicKeyString.getBytes(), Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey.getBytes(), Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    private static byte[] encrypted(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    private static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static void test(String data) throws Exception {

        PublicKey publicKey = getPublicKey(publicKeyString);
        System.out.println(publicKey.toString());

        byte[] encryptedBytes = encrypted(data.getBytes(), publicKey);
        System.out.println("加密后：" + new String(encryptedBytes));
        byte[] encryptedBase64Bytes = Base64.encode(encryptedBytes, Base64.NO_WRAP);
        System.out.println("加密后Base64编码：" + new String(encryptedBase64Bytes));
        PrivateKey privateKey = getPrivateKey(privateKeyString);
        byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
        System.out.println("解密后：" + new String(decryptedBytes));
    }
}
