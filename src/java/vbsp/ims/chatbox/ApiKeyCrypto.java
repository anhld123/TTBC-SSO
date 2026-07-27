package vbsp.ims.chatbox;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class ApiKeyCrypto {

    private static final String SECRET_KEY = "My@VeryLong#Password!2026$ABC";

    private static SecretKeySpec getSecretKey() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] keyBytes = digest.digest(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );

        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec key = getSecretKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(
                plainText.getBytes(StandardCharsets.UTF_8)
        );

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec key = getSecretKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decrypted = cipher.doFinal(
                Base64.getDecoder().decode(encryptedText)
        );

        return new String(
                decrypted,
                StandardCharsets.UTF_8
        );
    }
}