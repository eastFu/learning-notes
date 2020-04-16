# Mybatis的源码分析


	大家都知道，Mybatis 只有接口，没有接口实现类，主要通过操作执行定义好的sql文件，将操作结果转化后返回的，但是没有接口实现类，是怎么搭配spring来实现的？这里要熟悉两点： spring容器的注册原理和动态代理。

MapperFactoryBean MapperProxyFactory  MapperProxy





