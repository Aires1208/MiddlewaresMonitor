package com.zte.kafka.monitor.domain.model.zookeeperdata;

import java.util.Map;

/**
 * Created by ${10183966} on 12/13/16.
 */
public class ConsumerDetail {
    private int version;
    private String pattern;
    private String timestamp;
    private Map<String, Integer> subscription;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Integer> getSubscription() {
        return subscription;
    }

    public void setSubscription(Map<String, Integer> subscription) {
        this.subscription = subscription;
    }


    @Override
    public String toString() {
        return "ConsumerDetail{" +
                "version=" + version +
                ", pattern='" + pattern + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", subscription=" + subscription +
                '}';
    }
}
