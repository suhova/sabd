package app;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ObfuscationUtils {
    static SecretKeySpec secretKeySpec = new SecretKeySpec("a76nb5h900007777a76nb5h9".getBytes(StandardCharsets.UTF_8), "TripleDES");

    public static String obfuscate(String input) {
        try {
            Cipher cipher = Cipher.getInstance("TripleDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] dec = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
            byte[] utf8 = cipher.doFinal(dec);
            return Arrays.toString(utf8)
                .replace("[", "")
                .replace("]", "")
                .replaceAll(", ", "_");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static String unobfuscate(String input) {
        try {
            Cipher cipher = Cipher.getInstance("TripleDES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            List<String> list = Arrays.stream(input.split("_")).collect(Collectors.toList());
            byte[] arr = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = Byte.parseByte(list.get(i));
            }
            byte[] utf8 = cipher.doFinal(arr);
            byte[] dec = Base64.getDecoder().decode(utf8);
            return new String(dec, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
