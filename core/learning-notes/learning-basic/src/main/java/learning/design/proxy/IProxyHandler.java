package pers.east.learning.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IProxyHandler<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    public IProxyHandler(Class<T> mapperInterface){
        this.mapperInterface=mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("接口方法调用开始");
        System.out.println("method toGenericString:"+method.toGenericString());
        System.out.println("method name:"+method.getName());
        System.out.println("method args:"+args);
        System.out.println("接口方法调用结束");
        method.invoke(this, args);
        return "执行完毕";
    }
}
