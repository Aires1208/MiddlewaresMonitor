package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${10183966} on 12/15/16.
 */
public class TopicVo {
    private List<PairKeyValue> summary;
    private List<CombinedMetric> metrics;
    private List<TopicPartitionInfo> partitionInfo;
    private List<TopicPartitionBroker> partitionBroker;

    public List<PairKeyValue> getSummary() {
        return summary;
    }

    public void setSummary(List<PairKeyValue> summary) {
        this.summary = summary;
    }

    public List<CombinedMetric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<CombinedMetric> metrics) {
        this.metrics = metrics;
    }

    public List<TopicPartitionInfo> getPartitionInfo() {
        return partitionInfo;
    }

    public void setPartitionInfo(List<TopicPartitionInfo> partitionInfo) {
        this.partitionInfo = partitionInfo;
    }

    public List<TopicPartitionBroker> getPartitionBroker() {
        return partitionBroker;
    }

    public void setPartitionBroker(List<TopicPartitionBroker> partitionBroker) {
        this.partitionBroker = partitionBroker;
    }
}
