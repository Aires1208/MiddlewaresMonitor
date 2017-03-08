package com.zte.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class Brokers {
    private List<CombinedMetric> combinedMetrics;
    private List<Broker> brokerList;

    public List<CombinedMetric> getCombinedMetrics() {
        return combinedMetrics;
    }

    public void setCombinedMetrics(List<CombinedMetric> combinedMetrics) {
        this.combinedMetrics = combinedMetrics;
    }

    public List<Broker> getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }

    @Override
    public String toString() {
        return "Brokers{" +
                "combinedMetrics=" + combinedMetrics +
                ", brokerList=" + brokerList +
                '}';
    }
}
