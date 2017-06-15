package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/8/16.
 */
public class Broker {
    private String id;
    private String host;
    private String port;
    private String jmxport;
    private double bytesIn;
    private double bytesOut;

    public Broker(String id, String host, String port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getJmxport() {
        return jmxport;
    }

    public void setJmxport(String jmxport) {
        this.jmxport = jmxport;
    }

    public double getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(double bytesIn) {
        this.bytesIn = bytesIn;
    }

    public double getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(double bytesOut) {
        this.bytesOut = bytesOut;
    }

    @Override
    public String toString() {
        return "Broker{" +
                "id='" + id + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", jmxport='" + jmxport + '\'' +
                ", bytesIn=" + bytesIn +
                ", bytesOut=" + bytesOut +
                '}';
    }
}
