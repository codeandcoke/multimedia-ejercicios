package sfaci.com.videojuegos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

import sfaci.com.videojuegos.R;
import sfaci.com.videojuegos.base.Juego;
import sfaci.com.videojuegos.util.Util;

public class AltaVideojuego extends AppCompatActivity implements View.OnClickListener{

    private enum Accion {
        NUEVO, MODIFICAR
    }
    private Accion accion;

    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_videojuego);

        rellenarGeneros();

        Button btAlta = (Button) findViewById(R.id.btAlta);
        btAlta.setOnClickListener(this);

        String titulo = getIntent().getStringExtra("titulo");
        prepararFormulario(titulo);
    }

    private void rellenarGeneros() {

        ArrayList<String> generos = new ArrayList<>();
        generos.add("Accion");
        generos.add("Aventura");
        generos.add("Guerra");
        generos.add("Deportes");

        Spinner spGenero = (Spinner) findViewById(R.id.spGenero);
        adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, generos);
        spGenero.setAdapter(adaptador);
    }

    private void prepararFormulario(String titulo) {

        if (titulo != null) {
            accion = Accion.MODIFICAR;
            setTitle(R.string.modificar);
            // Cargar los datos del juego
            Juego juego = MainActivity.juegos.get(titulo);
            EditText etNombre = (EditText) findViewById(R.id.etNombre);
            Spinner spGenero = (Spinner) findViewById(R.id.spGenero);
            EditText etFecha = (EditText) findViewById(R.id.etFecha);
            EditText etPrecio = (EditText) findViewById(R.id.etPrecio);

            etNombre.setText(juego.getNombre());
            spGenero.setSelection(adaptador.getPosition(juego.getGenero()));
            etFecha.setText(Util.formatFecha(juego.getFecha()));
            etPrecio.setText(String.valueOf(juego.getPrecio()));
        }
        else {
            accion = Accion.NUEVO;
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btAlta:
                EditText etNombre = (EditText) findViewById(R.id.etNombre);
                EditText etPrecio = (EditText) findViewById(R.id.etPrecio);
                Spinner spGenero = (Spinner) findViewById(R.id.spGenero);
                EditText etFecha = (EditText) findViewById(R.id.etFecha);

                try {
                    Juego juego = new Juego();
                    juego.setNombre(etNombre.getText().toString());
                    juego.setPrecio(Float.parseFloat(etPrecio.getText().toString()));
                    juego.setGenero((String) spGenero.getSelectedItem());
                    juego.setFecha(Util.parseFecha(etFecha.getText().toString()));

                    MainActivity.juegos.put(juego.getNombre(), juego);
                } catch (ParseException pe) {
                    Toast.makeText(this, R.string.mensaje_error_fecha,
                            Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btLimpiar:

                break;
            default:
                break;
        }
    }
}
