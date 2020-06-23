package pers.east.learning.algorithm.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class DesCbcSecurity {

    public static final String SECRET_DES    = "DES";
    public static final String ALGORITHM_DES = "DES/OFB/NoPadding";


    public static void main(String[] args) throws Exception {
        String aaaString = "E99A18C428CB38D5F260853678922E03";
        String key = "1234567812345678";
        System.out.println("加密前：" + aaaString);
        String encrypedString = encrypt(aaaString, key);

        System.out.println("加密后：" + encrypedString);
        String bString = decrypt(encrypedString, key);
        System.out.println("解密后：" + bString);
    }
    /**
     * 加密
     * @author xiaoliang.chen
     * 2017年12月16日 下午12:59:28
     * @param content
     * @param key
     * @return
     */
    public static String encrypt(String content, String key) {
        System.out.println(Arrays.toString(key.getBytes()));
        byte[] bt=new byte[]{77,162-256,54,229-256,95,6,199-256,69};
        System.out.println(new String(bt));
        return byteToHexString(encrypt(content.getBytes(), bt));
    }

    public static byte[] encrypt(byte[] content, byte[] keyBytes) {
        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_DES);
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            System.out.println("kkkkkk===="+Arrays.toString(keySpec.getKey()));
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keySpec.getKey()));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密
     * @author xiaoliang.chen
     * 2017年12月16日 下午1:01:01
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        byte[] contentBytes = hexStringToBytes(content);
        return decrypt(contentBytes, key.getBytes());
    }

    public static String decrypt(byte[] content, byte[] keyBytes) {
        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_DES);
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keyBytes));
            byte[] result = cipher.doFinal(content);
            String contentString = new String(result);
            return contentString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
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

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
