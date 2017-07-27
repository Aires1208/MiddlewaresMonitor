package com.aires.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ${aires} on 12/6/16.
 */
@FunctionalInterface
public interface KafkaClustersServer {
    JSONObject getKafkaClusters();
}
