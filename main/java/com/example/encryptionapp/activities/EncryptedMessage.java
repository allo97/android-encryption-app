package com.example.encryptionapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.encryptionapp.R;

public class EncryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String encryptedMessage = intent.getStringExtra(AESEncrypt.ENCRYPTED_MESSAGE);
        String secretKey = intent.getStringExtra(AESEncrypt.SECRET_KEY);
        String initVector = intent.getStringExtra(AESEncrypt.INIT_VECTOR);

        // Capture the layout's TextView and set the string as its text
        TextView encryptedMessageTextView = findViewById(R.id.encrypted_message_text);
        encryptedMessageTextView.setText(encryptedMessage);

        TextView secretKeyTextView = findViewById(R.id.secret_key_text);
        secretKeyTextView.setText(secretKey);

        ConstraintLayout initVectorConstraintLayout = findViewById(R.id.init_vector_constraint_layout);

        if (initVector == null) {
            initVectorConstraintLayout.setVisibility(View.GONE);
        } else {
            TextView initVectorTextView = findViewById(R.id.init_vector_text);
            initVectorTextView.setText(initVector);
        }


    }
}