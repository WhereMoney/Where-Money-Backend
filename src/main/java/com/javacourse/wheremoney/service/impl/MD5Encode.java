package com.javacourse.wheremoney.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encode {
    public static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte value : b) {
                i = value;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return plainText;
    }
}
