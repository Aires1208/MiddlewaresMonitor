package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class Cluster {
    private String name;
    private String host;
    private boolean status;

    public Cluster(String name, String host) {
        this.name = name;
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", status=" + status +
                '}';
    }
}
