package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${aires} on 12/13/16.
 */
public class ConsumedTopicInfoVo {
    List<ConsumedTopicInfo> consumedTopicInfo;

    public ConsumedTopicInfoVo(List<ConsumedTopicInfo> consumedTopicInfo) {
        this.consumedTopicInfo = consumedTopicInfo;
    }

    public List<ConsumedTopicInfo> getConsumedTopicInfo() {
        return consumedTopicInfo;
    }

    public void setConsumedTopicInfo(List<ConsumedTopicInfo> consumedTopicInfo) {
        this.consumedTopicInfo = consumedTopicInfo;
    }
}
