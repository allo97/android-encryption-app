package com.example.encryptionapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.encryptionapp.Services.AESService;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void givenStringAndKeyGenerator_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        String input = "baeldung";
        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = AESService.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = AESService.decrypt(algorithm, cipherText, key, ivParameterSpec);
        assertEquals(input, plainText);
    }

    @Test
    public void givenStringAndPassword_whenEncrypt_thenSuccess() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        String input = "baeldung";
        SecretKey key = AESService.generateKeyFromPassword("alekPassword1.", "randomSalt");
        IvParameterSpec ivParameterSpec = AESService.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = AESService.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = AESService.decrypt(algorithm, cipherText, key, ivParameterSpec);
        assertEquals(input, plainText);
    }
}