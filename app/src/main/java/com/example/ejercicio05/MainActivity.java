package com.example.ejercicio05;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicio05.activities.AddActivity;
import com.example.ejercicio05.modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ejercicio05.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.nio.channels.Pipe;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // es una clase automática;


    private ArrayList<Piso> listaPisos;

    private ActivityResultLauncher<Intent> launcherCrearPisos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaPisos = new ArrayList<>();
        inicializaLaunchers();


        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPisos.launch(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherCrearPisos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK){
                            if(result.getData()!= null && result.getData().getExtras()!=null){
                                Piso piso = (Piso) result.getData().getExtras().getSerializable("PISO");
                                listaPisos.add(piso);
                                pintarElementos();
                            }
                        }
                    }
                }
        );


    }

    private void pintarElementos() {
        binding.content.contenedor.removeAllViews();

        for (Piso piso:listaPisos) {
            View pisosView = LayoutInflater.from(MainActivity.this).inflate(R.layout.piso_layout, null); //aqui meto el xml que hay en alumno_model_view
            TextView lblCalle = pisosView.findViewById(R.id.lblCallePiso);
            TextView lbl = alumnoView.findViewById(R.id.lblApellidoView);
            TextView lblCiclo = alumnoView.findViewById(R.id.lblCicloView);
            TextView lblgrupo = alumnoView.findViewById(R.id.lblGrupoView);

            lblNombre.setText(alumno.getNombre());
            lblApellidos.setText(alumno.getApellidos());
            lblCiclo.setText(alumno.getCiclo());
            lblgrupo.setText(String.valueOf(alumno.getGrupo()));

            binding.content.contenedor.addView(alumnoView);
    }
}