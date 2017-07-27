package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${aires} on 12/15/16.
 */
public class PairKeyValue {
    private String name;
    private Object value;

    public PairKeyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PairKeyValue{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
