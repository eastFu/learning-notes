package pers.east.learning.java8.methodreference;

/**
 * @author East.F
 * @ClassName: Apple
 * @Description: TODO
 * @date 2019/7/20 11:01
 */
public class Apple {
    private long weight;
    private String color;
    private String from;

    public Apple(long weight,String color,String from){
        this.setWeight(weight);
        this.setColor(color);
        this.setFrom(from);
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
