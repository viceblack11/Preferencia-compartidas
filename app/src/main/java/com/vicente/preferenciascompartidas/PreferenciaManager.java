package com.vicente.preferenciascompartidas;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciaManager {

    Context context;

    //Constructor de la clase que recibe Context de otra Clase2
    PreferenciaManager(Context contextRecibido) {
        this.context = contextRecibido;
    }

    public void salvarLoginDetalles(String email, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDatos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();

    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDatos", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUsuarioLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDatos", Context.MODE_PRIVATE);
        boolean isEmailVacio = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordVacio = sharedPreferences.getString("Password", "").isEmpty();

        return isEmailVacio || isPasswordVacio;
    }

}
