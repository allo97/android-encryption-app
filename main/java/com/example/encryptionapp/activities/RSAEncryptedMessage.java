package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.encryptionapp.R;

public class RSAEncryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa_encrypted_message);

        Intent intent = getIntent();
        String encryptedMessage = intent.getStringExtra(RSAEncrypt.ENCRYPTED_MESSAGE);

        TextView encryptedMessageTextView = findViewById(R.id.encrypted_message_text);
        encryptedMessageTextView.setText(encryptedMessage);
    }
}