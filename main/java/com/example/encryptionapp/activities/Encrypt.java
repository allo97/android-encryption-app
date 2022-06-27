package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.encryptionapp.AESencryption.AES;
import com.example.encryptionapp.R;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encrypt extends AppCompatActivity {
    public static final String ENCRYPTED_MESSAGE = "com.example.encryptionapp.activities.ENCRYPTED_MESSAGE";
    public static final String SECRET_KEY = "com.example.encryptionapp.activities.SECRET_KEY";
    public static final String INIT_VECTOR = "com.example.encryptionapp.activities.INIT_VECTOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    public void encrypt(View view) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        EditText editTextMessage = (EditText) findViewById(R.id.editTextTextPersonName2);
        String messageToEncrypt = editTextMessage.getText().toString();

        SecretKey key = AES.generateKey(256);
        IvParameterSpec ivParameterSpec = AES.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = AES.encrypt(algorithm, messageToEncrypt, key, ivParameterSpec);
        String stringKey = AES.convertSecretKeyToString(key);
        String initVector = AES.convertInitVectorToString(ivParameterSpec);

        Intent intent = new Intent(this, EncryptedMessage.class);
        intent.putExtra(ENCRYPTED_MESSAGE, cipherText);
        intent.putExtra(SECRET_KEY, stringKey);
        intent.putExtra(INIT_VECTOR, initVector);
        startActivity(intent);
    }
}