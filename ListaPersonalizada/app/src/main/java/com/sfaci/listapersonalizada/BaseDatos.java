package com.sfaci.listapersonalizada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private final String TABLA_EVENTOS = "eventos";

    public BaseDatos(Context contexto) {
        super(contexto, NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_EVENTOS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, direccion TEXT," +
                "precio REAL, fecha TEXT, foto BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLA_EVENTOS);
        onCreate(db);
    }

    public void nuevoEvento(Evento evento) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", evento.getNombre());
        valores.put("direccion", evento.getDireccion());
        valores.put("precio", String.valueOf(evento.getPrecio()));
        valores.put("fecha", Util.formatFecha(evento.getFecha()));
        valores.put("foto", Util.getBytes(evento.getFoto()));

        db.insertOrThrow(TABLA_EVENTOS, null, valores);
        db.close();
    }

    public ArrayList<Evento> obtenerEventos() {

        ArrayList<Evento> eventos = new ArrayList<>();
        String[] SELECT = {"nombre", "direccion", "precio", "foto"};

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_EVENTOS, SELECT, null, null, null, null,
                null);

        Evento evento = null;
        while (cursor.moveToNext()) {
            evento = new Evento();
            evento.setNombre(cursor.getString(0));
            evento.setDireccion(cursor.getString(1));
            evento.setPrecio(cursor.getFloat(2));
            evento.setFoto(Util.getBitmap(cursor.getBlob(3)));

            eventos.add(evento);
        }
        cursor.close();

        return eventos;
    }
}
