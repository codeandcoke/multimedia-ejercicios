package com.sfaci.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private HashMap<String, Tarea> tareas;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAnadir = (Button) findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);

        tareas = new HashMap<>();

        ListView lvTareas = (ListView) findViewById(R.id.lvTareas);
        listaTareas = new ArrayList<>(tareas.keySet());
        adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listaTareas);
        lvTareas.setAdapter(adaptador);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btAnadir:
                EditText etTarea = (EditText) findViewById(R.id.etTarea);
                String nombreTarea = etTarea.getText().toString();

                tareas.put(nombreTarea, new Tarea(nombreTarea));
                etTarea.setText("");
                listaTareas.clear();
                listaTareas.addAll(tareas.keySet());
                adaptador.notifyDataSetChanged();
                break;
        }
    }
}
