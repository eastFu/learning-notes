package pers.east.learning.algorithm.stack;

/**
 * 栈的应用： 颠倒字符串
 * @author eastFu
 */
public class Reverse {

    public static String doTransfer(String str){
        StringBuffer result = new StringBuffer();
        MyStack ms = new MyStack(str.length());

        char[] cs = str.toCharArray();
        for (char c: cs){
            ms.push(c);
        }
        while (!ms.isEmpty()){
            char c = (char) ms.pop();
            result .append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(doTransfer("dongcitaci"));
    }
}
