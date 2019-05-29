package pers.east.learning.spi;

import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String[] args) {

        /**
         *  src/META-INF/services/pers.east.learning.spi.People文件代码：
         *
         *  pers.east.learning.spi.Chinese
         *  pers.east.learning.spi.English
         *
         *  ava的spi运行流程是运用java.util.ServiceLoader这个类的load方法去在src/META-INF/services/寻找对应的全路径接口名称的文件，
         *  然后在文件中找到对应的实现方法并注入实现，然后你可以运用了
         */


        ServiceLoader<People> serviceLoader = ServiceLoader.load(People.class);
        for (People people : serviceLoader){
            people.say();
        }

    }
}
