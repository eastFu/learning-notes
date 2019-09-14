package pers.east.learning.algorithm.stack;

/**
 * 栈的应用： 检查表达式中括号（小、中、大）是否成对出现
 *
 * @author Administrator
 */
public class CheckBrackets {

    public static void check(String str) {
        MyStack myStack = new MyStack(20);
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (c == '{' || c == '[' || c == '(') {
                myStack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                char s = (char) myStack.pop();
                if ((s == '{' && c == '}') || (s == '[' && c == ']') || (s == '(' && c == ')')) {
                    System.out.println("匹配成功"+c);
                }else{
                    System.out.println("匹配不成功"+c);
                }
            }
        }
    }

    public static void main(String[] args) {
        check("(3+2)/6+[(6*3/6+8]");
    }
}
