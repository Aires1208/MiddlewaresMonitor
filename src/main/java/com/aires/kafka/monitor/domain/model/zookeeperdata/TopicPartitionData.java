package com.aires.kafka.monitor.domain.model.zookeeperdata;

import java.util.List;

/**
 * Created by ${aires} on 12/15/16.
 */
public class TopicPartitionData {
    private int controllerEpoch;
    private int leader;
    private int version;
    private int leaderEpoch;
    private List<Integer> isr;

    public int getControllerEpoch() {
        return controllerEpoch;
    }

    public void setControllerEpoch(int controllerEpoch) {
        this.controllerEpoch = controllerEpoch;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getLeaderEpoch() {
        return leaderEpoch;
    }

    public void setLeaderEpoch(int leaderEpoch) {
        this.leaderEpoch = leaderEpoch;
    }

    public List<Integer> getIsr() {
        return isr;
    }

    public void setIsr(List<Integer> isr) {
        this.isr = isr;
    }

    @Override
    public String toString() {
        return "TopicPartitionData{" +
                "controllerEpoch=" + controllerEpoch +
                ", leader=" + leader +
                ", version=" + version +
                ", leaderEpoch=" + leaderEpoch +
                ", isr=" + isr +
                '}';
    }

}
