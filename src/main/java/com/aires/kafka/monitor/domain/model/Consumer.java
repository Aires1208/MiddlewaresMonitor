package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class Consumer {
    private String name;
    private String type;

    public Consumer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
