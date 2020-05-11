package pers.east.learning.datastructure.stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 栈的应用：计算后缀表达式
 * @author Administrator
 */
public class Calculate {

    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    private static int calculation(String expStr) throws ScriptException {
        MyStack myStack = new MyStack(20);
        char[] cs = expStr.toCharArray();
        for (char c :cs){
            if (c=='+'||c=='-'||c=='*'||c=='/'){
                int a =  myStack.pop();
                int b =  myStack.pop();
                System.out.print(""+ b +c +a);
                int result = (int) jse.eval(""+ b +c +a);
                System.out.println(" = "+result);
                myStack.push(result);
            } else {
                myStack.push(c-'0');
            }
        }
        return myStack.pop();
    }
    public static void main(String[] args) throws ScriptException {
        String str = PostInfix.doTransfer("(3+2)/5-((7+8)*4-5)");
        System.out.println(calculation(str));
    }
}
