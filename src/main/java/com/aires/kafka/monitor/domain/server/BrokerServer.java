package com.aires.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ${aires} on 12/14/16.
 */
@FunctionalInterface
public interface BrokerServer {
    JSONObject getDetailofBroker(String zk, String brokerId);

}
