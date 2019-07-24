package pers.east.learning.java8.optional;

import java.util.Optional;

/**
 * @author East.F
 * @ClassName: OptionalInAction
 * @Description: TODO
 * @date 2019/7/20 21:08
 */
public class OptionalInAction {
    public static void main(String[] args) {

        Optional.ofNullable(getInsuranceNameFromPersonByOptional(new Person())).ifPresent(System.out::println);

        Optional.ofNullable(getInsuranceNameFromPersonByOptional(null)).ifPresent(System.out::println);
    }

    // pers.east.learning.java8.optional 做法, 使用 flatMap 来合并
    private static String getInsuranceNameFromPersonByOptional (Person person){
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }
}
