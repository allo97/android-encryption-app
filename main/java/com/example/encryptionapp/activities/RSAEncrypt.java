package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.encryptionapp.R;
import com.example.encryptionapp.services.RSAService;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncrypt extends AppCompatActivity {
    public static final String ENCRYPTED_MESSAGE = "com.example.encryptionapp.activities.ENCRYPTED_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa_encrypt);
    }

    public void onGeneratePublicAndPrivateKeys(View view) throws NoSuchAlgorithmException {
        KeyPair keyPair = RSAService.generateRSAKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        TextView publicKeyText = findViewById(R.id.public_key_text);
        TextView privateKeyText = findViewById(R.id.private_key_text);

        publicKeyText.setText(RSAService.convertBytesToString(publicKey.getEncoded()));
        privateKeyText.setText(RSAService.convertBytesToString(privateKey.getEncoded()));

        ConstraintLayout keysLayout = findViewById(R.id.public_private_keys_layout);
        keysLayout.setVisibility(View.VISIBLE);
    }

    public void encrypt(View view) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        EditText publicKeyInput = findViewById(R.id.public_key_input);
        EditText messageToEncryptInput = findViewById(R.id.message_to_encrypt_input);

        PublicKey publicKey = RSAService.getPublicKey(publicKeyInput.getText().toString());

        String encryptedMessage = RSAService.encrypt(messageToEncryptInput.getText().toString(), publicKey);

        Intent intent = new Intent(this, RSAEncryptedMessage.class);
        intent.putExtra(ENCRYPTED_MESSAGE, encryptedMessage);
        startActivity(intent);
    }
}