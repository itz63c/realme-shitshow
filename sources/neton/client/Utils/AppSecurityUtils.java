package neton.client.Utils;

import android.util.Base64;
import com.coloros.neton.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AppSecurityUtils {
    public static final String HEX = "0123456789ABCDEF";
    private static String[] binaryArray = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    public static String bytes2BinStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(binaryArray[(b & 240) >> 4]);
            sb.append(binaryArray[b & 15]);
        }
        return sb.toString();
    }

    public static String bin2HexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.valueOf(HEX.charAt((bArr[i] & 240) >> 4)) + String.valueOf(HEX.charAt(bArr[i] & 15)));
        }
        return sb.toString();
    }

    public static byte[] hexStr2BinArr(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (((byte) (HEX.indexOf(str.charAt(i * 2)) << 4)) | ((byte) HEX.indexOf(str.charAt((i * 2) + 1))));
        }
        return bArr;
    }

    public static String hexStr2BinStr(String str) {
        return bytes2BinStr(hexStr2BinArr(str));
    }

    public static String binToHex(byte[] bArr) {
        if (bArr == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    public static byte[] hexToBin(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String base64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public static String base64Decode(String str) {
        return new String(Base64.decode(str, 0), Charset.defaultCharset());
    }

    public static String md5Encode(String str) {
        MessageDigest messageDigest = null;
        if (str == null) {
            return null;
        }
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("md5 encode error", e2);
        }
        byte[] digest = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : digest) {
            if (Integer.toHexString(b & 255).length() == 1) {
                stringBuffer.append("0").append(Integer.toHexString(b & 255));
            } else {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
        }
        return stringBuffer.toString();
    }

    public static String getHmacSHA1(byte[] bArr, String str) {
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(new SecretKeySpec(str.getBytes("UTF-8"), instance.getAlgorithm()));
            return binToHex(instance.doFinal(bArr));
        } catch (Exception e) {
            throw new RuntimeException("HMAC-SHA1 encode error", e);
        }
    }

    public class ECDSA {
        private static final String KEY_ALGORITHM = "EC";

        public class ECDSAKey {
            private String privateKey;
            private String publicKey;

            public ECDSAKey(String str, String str2) {
                this.publicKey = str;
                this.privateKey = str2;
            }

            public String getPublicKey() {
                return this.publicKey;
            }

            public String getPrivateKey() {
                return this.privateKey;
            }

            public String toString() {
                return "ECDSAKey{publicKey='" + this.publicKey + '\'' + ", privateKey='" + this.privateKey + '\'' + '}';
            }
        }

        public static ECDSAKey genKey(int i) {
            try {
                KeyPairGenerator instance = KeyPairGenerator.getInstance(KEY_ALGORITHM);
                instance.initialize(i);
                KeyPair generateKeyPair = instance.generateKeyPair();
                return new ECDSAKey(AppSecurityUtils.binToHex(generateKeyPair.getPublic().getEncoded()), AppSecurityUtils.binToHex(generateKeyPair.getPrivate().getEncoded()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static byte[] sign(byte[] bArr, String str) {
            try {
                PrivateKey generatePrivate = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(AppSecurityUtils.hexToBin(str)));
                Signature instance = Signature.getInstance("SHA1withECDSA");
                instance.initSign(generatePrivate);
                instance.update(bArr);
                return instance.sign();
            } catch (Exception e) {
                throw new RuntimeException("sign with ecdsa error", e);
            }
        }

        public static boolean verify(byte[] bArr, byte[] bArr2, String str) {
            try {
                PublicKey generatePublic = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(AppSecurityUtils.hexToBin(str)));
                Signature instance = Signature.getInstance("SHA1withECDSA");
                instance.initVerify(generatePublic);
                instance.update(bArr);
                return instance.verify(bArr2);
            } catch (Exception e) {
                throw new RuntimeException("verify sign with ecdsa error", e);
            }
        }
    }

    public class RSA {
        public static final String KEY_ALGORITHM = "RSA";

        public class RSAKey {
            private String privateKey;
            private String publicKey;

            public String getPublicKey() {
                return this.publicKey;
            }

            public void setPublicKey(String str) {
                this.publicKey = str;
            }

            public String getPrivateKey() {
                return this.privateKey;
            }

            public void setPrivateKey(String str) {
                this.privateKey = str;
            }

            public String toString() {
                return "RSAKey{publicKey='" + this.publicKey + '\'' + ", privateKey='" + this.privateKey + '\'' + '}';
            }
        }

        public static byte[] sign(byte[] bArr, String str) {
            try {
                PrivateKey generatePrivate = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(AppSecurityUtils.hexToBin(str)));
                Signature instance = Signature.getInstance("SHA1withRSA");
                instance.initSign(generatePrivate);
                instance.update(bArr);
                return instance.sign();
            } catch (Exception e) {
                throw new RuntimeException("sign with rsa error", e);
            }
        }

        public static boolean verify(byte[] bArr, byte[] bArr2, String str) {
            try {
                PublicKey generatePublic = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(AppSecurityUtils.hexToBin(str)));
                Signature instance = Signature.getInstance("SHA1withRSA");
                instance.initVerify(generatePublic);
                instance.update(bArr);
                return instance.verify(bArr2);
            } catch (Exception e) {
                throw new RuntimeException("verify sign with rsa error", e);
            }
        }

        public static byte[] decryptByPrivateKey(byte[] bArr, byte[] bArr2) {
            try {
                PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bArr2);
                KeyFactory instance = KeyFactory.getInstance(KEY_ALGORITHM);
                PrivateKey generatePrivate = instance.generatePrivate(pKCS8EncodedKeySpec);
                Cipher instance2 = Cipher.getInstance(instance.getAlgorithm());
                instance2.init(2, generatePrivate);
                return instance2.doFinal(bArr);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static byte[] decryptByPrivateKey(byte[] bArr, String str) {
            return decryptByPrivateKey(bArr, AppSecurityUtils.hexToBin(str));
        }

        public static byte[] encryptByPublicKey(byte[] bArr, byte[] bArr2) {
            try {
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bArr2);
                KeyFactory instance = KeyFactory.getInstance(KEY_ALGORITHM);
                PublicKey generatePublic = instance.generatePublic(x509EncodedKeySpec);
                Cipher instance2 = Cipher.getInstance(instance.getAlgorithm());
                instance2.init(1, generatePublic);
                return instance2.doFinal(bArr);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static byte[] encryptByPublicKey(byte[] bArr, String str) {
            return encryptByPublicKey(bArr, AppSecurityUtils.hexToBin(str));
        }

        public static RSAKey genKey(int i) {
            try {
                KeyPairGenerator instance = KeyPairGenerator.getInstance(KEY_ALGORITHM);
                instance.initialize(i);
                KeyPair generateKeyPair = instance.generateKeyPair();
                PublicKey publicKey = generateKeyPair.getPublic();
                PrivateKey privateKey = generateKeyPair.getPrivate();
                RSAKey rSAKey = new RSAKey();
                rSAKey.setPublicKey(AppSecurityUtils.binToHex(publicKey.getEncoded()));
                rSAKey.setPrivateKey(AppSecurityUtils.binToHex(privateKey.getEncoded()));
                return rSAKey;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class ThreeDes {
        public static final String algorithm = "DESede";

        public class ECB {
            public static final String ECB = "DESede/ECB/PKCS5Padding";

            public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
                try {
                    Key access$000 = ThreeDes.toKey(bArr2);
                    Cipher instance = Cipher.getInstance(ECB);
                    instance.init(2, access$000);
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
                try {
                    Key access$000 = ThreeDes.toKey(bArr2);
                    Cipher instance = Cipher.getInstance(ECB);
                    instance.init(1, access$000);
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public class CBC {
            public static final String CBC = "DESede/CBC/PKCS5Padding";

            public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                try {
                    Key access$000 = ThreeDes.toKey(bArr2);
                    Cipher instance = Cipher.getInstance(CBC);
                    instance.init(2, access$000, new IvParameterSpec(bArr3));
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                try {
                    Key access$000 = ThreeDes.toKey(bArr2);
                    Cipher instance = Cipher.getInstance(CBC);
                    instance.init(1, access$000, new IvParameterSpec(bArr3));
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public static byte[] genKey(int i) {
            try {
                KeyGenerator instance = KeyGenerator.getInstance(algorithm);
                instance.init(i);
                return instance.generateKey().getEncoded();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public static Key toKey(byte[] bArr) {
            try {
                return SecretKeyFactory.getInstance(algorithm).generateSecret(new DESedeKeySpec(bArr));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class AES {
        public static byte[] genKey(int i) {
            try {
                KeyGenerator instance = KeyGenerator.getInstance("AES");
                instance.init(i);
                return instance.generateKey().getEncoded();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public class ECB {
            private static final String ECB = "AES/ECB/PKCS5Padding";

            public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
                    Cipher instance = Cipher.getInstance(ECB);
                    instance.init(1, secretKeySpec);
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public static byte[] encrypt(byte[] bArr, String str) {
                return encrypt(bArr, AppSecurityUtils.hexToBin(str));
            }

            public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
                    Cipher instance = Cipher.getInstance(ECB);
                    instance.init(2, secretKeySpec);
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public static byte[] decrypt(byte[] bArr, String str) {
                return decrypt(bArr, AppSecurityUtils.hexToBin(str));
            }
        }

        public class CBC {
            private static final String CBC = "AES/CBC/PKCS5Padding";

            public static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
                    Cipher instance = Cipher.getInstance(CBC);
                    instance.init(1, secretKeySpec, new IvParameterSpec(bArr3));
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public static byte[] encrypt(byte[] bArr, String str, byte[] bArr2) {
                return encrypt(bArr, AppSecurityUtils.hexToBin(str), bArr2);
            }

            public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
                    Cipher instance = Cipher.getInstance(CBC);
                    instance.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                    return instance.doFinal(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public static byte[] decrypt(byte[] bArr, String str, byte[] bArr2) {
                return decrypt(bArr, AppSecurityUtils.hexToBin(str), bArr2);
            }
        }
    }

    public static void main(String[] strArr) {
        System.out.println(getHmacSHA1("我的神啊巴厘岛".getBytes(), "123"));
        ECDSA.ECDSAKey genKey = ECDSA.genKey(256);
        System.out.println("ecdsaKey:" + genKey);
        System.out.println("ecdsa public key size=" + hexToBin(genKey.getPublicKey()).length);
        System.out.println("ecdsa private key size=" + genKey.getPrivateKey().length());
        byte[] sign = ECDSA.sign("我的神啊巴厘岛".getBytes(), genKey.getPrivateKey());
        System.out.println("ecdsa sign size=" + sign.length + ", hex=" + binToHex(sign));
        System.out.println("ecdsa verify result=" + ECDSA.verify("我的神啊巴厘岛".getBytes(), sign, genKey.getPublicKey()));
        RSA.RSAKey genKey2 = RSA.genKey(2048);
        System.out.println("rsaKey:" + genKey2);
        System.out.println("rsa public key size=" + hexToBin(genKey2.getPublicKey()).length);
        System.out.println("rea private key size=" + genKey2.getPrivateKey().length());
        byte[] sign2 = RSA.sign("我的神啊巴厘岛".getBytes(), genKey2.getPrivateKey());
        System.out.println("rsa sign size=" + sign2.length + ", hex=" + binToHex(sign2));
        System.out.println("rsa verify result=" + RSA.verify("我的神啊巴厘岛".getBytes(), sign2, genKey2.getPublicKey()));
        System.out.println("rsa decrypt data:" + new String(RSA.decryptByPrivateKey(RSA.encryptByPublicKey("我的神啊巴厘岛".getBytes("UTF-8"), genKey2.getPublicKey()), genKey2.getPrivateKey()), "UTF-8"));
        byte[] genKey3 = AES.genKey(128);
        System.out.println("aes ecb decrypt data:" + new String(AES.ECB.decrypt(AES.ECB.encrypt("我的神啊巴厘岛".getBytes("UTF-8"), genKey3), genKey3), "UTF-8"));
        byte[] genKey4 = AES.genKey(128);
        byte[] bytes = "1234567812345678".getBytes();
        System.out.println("aes cbc decrypt data:" + new String(AES.CBC.decrypt(AES.CBC.encrypt("我的神啊巴厘岛".getBytes("UTF-8"), genKey4, bytes), genKey4, bytes), "UTF-8"));
        byte[] genKey5 = ThreeDes.genKey(168);
        System.out.println("ThreeDes ecb decrypt data:" + new String(ThreeDes.ECB.decrypt(ThreeDes.ECB.encrypt("我的神啊巴厘岛".getBytes("UTF-8"), genKey5), genKey5), "UTF-8"));
        byte[] genKey6 = ThreeDes.genKey(168);
        byte[] bytes2 = "12345678".getBytes();
        System.out.println("ThreeDes cbc decrypt data:" + new String(ThreeDes.CBC.decrypt(ThreeDes.CBC.encrypt("我的神啊巴厘岛".getBytes("UTF-8"), genKey6, bytes2), genKey6, bytes2), "UTF-8"));
    }
}
