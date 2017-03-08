package com.zte.kafka.monitor.domain.model;

/**
 * Created by 10183966 on 12/6/16.
 */
public class KafkaCluster {
    private String name;
    private String zookeeper;
    private String version;
    private boolean isJMXOn;
    private long jmxPort;
    private int topics;
    private int brokers;
    private String description;

    public KafkaCluster(String name, String zookeeper, String version, boolean isJMXOn, long jmxPort, int topics, int brokers, String description) {
        this.name = name;
        this.zookeeper = zookeeper;
        this.version = version;
        this.isJMXOn = isJMXOn;
        this.jmxPort = jmxPort;
        this.topics = topics;
        this.brokers = brokers;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isJMXOn() {
        return isJMXOn;
    }

    public void setJMXOn(boolean JMXOn) {
        isJMXOn = JMXOn;
    }

    public long getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(long jmxPort) {
        this.jmxPort = jmxPort;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "KafkaCluster{" +
                "name='" + name + '\'' +
                ", zookeeper='" + zookeeper + '\'' +
                ", version='" + version + '\'' +
                ", isJMXOn=" + isJMXOn +
                ", jmxPort=" + jmxPort +
                ", topics=" + topics +
                ", brokers=" + brokers +
                ", description='" + description + '\'' +
                '}';
    }
}
