package pers.east.learning.algorithm.stack;

/**
 * 用栈来实现： 把中缀表达式转换成为后缀表达式
 *
 * @author eastFu
 */
public class PostInfix {
    /**
     * 把中缀表达式转化为后缀表达式
     * <p>
     * 1：从左到右顺序读取表达式中的字符
     * 2：是操作数，复制到后缀表达式字符串
     * 3：是左括号，把字符压入栈中
     * 4：是右括号，从栈中弹出符号到后缀表达式，直到遇到左括号，然后把左括号弹出。
     * 5：是操作符，如果此时栈顶操作符优先级大于或等于此操作符，弹出栈顶操作符到后缀表达式，直到发现优先级更低的元素位置，把操作符压入。
     * 6：读到输入的末尾，将栈元素弹出直到该栈变成空栈，将符号写到后缀表达式中
     * </p>
     * @param str 要转换的中缀表达式
     * @return 转换成的后缀表达式
     */
    public static String doTransfer(String str) {
        StringBuffer buffer = new StringBuffer();
        MyStack myStack = new MyStack(20);
        char[] cs = str.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == '+' || c == '-') {
                doOperation(c, buffer, 1, myStack);
            } else if (c == '*' || c == '/') {
                doOperation(c, buffer, 2, myStack);
            } else if (c == '(') {
                myStack.push(c);
            } else if (c == ')') {
                doRightBracket(myStack, buffer);
            } else {
                buffer.append(c);
            }
        }

        while (!myStack.isEmpty()) {
            buffer.append((char) myStack.pop());
        }
        return buffer.toString();
    }

    private static void doOperation(char c, StringBuffer buf, int level, MyStack myStack) {
        while (!myStack.isEmpty()) {
            char top = (char) myStack.pop();
            if (top == '(') {
                myStack.push(top);
                break;
            } else {
                int topLevel = 0;
                if (top == '+' || top == '-') {
                    topLevel = 1;
                } else {
                    topLevel = 2;
                }

                if (topLevel >= level) {
                    buf.append(top);
                } else {
                    myStack.push(top);
                    break;
                }
            }
        }
        myStack.push(c);
    }

    private static void doRightBracket(MyStack myStack, StringBuffer buffer) {
        while (!myStack.isEmpty()) {
            char top = (char) myStack.pop();
            if (top == '(') {
                break;
            } else {
                buffer.append(top);
            }
        }
    }

    public static void main(String[] args) {
        String str = "(3+2)/5-((7+8)*4-5)";
        System.out.println("before:" + str);
        System.out.println("after:" + doTransfer(str));
    }
}
