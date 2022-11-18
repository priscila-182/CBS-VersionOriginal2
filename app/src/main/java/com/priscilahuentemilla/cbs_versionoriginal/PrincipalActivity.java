package com.priscilahuentemilla.cbs_versionoriginal;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.priscilahuentemilla.cbs_versionoriginal.HomeAdapter.FeaturedAdapter;
import com.priscilahuentemilla.cbs_versionoriginal.HomeAdapter.FeaturedHelpedClass;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton ingreso1;

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1;
    ImageView menuIcon;

    private ProgressDialog progressDialog;

    SensorManager sensorManager;
    Sensor sensor;

    //Sección de menú

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        init();

        showPDialog1();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },1400);

        Button btnVerTodosLosLibros = (Button) findViewById(R.id.btnVerTodos);

        // hooks
        featuredRecycler = findViewById(R.id.featured_recycle);
        menuIcon = findViewById(R.id.menu_icono);

        //menu hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navegation_view);


        navigationDrawer();

        //recycler view function calls

        featuredRecycler();

        //

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        ingreso1 = findViewById(R.id.ingreso);

        ingreso1.setOnClickListener(view ->  startActivity(new Intent(getApplicationContext(), LoginActivity.class)));


        final Vibrator vibrator2 = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        btnVerTodosLosLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, LibrosActivity.class));

                if (Build.VERSION.SDK_INT >= 26){
                    vibrator2.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE));
                }
                else
                {
                    vibrator2.vibrate(200);
                }
            }
        });

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


    //Funciones para la sección de navegación
    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_inicio);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    //Información de las tarjetas

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelpedClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelpedClass(R.drawable.soyleyenda, "Soy Leyenda", "Robert Neville es el único sobreviviente en un planeta asolado...", "Autor: Richard Matheson", "Género: Ciencia Ficción", "Páginas: "));
        featuredLocations.add(new FeaturedHelpedClass(R.drawable.sangreycenizas, "De Sangre y Cenizas", "Una Doncella elegida desde su nacimiento para comenzar una nueva era...", "Jennifer L. Armentrout", "Género: Fantasía", "Páginas: "));
        featuredLocations.add(new FeaturedHelpedClass(R.drawable.elresplandor, "El resplandor", "Jack Torrance acepta una oferta de trabajo en un hotel de montaña que se encuentra a 65 kilómetros del...", "Stephen King", "Género: Horror", "Páginas: "));
        featuredLocations.add(new FeaturedHelpedClass(R.drawable.cumbresborrascosas, "Cumbres Borrascosas", "Cumbres borrascosas constituye una asombrosa visión metafísica del destino, la obsesión, la pasión y la venganza...", "Emily Bronte", "Género: Romance", "Páginas: "));
        featuredLocations.add(new FeaturedHelpedClass(R.drawable.lachicadeltren, "La Chica del Tren", "Rachel Watson se divorcia del marido que la engaña y se siente sola y deprimida. No tiene trabajo y...", "Autor: Paula Hawkins", "Género: Thriller", "Páginas: "));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        if (id == R.id.nav_inicio){
            startActivity(new Intent(this, PrincipalActivity.class));
            finish();
        } else if (id == R.id.nav_Libros){
            startActivity(new Intent(this, LibrosActivity.class));
            finish();
        } else if (id == R.id.nav_categorias){
            startActivity(new Intent(this, CategoriasActivity.class));
            finish();
        } else if (id == R.id.nav_IngresarLibro){
            startActivity(new Intent(this, IngresarLibroActivity.class));
            finish();
        }else if (id == R.id.nav_Mapa){
            startActivity(new Intent(this, MapaActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}