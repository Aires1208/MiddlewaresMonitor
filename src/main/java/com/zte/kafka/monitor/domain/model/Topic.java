package com.zte.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class Topic {
    private String topic;
    private int partition;
    private int brokers;
    private int brokersSpread;
    private int brokersSkew;
    private int replicas;
    private int underReplicated;
    private double producerMessage;

    public Topic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public int getBrokers() {
        return brokers;
    }

    public void setBrokers(int brokers) {
        this.brokers = brokers;
    }

    public int getBrokersSpread() {
        return brokersSpread;
    }

    public void setBrokersSpread(int brokersSpread) {
        this.brokersSpread = brokersSpread;
    }

    public int getBrokersSkew() {
        return brokersSkew;
    }

    public void setBrokersSkew(int brokersSkew) {
        this.brokersSkew = brokersSkew;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public int getUnderReplicated() {
        return underReplicated;
    }

    public void setUnderReplicated(int underReplicated) {
        this.underReplicated = underReplicated;
    }

    public double getProducerMessage() {
        return producerMessage;
    }

    public void setProducerMessage(double producerMessage) {
        this.producerMessage = producerMessage;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic='" + topic + '\'' +
                ", partition=" + partition +
                ", brokers=" + brokers +
                ", brokersSpread=" + brokersSpread +
                ", brokersSkew=" + brokersSkew +
                ", replicas=" + replicas +
                ", underReplicated=" + underReplicated +
                ", producerMessage=" + producerMessage +
                '}';
    }
}
