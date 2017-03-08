package com.zte.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;
import com.zte.kafka.monitor.domain.model.KafkaMonitorInfo;
import com.zte.kafka.monitor.domain.model.Result;

/**
 * Created by ${10183966} on 12/6/16.
 */

public interface KafkaMonitorServer {
    Result putKafkaMonitorInfo(KafkaMonitorInfo kafkaMonitorInfo);

    Result getKafkaMonitors();

    JSONObject getDetailsMonitorNode(String zk, String name);

}
