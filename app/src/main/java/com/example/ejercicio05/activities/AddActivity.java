package com.example.ejercicio05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio05.R;
import com.example.ejercicio05.configuraciones.Constantes;
import com.example.ejercicio05.databinding.ActivityAddBinding;
import com.example.ejercicio05.modelos.Piso;

public class AddActivity extends AppCompatActivity {


    private ActivityAddBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnAddCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnAddAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso inmueble = crearInmueble();
                if(inmueble != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.INMUEBLE, inmueble);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();

                }else{
                    Toast.makeText(AddActivity.this, "FALTAN DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Piso crearInmueble() {
        if(binding.txtAddDireccion.getText().toString().isEmpty() ||
        binding.txtAddNumero.getText().toString().isEmpty() ||
                binding.txtAddCP.getText().toString().isEmpty() ||
                binding.txtAddProvincia.getText().toString().isEmpty() ||
                binding.txtAddCiudad.getText().toString().isEmpty()



        )
            return null;
        return new Piso(
                binding.txtAddDireccion.getText().toString(),
                Integer.parseInt(binding.txtAddNumero.getText().toString()),
                binding.txtAddCiudad.getText().toString(),
                binding.txtAddProvincia.getText().toString(),
                binding.txtAddCP.getText().toString(),
                binding.rbAddranking.getRating()
        );

    }

}
