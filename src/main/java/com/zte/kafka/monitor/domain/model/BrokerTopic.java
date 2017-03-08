package com.zte.kafka.monitor.domain.model;

/**
 * Created by 10183966 on 12/14/16.
 */
public class BrokerTopic {
    private String topic;
    private int replication;
    private int totalPartitions;
    private int partitionsOnBroker;
    private String partitions;
    private boolean skewed;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getReplication() {
        return replication;
    }

    public void setReplication(int replication) {
        this.replication = replication;
    }

    public int getTotalPartitions() {
        return totalPartitions;
    }

    public void setTotalPartitions(int totalPartitions) {
        this.totalPartitions = totalPartitions;
    }

    public int getPartitionsOnBroker() {
        return partitionsOnBroker;
    }

    public void setPartitionsOnBroker(int partitionsOnBroker) {
        this.partitionsOnBroker = partitionsOnBroker;
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
        return "BrokerTopic{" +
                "topic='" + topic + '\'' +
                ", replication=" + replication +
                ", totalPartitions=" + totalPartitions +
                ", partitionsOnBroker=" + partitionsOnBroker +
                ", partitions=" + partitions +
                ", skewed=" + skewed +
                '}';
    }
}
