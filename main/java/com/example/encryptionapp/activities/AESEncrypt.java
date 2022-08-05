package com.example.encryptionapp.activities;

import static com.example.encryptionapp.services.DataService.AES_ALGORITHMS;
import static com.example.encryptionapp.services.DataService.AES_MODES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.encryptionapp.R;
import com.example.encryptionapp.services.AESService;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESEncrypt extends AppCompatActivity {
    public static final String ENCRYPTED_MESSAGE = "com.example.encryptionapp.activities.ENCRYPTED_MESSAGE";
    public static final String SECRET_KEY = "com.example.encryptionapp.activities.SECRET_KEY";
    public static final String INIT_VECTOR = "com.example.encryptionapp.activities.INIT_VECTOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes_encrypt);

        Spinner dropdown = findViewById(R.id.aes_mode_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, AES_MODES);
        dropdown.setAdapter(adapter);
    }

    public void encrypt(View view) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        SecretKey key;

        Spinner aesModesSpinner = findViewById(R.id.aes_mode_spinner);
        RadioButton enterSecretRadioButton = findViewById(R.id.radioButton_enter_secret);
        EditText enterSecretInput = findViewById(R.id.enter_secret_input);
        RadioButton useKeyLengthRadioButton = findViewById(R.id.radioButton_use_key_length);
        RadioGroup radioGroupKeySize = findViewById(R.id.radioGroupKeySize);

        // Get key length
        RadioButton selectedRadioButton = findViewById(radioGroupKeySize.getCheckedRadioButtonId());
        int keyLength = Integer.parseInt(selectedRadioButton.getText().toString());

        //region Get password and salt

        EditText passwordInput = findViewById(R.id.password_for_secret);
        String password = passwordInput.getText().toString();

        EditText saltInput = findViewById(R.id.salt_for_secret);
        String salt = passwordInput.getText().toString();

        //endregion

        if (enterSecretRadioButton.isChecked()) {
            key = AESService.convertStringToSecretKey(enterSecretInput.getText().toString());
        } else {
            if (useKeyLengthRadioButton.isChecked())
                key = AESService.generateKey(keyLength);
            else
                key = AESService.generateKeyFromPassword(password, salt);
        }

        //region Get message to encrypt
        EditText editTextMessage = findViewById(R.id.message_to_encrypt);
        String messageToEncrypt = editTextMessage.getText().toString();
        // endregion


        String algorithm = AES_ALGORITHMS[aesModesSpinner.getSelectedItemPosition()];
        String cipherText;

        String initVector = null;


        if (aesModesSpinner.getSelectedItem().toString().equals("ECB")) {
            cipherText = AESService.encrypt(algorithm, messageToEncrypt, key);
        } else {
            IvParameterSpec ivParameterSpec = AESService.generateIv();
            cipherText = AESService.encrypt(algorithm, messageToEncrypt, key, ivParameterSpec);
            initVector = AESService.convertInitVectorToString(ivParameterSpec);
        }

        String stringKey = AESService.convertSecretKeyToString(key);

        Intent intent = new Intent(this, AESEncryptedMessage.class);
        intent.putExtra(ENCRYPTED_MESSAGE, cipherText);
        intent.putExtra(SECRET_KEY, stringKey);
        intent.putExtra(INIT_VECTOR, initVector);
        startActivity(intent);
    }

    public void onRadioButtonSecretDecisionClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        EditText ownSecretInput = findViewById(R.id.enter_secret_input);
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