package com.sfaci.puntoswifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dam on 15/11/16.
 */
public class PuntoWifiAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<PuntoWifi> puntosWifi;
    private LayoutInflater inflater;

    public PuntoWifiAdapter(Context contexto, ArrayList<PuntoWifi> puntosWifi) {
        this.contexto = contexto;
        this.puntosWifi = puntosWifi;
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return puntosWifi.size();
    }

    @Override
    public PuntoWifi getItem(int i) {
        return puntosWifi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;

        // Si la View es null se crea de nuevo
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.puntowifi, null);

            holder = new ViewHolder();
            holder.foto = (ImageView) convertView.findViewById(R.id.ivImagen);
            holder.nombre = (TextView) convertView.findViewById(R.id.tvNombre);
            holder.descripcion = (TextView) convertView.findViewById(R.id.tvDescripcion);

            convertView.setTag(holder);
        }
/*
 * En caso de que la View no sea null se reutilizará con los
 * nuevos valores
 */
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        PuntoWifi puntoWifi = puntosWifi.get(i);
        holder.nombre.setText(puntoWifi.getNombre());
        holder.descripcion.setText(puntoWifi.getDescripcion());

        return convertView;
    }

    static class ViewHolder {
        ImageView foto;
        TextView nombre;
        TextView descripcion;
    }
}
