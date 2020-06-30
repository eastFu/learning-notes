package pers.east.learning.algorithm.encryption;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Des32 {

    private static final String KEY_ALGORITHM = "DES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/NoPadding";// 默认的加密算法

    private static String sKey = "";

    // 加密
    public static String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
//        byte[] raw = sKey.getBytes();
        byte[] raw = hex(sKey);

        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return getHexString(encrypted);
    }

    public static byte[] hex(String keyStr) throws UnsupportedEncodingException {
        //声明一个24位的字节数组，默认里面都是0
        byte[] key = new byte[]{1,35,69,103,137-256,171-256,205-256,239-256};
//        byte[] key = new byte[]{1,35,69,103,137-256,171-256,205-256,239-256,254-256,220-256,186-256,152-256,118,84,50,16,137-256,171-256,205-256,239-256,1,35,69,103};
        byte[] temp = keyStr.getBytes("gbk");    //将字符串转成字节数组

        //System.out.println(Arrays.toString(temp));
        //temp=new byte[]{105,214-256,68,92,187-256,159-256,59,199-256};
        if(key.length > temp.length){
            System.arraycopy(temp, 0, key, 0, temp.length);
        }else{
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

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


    public static void main(String[] args) throws Exception {
        // 加密
//        String enString = Des3.encrypt("E99A18C428CB38D5F260853678922E03");
        /*String enString = Des32.encrypt("12341234");
        System.out.println("加密后的字串是：" + enString);
        System.out.println(Des3.decrypt(enString));*/



        byte[] key=new byte[]{48,19,112,87,191-256,153-256,244-256,239-256};
        String enString = Des32.encrypt(new String(key));
        System.out.println("加密后的字串是：" + enString);
    }

}
