package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${aires} on 12/13/16.
 */
public class BrokerVo {
    private BrokerSummary summary;
    private List<CombinedMetric> metrics;
    private List<BrokerTopic> topics;

    public BrokerSummary getSummary() {
        return summary;
    }

    public void setSummary(BrokerSummary summary) {
        this.summary = summary;
    }

    public List<CombinedMetric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<CombinedMetric> metrics) {
        this.metrics = metrics;
    }

    public List<BrokerTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<BrokerTopic> topics) {
        this.topics = topics;
    }
}
