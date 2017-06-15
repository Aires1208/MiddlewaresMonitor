package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${10183966} on 12/15/16.
 */
public class TopicPartitionInfo {
    private int partition;
    private long latestOffset;
    private int leader;
    private String replicas;
    private String inSyncReplicas;
    private boolean preferredLeader;
    private boolean underReplicated;

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public long getLatestOffset() {
        return latestOffset;
    }

    public void setLatestOffset(long latestOffset) {
        this.latestOffset = latestOffset;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public String getReplicas() {
        return replicas;
    }

    public void setReplicas(String replicas) {
        this.replicas = replicas;
    }

    public String getInSyncReplicas() {
        return inSyncReplicas;
    }

    public void setInSyncReplicas(String inSyncReplicas) {
        this.inSyncReplicas = inSyncReplicas;
    }

    public boolean isPreferredLeader() {
        return preferredLeader;
    }

    public void setPreferredLeader(boolean preferredLeader) {
        this.preferredLeader = preferredLeader;
    }

    public boolean isUnderReplicated() {
        return underReplicated;
    }

    public void setUnderReplicated(boolean underReplicated) {
        this.underReplicated = underReplicated;
    }

    @Override
    public String toString() {
        return "TopicPartitionInfo{" +
                "partition=" + partition +
                ", latestOffset=" + latestOffset +
                ", leader=" + leader +
                ", replicas='" + replicas + '\'' +
                ", inSyncReplicas='" + inSyncReplicas + '\'' +
                ", preferredLeader=" + preferredLeader +
                ", underReplicated=" + underReplicated +
                '}';
    }
}
