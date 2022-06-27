package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.encryptionapp.R;

public class EncryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String encryptedMessage = intent.getStringExtra(Encrypt.ENCRYPTED_MESSAGE);
        String secretKey = intent.getStringExtra(Encrypt.SECRET_KEY);
        String initVector = intent.getStringExtra(Encrypt.INIT_VECTOR);

        // Capture the layout's TextView and set the string as its text
        EditText encryptedMessageEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        encryptedMessageEditText.setText(encryptedMessage);

        EditText secretKeyEditText = (EditText) findViewById(R.id.editTextTextPersonName5);
        secretKeyEditText.setText(secretKey);

        EditText initVectorEditText = (EditText) findViewById(R.id.editTextTextPersonName6);
        initVectorEditText.setText(initVector);
    }
}