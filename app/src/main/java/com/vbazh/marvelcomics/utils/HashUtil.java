package com.vbazh.marvelcomics.utils;

import com.vbazh.marvelcomics.BuildConfig;


public class HashUtil {

    private String ts, hash;

    public HashUtil(){
        ts = String.valueOf(System.currentTimeMillis());
        generateHash();
    }

    private void generateHash() {

        hash = ts + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY;

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(hash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            hash =  sb.toString();
        } catch (java.security.NoSuchAlgorithmException ignored) {
        }
    }

    public String getTs(){
        return ts;
    }

    public String getHash(){
        return hash;
    }

}
