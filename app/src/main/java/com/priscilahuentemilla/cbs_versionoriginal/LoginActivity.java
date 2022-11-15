package com.priscilahuentemilla.cbs_versionoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    Button btnIniciarSesion1;
    EditText correo, contraseña;
    TextView registrarse;

    private ProgressDialog progressDialog;
    FloatingActionButton btnAtras1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        showPDialog1();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },1000);


        btnAtras1 = findViewById(R.id.atras1);
        btnAtras1.setOnClickListener(view ->  startActivity(new Intent(getApplicationContext(), PrincipalActivity.class)));

        btnIniciarSesion1 = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion1.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), PrincipalActivity.class)));

        registrarse = findViewById(R.id.txtGuiarRegistro);
        registrarse.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegistroActivity.class)));

        correo = findViewById(R.id.txtCorreoElectronico);
        contraseña = findViewById(R.id.txtContraseña);


    }
    private void init() {
        this.progressDialog = new ProgressDialog(this);
    }

    private void showPDialog1(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Por favor, espere");
        progressDialog.show();
    }
}