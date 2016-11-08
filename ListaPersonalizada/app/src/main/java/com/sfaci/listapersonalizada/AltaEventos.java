package com.sfaci.listapersonalizada;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sfaci.listapersonalizada.util.Util;

import java.text.ParseException;

public class AltaEventos extends AppCompatActivity
    implements View.OnClickListener {

    private int RESULTADO_CARGA_IMAGEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_eventos);

        Button btAlta = (Button) findViewById(R.id.btAlta);
        btAlta.setOnClickListener(this);
        Button btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(this);
        ImageView ivFoto = (ImageView) findViewById(R.id.ivFoto);
        ivFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btAlta:
                EditText etNombre = (EditText) findViewById(R.id.etNombre);
                EditText etDireccion = (EditText) findViewById(R.id.etDireccion);
                EditText etFecha = (EditText) findViewById(R.id.etFecha);
                EditText etPrecio = (EditText) findViewById(R.id.etPrecio);
                ImageView ivFoto = (ImageView) findViewById(R.id.ivFoto);

                try {
                    Evento evento = new Evento();
                    evento.setNombre(etNombre.getText().toString());
                    evento.setDireccion(etDireccion.getText().toString());
                    evento.setFecha(Util.parseFecha(etFecha.getText().toString()));
                    evento.setPrecio(Float.parseFloat(etPrecio.getText().toString()));
                    evento.setFoto(((BitmapDrawable) ivFoto.getDrawable()).getBitmap());

                    BaseDatos db = new BaseDatos(this);
                    db.nuevoEvento(evento);
                } catch (ParseException pe) {
                    Toast.makeText(this, R.string.error_formato_fecha,
                            Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.ivFoto:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULTADO_CARGA_IMAGEN);
                break;
            case R.id.btCancelar:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == RESULTADO_CARGA_IMAGEN) && (resultCode == RESULT_OK)
                && (data != null)) {
            // Obtiene el Uri de la imagen seleccionada por el usuario
            Uri imagenSeleccionada = data.getData();
            String[] ruta = {MediaStore.Images.Media.DATA };

            // Realiza una consulta a la galería de imágenes solicitando la imagen seleccionada
            Cursor cursor = getContentResolver().query(imagenSeleccionada, ruta, null, null, null);
            cursor.moveToFirst();

            // Obtiene la ruta a la imagen
            int indice = cursor.getColumnIndex(ruta[0]);
            String picturePath = cursor.getString(indice);
            cursor.close();

            // Carga la imagen en una vista ImageView que se encuentra en
            // en layout de la Activity actual
            ImageView imageView = (ImageView) findViewById(R.id.ivFoto);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
