package sfaci.com.videojuegos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static sfaci.com.videojuegos.util.Constantes.FORMATO_FECHA;

/**
 * Created by Santi on 04/10/16.
 */
public class Util {

    public static Date parseFecha(String fecha) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
        return sdf.parse(fecha);
    }

    public static String formatFecha(Date fecha) {

        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
        return sdf.format(fecha);
    }
}
