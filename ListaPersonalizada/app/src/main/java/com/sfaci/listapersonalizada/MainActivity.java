package com.sfaci.listapersonalizada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Evento> listaEventos;
    private BaseDatos db;
    private EventoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventos = new ArrayList<>();
        db = new BaseDatos(this);
        listaEventos.addAll(db.obtenerEventos());

        ListView lvEventos = (ListView) findViewById(R.id.lvEventos);
        adaptador = new EventoAdapter(
                this, R.layout.fila, listaEventos);
        lvEventos.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaEventos.clear();
        listaEventos.addAll(db.obtenerEventos());
        adaptador.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_nuevo_evento:
                Intent intent = new Intent(this, AltaEventos.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    private void insertarEventos() {

        listaEventos = new ArrayList<>();

        Evento evento = new Evento();
        evento.setNombre("Estudiar");
        evento.setPrecio(100);
        evento.setDireccion("Mi casa");
        listaEventos.add(evento);

        evento = new Evento();
        evento.setNombre("Estudiar más");
        evento.setPrecio(200);
        evento.setDireccion("En mi casa");
        listaEventos.add(evento);
    }
}
