package com.example.ejercicio05;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicio05.activities.AddActivity;
import com.example.ejercicio05.configuraciones.Constantes;
import com.example.ejercicio05.modelos.Piso;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.ejercicio05.databinding.ActivityMainBinding;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
        setSupportActionBar(binding.toolbar);
        inicializaLaunchers();




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
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                if (result.getData().getExtras() != null) {
                                    if (result.getData().getExtras().getSerializable(Constantes.INMUEBLE) != null) {

                                        Piso inmuble = (Piso) result.getData().getExtras().getSerializable(Constantes.INMUEBLE);
                                        listaPisos.add(inmuble);
                                        pintarElementos();

                                    } else {
                                        Toast.makeText(MainActivity.this, "NO HAY DATOS", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "NO HAY BUNDLE EN EL INTENT", Toast.LENGTH_LONG).show();
                                }
                            } else {

                                Toast.makeText(MainActivity.this, "NO HAY INTENT", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "VENTANA CANCELADA", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }


    private void pintarElementos() {
        binding.content.contenedor.removeAllViews();

        for (int i = 0; i < listaPisos.size(); i++) {
            Piso piso = listaPisos.get(i);

            View pisosView = LayoutInflater.from(MainActivity.this).inflate(R.layout.inmueble_view_model, null);

            TextView lblCalle = pisosView.findViewById(R.id.lblDireccionInmuebleModel);
            TextView lblNumeroPiso = pisosView.findViewById(R.id.lblNumeroInmuebleModel);
            TextView lblCiudad = pisosView.findViewById(R.id.lblCiudadInmuebleModel);
            TextView lblProvincia = pisosView.findViewById(R.id.lblInmuebleProvinciaModel);
            RatingBar rbValoracion = pisosView.findViewById(R.id.rbRatingInmuebleModel);

            lblCalle.setText(piso.getDireccion());
            lblNumeroPiso.setText(String.valueOf(piso.getNumero()));
            lblCiudad.setText(piso.getCiudad());
            lblProvincia.setText(piso.getProvincia());
            rbValoracion.setRating(piso.getValoracion());

            binding.content.contenedor.addView(pisosView);
        }




    }
}