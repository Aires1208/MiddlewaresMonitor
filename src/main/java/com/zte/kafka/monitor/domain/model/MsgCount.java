package com.zte.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class MsgCount {
    private List<String> times;
    private List<Integer> producer;
    private List<Integer> consumer;

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<Integer> getProducer() {
        return producer;
    }

    public void setProducer(List<Integer> producer) {
        this.producer = producer;
    }

    public List<Integer> getConsumer() {
        return consumer;
    }

    public void setConsumer(List<Integer> consumer) {
        this.consumer = consumer;
    }

    @Override
    public String toString() {
        return "MsgCount{" +
                "times=" + times +
                ", producer=" + producer +
                ", consumer=" + consumer +
                '}';
    }
}
