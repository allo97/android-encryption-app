package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.encryptionapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToEncrypt(View view) {
        Intent intent = new Intent(this, Encrypt.class);
        startActivity(intent);
    }

    public void goToDecrypt(View view) {
        Intent intent = new Intent(this, Decrypt.class);
        startActivity(intent);
    }


}