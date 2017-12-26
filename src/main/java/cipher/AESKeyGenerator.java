package cipher;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author fotg
 */
public class AESKeyGenerator {
    static SecretKey getKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, DigestException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] keyBytes = new byte[16];
        md5.update(password.getBytes("UTF-8"));
        md5.digest(keyBytes, 0, keyBytes.length);
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }
}
