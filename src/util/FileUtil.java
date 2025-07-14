package util;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.Base64;

public class FileUtil {

    public static String fileToBase64(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void base64ToFile(String base64, String destPath) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(base64);
        Files.write(Paths.get(destPath), bytes);
    }

    public static String hashFile(String path) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
