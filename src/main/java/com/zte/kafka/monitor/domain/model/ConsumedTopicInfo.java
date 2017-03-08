package com.zte.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/13/16.
 */
public class ConsumedTopicInfo {
    private String topic;
    private double partitionsCovered;
    private String totalLag;

    public ConsumedTopicInfo(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getPartitionsCovered() {
        return partitionsCovered;
    }

    public void setPartitionsCovered(double partitionsCovered) {
        this.partitionsCovered = partitionsCovered;
    }

    public String getTotalLag() {
        return totalLag;
    }

    public void setTotalLag(String totalLag) {
        this.totalLag = totalLag;
    }

    @Override
    public String toString() {
        return "ConsumedTopicInfo{" +
                "topic='" + topic + '\'' +
                ", partitionsCovered=" + partitionsCovered +
                ", totalLag='" + totalLag + '\'' +
                '}';
    }
}
