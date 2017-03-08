package com.zte.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/14/16.
 */
public class BrokerSummary {
    private int topics;
    private int partitions;
    private double messages;
    private double incoming;
    private double outgoing;
    private MsgCount msgCount;

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public double getMessages() {
        return messages;
    }

    public void setMessages(double messages) {
        this.messages = messages;
    }

    public double getIncoming() {
        return incoming;
    }

    public void setIncoming(double incoming) {
        this.incoming = incoming;
    }

    public double getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(double outgoing) {
        this.outgoing = outgoing;
    }

    public MsgCount getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(MsgCount msgCount) {
        this.msgCount = msgCount;
    }
}
