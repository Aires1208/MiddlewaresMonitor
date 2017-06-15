package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/14/16.
 */
public class ConsumerTopicDetailSummary {
    private ConsumerTopicDetailVo topicSummary;

    public ConsumerTopicDetailSummary(ConsumerTopicDetailVo topicSummary) {
        this.topicSummary = topicSummary;
    }

    public ConsumerTopicDetailVo getTopicSummary() {
        return topicSummary;
    }

    public void setTopicSummary(ConsumerTopicDetailVo topicSummary) {
        this.topicSummary = topicSummary;
    }
}
