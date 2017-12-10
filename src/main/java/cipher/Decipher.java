package cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class Decipher {
    private String result;

    public Decipher(String password, String cipherText) {
        cipherText = cipherText.substring(1, cipherText.length() - 1);
        cipherText = cipherText.replaceAll(" ", "+");

        try {
            Cipher cipher = Cipher.getInstance("AES/OFB8/PKCS5Padding");

            byte[] total = Base64.getDecoder().decode(cipherText);
            IvParameterSpec parameterSpec = new IvParameterSpec(total, 0, cipher.getBlockSize());

            SecureRandom random = new SecureRandom(password.getBytes("UTF-8"));
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
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
