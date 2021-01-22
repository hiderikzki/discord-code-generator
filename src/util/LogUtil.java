package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    public static void addLog(String details) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss] ");
        System.out.println(formatter.format(currentTime) + details);
    }

    public static void addDebugLog(String details) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss] ");
        System.out.println(formatter.format(currentTime) + "[DEBUG] " + details);
    }

    public static void addWarnLog(String details) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss] ");
        System.out.println(formatter.format(currentTime) + "[WARN] " + details);
    }
}
