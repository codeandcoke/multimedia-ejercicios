package com.sfaci.puntoswifi.util;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;

/**
 * Created by dam on 25/11/16.
 */
public class Util {

    public static LatLng DeUMTSaLatLng(double este, double oeste) {

        UTMRef utm = new UTMRef(este, oeste, 'N', 30);

        return utm.toLatLng();
    }
}
