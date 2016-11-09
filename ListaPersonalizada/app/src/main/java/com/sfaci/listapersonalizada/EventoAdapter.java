package com.sfaci.listapersonalizada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dam on 4/11/16.
 */
public class EventoAdapter extends BaseAdapter {

    private Context contexto;
    private int layoutId;
    private ArrayList<Evento> listaEventos;

    public EventoAdapter(Context contexto, int layoutId,
                         ArrayList<Evento> listaEventos) {
        this.contexto = contexto;
        this.layoutId = layoutId;
        this.listaEventos = listaEventos;
    }

    @Override
    public int getCount() {
        return listaEventos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaEventos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(layoutId, null);

            holder = new ViewHolder();
            holder.foto = (ImageView) view.findViewById(R.id.ivFoto);
            holder.nombre = (TextView) view.findViewById(R.id.tvNombre);
            holder.precio = (TextView) view.findViewById(R.id.tvPrecio);
            holder.direccion = (TextView) view.findViewById(R.id.tvDireccion);

            view.setTag(holder);
        }
    /*
     * En caso de que la View no sea null se reutilizará con los
     * nuevos valores
     */
        else {
            holder = (ViewHolder) view.getTag();
        }

        Evento evento = listaEventos.get(position);
        holder.foto.setImageBitmap(evento.getFoto());
        holder.nombre.setText(evento.getNombre());
        holder.precio.setText(String.valueOf(evento.getPrecio()));
        holder.direccion.setText(evento.getDireccion());

        return view;
    }

    static class ViewHolder {
        ImageView foto;
        TextView nombre;
        TextView precio;
        TextView direccion;
    }
}
