package com.vicente.preferenciascompartidas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private CheckBox checkBoxRecordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);

        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL) {
                    intentarLogin();
                    return true;
                }
                return false;
            }
        });

        Button btnIniciarSesionEmail = (Button) findViewById(R.id.btnIniciarSesionEmail);
        btnIniciarSesionEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentarLogin();
            }
        });

        checkBoxRecordar = (CheckBox) findViewById(R.id.chkRecodar);
        if (!new PreferenciaManager(this).isUsuarioLogedOut()) {
            startHomeActivity();
        }

    }

    private void intentarLogin() {
        emailText.setError(null);
        passwordText.setError(null);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        boolean cancelar = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) || !isPasswordValido(password)) {
            passwordText.setError(getString(R.string.error_password_invalido));
            focusView = passwordText;
            cancelar = true;
        }

        if (TextUtils.isEmpty(email)) {
            emailText.setError(getString(R.string.error_campo_requerido));
            focusView = emailText;
            cancelar = true;
        } else if (!isEmailValido(email)) {
            emailText.setError(getString(R.string.error_email_invalido));
            focusView = emailText;
            cancelar = true;
        }

        if (cancelar == true) {
            focusView.requestFocus();
        } else {
            if (checkBoxRecordar.isChecked())
                salvarLoginDetalles(email, password);
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void salvarLoginDetalles(String email, String password) {
        new PreferenciaManager(this).salvarLoginDetalles(email, password);
    }

    private boolean isEmailValido(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValido(String password) {
        return password.length() > 4;
    }

}
