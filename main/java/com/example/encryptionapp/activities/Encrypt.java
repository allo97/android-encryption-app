package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.encryptionapp.R;

public class Encrypt extends AppCompatActivity {
    public static final String MESSAGE_TO_ENCRYPT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    public void encrypt(View view) {

        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine);
        String message = editText.getText().toString();

        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);

        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}