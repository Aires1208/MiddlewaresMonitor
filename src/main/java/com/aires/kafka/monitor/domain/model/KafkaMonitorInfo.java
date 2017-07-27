package com.aires.kafka.monitor.domain.model;


/**
 * Created by aires on 12/6/16.
 */
public class KafkaMonitorInfo {
    private String name;
    private String zookeeper;
    private String version;
    private String description;
    private boolean isJMXOn;
    private long jmxPort;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "KafkaMonitorInfo{" +
                "name='" + name + '\'' +
                ", zookeeper='" + zookeeper + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", isJMXOn=" + isJMXOn +
                ", jmxPort=" + jmxPort +
                '}';
    }
}
