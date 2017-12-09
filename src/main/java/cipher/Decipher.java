package cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class Decipher {
    private String result;

    private static KeyGenerator keyGenerator;
    private static SecureRandom random;
    private static Cipher cipher;

    static {
        random = new SecureRandom();
        try {
            cipher = Cipher.getInstance("AES/OFB8/PKCS5Padding");
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Decipher(String password, String cipherText) {
        System.out.println(cipherText);
        cipherText = cipherText.substring(1, cipherText.length() - 1);
        try {
            byte[] total = Base64.getDecoder().decode(cipherText);
            IvParameterSpec parameterSpec = new IvParameterSpec(total, 0, cipher.getBlockSize());

            random.setSeed(password.getBytes("UTF-8"));
            keyGenerator.init(random);
            SecretKey key = keyGenerator.generateKey();

            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

            byte[] ct = Arrays.copyOfRange(total, cipher.getBlockSize(), total.length);
            byte[] pt = cipher.doFinal(ct);
            result = new String(pt, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();
        }

    }

    public String getResult() {
        return result;
    }
}
