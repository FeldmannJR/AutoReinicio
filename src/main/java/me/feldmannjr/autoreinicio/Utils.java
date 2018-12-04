package me.feldmannjr.autoreinicio;

public class Utils {
    public static String millisToString(long duration) {
        StringBuffer res = new StringBuffer();
        long temp = 0L;
        if (duration >= 1000L) {
            temp = duration / 86400000L;
            if (temp > 0L) {
                duration -= temp * 86400000L;
                res.append(temp).append(" dia").append(temp > 1L ? "s" : "").append(duration >= 60000L ? ", " : "");
            }

            temp = duration / 3600000L;
            if (temp > 0L) {
                duration -= temp * 3600000L;
                res.append(temp).append(" hora").append(temp > 1L ? "s" : "").append(duration >= 60000L ? ", " : "");
            }

            temp = duration / 60000L;
            if (temp > 0L) {
                duration -= temp * 60000L;
                res.append(temp).append(" minuto").append(temp > 1L ? "s" : "");
            }

            if (!res.toString().equals("") && duration >= 1000L) {
                res.append(" and ");
            }

            temp = duration / 1000L;
            if (temp > 0L) {
                res.append(temp).append(" segundo").append(temp > 1L ? "s" : "");
            }

            return res.toString();
        } else {
            return "0 segundos";
        }
    }
}
