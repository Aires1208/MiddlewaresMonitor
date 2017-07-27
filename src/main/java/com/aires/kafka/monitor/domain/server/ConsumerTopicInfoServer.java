package com.aires.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ${aires} on 12/13/16.
 */
public interface ConsumerTopicInfoServer {
    JSONObject getConsumerTopicDetail(String zk, String name);

    JSONObject getTopicDetail(String zk, String consumerNme, String topicName);

}
