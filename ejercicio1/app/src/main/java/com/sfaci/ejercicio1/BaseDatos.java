package com.sfaci.ejercicio1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dam on 25/10/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private static final String NOMBRE = "recordatorios.db";
    private static final int VERSION = 1;

    private static final String TABLA_TAREAS = "tareas";
    private static final String[] SELECT = {"nombre", "hecha"};
    private static final String ORDER_BY = "nombre ASC";

    public BaseDatos(Context context) {
        super(context, NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_TAREAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, hecha BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLA_TAREAS);
        onCreate(db);
    }

    public void nuevaTarea(Tarea tarea) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", tarea.getNombre());
        valores.put("hecha", tarea.estaHecha());

        db.insertOrThrow(TABLA_TAREAS, null, valores);
        db.close();

    }

    public void eliminarTarea(String nombreTarea) {

    }

    public void hacerTarea(String nombreTarea) {

    }

    public ArrayList<String> obtenerTareas() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT,
                null, null, null, null, ORDER_BY);

        ArrayList<String> tareas = new ArrayList<>();

        while (cursor.moveToNext()) {
            tareas.add(cursor.getString(0));
        }
        db.close();

        return tareas;
    }

    public ArrayList<String> otenerTareas(boolean hecha) {

        return null;
    }
}
