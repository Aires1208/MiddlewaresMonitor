package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${aires} on 12/14/16.
 */
public class ConsumerTopicDetailVo {
    List<ConsumerTopicDetail> detail;
    private String name;
    private int ofPartitions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfPartitions() {
        return ofPartitions;
    }

    public void setOfPartitions(int ofPartitions) {
        this.ofPartitions = ofPartitions;
    }

    public List<ConsumerTopicDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<ConsumerTopicDetail> detail) {
        this.detail = detail;
    }

}
