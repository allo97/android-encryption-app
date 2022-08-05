package com.example.encryptionapp.services;


public class DataService {
    public static final String[] AES_MODES = {"ECB", "CBC", "CFB", "OFB", "CTR"};

    public static final String[] AES_ALGORITHMS = {
            "AES/ECB/PKCS5Padding",
            "AES/CBC/PKCS5Padding",
            "AES/CFB/PKCS5Padding",
            "AES/OFB/PKCS5Padding",
            "AES/CTR/PKCS5Padding"
    };

    public enum CipherMode {
        AES,
        RSA
    }
}