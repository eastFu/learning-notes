package pers.east.learning.algorithm;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Title: AesUtil.java
 * @Description: AES 加密-解密工具类
 * @author FuDongFang
 * @date 2015年11月10日 上午11:57:02
 * @version V1.0
 */

public class AesUtil {

    private static final String AESTYPE = "AES";// 加密方式
    /*
     * 密钥的长度: DES算法，则密钥长度必须是56位； DESede，则可以是112或168位，其中112位有效； AES，可以是128,
     * 192或256位； Blowfish，则可以是32至448之间可以被8整除的数； HmacMD5和HmacSHA1默认的密钥长度都是64个字节。
     */
    private static final int KEY_LENGTH = 128;

    /**
     * @Title: aesEncrypt
     * @Description: AES 加密:因为加密后的byte数组是不能强制转换成字符串的，换言之：字符串和byte数组在这种情况下不是互逆的
     *               <p>
     *               这里将Base64.encodeBase64(byte[])(同样也可以使用二进制byte[]
     *               转化为16进制字符串返回)
     *               </p>
     * @param keyStr key
     * @param plainText	需要加密的字符串
     * @return String 返回AES加密后的字符串
     */
    public static String aesEncrypt(String keyStr, String plainText) throws Exception{
        byte[] encrypt = null;
//		SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), AESTYPE);
        Cipher cipher = Cipher.getInstance(AESTYPE);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(keyStr));
        encrypt = cipher.doFinal(plainText.getBytes("utf-8"));
        return parseByte2HexStr(encrypt);
        //return Base64.getEncoder().encodeToString(encrypt);
    }

    /**
     * @Title: aesDecrypt
     * @Description: AES 解密：如果用Base加密的byte[]，用Base64.decodeBase64(byte[])进行处理后解密
     *               <p>
     *               注：将16进制的字符串转化为二进制的byte[]进行AES解密，获得解密后的字符串
     *               </p>
     * @param keyStr	key
     * @param encryptData	需要解密的字符串
     * @return String 返回类型
     */
    public static String aesDecrypt(String keyStr, String encryptData) throws Exception{
        byte[] decrypt = null;
        Cipher cipher = Cipher.getInstance(AESTYPE);
        cipher.init(Cipher.DECRYPT_MODE, getKey(keyStr));
        decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptData));
        return new String(decrypt).trim();
    }

    /**
     * @Title: getKey
     * @Description: 自动生成AES128位密钥：AES 加密密钥长度可以是 128、192、256
     *               <p>
     *               生成的密钥为byte[]数组，转化为16进制的字符串进行使用
     *               </p>
     * @return String 返回类型
     */
    public static SecretKey getKey(final String key) throws NoSuchAlgorithmException{
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
        secureRandom.setSeed(key.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance(AESTYPE);
        kg.init(KEY_LENGTH,secureRandom);
        SecretKey sk = kg.generateKey();
        return sk;
    }

    /**
     * @Title: parseByte2HexStr
     * @Description: 将二进制转换成16进制String
     * @param buf
     * @return String 返回类型
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @Title: parseHexStr2Byte
     * @Description: 将16进制转换为二进制字符串
     * @param hexStr
     * @return byte[] 返回类型
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        String secretkey = "1050629";
        String secretSign = AesUtil.aesEncrypt(secretkey,"E99A18C428CB38D5F260853678922E03");
        System.out.println(secretSign);
        System.out.println(aesDecrypt(secretkey, secretSign));
    }
}

