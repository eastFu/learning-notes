package pers.east.learning.java8.optional;

/**
 * @author East.F
 * @ClassName: Insurance
 * @Description: TODO
 * @date 2019/7/20 19:31
 */
public class Insurance {

    private int id;
    private String name;

    public Insurance(){}

    public Insurance(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
