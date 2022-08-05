package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.encryptionapp.R;
import com.example.encryptionapp.services.RSAService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSADecrypt extends AppCompatActivity {
    public static final String DECRYPTED_MESSAGE = "com.example.encryptionapp.activities.DECRYPTED_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa_decrypt);
    }

    public void decrypt(View view) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        EditText privateKeyInput = findViewById(R.id.private_key_input);
        EditText messageToDecryptInput = findViewById(R.id.message_to_decrypt_input);

        PrivateKey privateKey = RSAService.getPrivateKey(privateKeyInput.getText().toString());

        String decryptedMessage = RSAService.decrypt(messageToDecryptInput.getText().toString(), privateKey);

        Intent intent = new Intent(this, RSADecryptedMessage.class);
        intent.putExtra(DECRYPTED_MESSAGE, decryptedMessage);
        startActivity(intent);
    }
}