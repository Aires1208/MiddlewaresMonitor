package com.zte.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/14/16.
 */
public class ConsumerTopicDetail {

    private String partition;
    private long logSize;
    private long consumerOffset;
    private String lag;
    private String instanceOwner;

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public long getLogSize() {
        return logSize;
    }

    public void setLogSize(long logSize) {
        this.logSize = logSize;
    }

    public long getConsumerOffset() {
        return consumerOffset;
    }

    public void setConsumerOffset(long consumerOffset) {
        this.consumerOffset = consumerOffset;
    }

    public String getLag() {
        return lag;
    }

    public void setLag(String lag) {
        this.lag = lag;
    }

    public String getInstanceOwner() {
        return instanceOwner;
    }

    public void setInstanceOwner(String instanceOwner) {
        this.instanceOwner = instanceOwner;
    }

    @Override
    public String toString() {
        return "ConsumerTopicDetail{" +
                "partition='" + partition + '\'' +
                ", logSize=" + logSize +
                ", consumerOffset=" + consumerOffset +
                ", lag='" + lag + '\'' +
                ", instanceOwner='" + instanceOwner + '\'' +
                '}';
    }
}
