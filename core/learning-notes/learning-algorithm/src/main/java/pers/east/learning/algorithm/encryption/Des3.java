package pers.east.learning.algorithm.encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Des3 {

    private static final String KEY_ALGORITHM = "DESede";

    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/NoPadding";// 默认的加密算法

    private static final String CBC = "DESede/CBC/NoPadding";

    private static final String OFB = "DESede/OFB/NoPadding";

    private static final String CFB = "DESede/CFB/NoPadding";

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey = "10506290";
    private static String ivParameter = "13223302";

    // 加密
    public static String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
//        byte[] raw = sKey.getBytes();
        byte[] raw = hex(sKey);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
        byte[] bt=new byte[]{1,35,69,103,137-256,171-256,205-256,239-256};
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes("gbk"));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return getHexString(encrypted);
    }

    public static byte[] hex(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("gbk");    //将字符串转成字节数组
        /*
         * 执行数组拷贝
         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if(key.length > temp.length){
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        }else{
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }


    /*
     * 转换为16进制数
     * @param data
     * @return
     *        16进制数
     */
    public static String getHexString(byte[] data) {
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

    /**
     * 将16进制的字符串转换为byte数组
     * @param hexString
     * @return
     */
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

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    /**
     * byte数组转hex
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes){
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

    // 解密
    public static String decrypt(String sSrc) throws Exception {
        try {
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] encrypted1 = hexStringToBytes(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        // 加密
        String enString = Des3.encrypt("E99A18C428CB38D5F260853678922E03");
        System.out.println("加密后的字串是：" + enString);
        System.out.println(Des3.decrypt(enString));
    }

}
