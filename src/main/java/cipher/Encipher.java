package cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Encipher {


    private String result;
    private static KeyGenerator keyGenerator;
    private static SecureRandom random;
    private static Cipher cipher;

    static {
        random = new SecureRandom();
        try {
            cipher = Cipher.getInstance("AES/OFB8/PKCS5Padding");
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public Encipher(String password, String plaintext) {
        try {

            plaintext = plaintext.substring(1, plaintext.length() - 1);
//            System.out.println(plaintext);

            random.setSeed(password.getBytes("UTF-8"));
            keyGenerator.init(random);
            SecretKey key = keyGenerator.generateKey();
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
