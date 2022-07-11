package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.encryptionapp.R;

public class DecryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypted_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String decryptedMessage = intent.getStringExtra(AESDecrypt.DECRYPTED_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        EditText encryptedMessageEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        encryptedMessageEditText.setText(decryptedMessage);
    }
}