package com.sfaci.puntoswifi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sfaci.puntoswifi.util.Constantes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PuntoWifi> puntosWifi;
    private PuntoWifiAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvPuntosWifi = (ListView) findViewById(R.id.lvPuntosWifi);
        puntosWifi = new ArrayList<>();
        adaptador = new PuntoWifiAdapter(this, puntosWifi);
        lvPuntosWifi.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TareaDescargarDatos tarea = new TareaDescargarDatos();
        tarea.execute(Constantes.URL);
    }

    private class TareaDescargarDatos extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {

            InputStream is;

            HttpClient clienteHttp = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urls[0]);
            HttpResponse respuesta = clienteHttp.execute(httpPost);
            HttpEntity entity = respuesta.getEntity();
            is = entity.getContent();

            // Lee el fichero de datos y genera una cadena de texto como resultado
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea = null;

            while ((linea = br.readLine()) != null)
                sb.append(linea + "\n");

            is.close();
            resultado = sb.toString();

            return null;
        }
    }
}
