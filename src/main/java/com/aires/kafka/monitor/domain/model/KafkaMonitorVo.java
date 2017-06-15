package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class KafkaMonitorVo {
    private Overview overview;
    private Brokers brokers;

    private List<Topic> topics;
    private List<Consumer> consumers;

    public Overview getOverview() {
        return overview;
    }

    public void setOverview(Overview overview) {
        this.overview = overview;
    }

    public Brokers getBrokers() {
        return brokers;
    }

    public void setBrokers(Brokers brokers) {
        this.brokers = brokers;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<Consumer> consumers) {
        this.consumers = consumers;
    }

    @Override
    public String toString() {
        return "KafkaMonitorVo{" +
                "overview=" + overview +
                ", brokers=" + brokers +
                ", topics=" + topics +
                ", consumers=" + consumers +
                '}';
    }
}
