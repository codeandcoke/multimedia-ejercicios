package com.sfaci.listapersonalizada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Evento> listaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertarEventos();

        ListView lvEventos = (ListView) findViewById(R.id.lvEventos);
        EventoAdapter adaptador = new EventoAdapter(
                this, R.layout.fila, listaEventos);
        lvEventos.setAdapter(adaptador);
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
