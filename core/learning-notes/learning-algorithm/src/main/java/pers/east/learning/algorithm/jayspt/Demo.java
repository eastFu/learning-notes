package pers.east.learning.algorithm.jayspt;

import org.jasypt.util.text.BasicTextEncryptor;

public class Demo {

    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //设置加密密钥
        textEncryptor.setPassword("MySalt");
        //加密信息
        String encryptedText = textEncryptor.encrypt("This is a secret message.");
        System.out.println("encryptedText:" + encryptedText);
        //解密
        String decryptedText = textEncryptor.decrypt(encryptedText);
        System.out.println("decryptedText:" + decryptedText);
    }

}
