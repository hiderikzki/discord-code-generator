package util;

public class PathUtil {
    public static String getDesktop() {
        return System.getProperty("user.home") + "/Desktop";
    }

    public static String getCustomDesktop(String loc) {
        return System.getProperty("user.home") + "/Desktop/" + loc;
    }
}
