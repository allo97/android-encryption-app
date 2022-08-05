package com.example.encryptionapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.encryptionapp.R;
import com.example.encryptionapp.services.DataService;

public class MainActivity extends AppCompatActivity {

    public DataService.CipherMode cipherMode = DataService.CipherMode.AES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToEncrypt(View view) {
        Intent intent = new Intent(this, AESEncrypt.class);

        switch (cipherMode) {
            case AES:
                intent = new Intent(this, AESEncrypt.class);
                break;
            case RSA:
                intent = new Intent(this, RSAEncrypt.class);
                break;
        }
        startActivity(intent);
    }

    public void goToDecrypt(View view) {
        Intent intent = new Intent(this, AESDecrypt.class);

        switch (cipherMode) {
            case AES:
                intent = new Intent(this, AESDecrypt.class);
                break;
            case RSA:
                intent = new Intent(this, RSADecrypt.class);
                break;
        }
        startActivity(intent);
    }

    public void onRadioButtonEncryptionMode(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton_AES:
                if (checked) {
                    this.cipherMode = DataService.CipherMode.AES;
                }
                break;
            case R.id.radioButton_RSA:
                if (checked) {
                    this.cipherMode = DataService.CipherMode.RSA;
                }
                break;
        }
    }


}