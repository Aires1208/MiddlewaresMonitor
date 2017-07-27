package com.aires.kafka.monitor.domain.model;

/**
 * Created by aires on 12/15/16.
 */
public class TopicPartitionBroker {
    private int broker;
    private int ofPartitions;
    private String partitions;
    private boolean skewed;

    public int getBroker() {
        return broker;
    }

    public void setBroker(int broker) {
        this.broker = broker;
    }

    public int getOfPartitions() {
        return ofPartitions;
    }

    public void setOfPartitions(int ofPartitions) {
        this.ofPartitions = ofPartitions;
    }

    public String getPartitions() {
        return partitions;
    }

    public void setPartitions(String partitions) {
        this.partitions = partitions;
    }

    public boolean isSkewed() {
        return skewed;
    }

    public void setSkewed(boolean skewed) {
        this.skewed = skewed;
    }

    @Override
    public String toString() {
        return "TopicPartitionBroker{" +
                "broker=" + broker +
                ", ofPartitions=" + ofPartitions +
                ", partitions='" + partitions + '\'' +
                ", skewed=" + skewed +
                '}';
    }
}
