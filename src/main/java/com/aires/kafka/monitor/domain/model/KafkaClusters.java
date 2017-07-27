package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${aires} on 12/6/16.
 */
public class KafkaClusters {
    private List<KafkaCluster> kafkaClusters;

    public List<KafkaCluster> getKafkaClusters() {
        return kafkaClusters;
    }

    public void setKafkaClusters(List<KafkaCluster> kafkaClusters) {
        this.kafkaClusters = kafkaClusters;
    }
}
