package com.example.ejercicio05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    //1. Activa ek binding para la Activity:
    private AddActivity binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Contruye el binding:
        binding = AddActivity.inflate(getLayoutInflater()); // el inflate lee el xml y construye las variables

        //Asocia el binding a la Activity:
        setContentView(binding.getRoot());


        binding.btnAddCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno alumno = createAlumno();
                if(alumno!= null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(AddAlumnoActivity.this, "FALTAN DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private Alumno createAlumno() {
        if(binding.txtNombreAddAlumno.getText().toString().isEmpty() || binding.txtApellidosAddAlumno.getText().toString().isEmpty())
            return null;

        if (binding.spCiclosAddAlumno.getSelectedItemPosition()== 0)
            return null;
        if(!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked())
            return null;

        String ciclo = (String) binding.spCiclosAddAlumno.getSelectedItem();
        RadioButton rb = findViewById(binding.rgGruposAdd.getCheckedRadioButtonId());
        char grupo = rb.getText().charAt(0);

        return new Alumno(binding.txtNombreAddAlumno.getText().toString(), binding.txtApellidosAddAlumno.getText().toString(), ciclo, grupo);
    }

}
