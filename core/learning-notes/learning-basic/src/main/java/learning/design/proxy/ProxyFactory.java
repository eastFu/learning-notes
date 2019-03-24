package pers.east.learning.design.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    private final Class<T> mapperInterface;

    public ProxyFactory(Class<T> mapperInterface){
        this.mapperInterface=mapperInterface;
    }

    protected T newInstance(IProxyHandler<T> mapperProxy) {
        return  (T)Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, mapperProxy);
    }

    public T newInstance() {
    	IProxyHandler<T> mapperProxy = new IProxyHandler(this.mapperInterface);
        return this.newInstance(mapperProxy);
    }
}
