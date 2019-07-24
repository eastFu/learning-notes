package pers.east.learning.java8.optional;

import java.util.Optional;

/**
 * @author East.F
 * @ClassName: OptionalUsage
 * @Description: TODO
 * @date 2019/7/20 19:28
 */
public class OptionalUsage {
    public static void main(String[] args) {
        Optional<Insurance> optional = Optional.<Insurance>empty();
        //NoSuchElementException: No value present
//        pers.east.learning.java8.optional.get();

        System.out.println(Optional.of(new Insurance()).get());

        System.out.println(Optional.ofNullable(new Insurance()).get());

        Optional.ofNullable(new Insurance()).orElseGet(Insurance::new);

        Optional.ofNullable(new Insurance()).orElse(new Insurance());

        Optional.ofNullable(new Insurance()).orElseThrow(RuntimeException::new);

        Optional.ofNullable(new Insurance()).orElseThrow(()->new RuntimeException("Not find Insurance"));

        // 报 空指针
//        Optional.ofNullable(new Insurance()).filter(i->i.getName()!=null).get();

        Optional<String> optional1 = Optional.ofNullable(new Insurance()).map(i->i.getName());
        System.out.println(optional1.orElse("empty"));

        System.out.println(optional1.isPresent());

        System.out.println(getInsuranceName(null));

        System.out.println("=================");
        System.out.println(getInsuranceNameByOptional(null));
    }


    // 现在做法
    private static String getInsuranceName (Insurance insurance){
        if (insurance==null){
            return "unknown";
        }
        return insurance.getName();
    }

    // pers.east.learning.java8.optional 做法
    private static String getInsuranceNameByOptional (Insurance insurance){
        Optional<Insurance> oo = Optional.ofNullable(insurance);
        return oo.map(Insurance::getName).orElse("unknown");
    }

}
