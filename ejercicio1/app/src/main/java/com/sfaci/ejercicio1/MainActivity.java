package com.sfaci.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    ArrayAdapter<String> adaptador;
    ArrayList<String> listaTareas;
    BaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAnadir = (Button) findViewById(R.id.btAnadir);
        btAnadir.setOnClickListener(this);
        Button btPendientes = (Button) findViewById(R.id.btPendientes);
        btPendientes.setOnClickListener(this);
        Button btHechas = (Button) findViewById(R.id.btHechas);
        btHechas.setOnClickListener(this);

        ListView lvTareas = (ListView) findViewById(R.id.lvTareas);
        db = new BaseDatos(this);
        listaTareas = db.obtenerTareas();
        adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listaTareas);
        lvTareas.setAdapter(adaptador);

        registerForContextMenu(lvTareas);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = info.position;

        String nombreTareaSeleccionada = null;

        switch (item.getItemId()) {
            case R.id.opcion_eliminar:
                nombreTareaSeleccionada = listaTareas.get(posicion);
                db.eliminarTarea(nombreTareaSeleccionada);

                listaTareas.remove(posicion);
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.opcion_hecho:
                nombreTareaSeleccionada = listaTareas.get(posicion);
                db.hacerTarea(nombreTareaSeleccionada);

                listaTareas.remove(posicion);
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btAnadir:
                EditText etTarea = (EditText) findViewById(R.id.etTarea);
                String nombreTarea = etTarea.getText().toString();
                db.nuevaTarea(new Tarea(nombreTarea));

                etTarea.setText("");
                listaTareas.clear();
                listaTareas.addAll(db.obtenerTareas());
                adaptador.notifyDataSetChanged();
                break;
            case R.id.btHechas:
                ArrayList<String> tareasHechas = db.obtenerTareas(true);
                listaTareas.clear();
                listaTareas.addAll(tareasHechas);
                adaptador.notifyDataSetChanged();
                break;
            case R.id.btPendientes:
                ArrayList<String> tareasSinHacer = db.obtenerTareas(false);
                listaTareas.clear();
                listaTareas.addAll(tareasSinHacer);
                adaptador.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    /*
    private void listarTareas(boolean hecha) {

        listaTareas.clear();
        Iterator<Tarea> iterTareas= tareas.values().iterator();
        while (iterTareas.hasNext()) {
            Tarea tarea = iterTareas.next();
            if (tarea.estaHecha() == hecha)
                listaTareas.add(tarea.getNombre());
        }
        adaptador.notifyDataSetChanged();
    }*/
}
