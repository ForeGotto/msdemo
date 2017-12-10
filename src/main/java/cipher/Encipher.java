package cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class Encipher {

    private String result;

    public Encipher(String password, String plaintext) {
        try {

            plaintext = plaintext.substring(1, plaintext.length() - 1);

            SecureRandom random = new SecureRandom(password.getBytes("UTF-8"));
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(random);
            SecretKey key = keyGenerator.generateKey();

            Cipher cipher = Cipher.getInstance("AES/OFB8/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] ivArray = cipher.getIV();
            byte[] ctArray = cipher.doFinal(plaintext.getBytes("UTF-8"));
            byte[] total = new byte[ivArray.length + ctArray.length];
            System.arraycopy(ivArray, 0, total, 0, ivArray.length);
            System.arraycopy(ctArray, 0, total, ivArray.length, ctArray.length);

            result = Base64.getEncoder().encodeToString(total);

        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();
        }

    }

    public String getResult() {
        return result;
    }

}
