package pers.east.learning.algorithm.leetcode;

import java.io.UnsupportedEncodingException;

public class Latin1ToUtf8 {

    public static String convertCharset(String s) {
        if (s != null) {
            try {
                int length = s.length();
                byte[] buffer = new byte[length];
                // 0x81 to Unicode 0x0081, 0x8d to 0x008d, 0x8f to 0x008f, 0x90
                // to 0x0090, and 0x9d to 0x009d.
                for (int i = 0; i < length; ++i) {
                    char c = s.charAt(i);
                    if (c == 0x0081) {
                        buffer[i] = (byte) 0x81;
                    } else if (c == 0x008d) {
                        buffer[i] = (byte) 0x8d;
                    } else if (c == 0x008f) {
                        buffer[i] = (byte) 0x8f;
                    } else if (c == 0x0090) {
                        buffer[i] = (byte) 0x90;
                    } else if (c == 0x009d) {
                        buffer[i] = (byte) 0x9d;
                    } else {
                        buffer[i] = Character.toString(c).getBytes("CP1252")[0];
                    }
                }
                String result = new String(buffer, "UTF-8");
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String aaa=new String("测试".getBytes(),"ISO8859-1");
        String bbb=new String(aaa.getBytes("utf8"));

        System.out.println(bbb);

        System.out.println(new String(bbb.getBytes("ISO8859-1"),"utf-8"));
        System.out.println(convertCharset(bbb));


    }
}
