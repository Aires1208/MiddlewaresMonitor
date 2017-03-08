package com.zte.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ${10183966} on 12/6/16.
 */
@FunctionalInterface
public interface KafkaClustersServer {
    JSONObject getKafkaClusters();
}
