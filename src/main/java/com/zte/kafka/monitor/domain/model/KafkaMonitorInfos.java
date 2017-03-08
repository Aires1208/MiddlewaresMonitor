package com.zte.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${10183966} on 12/6/16.
 */
public class KafkaMonitorInfos {
    private List<KafkaMonitorInfo> kafkaMonitorInfoList;

    public List<KafkaMonitorInfo> getKafkaMonitorInfoList() {
        return kafkaMonitorInfoList;
    }

    public void setKafkaMonitorInfoList(List<KafkaMonitorInfo> kafkaMonitorInfoList) {
        this.kafkaMonitorInfoList = kafkaMonitorInfoList;
    }

    @Override
    public String toString() {
        return "KafkaMonitorInfos{" +
                "kafkaMonitorInfoList=" + kafkaMonitorInfoList +
                '}';
    }
}
