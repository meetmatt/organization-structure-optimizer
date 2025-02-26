package lu.golikov.oso.Util;

public class FileUtil {
    public static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return ""; // No extension found
        }
        return filename.substring(lastDotIndex + 1);
    }
}