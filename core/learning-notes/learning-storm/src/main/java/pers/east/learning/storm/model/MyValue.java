package pers.east.learning.storm.model;

import java.io.Serializable;

public class MyValue implements Serializable,Cloneable {

    private String step1;

    private Integer step2;

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public int getStep2() {
        return step2;
    }

    public void setStep2(int step2) {
        this.step2 = step2;
    }
}
