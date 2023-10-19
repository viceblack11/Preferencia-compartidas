package com.vicente.preferenciascompartidas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private TextView dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dato = findViewById(R.id.textViewUser);

        dato.setText("Bienvenido " + new PreferenciaManager(this).getEmail());
    }
}
