package com.example.ejercicio05.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio05.configuraciones.Constantes;
import com.example.ejercicio05.databinding.ActivityAddBinding;
import com.example.ejercicio05.databinding.ActivityEditBinding;
import com.example.ejercicio05.modelos.Piso;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //lo siguiente es para que cuando se hace clic en una de los atributos del ebjeto (por ejemplo, ciudad), nos lee esa infomación en particular.
        //el elemento cliqueado va a la ventana de edit


        //FASE 1: obtener el objeto a editar
        Intent intentmain = getIntent();
        Bundle bundleMain = intentmain.getExtras();
        Piso inmuebleEdit = (Piso) bundleMain.getSerializable(Constantes.INMUEBLE);

        Log.d("OBJETO", inmuebleEdit.toString());

        //Fase 2: Mostrar los datos del objeto en la interfaz

        binding.txtAEditDireccion.setText(inmuebleEdit.getDireccion());
        binding.txtEditNumero.setText(String.valueOf(inmuebleEdit.getNumero()));
        binding.txtEditCP.setText(inmuebleEdit.getCp());
        binding.txtEditCiudad.setText(inmuebleEdit.getCiudad());
        binding.txtEditProvincia.setText(inmuebleEdit.getProvincia());
        binding.rbEditranking.setRating(inmuebleEdit.getValoracion());


        binding.btnEditEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso inmuebleUpdate = crearInmueble();
                if(crearInmueble()!=null){
                    Intent intent =new Intent();
                    Bundle bundle = new Bundle();
                    int posicion = bundleMain.getInt(Constantes.POSICION);
                    bundle.putInt(Constantes.POSICION, posicion);
                    bundle.putSerializable(Constantes.INMUEBLE, inmuebleUpdate);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

          //PARA ELIMINAR
        binding.btnEditEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = bundleMain.getInt(Constantes.POSICION);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(Constantes.POSICION, posicion);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private Piso crearInmueble() {
        if(binding.txtAEditDireccion.getText().toString().isEmpty() ||
                binding.txtEditNumero.getText().toString().isEmpty() ||
                binding.txtEditCP.getText().toString().isEmpty() ||
                binding.txtEditCiudad.getText().toString().isEmpty() ||
                binding.txtEditProvincia.getText().toString().isEmpty()
        )
            return null;
        return new Piso(
                binding.txtAEditDireccion.getText().toString(),
                Integer.parseInt(binding.txtEditNumero.getText().toString()),
                binding.txtEditCiudad.getText().toString(),
                binding.txtEditProvincia.getText().toString(),
                binding.txtEditCP.getText().toString(),
                binding.rbEditranking.getRating()
        );
    }
}






