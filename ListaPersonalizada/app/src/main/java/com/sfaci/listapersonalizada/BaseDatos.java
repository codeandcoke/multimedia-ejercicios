package com.sfaci.listapersonalizada;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sfaci.listapersonalizada.util.Util;

import java.util.ArrayList;

/**
 * Created by dam on 8/11/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private static final String NOMBRE = "eventos.db";
    private static final int VERSION = 1;

    public BaseDatos(Context contexto) {
        super(contexto, NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void nuevoEvento(Evento evento) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", evento.getNombre());
        valores.put("direccion", evento.getDireccion());
        valores.put("fecha", Util.formatFecha(evento.getFecha()));
        valores.put("precio", String.valueOf(evento.getPrecio()));
        valores.put("foto", Util.getBytes(evento.getFoto()));

        db.insertOrThrow("eventos", null, valores);
    }

    public ArrayList<Evento> obtenerEventos() {

        ArrayList<Evento> eventos = new ArrayList<>();



        return eventos;
    }
}
