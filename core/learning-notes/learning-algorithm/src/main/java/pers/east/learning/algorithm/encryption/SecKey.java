package pers.east.learning.algorithm.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
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

    /*
     * 密钥的长度: DES算法，则密钥长度必须是56位； DESede，则可以是112或168位，其中112位有效； AES，可以是128,
     * 192或256位； Blowfish，则可以是32至448之间可以被8整除的数； HmacMD5和HmacSHA1默认的密钥长度都是64个字节。
     */
    public static byte[] getKey(String password) throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("DESede");// 创建AES的Key生产者

//        byte[] temp=new byte[]{48,19,112,87,191-256,153-256,244-256,239-256};
//        byte[] temp=new byte[]{1, 35, 69, 103, 137-256, 171-256, 205-256, 239-256};
//        kgen.init(168, new SecureRandom(temp));// 利用用户密码作为随机数初始化出
        kgen.init(112, new SecureRandom(password.getBytes()));
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
//        System.out.println(kgen.getAlgorithm());
//        System.out.println(kgen.getProvider());

        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。
        return enCodeFormat;
    }


    public static void get3DesKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
        Key deskey = keyfactory.generateSecret(spec);
        System.out.println(deskey.getEncoded());
    }


    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }


    public static void Cripto(String Password)
    {
        String PasswordCripto = "";
        try
        {
            String encryptionKey = "anyEncryptionString";
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(encryptionKey.getBytes("UTF-8"), 0, encryptionKey.length());
            byte[] encryptionKeyBytes = messageDigest.digest();

            SecretKeySpec Key = new SecretKeySpec(encryptionKeyBytes,"DESede");
            Cipher cipher = Cipher.getInstance("DESEDE/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, Key);
            byte[] encryptedBytes = cipher.doFinal("12341234".getBytes("UTF-8"));

            System.out.println(Arrays.toString(encryptedBytes));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
//        System.out.println(Arrays.toString(key1("10506290")));
//        get3DESKey();

//        System.out.println(Arrays.toString("10506290".getBytes("gbk")));
//        System.out.println(Arrays.toString(getKey("1050629")));


//        System.out.println(Arrays.toString(encrypt("12341234".getBytes("gbk"),"10506290".getBytes("gbk"))));

        Cripto("");
    }
}
