package com.sfaci.puntoswifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class MapActivity extends AppCompatActivity {

    private MapView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this, "pk.eyJ1Ijoic2ZhY2kiLCJhIjoiY2l2dHdha2FqMDAzNzJ0bXdnY3pyM3VoYyJ9.vTeb0q2LjHQdYQxeWxbnbQ");

        setContentView(R.layout.activity_map);
        mapa = (MapView) findViewById(R.id.mapa);
        mapa.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        double latitud = intent.getDoubleExtra("latitud", 0);
        double longitud = intent.getDoubleExtra("longitud", 0);
        marcarUbicacion(nombre, latitud, longitud);
    }

    private void marcarUbicacion(final String nombre,
                                 final double latitud, final double longitud) {

        Toast.makeText(this, latitud + " " + longitud, Toast.LENGTH_SHORT).show();

        mapa.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitud, longitud))
                        .title(nombre));

                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(latitud, longitud)) // Sets the new camera position
                        .zoom(17) // Sets the zoom
                        .tilt(30) // Set the camera tilt
                        .build(); // Creates a CameraPosition from the builder

                mapboxMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 7000);
            }
        });
    }
}
