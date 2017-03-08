package com.zte.kafka.monitor.domain.server;

import com.zte.kafka.monitor.domain.model.KafkaMonitorInfo;

import java.util.List;

/**
 * Created by ${10183966} on 12/6/16.
 */
public interface KafkaMonitorRepo {
    void insertKafkaMonitorInfo(KafkaMonitorInfo monitorInfo);


    List<KafkaMonitorInfo> getKafkaMonitorInfos();

    KafkaMonitorInfo getKafkaMonitorByZK(String zk);

    KafkaMonitorInfo getKafkaMonitorByMonitorName(String kafkaMonitorName);

    List<KafkaMonitorInfo> getKafkaMonitorByKafkaMonitorName(String zookeeper, String kafkaMonitorName);

    boolean isKafkaMonitorExist(List<KafkaMonitorInfo> kafkaMonitorInfos, String kafkaMonitorName);


}
