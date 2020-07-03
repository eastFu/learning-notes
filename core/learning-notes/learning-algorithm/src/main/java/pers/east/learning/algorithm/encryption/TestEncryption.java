package pers.east.learning.algorithm.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class TestEncryption {

    /**
     *  DES AES DESede
     */
    private static final String KEY_ALGORITHM = "DES";

    /**
     * padding type:  NoPadding  PKCS5Padding   PKCS1Padding   ISO10126Padding  OAEPPadding
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/NoPadding";



    public static void main(String[] args) throws Exception{
        getDesKey1("1050629");
        System.out.println(desEncry("12341234","1050629"));
    }

    public static String desEncry(String text,String key) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] raw=new byte[]{105,214-256,68,92,187-256,159-256,59,199-256};
//        raw=getDesKey1(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return byteToHexString(encrypted);
    }

    public static String desDecry(String text,String key){
        return "";
    }


    public static byte[] getDesKey1(String key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException {
        byte[] mode = new byte[8];
        byte[] temp = key.getBytes("gbk");
        System.arraycopy(temp, 0, mode, 0, temp.length);

        KeyGenerator gen = KeyGenerator.getInstance("DES");// 创建AES的Key生产者

        gen.init(56, new SecureRandom(mode));
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

        SecretKey secretKey = gen.generateKey();// 根据用户密码，生成一个密钥

        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。
        System.out.println(Arrays.toString(enCodeFormat));
        return enCodeFormat;
    }

    public static void getDesKey2(String key){

    }

    public static String byteToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            String ch = Integer.toHexString(data[i] & 0xFF).toUpperCase();
            if (ch.length() == 2)
                sb.append(ch);
            else
                sb.append("0").append(ch);
        }
        return sb.toString();
    }

    public static byte[] stringTohexByte(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
