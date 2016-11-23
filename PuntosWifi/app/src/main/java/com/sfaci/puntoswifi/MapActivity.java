package com.sfaci.puntoswifi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.maps.MapView;

public class MapActivity extends AppCompatActivity {

    private MapView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this, "pk.eyJ1Ijoic2ZhY2kiLCJhIjoiY2l2dGRlc3E0MDAyMjJvcDhvOWh0Mm13NiJ9.oU7A9xn7BXzo4Q8PNjsZZg");

        setContentView(R.layout.activity_map);
        mapa = (MapView) findViewById(R.id.mapa);
        mapa.onCreate(savedInstanceState);
    }
}
