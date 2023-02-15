package com.samsung.sodam.util;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ciperUtil {
    public static String encryptSHA256(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuffer encryptData = new StringBuffer();
        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        sha.update(value.getBytes("utf-8"));

        byte[] digest = sha.digest();
        String result = DatatypeConverter.printHexBinary(digest);
        return result.substring(0, 32);
    }
    public static String makeSHA(String inputText) throws NoSuchAlgorithmException{
        String test = inputText;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(test.getBytes());
        byte[] digest = md.digest();
        String result = DatatypeConverter.printHexBinary(digest);
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String key = "mykey";
        String realKey1 = ciperUtil.encryptSHA256(key);
        String realKey2 = ciperUtil.makeSHA(key);


        System.out.println("encryptSHA256 "+realKey1);
        System.out.println("makeSHA "+realKey2);

        byte[] decodedBytes = Base64.getEncoder().encode(realKey1.getBytes("utf-8"));

        String decryptionKey = new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println(decryptionKey);
    }
}
