package com.sfaci.puntoswifi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sfaci.puntoswifi.util.Constantes;
import com.sfaci.puntoswifi.util.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uk.me.jstott.jcoord.LatLng;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

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

        lvPuntosWifi.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TareaDescargarDatos tarea = new TareaDescargarDatos();
        tarea.execute(Constantes.URL);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int i, long l) {

        PuntoWifi puntoWifi = puntosWifi.get(i);
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("nombre", puntoWifi.getNombre());
        intent.putExtra("latitud", puntoWifi.getLatitud());
        intent.putExtra("longitud", puntoWifi.getLongitud());
        startActivity(intent);
    }

    private class TareaDescargarDatos extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {

            InputStream is;
            String resultado = null;

            try {
                HttpClient clienteHttp = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(urls[0]);
                HttpResponse respuesta = clienteHttp.execute(httpGet);
                HttpEntity entity = respuesta.getEntity();
                is = entity.getContent();

                // Lee el fichero de datos y genera una cadena de texto como resultado
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String linea = null;

                while ((linea = br.readLine()) != null)
                    sb.append(linea + "\n");

                is.close();
                br.close();
                resultado = sb.toString();

                JSONObject json = new JSONObject(resultado);
                JSONArray jsonArray = json.getJSONArray("result");
                PuntoWifi puntoWifi = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    int  id = jsonArray.getJSONObject(i).getInt("id");
                    String nombre = jsonArray.getJSONObject(i).getString("title");
                    String descripcion = jsonArray.getJSONObject(i).getString("description");
                    String ssid = jsonArray.getJSONObject(i).getString("servicios");
                    ssid = "Wizi Milla Digital";
                    String coordenadas = jsonArray.getJSONObject(i).getJSONObject("geometry")
                            .getString("coordinates");
                    String[] arrayCoordenadas = coordenadas.substring(1, coordenadas.length() - 1)
                    .split(",");
                    LatLng latLng = Util.DeUMTSaLatLng(
                            Double.parseDouble(arrayCoordenadas[0]),
                            Double.parseDouble(arrayCoordenadas[1]));
                    int cobertura = 60;

                    puntoWifi = new PuntoWifi();
                    puntoWifi.setId(id);
                    puntoWifi.setSsid(ssid);
                    puntoWifi.setNombre(nombre);
                    puntoWifi.setLatitud(latLng.getLat());
                    puntoWifi.setLongitud(latLng.getLng());
                    puntoWifi.setDescripcion(descripcion);
                    puntoWifi.setCobertura(cobertura);
                    puntosWifi.add(puntoWifi);
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            adaptador.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adaptador.notifyDataSetChanged();
        }
    }
}
