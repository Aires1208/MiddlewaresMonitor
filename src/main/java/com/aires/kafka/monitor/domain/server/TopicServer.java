package com.aires.kafka.monitor.domain.server;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ${10183966} on 12/15/16.
 */
@FunctionalInterface
public interface TopicServer {
    JSONObject getTopicDetail(String zk, String topicName);

}
