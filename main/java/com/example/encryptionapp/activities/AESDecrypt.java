package com.example.encryptionapp.activities;

import static com.example.encryptionapp.services.DataService.AES_ALGORITHMS;
import static com.example.encryptionapp.services.DataService.AES_MODES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.encryptionapp.R;
import com.example.encryptionapp.services.AESService;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESDecrypt extends AppCompatActivity {
    public static final String DECRYPTED_MESSAGE = "com.example.encryptionapp.activities.DECRYPTED_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes_decrypt);

        Spinner dropdown = findViewById(R.id.aes_mode_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, AES_MODES);
        dropdown.setAdapter(adapter);
    }

    public void decrypt(View view) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Spinner aesModesSpinner = findViewById(R.id.aes_mode_spinner);

        EditText encryptedMessageInput = findViewById(R.id.encrypted_message_input);
        String encryptedText = encryptedMessageInput.getText().toString();

        EditText secretKeyInput = findViewById(R.id.secret_key_input);
        String stringKey = secretKeyInput.getText().toString();

        EditText initVectorInput = findViewById(R.id.init_vector_input);
        String stringInitVector = initVectorInput.getText().toString();

        SecretKey secretKey = AESService.convertStringToSecretKey(stringKey);
        IvParameterSpec ivParameterSpec = AESService.convertStringToInitVector(stringInitVector);
        String algorithm = AES_ALGORITHMS[aesModesSpinner.getSelectedItemPosition()];

        String decryptedText;

        if (aesModesSpinner.getSelectedItem().toString().equals("ECB")) {
            decryptedText = AESService.decrypt(algorithm, encryptedText, secretKey);
        } else {
            decryptedText = AESService.decrypt(algorithm, encryptedText, secretKey, ivParameterSpec);
        }

        Intent intent = new Intent(this, AESDecryptedMessage.class);
        intent.putExtra(DECRYPTED_MESSAGE, decryptedText);
        startActivity(intent);
    }

    public void onSwitchButtonClick(View view) {
        boolean checked = ((SwitchMaterial) view).isChecked();
        EditText initVectorInput = findViewById(R.id.init_vector_input);

        if (checked) {
            initVectorInput.setVisibility(View.VISIBLE);
        } else {
            initVectorInput.setVisibility(View.GONE);
        }
    }
}