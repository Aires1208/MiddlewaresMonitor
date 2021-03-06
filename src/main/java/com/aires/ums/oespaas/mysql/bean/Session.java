package com.aires.ums.oespaas.mysql.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by aires on 8/18/16.
 */
@JsonSerialize(using = SessionSerializer.class)
public class Session {
    private String sessionId;
    private String timeSpent;
    private String weight;

    public Session(String sessionId, String timeSpent, String weight) {
        this.sessionId = sessionId;
        this.timeSpent = timeSpent;
        this.weight = weight;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public String getWeight() {
        return weight;
    }
}
