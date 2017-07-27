package com.aires.kafka.monitor.domain.model;

/**
 * Created by ${aires} on 12/9/16.
 */
public class KafkaMetricsInfo {
    private Long messagesInPerSec;
    private Long bytesInPerSec;
    private Long bytesOutPerSec;
    private Long produceRequestsPerSec;
    private Long consumerRequestsPerSec;
    private Long fetchFollowerRequestsPerSec;
    private Long logFlushRateAndTimeMs;
    private Integer underReplicatedPartitions;
    private Integer activeControllerCount;
    private Long leaderElectionRateAndTimeMs;
    private Long uncleanLeaderElectionsPerSec;
    private Integer partitionCount;
    private Long leaderCount;
    private Long bytesRejectedPerSec;
    private Long failedFetchRequestsPerSec;
    private Long failedProduceRequestsPerSec;
    private Long topicUnderReplicated;

    public Long getMessagesInPerSec() {
        return messagesInPerSec;
    }

    public void setMessagesInPerSec(Long messagesInPerSec) {
        this.messagesInPerSec = messagesInPerSec;
    }

    public Long getBytesInPerSec() {
        return bytesInPerSec;
    }

    public void setBytesInPerSec(Long bytesInPerSec) {
        this.bytesInPerSec = bytesInPerSec;
    }

    public Long getBytesOutPerSec() {
        return bytesOutPerSec;
    }

    public void setBytesOutPerSec(Long bytesOutPerSec) {
        this.bytesOutPerSec = bytesOutPerSec;
    }

    public Long getProduceRequestsPerSec() {
        return produceRequestsPerSec;
    }

    public void setProduceRequestsPerSec(Long produceRequestsPerSec) {
        this.produceRequestsPerSec = produceRequestsPerSec;
    }

    public Long getConsumerRequestsPerSec() {
        return consumerRequestsPerSec;
    }

    public void setConsumerRequestsPerSec(Long consumerRequestsPerSec) {
        this.consumerRequestsPerSec = consumerRequestsPerSec;
    }

    public Long getFetchFollowerRequestsPerSec() {
        return fetchFollowerRequestsPerSec;
    }

    public void setFetchFollowerRequestsPerSec(Long fetchFollowerRequestsPerSec) {
        this.fetchFollowerRequestsPerSec = fetchFollowerRequestsPerSec;
    }

    public Long getLogFlushRateAndTimeMs() {
        return logFlushRateAndTimeMs;
    }

    public void setLogFlushRateAndTimeMs(Long logFlushRateAndTimeMs) {
        this.logFlushRateAndTimeMs = logFlushRateAndTimeMs;
    }

    public Integer getUnderReplicatedPartitions() {
        return underReplicatedPartitions;
    }

    public void setUnderReplicatedPartitions(Integer underReplicatedPartitions) {
        this.underReplicatedPartitions = underReplicatedPartitions;
    }

    public Integer getActiveControllerCount() {
        return activeControllerCount;
    }

    public void setActiveControllerCount(Integer activeControllerCount) {
        this.activeControllerCount = activeControllerCount;
    }

    public Long getLeaderElectionRateAndTimeMs() {
        return leaderElectionRateAndTimeMs;
    }

    public void setLeaderElectionRateAndTimeMs(Long leaderElectionRateAndTimeMs) {
        this.leaderElectionRateAndTimeMs = leaderElectionRateAndTimeMs;
    }

    public Long getUncleanLeaderElectionsPerSec() {
        return uncleanLeaderElectionsPerSec;
    }

    public void setUncleanLeaderElectionsPerSec(Long uncleanLeaderElectionsPerSec) {
        this.uncleanLeaderElectionsPerSec = uncleanLeaderElectionsPerSec;
    }

    public Integer getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(Integer partitionCount) {
        this.partitionCount = partitionCount;
    }

    public Long getLeaderCount() {
        return leaderCount;
    }

    public void setLeaderCount(Long leaderCount) {
        this.leaderCount = leaderCount;
    }

    public Long getBytesRejectedPerSec() {
        return bytesRejectedPerSec;
    }

    public void setBytesRejectedPerSec(Long bytesRejectedPerSec) {
        this.bytesRejectedPerSec = bytesRejectedPerSec;
    }

    public Long getFailedFetchRequestsPerSec() {
        return failedFetchRequestsPerSec;
    }

    public void setFailedFetchRequestsPerSec(Long failedFetchRequestsPerSec) {
        this.failedFetchRequestsPerSec = failedFetchRequestsPerSec;
    }

    public Long getFailedProduceRequestsPerSec() {
        return failedProduceRequestsPerSec;
    }

    public void setFailedProduceRequestsPerSec(Long failedProduceRequestsPerSec) {
        this.failedProduceRequestsPerSec = failedProduceRequestsPerSec;
    }

    public Long getTopicUnderReplicated() {
        return topicUnderReplicated;
    }

    public void setTopicUnderReplicated(Long topicUnderReplicated) {
        this.topicUnderReplicated = topicUnderReplicated;
    }

    @Override
    public String toString() {
        return "KafkaMetricsInfo{" +
                "messagesInPerSec=" + messagesInPerSec +
                ", bytesInPerSec=" + bytesInPerSec +
                ", bytesOutPerSec=" + bytesOutPerSec +
                ", produceRequestsPerSec=" + produceRequestsPerSec +
                ", consumerRequestsPerSec=" + consumerRequestsPerSec +
                ", fetchFollowerRequestsPerSec=" + fetchFollowerRequestsPerSec +
                ", logFlushRateAndTimeMs=" + logFlushRateAndTimeMs +
                ", underReplicatedPartitions=" + underReplicatedPartitions +
                ", activeControllerCount=" + activeControllerCount +
                ", leaderElectionRateAndTimeMs=" + leaderElectionRateAndTimeMs +
                ", uncleanLeaderElectionsPerSec=" + uncleanLeaderElectionsPerSec +
                ", partitionCount=" + partitionCount +
                ", leaderCount=" + leaderCount +
                ", bytesRejectedPerSec=" + bytesRejectedPerSec +
                ", failedFetchRequestsPerSec=" + failedFetchRequestsPerSec +
                ", failedProduceRequestsPerSec=" + failedProduceRequestsPerSec +
                ", topicUnderReplicated=" + topicUnderReplicated +
                '}';
    }
}
