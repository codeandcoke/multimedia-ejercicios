package sfaci.com.videojuegos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import sfaci.com.videojuegos.R;
import sfaci.com.videojuegos.base.Juego;

public class MainActivity extends AppCompatActivity {

    public static HashMap<String, Juego> juegos =
            new HashMap<>();

    private ArrayAdapter<String> adaptador;
    private ArrayList<String> listaTitulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTitulos = new ArrayList<>(juegos.keySet());

        ListView lvJuegos = (ListView) findViewById(R.id.lvJuegos);
        adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listaTitulos);
        lvJuegos.setAdapter(adaptador);

        registerForContextMenu(lvJuegos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refrescarLista();
    }

    private void refrescarLista() {
        listaTitulos.clear();
        listaTitulos.addAll(juegos.keySet());
        adaptador.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_nuevo_juego:
                Intent intent = new Intent(this, AltaVideojuego.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = menuInfo.position;
        String titulo = listaTitulos.get(posicion);

        switch (item.getItemId()) {
            case R.id.menu_eliminar:
                juegos.remove(titulo);
                refrescarLista();
                return true;
            case R.id.menu_modificar:
                Intent intentAlta = new Intent(this, AltaVideojuego.class);
                intentAlta.putExtra("titulo", titulo);
                startActivity(intentAlta);
                return true;
            case R.id.menu_ver_detalles:
                Intent intentDetalles = new Intent(this, DetallesVideojuego.class);
                intentDetalles.putExtra("titulo", titulo);
                startActivity(intentDetalles);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
