package wonton.utils;

public class Utils {
    public static String checkCamelCase(String text) {
        if(!text.equals(text.toLowerCase())){
            text = "`" + text + "`";
        }
        return text;
    }
}
