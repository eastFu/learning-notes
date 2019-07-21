package pers.east.learning.optional;

import java.util.Optional;

/**
 * @author East.F
 * @ClassName: Car
 * @Description: TODO
 * @date 2019/7/21 9:04
 */
public class Car {
    private Insurance insurance;

    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance);
    }
}
