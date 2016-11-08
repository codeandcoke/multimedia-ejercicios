package com.sfaci.listapersonalizada.util;

import android.graphics.Bitmap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dam on 8/11/16.
 */
public class Util {

    public static Date parseFecha(String fecha)
    throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.parse(fecha);
    }

    public static String formatFecha(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.format(fecha);
    }

    public static byte[] getBytes(Bitmap imagen) {

        return null;
    }

    public static Bitmap getBitmap(byte[] imagen) {

        return null;
    }
}
