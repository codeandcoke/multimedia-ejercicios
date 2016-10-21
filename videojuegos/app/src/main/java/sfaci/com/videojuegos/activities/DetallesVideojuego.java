package sfaci.com.videojuegos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sfaci.com.videojuegos.R;
import sfaci.com.videojuegos.base.Juego;
import sfaci.com.videojuegos.util.Util;

public class DetallesVideojuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_videojuego);

        String titulo = getIntent().getStringExtra("titulo");
        cargarJuego(titulo);
    }

    private void cargarJuego(String titulo) {

        Juego juego = MainActivity.juegos.get(titulo);

        TextView tvNombre = (TextView) findViewById(R.id.tvNombre);
        TextView tvGenero = (TextView) findViewById(R.id.tvGenero);
        TextView tvFechaLanzamiento = (TextView) findViewById(R.id.tvFechaLanzamiento);
        TextView tvPrecio = (TextView) findViewById(R.id.tvPrecio);

        tvNombre.setText(juego.getNombre());
        tvGenero.setText(juego.getGenero());
        tvFechaLanzamiento.setText(Util.formatFecha(juego.getFecha()));
        tvPrecio.setText(String.valueOf(juego.getPrecio()));
    }
}
