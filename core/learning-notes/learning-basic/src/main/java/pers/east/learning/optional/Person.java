package pers.east.learning.optional;

import java.util.Optional;

/**
 * @author East.F
 * @ClassName: Person
 * @Description: TODO
 * @date 2019/7/21 9:04
 */
public class Person {
    private Car car;

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }
}
