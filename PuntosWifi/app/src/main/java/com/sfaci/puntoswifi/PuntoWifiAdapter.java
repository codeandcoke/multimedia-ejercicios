package com.sfaci.puntoswifi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by dam on 15/11/16.
 */
public class PuntoWifiAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<PuntoWifi> puntosWifi;

    public PuntoWifiAdapter(Context contexto, ArrayList<PuntoWifi> puntosWifi) {
        this.contexto = contexto;
        this.puntosWifi = puntosWifi;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
