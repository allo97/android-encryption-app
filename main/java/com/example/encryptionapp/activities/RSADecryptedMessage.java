package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.encryptionapp.R;

public class RSADecryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa_decrypted_message);

        Intent intent = getIntent();
        String decryptedMessage = intent.getStringExtra(RSADecrypt.DECRYPTED_MESSAGE);

        TextView decryptedMessageText = findViewById(R.id.decrypted_message_text);
        decryptedMessageText.setText(decryptedMessage);
    }
}