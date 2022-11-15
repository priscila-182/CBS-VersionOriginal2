package com.priscilahuentemilla.cbs_versionoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistroActivity extends AppCompatActivity {

    Button btnRegistrar1;
    EditText nombreUsuario, correoElectronico, contraseña;

    private ProgressDialog progressDialog;
    FloatingActionButton btnAtras2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        init();

        showPDialog1();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },1000);


        btnAtras2 = findViewById(R.id.atras2);
        btnAtras2.setOnClickListener(view ->  startActivity(new Intent(getApplicationContext(), PrincipalActivity.class)));

        btnRegistrar1 = findViewById(R.id.btnRegistrar);
        btnRegistrar1.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), PrincipalActivity.class)));

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