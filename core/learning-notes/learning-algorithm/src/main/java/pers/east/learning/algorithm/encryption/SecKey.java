package pers.east.learning.algorithm.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class SecKey {

    public static byte[] key1(String key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        Provider provider = new BouncyCastleProvider();
        KeySpec keySpec = new DESedeKeySpec(hex(key));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede", provider);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        return secretKey.getEncoded();
    }

    public static Key get3DESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168);
        Key key = keyGenerator.generateKey();
        System.out.println(Arrays.toString(key.getEncoded()));
        return key;
    }


    public static byte[] hex(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
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

    public static void main(String[] args) throws Exception{
        System.out.println(Arrays.toString(key1("1234567")));
        get3DESKey();
    }
}
