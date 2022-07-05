package com.example.encryptionapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.encryptionapp.Services.AESService;
import com.example.encryptionapp.R;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encrypt extends AppCompatActivity {
    public static final String ENCRYPTED_MESSAGE = "com.example.encryptionapp.activities.ENCRYPTED_MESSAGE";
    public static final String SECRET_KEY = "com.example.encryptionapp.activities.SECRET_KEY";
    public static final String INIT_VECTOR = "com.example.encryptionapp.activities.INIT_VECTOR";
    public static final String[] AES_MODES = {"ECB", "CBC", "CFB", "OFB", "CTR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        Spinner dropdown = findViewById(R.id.aes_mode_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, AES_MODES);
        dropdown.setAdapter(adapter);
    }

    public void encrypt(View view) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        EditText editTextMessage = findViewById(R.id.message_to_encrypt);
        String messageToEncrypt = editTextMessage.getText().toString();

        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = AESService.encrypt(algorithm, messageToEncrypt, key, ivParameterSpec);
        String stringKey = AESService.convertSecretKeyToString(key);
        String initVector = AESService.convertInitVectorToString(ivParameterSpec);

        Intent intent = new Intent(this, EncryptedMessage.class);
        intent.putExtra(ENCRYPTED_MESSAGE, cipherText);
        intent.putExtra(SECRET_KEY, stringKey);
        intent.putExtra(INIT_VECTOR, initVector);
        startActivity(intent);
    }

    public void onRadioButtonSecretDecisionClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        EditText ownSecretInput = findViewById(R.id.own_secret_input);
        ConstraintLayout generateKeyLayout = findViewById(R.id.generate_key_layout);

        switch (view.getId()) {
            case R.id.radioButton_enter_secret:
                if (checked) {
                    ownSecretInput.setVisibility(View.VISIBLE);
                    generateKeyLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.radioButton_generate_secret:
                if (checked) {
                    generateKeyLayout.setVisibility(View.VISIBLE);
                    ownSecretInput.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void onRadioButtonKeyGenerationClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        ConstraintLayout newKeySizeLayout = findViewById(R.id.new_key_size);
        ConstraintLayout newKeyPasswordLayout = findViewById(R.id.new_key_password);

        switch (view.getId()) {
            case R.id.radioButton_use_key_length:
                if (checked) {
                    newKeySizeLayout.setVisibility(View.VISIBLE);
                    newKeyPasswordLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.radioButton_use_password_and_salt:
                if (checked) {
                    newKeyPasswordLayout.setVisibility(View.VISIBLE);
                    newKeySizeLayout.setVisibility(View.GONE);
                }
                break;
        }
    }
}