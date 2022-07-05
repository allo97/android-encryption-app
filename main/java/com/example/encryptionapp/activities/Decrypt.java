package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.encryptionapp.Services.AESService;
import com.example.encryptionapp.R;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Decrypt extends AppCompatActivity {
    public static final String DECRYPTED_MESSAGE = "com.example.encryptionapp.activities.DECRYPTED_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
    }

    public void decrypt(View view) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        EditText editTextCipher = (EditText) findViewById(R.id.editTextTextPersonName2);
        String encryptedText = editTextCipher.getText().toString();

        EditText editTextSecretKey = (EditText) findViewById(R.id.editTextTextPersonName3);
        String stringKey = editTextSecretKey.getText().toString();

        EditText editTextInitVector = (EditText) findViewById(R.id.editTextTextPersonName4);
        String stringInitVector = editTextInitVector.getText().toString();

        SecretKey secretKey = AESService.convertStringToSecretKey(stringKey);
        IvParameterSpec ivParameterSpec = AESService.convertStringToInitVector(stringInitVector);
        String algorithm = "AES/CBC/PKCS5Padding";

        String decryptedMessage = AESService.decrypt(algorithm, encryptedText, secretKey, ivParameterSpec);

        Intent intent = new Intent(this, DecryptedMessage.class);
        intent.putExtra(DECRYPTED_MESSAGE, decryptedMessage);
        startActivity(intent);
    }
}