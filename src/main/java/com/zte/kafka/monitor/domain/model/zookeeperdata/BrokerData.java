package com.zte.kafka.monitor.domain.model.zookeeperdata;

import java.util.List;

/**
 * Created by ${10183966} on 12/14/16.
 */
public class BrokerData {
    private long jmxPort;
    private String timestamp;
    private String host;
    private int version;
    private long port;
    private List<String> endpoints;

    public long getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(long jmxPort) {
        this.jmxPort = jmxPort;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public String toString() {
        return "BrokerData{" +
                "jmxPort=" + jmxPort +
                ", timestamp='" + timestamp + '\'' +
                ", host='" + host + '\'' +
                ", version=" + version +
                ", port=" + port +
                ", endpoints=" + endpoints +
                '}';
    }

}

