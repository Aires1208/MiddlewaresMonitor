package com.aires.kafka.monitor.domain.model;

import java.util.List;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class Overview {

    private String name;
    private boolean status;
    private String zookeeper;
    private String version;
    private int topics;
    private int brokers;
    private int consumers;
    private List<Cluster> clusters;
    private MsgCount msgCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public int getBrokers() {
        return brokers;
    }

    public void setBrokers(int brokers) {
        this.brokers = brokers;
    }

    public int getConsumers() {
        return consumers;
    }

    public void setConsumers(int consumers) {
        this.consumers = consumers;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public MsgCount getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(MsgCount msgCount) {
        this.msgCount = msgCount;
    }

    @Override
    public String toString() {
        return "Overview{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", zookeeper='" + zookeeper + '\'' +
                ", version='" + version + '\'' +
                ", topics=" + topics +
                ", brokers=" + brokers +
                ", consumers=" + consumers +
                ", clusters=" + clusters +
                ", msgCount=" + msgCount +
                '}';
    }
}
