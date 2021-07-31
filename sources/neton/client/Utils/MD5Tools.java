package neton.client.Utils;

import com.coloros.neton.BuildConfig;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tools {
    public static String calcMD5(String str) {
        return calcMD5(str.getBytes(Charset.defaultCharset()));
    }

    public static String calcMD5(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toString(b2, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return BuildConfig.FLAVOR;
        }
    }
}
